package twitter4s
import java.util.Properties
import twitter4j.conf.PropertyConfiguration
import twitter4j.conf.ConfigurationBuilder

object Twitter4sTestHelper {
  val prop = new Properties()
  val is = Twitter4sTestHelper.getClass().getResourceAsStream("test.properties")
  
  prop.load(is)
  is.close()
  
  /**
   * Get Twitter4S object from test.propreties with prefix id1
   */
  def twitter1 = {
    Twitter(conf = Some(new PropertyConfiguration(prop, "/id1")))
  }
  
  /**
   * Get Twitter4s object from test.properties with perfix r-w-private
   * with access level Read, write, and direct messages
   */
  def rwPrivateMessage = {
    Twitter(conf = Some(new PropertyConfiguration(prop, "/r-w-private")))
  }
  
  /**
   * Get Twitter4S object not authorized
   */
  def unauthenticated = {
    Twitter(conf = Some(new ConfigurationBuilder().setJSONStoreEnabled(true).build()))
  }

}