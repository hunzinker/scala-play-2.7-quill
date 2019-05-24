package config

import javax.inject.Inject

import com.typesafe.config.ConfigFactory

@Inject()
class AppConfig {

  private lazy val config = ConfigFactory.load()

}
