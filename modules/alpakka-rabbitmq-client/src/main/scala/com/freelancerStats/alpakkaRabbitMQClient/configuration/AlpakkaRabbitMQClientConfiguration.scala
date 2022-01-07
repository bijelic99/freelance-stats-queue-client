package com.freelancerStats.alpakkaRabbitMQClient.configuration

import scala.concurrent.duration.{DurationInt, FiniteDuration}

trait AlpakkaRabbitMQClientConfiguration {
  def url: String
  def queueName: String
  val writeBufferSize: Int = 10
  val writeConfirmationTimeout: FiniteDuration = 1.second
  val readBufferSize: Int = 10
}
