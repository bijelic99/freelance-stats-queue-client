package com.freelanceStats.alpakkaRabbitMQClient

import akka.NotUsed
import akka.stream.alpakka.amqp.NamedQueueSourceSettings
import akka.stream.alpakka.amqp.scaladsl.AmqpSource
import akka.stream.scaladsl.Source
import com.freelanceStats.alpakkaRabbitMQClient.elementConverter.ByteStringToElementConverter
import com.freelanceStats.queueClient.QueueProducer

trait AlpakkaRabbitMQPProducer[Element]
    extends AlpakkaRabbitMQClient
    with QueueProducer[Element] {
  def byteStringToElementConverter: ByteStringToElementConverter[Element]

  protected val producerSettings: NamedQueueSourceSettings =
    NamedQueueSourceSettings(
      amqpConnectionProvider,
      configuration.queueName
    )
      .withDeclaration(queueDeclaration)
      .withAckRequired(false)

  override def source: Source[Element, NotUsed] =
    AmqpSource
      .atMostOnceSource(
        producerSettings,
        configuration.readBufferSize
      )
      .map(_.bytes)
      .map(byteStringToElementConverter.toElement)
}
