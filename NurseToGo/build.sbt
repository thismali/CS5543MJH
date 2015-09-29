name := "NurseToGo"
version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.twilio.sdk" % "twilio-java-sdk" % "3.4.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "org.scalaz" %% "scalaz-core" % "7.1.2",
  "io.spray" %% "spray-client" % "1.3.3",
  "io.spray" %% "spray-json" % "1.3.2",
  "io.spray" %% "spray-can" % "1.3.3",
  "io.spray" %% "spray-routing" % "1.3.3",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.11"
)

dependencyOverrides ++= Set(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4"
)

    