package com.freelanceStats.queueClient

import akka.NotUsed
import akka.stream.scaladsl.Source

trait QueueProducer[Element] {
  def source: Source[Element, NotUsed]
}
