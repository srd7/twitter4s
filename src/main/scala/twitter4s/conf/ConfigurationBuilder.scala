package twitter4s.conf

object ConfigurationBuilder {
  def apply() = {
    new twitter4j.conf.ConfigurationBuilder()
  }
}