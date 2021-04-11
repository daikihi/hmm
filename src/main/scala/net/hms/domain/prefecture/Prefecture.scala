package net.hms.domain.prefecture

import net.hms.infrastructure.rdb.schema.h2.{Prefecture => H2Prefecture}

/**
  *  Memo : Prefecture Data model is too japanese.
  *     if we will use global beekeepers, then we should modify this model.
  */
case class Prefecture(prefectureId: PrefectureId,
                      name: PrefectureName,
                      katakana: PrefectureKatakana,
                      hiragana: PrefectureHiragana)

object Prefecture {
  def convertFromCsv(csv: String): Prefecture = {
    val csvElements = csv.split(",")
    val pId = PrefectureId(csvElements(0).toInt)
    val pName = PrefectureName(csvElements(1))
    val pKatakana = PrefectureKatakana(csvElements(2))
    val pHiragana = PrefectureHiragana(csvElements(3))
    Prefecture(pId, pName, pKatakana, pHiragana)
  }

  def toSchemaH2Prefecture(prefecture: Prefecture): H2Prefecture = {
    H2Prefecture(prefecture.prefectureId.id,
                 prefecture.name.name,
                 prefecture.katakana.name,
                 prefecture.hiragana.name)
  }
}

case class PrefectureId(id: Int)
case class PrefectureName(name: String)
case class PrefectureKatakana(name: String)
case class PrefectureHiragana(name: String)
