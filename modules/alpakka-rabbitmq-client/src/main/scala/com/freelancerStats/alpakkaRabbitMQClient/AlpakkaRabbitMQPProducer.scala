package com.freelancerStats.alpakkaRabbitMQClient

import akka.NotUsed
import akka.stream.alpakka.amqp.NamedQueueSourceSettings
import akka.stream.alpakka.amqp.scaladsl.AmqpSource
import akka.stream.scaladsl.Source
import com.freelancerStats.alpakkaRabbitMQClient.elementConverter.ByteStringToElementConverter
import com.freelancerStats.queueClient.QueueProducer

trait AlpakkaRabbitMQPProducer[Element]
    extends AlpakkaRabbitMQClient
    with QueueProducer[Element] {
  def byteStringToElementConverter: ByteStringToElementConverter[Element]

  protected val settings: NamedQueueSourceSettings =
    NamedQueueSourceSettings(
      connectionProviderFactory.get,
      configuration.queueName
    )
      .withDeclaration(queueDeclaration)
      .withAckRequired(false)

  override def source: Source[Element, NotUsed] =
    AmqpSource
      .atMostOnceSource(
        settings,
        configuration.readBufferSize
      )
      .map(_.bytes)
      .map(byteStringToElementConverter.toElement)
}
