package com.freelanceStats.alpakkaRabbitMQClient

import akka.Done
import akka.stream.alpakka.amqp.AmqpWriteSettings
import akka.stream.alpakka.amqp.scaladsl.AmqpSink
import akka.stream.scaladsl.{Flow, Keep, Sink}
import com.freelanceStats.alpakkaRabbitMQClient.elementConverter.ElementToByteStringConverter
import com.freelanceStats.queueClient.QueueConsumer

import scala.concurrent.Future

trait AlpakkaRabbitMQConsumer[Element]
    extends AlpakkaRabbitMQClient
    with QueueConsumer[Element] {

  def elementToByteStringConverter: ElementToByteStringConverter[Element]

  protected val settings: AmqpWriteSettings =
    AmqpWriteSettings(connectionProviderFactory.get)
      .withRoutingKey(configuration.queueName)
      .withDeclaration(queueDeclaration)
      .withBufferSize(configuration.writeBufferSize)
      .withConfirmationTimeout(configuration.writeConfirmationTimeout)

  override def sink: Sink[Element, Future[Done]] =
    Flow[Element]
      .map(elementToByteStringConverter.toByteString)
      .toMat(AmqpSink.simple(settings))(Keep.right)

}
