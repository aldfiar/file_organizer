name := "project_organizer"

version := "0.1"

scalaVersion := "2.13.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

val scalaTestVersion = "3.0.8"
val filesVersion = "3.8.0"

libraryDependencies ++= Seq(
  "com.github.pathikrit" %% "better-files-akka" % filesVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
)

val logback = "ch.qos.logback"
val logbackVersion = "1.2.3"

val loggingDependencies = Seq(
  logback % "logback-classic" % logbackVersion
)
libraryDependencies ++= loggingDependencies
