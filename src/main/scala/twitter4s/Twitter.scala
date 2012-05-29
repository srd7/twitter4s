package twitter4s

import twitter4j._
import twitter4j.conf._
import twitter4j.auth._

case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterAPIs {
	def getAvailableTrends(location: Option[GeoLocation] = None): ResponseList[Location] = {
	  location match {
	    case Some(locationData) => twitter4jObj.getAvailableTrends(locationData)
	    case None => twitter4jObj.getAvailableTrends()
	  }
	}
	
	def getLocationTrends(woeid: Int): Trends = {
	  null
	}
}

object Twitter {
  def apply(conf: Option[Configuration] = None, configTreePath: Option[String] = None, accessToken: Option[AccessToken] = None, auth: Option[Authorization] = None) = {
    val factory4j = getTwitterFactory4j(conf, configTreePath)
    new Twitter(getTwitter4jInstance(factory4j, accessToken, auth))
  }
  
  private def getTwitterFactory4j(conf: Option[Configuration], configTreePath: Option[String]) = {
    (conf, configTreePath) match {
      case (None, None) => new TwitterFactory()
      case (Some(config), None) => new TwitterFactory(config)
      case (None, Some(confTree)) => new TwitterFactory(confTree)
      //case _ => // Exception?
    }
  }
  
  private def getTwitter4jInstance(factory4j: TwitterFactory, accessToken: Option[AccessToken], auth: Option[Authorization]) = {
    (accessToken, auth) match {
      case (None, None) => factory4j.getInstance()
      case (Some(token), None) => factory4j.getInstance(token)
      case (None, Some(authInfo)) => factory4j.getInstance(authInfo)
      //case _ => // Exception?
    }
  }
}