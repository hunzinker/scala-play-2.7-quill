import javax.inject.{Inject, Singleton}

import io.getquill.{H2JdbcContext, SnakeCase}
import com.zaxxer.hikari.HikariDataSource
import play.api.db.Database

// Credit:
// https://stackoverflow.com/questions/49075152/conflict-between-hikari-quill-and-postgres-in-the-conf-file-for-play-2-6/49085792#49085792

package object dao {
  @Singleton
  class DBContext @Inject()(db: Database) extends H2JdbcContext(SnakeCase, db.dataSource.asInstanceOf[HikariDataSource])
}
