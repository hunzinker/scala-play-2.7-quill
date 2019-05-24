package controllers

import javax.inject._

import scala.util.Try
import akka.util.ByteString
import com.google.inject.Inject
import models.Visitor
import models.enums.EventType
import play.api.http.HttpEntity
import play.api.mvc._
import services.VisitorService
import utils.Codec

@Singleton
class VisitorsController @Inject()(cc: ControllerComponents,
                                   visitorService: VisitorService) extends AbstractController(cc) {

  def create(timestamp: Long, user: Long, event: String) = Action { implicit request: Request[AnyContent] =>
    Try {
      val dateHour = Codec.timestampToDateHour(timestamp)

      val visitor = Visitor(0L, dateHour, timestamp, user, EventType.withName(event.toLowerCase))
      visitorService.insert(visitor)
    }

    Result(
      header = ResponseHeader(204, Map.empty),
      body = HttpEntity.Strict(ByteString(""), Some("text/plain"))
    )
  }

  def getVisitorsByTimestamp(timestamp: Long) = Action { implicit request: Request[AnyContent] =>
    val dateHour = Codec.timestampToDateHour(timestamp)

    val data = visitorService.getVisitorsByDateHour(dateHour)

    Ok(s"unique_visitors,${data.unique_visitors}\nclicks,${data.clicks}\nimpressions,${data.impressions}")
  }
}
