package dao

import io.getquill.MappedEncoding
import models.enums.EventType

trait EventTypeEncoding {

  implicit val encodeEventType = MappedEncoding[EventType.Value, String](_.toString)
  implicit val decodeEventType = MappedEncoding[String, EventType.Value](EventType.withName(_))

}
