package net.hms.batches.prefecture

import akka.actor.ActorSystem
import akka.actor.Props
import net.hms.lib.{ApplicationMessage, MainActor, StartExecuteBatchMessage}
import net.hms.use_case.prefecture.converters.CsvJsonConverterUseCase


object PrefectureMasterCsvJsonConverter {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("local-main-actor")
    val mainActor = actorSystem.actorOf(Props(new MainActor[CsvJsonConverterUseCase]()))
    mainActor ! StartExecuteBatchMessage(ApplicationMessage())
  }
}
