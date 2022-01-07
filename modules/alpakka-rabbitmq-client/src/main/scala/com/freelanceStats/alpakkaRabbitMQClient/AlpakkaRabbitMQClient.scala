package com.freelanceStats.alpakkaRabbitMQClient

import akka.stream.Materializer
import akka.stream.alpakka.amqp.QueueDeclaration
import com.freelanceStats.alpakkaRabbitMQClient.configuration.AlpakkaRabbitMQClientConfiguration
import com.freelanceStats.alpakkaRabbitMQClient.connectionProviderFactory.ConnectionProviderFactory

trait AlpakkaRabbitMQClient {
  implicit val materializer: Materializer
  def connectionProviderFactory: ConnectionProviderFactory
  def configuration: AlpakkaRabbitMQClientConfiguration
  lazy val queueDeclaration: QueueDeclaration = QueueDeclaration(
    configuration.queueName
  )
}
