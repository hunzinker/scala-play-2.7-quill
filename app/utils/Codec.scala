package utils

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId}

object Codec {

  lazy val dateHourPattern = "yyyy-MM-dd-HH"

  def timestampToDateHour(timestamp: Long): String = {
    Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC"))
      .toLocalDateTime
      .format(DateTimeFormatter.ofPattern(dateHourPattern))
  }

}

