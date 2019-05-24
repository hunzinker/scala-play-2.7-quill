package dao

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

import scala.util.Random
import models.Visitor
import models.enums.EventType
import org.scalatest.{FunSpec, MustMatchers}
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting
import utils.Codec

class VisitorDaoSpec extends FunSpec with MustMatchers with Injecting with GuiceOneAppPerTest {

  val dateTime = LocalDateTime.of(2019, 5, 12, 10, 25, 13)
  val dateHour1 = dateTime.format(DateTimeFormatter.ofPattern(Codec.dateHourPattern))
  val dateHour2 = dateTime.plusHours(2).format(DateTimeFormatter.ofPattern(Codec.dateHourPattern))

  describe("VisitorDao") {
    describe("#insertVisitor") {
      it("inserts data in db") {
        val visitorDao = inject[VisitorDao]

        val dateHour = dateHour1

        visitorDao.getVisitorByDateHour(dateHour).size mustEqual(0)

        visitorDao.insertVisitor(
          Visitor(0L, "random", Random.nextLong(), Random.nextLong(), EventType.Click)
        ) mustBe a[java.lang.Long]
      }
    }

    describe("#getVisitorByDateHour") {
      it("get values by dateHour key") {
        val visitorDao = inject[VisitorDao]

        val dateHour = dateHour2

        List(
          Visitor(0L, dateHour, Random.nextLong(), Random.nextLong(), EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), Random.nextLong(), EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), Random.nextLong(), EventType.Impression),
          Visitor(0L, dateHour, Random.nextLong(), Random.nextLong(), EventType.Impression),
          Visitor(0L, "random", Random.nextLong(), Random.nextLong(), EventType.Click)
        ).foreach(w => visitorDao.insertVisitor(w))

        visitorDao.getVisitorByDateHour(dateHour).size mustEqual(4)
      }
    }
  }


}
