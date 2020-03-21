name := """logInfrastructure"""
organization := "com.kinsho"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "jp.t2v" %% "play2-auth" % "0.14.1"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.kinsho.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.kinsho.binders._"
