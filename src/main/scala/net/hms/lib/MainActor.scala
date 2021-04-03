package net.hms.lib

import akka.actor.{Actor, ActorRef, Props}

import scala.reflect.ClassTag

class MainActor[T <:  Actor: ClassTag](implicit tag: ClassTag[T]) extends HmmActor {
  isMain()
  val useCase: ActorRef = context.system.actorOf(Props(tag.runtimeClass))

  var sent: Long = 0

  def handleSystemMessage(m: SystemMessage): Unit = {
    m match{
      case StartExecuteBatchMessage(appMessage) =>
        send()
        useCase ! appMessage
      case DoneMessage() =>
        receives()
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

