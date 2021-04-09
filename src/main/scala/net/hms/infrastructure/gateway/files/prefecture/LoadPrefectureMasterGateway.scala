package net.hms.infrastructure.gateway.files.prefecture

import akka.actor.ActorRef
import net.hms.infrastructure.gateway.files.prefecture.LoadPrefectureMasterGateway.{
  RequestLoadMessage,
  ResponsePrefecture
}
import net.hms.lib.{DoneMessage, HmmActor, InputMessage, OutputMessage}

import scala.io.Source

class LoadPrefectureMasterGateway extends HmmActor {
  def load(contextSender: ActorRef): Unit = {
    Source.fromResource("prefectures/prefecture_master.csv").getLines().foreach { line =>
      contextSender ! ResponsePrefecture(line)
    }
  }

  override def receive: Receive = {
    case RequestLoadMessage() =>
      log.info("LoadPrefectureMasterGateway receives a mesage : RequestLoadMessage")
      load(sender)
      setParentActor(context.sender())
      sender ! DoneMessage()
    case _ =>
  }
}

object LoadPrefectureMasterGateway {
  case class RequestLoadMessage() extends InputMessage

  case class ResponsePrefecture(csv: String) extends OutputMessage
}
