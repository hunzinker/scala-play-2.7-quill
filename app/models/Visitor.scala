package models

import models.enums.EventType

case class Visitor(id: Long,
                   dateHour: String,
                   timestamp: Long,
                   user: Long,
                   event: EventType.Value)
