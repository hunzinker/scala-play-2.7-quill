name := """example-play-quill"""
organization := "com.hunzinker"
scalaVersion := "2.12.8"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  evolutions,
  jdbc,
  "net.codingwell"         %% "scala-guice"        % "4.2.3",
  "com.h2database"         %  "h2"                 % "1.4.199",
  "io.getquill"            %% "quill-jdbc"         % "3.1.0",
  "org.mockito"            %  "mockito-core"       % "2.27.0"    % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2"     % Test
)

javaOptions in Test += "-Duser.timezone=UTC"
