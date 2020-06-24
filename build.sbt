name := "file_organizer"

version := "0.1"

scalaVersion := "2.12.11"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies /**/ ++= Seq(
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "com.typesafe" % "config" % "1.4.0",
  "com.lihaoyi" %% "fastparse" % "2.2.2"
)

val loggingDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)
libraryDependencies ++= loggingDependencies

