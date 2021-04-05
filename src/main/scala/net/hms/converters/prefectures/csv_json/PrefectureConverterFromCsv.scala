package net.hms.converters.prefectures.csv_json

import net.hms.converters.prefectures.csv_json.PrefectureConverterFromCsv.{
  RequestConvertPrefectureMessage,
  ResponseConvertPrefectureMessage
}
import net.hms.domain.prefecture.Prefecture
import net.hms.lib.{HmmActor, InputMessage}

class PrefectureConverterFromCsv extends HmmActor {
  def convert(line: String): Prefecture = {
    Prefecture.convertFromCsv(line)
  }

  override def receive: Receive = {
    case RequestConvertPrefectureMessage(csv) =>
      sender ! ResponseConvertPrefectureMessage(convert(csv))
    case _ =>
  }
}

object PrefectureConverterFromCsv {
  case class RequestConvertPrefectureMessage(csv: String) extends InputMessage
  case class ResponseConvertPrefectureMessage(prefecture: Prefecture)
}
