name := "honey_management"

version := "0.1"

scalaVersion := "2.13.5"

//idePackagePrefix := Some("net.hms")

organization := "local.hms"

val AkkaVersion = "2.6.13"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test
)
