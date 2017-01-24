package com.nullpointerbay.u2020s.dao

import java.util.concurrent.Executors

import android.util.Log
import argonaut.Parse
import com.nullpointerbay.u2020s.model.{ApiRepo, Repo}
import io.taig.communicator.{Request, _}
import okhttp3.OkHttpClient

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.{-\/, \/, \/-}

/**
  * Created by rafal on 7/2/16.
  */
trait RepoDao {
  def loadTrendingRepos(): Request[String]

}

class RepoDaoImpl extends RepoDao {

  val TAG = "RepoDaoImpl"

  override def loadTrendingRepos(): Request[String] = {
    implicit val client = new OkHttpClient()

    val single = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())

    val url = "https://api.github.com/search/repositories?q=created&sort=stars&order=desc"

    Request.prepare(url)
      .addHeader("Accept", "application/vnd.github.v3+json")
      .get()
      .start[String]()
  }
}
