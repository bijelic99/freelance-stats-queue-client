package com.freelancerStats.queueClient

import akka.{Done, NotUsed}
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future

trait QueueClient[Element] {
  def source: Source[Element, NotUsed]
  def sink: Sink[Element, Future[Done]]
}
