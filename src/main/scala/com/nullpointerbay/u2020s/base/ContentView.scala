package com.nullpointerbay.u2020s.base

import android.os.Bundle
import com.nullpointerbay.u2020s.{TR, TypedFindView, TypedLayout}

/**
  * Created by rafal on 6/27/16.
  */
trait ContentView extends BaseActivity with TypedFindView {

  def ContentView: TypedLayout[_]

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(ContentView.id)
  }
}

trait ToolbarView extends BaseActivity with TypedFindView {

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    val toolbar = findView(TR.toolbar)
    setSupportActionBar(toolbar)
  }
}
