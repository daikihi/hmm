package net.hms.infrastructure.repository

import net.hms.lib.{HmmActor, InputMessage}
import net.hms.infrastructure.rdb.schema.h2.{Prefecture => SchemaPrefecture}
import net.hms.domain.prefecture.{Prefecture => DomainPrefecture}
import net.hms.infrastructure.repository.Prefecture.{
  PrefectureInputMessage,
  RequestWritePrefectureMessage
}

class Prefecture extends HmmActor {
  def receiveInputMessage(message: PrefectureInputMessage) = {
    message match {
      case RequestWritePrefectureMessage(prefecture) =>
        println(s"Prefecture : request write prefecture : ${message}")
    }
  }

  override def receive: Receive = {
    case m: PrefectureInputMessage =>
      setParentActor(sender())
      receiveInputMessage(m)
      receive
    case _ =>
      receives()

  }
}

object Prefecture {
  trait PrefectureInputMessage extends InputMessage
  case class RequestWritePrefectureMessage(prefecture: DomainPrefecture)
      extends PrefectureInputMessage
}
