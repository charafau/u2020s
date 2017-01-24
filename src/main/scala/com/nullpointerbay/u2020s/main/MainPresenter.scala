package com.nullpointerbay.u2020s.main

import android.util.Log
import argonaut.Parse
import com.nullpointerbay.u2020s.dao.RepoDao
import com.nullpointerbay.u2020s.di.DaoModule
import com.nullpointerbay.u2020s.model.{ApiRepo, Repo}
import io.taig.communicator.Response
import io.taig.communicator._

import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.{-\/, \/, \/-}


/**
  * Created by rafal on 7/2/16.
  */
class MainPresenterImpl(mainView: MainView)
  extends MainPresenter
    with DaoModule {

  val TAG = "MainPresenterImpl"


  override def loadRepos(): Unit = {

    repoDao.loadTrendingRepos()
      .done {
        case Response(code, body) => Log.d(TAG, s"$code: $body")
          val result: \/[String, ApiRepo] = Parse.decodeEither[ApiRepo](body)
          result match {
            case -\/(msg) => mainView.showError(msg)
            case \/-(repos) => Some(repos)
              repos.repos.foreach(r =>
                mainView.showRepos(r)
              )
              Log.d("blabla", s"${repos.totalCount}")
          }

      }
      .fail {
        case exception => mainView.showError(exception.getMessage)
      }
  }
}

trait MainPresenter {
  def loadRepos(): Unit

}

trait MainView {
  def showRepos(repos: Seq[Repo]): Unit

  def showError(msg: String): Unit

  def printSomething(message: String): Unit

}
