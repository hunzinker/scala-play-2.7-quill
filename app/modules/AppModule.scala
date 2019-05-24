package modules

import java.time.{Clock, ZoneId}

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

class AppModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind(classOf[Clock]).toInstance(Clock.system(ZoneId.of("UTC")))
  }

}
