name := "file_organizer"

version := "0.1"

scalaVersion := "2.12.11"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

val scalaTestVersion = "3.0.8"
val filesVersion = "3.8.0"
val circeVersion = "0.12.3"
libraryDependencies ++= Seq(
  "com.github.pathikrit" %% "better-files" % filesVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
)

val logback = "ch.qos.logback"
val logbackVersion = "1.2.3"

val loggingDependencies = Seq(
  logback % "logback-classic" % logbackVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)
libraryDependencies ++= loggingDependencies
