package dao

import javax.inject.{Inject, Singleton}

import models.Visitor

@Singleton
class VisitorDao @Inject()(dbContext: DBContext) extends EventTypeEncoding {

  import dbContext._

  def insertVisitor(visitor: Visitor): Long = {

    implicit val visitorInsertMeta = insertMeta[Visitor](_.id)

    val q = quote {
      query[Visitor].insert(lift(visitor)).returning(_.id)
    }

    dbContext.run(q)
  }

  def getVisitorByDateHour(dateHour: String): List[Visitor] = {
    val q: Quoted[Query[Visitor]]= quote {
      query[Visitor].filter(wv => wv.dateHour == lift(dateHour))
    }

    dbContext.run(q)
  }

}
