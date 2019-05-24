package models.enums

object EventType extends Enumeration {
  type EventType = Val

  val Click = Value("click")
  val Impression = Value("impression")
}
