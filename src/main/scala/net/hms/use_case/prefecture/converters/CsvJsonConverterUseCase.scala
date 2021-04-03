package net.hms.use_case.prefecture.converters

import akka.actor.{Actor, ActorLogging}

class CsvJsonConverterUseCase extends Actor with ActorLogging{
  override def receive: Receive = {
    case _ => log.warning("CsvJsonConverterUseCase receives an unknown message : dead leatter")
  }
}