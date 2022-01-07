package com.freelancerStats.queueClient

import akka.Done
import akka.stream.scaladsl.Sink

import scala.concurrent.Future

trait QueueConsumer[Element] {
  def sink: Sink[Element, Future[Done]]
}
