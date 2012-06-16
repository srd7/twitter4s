package twitter4s
import java.util.Properties
import twitter4j.conf.PropertyConfiguration
import twitter4j.conf.ConfigurationBuilder
import twitter4j.json.DataObjectFactory

object Twitter4sTestHelper {
  val prop = new Properties()
  val is = Twitter4sTestHelper.getClass().getResourceAsStream("test.properties")
  
  prop.load(is)
  is.close()
  
  class TestUserInfo(propName: String) {
    val screenName = prop.getProperty(propName + ".user")
    val password = prop.getProperty(propName + ".password")
    val id = prop.getProperty(propName + ".id").toLong
    val accessToken = prop.getProperty(propName + ".oauth.accessToken")
    val accessTokenSecret = prop.getProperty(propName + ".oauth.accessTokenSecret")
  }
  
  val id1 = new TestUserInfo("id1")
  val id2 = new TestUserInfo("id2")
  val id3 = new TestUserInfo("id3")
  
  /**
   * Get Twitter4S object from test.propreties with prefix id1
   */
  def twitter1 = {
    Twitter(conf = Some(new PropertyConfiguration(prop, "/id1")))
  }
  
  /**
   * Get Twitter4S object from test.properties with prefix id2
   */
  def twitter2 = {
    Twitter(conf = Some(new PropertyConfiguration(prop, "/id2")))
  }
  
  /**
   * Get Twitter4S object from test.propreties with prefix id3
   * TODO protected user
   */
  def twitter3 = {
    Twitter(conf = Some(new PropertyConfiguration(prop, "/id3")))
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
  
  /**
   * Get raw JSON string from Twitter4J object
   */
  def rawJSON(target: Any) = DataObjectFactory.getRawJSON(target)
  
  /**
   * Get id1 blocking user screen name
   */
  def blockingScreenName = prop.getProperty("id1.block.user")
  
  /**
   * Get id1 blocking user id
   */
  def blockingUserId = prop.getProperty("id1.block.id").toLong
  
  /**
   * Get id1 blocking users list size
   */
  def blockingUsersSize = prop.getProperty("id1.block.length").toInt

}