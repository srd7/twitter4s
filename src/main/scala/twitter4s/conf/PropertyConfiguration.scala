package twitter4s.conf
import java.util.Properties

object PropertyConfiguration {
  def apply(prop: Properties, treePath: String) = {
    new twitter4j.conf.PropertyConfiguration(prop, treePath)
  }
}