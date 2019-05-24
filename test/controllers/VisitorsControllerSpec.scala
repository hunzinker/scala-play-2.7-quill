package controllers

import java.time.Instant

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._

class VisitorsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with BeforeAndAfter {

  val timestamp = Instant.now().toEpochMilli
  val userId = 123L
  val click = "click"
  val impression = "impression"

  "VisitorsController POST" should {

    "return 204" in {
      val controller = inject[VisitorsController]
      val post = controller.create(Instant.now().toEpochMilli, userId, click)
        .apply(FakeRequest(POST, s"/analytics?timestamp=${timestamp}&user=${userId}&event=${click}"))

      status(post) mustBe NO_CONTENT
      contentType(post) mustBe Some("text/plain")
      contentAsString(post) mustBe empty
    }
  }

  "VisitorsController GET" should {

    "return 200 with website visitor analytics" in {
      val controller = inject[VisitorsController]
      val get = controller.getVisitorsByTimestamp(timestamp)
        .apply(FakeRequest(GET, s"/analytics?timestamp=${timestamp}"))

      status(get) mustBe OK
      contentType(get) mustBe Some("text/plain")
      contentAsString(get) mustBe "unique_visitors,1\nclicks,1\nimpressions,0"
    }

  }

}
