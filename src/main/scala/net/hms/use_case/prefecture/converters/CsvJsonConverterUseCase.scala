package net.hms.use_case.prefecture.converters

import akka.actor.{ActorLogging, Props}
import net.hms.gateways.files.prefecture.LoadPrefectureMasterGateway
import net.hms.lib.{DoneMessage, HmmActor, InputMessage}
import net.hms.use_case.prefecture.converters.CsvJsonConverterUseCase.RequestConvertMessage

class CsvJsonConverterUseCase extends HmmActor with ActorLogging {
  val loader = context.actorOf(Props[LoadPrefectureMasterGateway])

  override def receive: Receive = {
    case RequestConvertMessage() =>
      log.info("CsvJsonConverterUseCase : receives a message.")
      send()
      setParentActor(context.sender())
      loader ! LoadPrefectureMasterGateway.RequestLoadMessage()
    case LoadPrefectureMasterGateway.ResponsePrefecture(line) =>
      println(line)
    case DoneMessage() =>
      log.info("CsvJsonConverterUseCase receives DONEMessage")
      receives()
    case msg => log.warning(s"CsvJsonConverterUseCase receives an unknown message : dead letter : $msg")
  }
}

object CsvJsonConverterUseCase {
  case class RequestConvertMessage() extends InputMessage()
}
