package com.freelancerStats.alpakkaRabbitMQClient

import akka.stream.Materializer
import akka.stream.alpakka.amqp.QueueDeclaration
import com.freelancerStats.alpakkaRabbitMQClient.configuration.AlpakkaRabbitMQClientConfiguration
import com.freelancerStats.alpakkaRabbitMQClient.connectionProviderFactory.ConnectionProviderFactory

trait AlpakkaRabbitMQClient {
  implicit val materializer: Materializer
  def connectionProviderFactory: ConnectionProviderFactory
  def configuration: AlpakkaRabbitMQClientConfiguration
  lazy val queueDeclaration: QueueDeclaration = QueueDeclaration(
    configuration.queueName
  )
}
