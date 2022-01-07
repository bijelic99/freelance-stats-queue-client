package com.freelanceStats.alpakkaRabbitMQClient.elementConverter

import akka.util.ByteString

trait ElementToByteStringConverter[Element] {
  def toByteString(element: Element): ByteString
}
