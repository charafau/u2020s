package com.nullpointerbay.u2020s.model

import argonaut._, Argonaut._

case class User(login: String, id: Int, avatarUrl: Option[String], url: String, htmlUrl: String, folowersUrl: String)

case class Repo(name: String, owner: String, description: String, url: String, stargazersCount: Integer, forkCount: Integer, user: User)

case class ApiRepo(totalCount: Integer, incompleteResult: Boolean, repos: Option[List[Repo]])

object User {
 implicit def casecodec = casecodec6(User.apply, User.unapply)("login", "id", "avatar_url", "url", "html_url", "followers_url")
}

object Repo {
  implicit def casecodec = casecodec7(Repo.apply, Repo.unapply)("name", "full_name", "description", "url", "stargazers_count", "forks_count", "owner")

}

object ApiRepo {
  implicit def casecodec = casecodec3(ApiRepo.apply, ApiRepo.unapply)("total_count", "incomplete_results", "items")

}
