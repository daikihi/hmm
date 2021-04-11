package net.hms.infrastructure.rdb.schema.h2

import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

case class Prefecture(id: Int, name: String, katakana: String, hiragana: String)

class Prefectures(tag: Tag) extends Table[Prefecture](tag, "prefectures") {
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)
  def name: Rep[String] = column[String]("name")
  def katakana: Rep[String] = column[String]("katakana")
  def hiragana: Rep[String] = column[String]("hiragana")

  override def * : ProvenShape[Prefecture] =
    (id, name, katakana, hiragana) <> (Prefecture.tupled, Prefecture.unapply)
}
