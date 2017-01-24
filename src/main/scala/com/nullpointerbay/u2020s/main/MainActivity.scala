package com.nullpointerbay.u2020s.main

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.{LinearLayoutManager, RecyclerView}
import android.util.Log
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.Toast
import com.nullpointerbay.u2020s.base.{BaseActivity, CircularTransform, ContentView, ToolbarView}
import com.nullpointerbay.u2020s.di.MainModule
import com.nullpointerbay.u2020s.model.Repo
import com.nullpointerbay.u2020s.{R, TR, TypedFindView, TypedLayout}
import com.squareup.picasso.Picasso

class MainActivity extends BaseActivity
  with ContentView
  with ToolbarView
  with MainView
  with MainModule {

  lazy val recyclerRepo = findView(TR.recycler_repo)
  lazy val presenter: MainPresenter = presenter(this)
  lazy val btnFetch = findView(TR.btn_fetch)
  val adapter: RepoAdapter = new RepoAdapter(this, List())

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    recyclerRepo.setLayoutManager(new LinearLayoutManager(this))
    recyclerRepo.setHasFixedSize(true)
    recyclerRepo.setAdapter(adapter)
    btnFetch.setOnClickListener(_ => presenter.loadRepos())

  }

  override def TAG: String = "MainActivity"

  override def ContentView: TypedLayout[_] = TR.layout.main

  class RepoAdapter(context: Context, var repos: Seq[Repo]) extends RecyclerView.Adapter[RepoViewHolder] {
    def addAll(repos: Seq[Repo]) = {
      this.repos = repos
    }

    override def getItemCount: Int = repos.size

    override def onBindViewHolder(vh: RepoViewHolder, position: Int): Unit = {
      val repo = repos(position)
      vh.txtName.setText(repo.name)
      vh.txtDesc.setText(repo.description)
      vh.txtForks.setText(String.valueOf(repo.forkCount))
      vh.txtStars.setText(String.valueOf(repo.stargazersCount))


      repo.user.avatarUrl match {
        case Some(value) => Picasso.`with`(context).load(value).transform(CircularTransform()).into(vh.imgAvatar)
        case None => vh.imgAvatar.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.avatar))
      }

    }

    override def onCreateViewHolder(viewGroup: ViewGroup, i: Int): RepoViewHolder = {
      val view = LayoutInflater.from(viewGroup.getContext).inflate(R.layout.item_repository, viewGroup, false)

      new RepoViewHolder(view)
    }
  }

  class RepoViewHolder(view: View) extends RecyclerView.ViewHolder(view) with TypedFindView {

    lazy val imgAvatar = findView(TR.trending_repository_avatar)
    lazy val txtName = findView(TR.trending_repository_name)
    lazy val txtDesc = findView(TR.trending_repository_description)
    lazy val txtStars = findView(TR.trending_repository_stars)
    lazy val txtForks = findView(TR.trending_repository_forks)


    override protected def findViewById(id: Int): View = view.findViewById(id)
  }

  override def printSomething(message: String): Unit = {
    Log.d(TAG, message)
  }

  override def showRepos(repos: Seq[Repo]): Unit = {
    runOnUiThread(() => {
      adapter.addAll(repos)
      adapter.notifyDataSetChanged()
    })

  }

  override def showError(msg: String): Unit =
    runOnUiThread(() => Toast.makeText(this, s"Error :( ${msg}", Toast.LENGTH_LONG))

}
