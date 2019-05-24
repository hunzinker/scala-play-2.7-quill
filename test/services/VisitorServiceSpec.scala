package services

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

import scala.util.Random
import com.google.inject.Guice
import config.AppConfig
import dao.VisitorDao
import models.Visitor
import models.enums.EventType
import org.scalatest.{BeforeAndAfter, FunSpec, MustMatchers}
import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import utils.Codec

class VisitorServiceSpec extends FunSpec with MustMatchers with BeforeAndAfter with MockitoSugar {

  val injector = Guice.createInjector()
  val config = injector.getInstance(classOf[AppConfig])

  val visitorDaoMock = mock[VisitorDao]
  val visitorService = new VisitorService(visitorDaoMock)

  describe("VisitorService") {
    describe("#insert") {
      it("inserts a website visitor") {
        visitorService.insert(
          Visitor(0L, "random", Random.nextLong(), Random.nextLong(), EventType.Click)
        ) mustBe a[java.lang.Long]
      }
    }

    describe("#getByDateHour") {
      it("rolls up visitors by date hour") {
        val dateTime = LocalDateTime.of(2019, 5, 12, 10, 25, 13)
        val dateHour = dateTime.format(DateTimeFormatter.ofPattern(Codec.dateHourPattern))

        val user1 = 34L
        val user2 = 56L
        val user3 = 87L

        val visitors = List(
          Visitor(0L, dateHour, Random.nextLong(), user1, EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), user1, EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), user2, EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), user3, EventType.Click),
          Visitor(0L, dateHour, Random.nextLong(), user1, EventType.Impression),
          Visitor(0L, dateHour, Random.nextLong(), user3, EventType.Impression),
        )

        when(visitorDaoMock.getVisitorByDateHour(dateHour)).thenReturn(visitors)

        val data = visitorService.getVisitorsByDateHour(dateHour)

        data.unique_visitors mustEqual(3)
        data.clicks mustEqual(4)
        data.impressions mustEqual(2)
      }
    }
  }

}
