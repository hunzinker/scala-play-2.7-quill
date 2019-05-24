package services

import dao.VisitorDao
import javax.inject.Inject

import models.enums.EventType
import models.{Visitor, VisitorsPerHour}

class VisitorService @Inject()(visitorDao: VisitorDao) {

  def insert(visitor: Visitor): Long = {
    visitorDao.insertVisitor(visitor)
  }

  def getVisitorsByDateHour(dateHour: String): VisitorsPerHour = {
    val data = visitorDao.getVisitorByDateHour(dateHour)
    VisitorsPerHour(
      data.map(_.user).distinct.size,
      data.count(_.event == EventType.Click),
      data.count(_.event == EventType.Impression)
    )
  }
}
