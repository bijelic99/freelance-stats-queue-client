package com.freelancerStats.alpakkaRabbitMQClient.elementConverter

import akka.util.ByteString

trait ByteStringToElementConverter[Element] {
  def toElement(byteString: ByteString): Element
}
