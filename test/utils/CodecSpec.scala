package utils

import java.time.{LocalDateTime, ZoneId, ZoneOffset}

import org.scalatest.{FunSpec, MustMatchers}

class CodecSpec extends FunSpec with MustMatchers {

  describe("Codec") {
    describe("#timestampToDateHour") {
      it("returns timestamp with millis to formatted date hour string") {
        val dateTime = LocalDateTime.of(2019, 5, 12, 10, 25, 13)
        val timestamp = dateTime.toInstant(ZoneOffset.of("Z")).toEpochMilli

        Codec.timestampToDateHour(timestamp) mustBe "2019-05-12-10"
      }
    }
  }

}
