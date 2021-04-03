package net.hms.lib

import akka.actor.{Actor, ActorRef, Props}
import akka.event.{Logging, LoggingAdapter}

import scala.reflect.ClassTag

class MainActor[T <:  Actor: ClassTag](implicit tag: ClassTag[T]) extends Actor {
  val useCase: ActorRef = context.system.actorOf(Props(tag.runtimeClass))
  val log: LoggingAdapter = Logging(context.system, this)

  def handleSystemMessage(m: SystemMessage): Unit = {
    m match{
      case StartExecuteBatchMessage(appMessage) =>
        useCase ! appMessage
    }
  }

  override def receive: Receive = {
    case m: SystemMessage =>
      log.info {
        s"MainActor receives a SystemMessage : $m"
      }
      handleSystemMessage(m)
    case _ =>
  }
}

