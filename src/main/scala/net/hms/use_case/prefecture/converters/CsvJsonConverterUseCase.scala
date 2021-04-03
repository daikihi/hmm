package net.hms.use_case.prefecture.converters

import akka.actor.{ActorLogging}
import net.hms.lib.{ DoneMessage, HmmActor, InputMessage}
import net.hms.use_case.prefecture.converters.CsvJsonConverterUseCase.RequestConvertMessage

class CsvJsonConverterUseCase extends HmmActor with ActorLogging{
  override def receive: Receive = {
    case RequestConvertMessage() =>
      sender ! DoneMessage()
      // temp
      send()
      receives()
    case _ => log.warning("CsvJsonConverterUseCase receives an unknown message : dead leatter")
  }
}

object CsvJsonConverterUseCase{
  case class RequestConvertMessage() extends InputMessage()
}