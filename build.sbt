
lazy val sharedSettings = Seq(
  organization := "com.freelance-stats",
  scalaVersion := "2.13.6",
  scalafmtOnCompile := true
)

lazy val githubPackagesConfig = Seq(
  githubOwner := "bijelic99",
  githubRepository := "freelance-stats-queue-client",
  githubTokenSource := TokenSource.GitConfig("github.token")
)

val AkkaVersion = "2.6.14"

lazy val root =
  (project in file("."))
    .settings(
      Seq(
        publishArtifact := false
      ) ++ sharedSettings ++ githubPackagesConfig: _*
    )
    .aggregate(queueClient, alpakkaRabbitMQClient)

lazy val queueClient =
  (project in file("modules/queue-client"))
    .settings(
      Seq(
        name := "queue-client",
        libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-stream" % AkkaVersion
        )
      ) ++ sharedSettings ++ githubPackagesConfig: _*
    )

lazy val alpakkaRabbitMQClient =
  (project in file("modules/alpakka-rabbitmq-client"))
    .settings(
      Seq(
        name := "alpakka-rabbitmq-client",
        libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-stream" % AkkaVersion
        )
      ) ++ sharedSettings ++ githubPackagesConfig: _*
    ).dependsOn(queueClient)