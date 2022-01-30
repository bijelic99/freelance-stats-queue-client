package com.freelanceStats.alpakkaRabbitMQClient

import akka.stream.Materializer
import akka.stream.alpakka.amqp.{AmqpConnectionProvider, QueueDeclaration}
import com.freelanceStats.alpakkaRabbitMQClient.configuration.AlpakkaRabbitMQClientConfiguration

trait AlpakkaRabbitMQClient {
  implicit val materializer: Materializer
  def amqpConnectionProvider: AmqpConnectionProvider
  def configuration: AlpakkaRabbitMQClientConfiguration
  lazy val queueDeclaration: QueueDeclaration = QueueDeclaration(
    configuration.queueName
  )
}
