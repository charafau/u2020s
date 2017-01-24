enablePlugins(AndroidApp)

name := "u2020s"
organization := "com.nullpointerbay.u2020s"
version := "0.0.1"
versionCode := Some(1)

scalaVersion := "2.11.8"

javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil

scalacOptions ++= Seq("-feature", "-Xexperimental" ,"-language:implicitConversions", "-language:postfixOps", "-target:jvm-1.7")

platformTarget in Android := "android-23"
minSdkVersion in Android := "21"

typedResources in Android :=true

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  "jcenter" at "http://jcenter.bintray.com"
)

useProguard in Android := false
useProguardInDebug in Android := (useProguard in Android).value
typedResources in Android := true
dexMulti in Android := true
dexMaxHeap in Android := "2048M"

val supportLibVersion = "23.3.0"

libraryDependencies ++= Seq (
  "com.softwaremill.macwire" %% "macros" % "2.2.3" % "provided",
  aar("com.android.support" % "support-v4" % supportLibVersion),
  aar("com.android.support" % "appcompat-v7" % supportLibVersion),
  aar("com.android.support" % "recyclerview-v7" % supportLibVersion),
  aar("com.android.support" % "cardview-v7" % supportLibVersion),
  aar("com.android.support" % "design" % supportLibVersion),
  "com.squareup.picasso" % "picasso" % "2.5.2",
  "com.lihaoyi" %% "scalarx" % "0.3.1",
  "io.taig" %% "communicator" % "2.3.2",
  "io.argonaut" %% "argonaut" % "6.1",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

proguardCache += "com.android.support"
