package com.freelancerStats.alpakkaRabbitMQClient.connectionProviderFactory

import akka.stream.alpakka.amqp.AmqpConnectionProvider

trait ConnectionProviderFactory {
  def get: AmqpConnectionProvider
}
