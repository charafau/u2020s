package com.nullpointerbay.u2020s.di

/**
  * Created by rafal on 7/2/16.
  */

import com.nullpointerbay.u2020s.dao.RepoDaoImpl
import com.nullpointerbay.u2020s.main.{MainPresenterImpl, MainView}
import com.softwaremill.macwire._

trait DaoModule {
  lazy val repoDao = wire[RepoDaoImpl]
}

trait MainModule {
  def presenter(mainView: MainView) = wire[MainPresenterImpl]
}