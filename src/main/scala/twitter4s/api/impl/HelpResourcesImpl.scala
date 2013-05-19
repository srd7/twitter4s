package twitter4s.api.impl

import twitter4s._
import api.HelpResources
import twitter4j.api.HelpResources.Language
import api.impl._
import scala.collection.JavaConversions._
import scala.collection.mutable.Map

trait HelpResourcesImpl extends HelpResources {
  self: Twitter => 
  
  /**
   * {@inheritDoc}
   */
  def getAPIConfiguration: TwitterAPIConfiguration = {
    twitter4jObj.getAPIConfiguration()
  }
  
  /**
   * {@inheritDoc}
   */
  def getLanguages: ResponseList[Language] = {
    twitter4jObj.getLanguages()
  }
  
  /**
   * {@inheritDoc}
   */
  def getTermsOfService: String = {
    twitter4jObj.getTermsOfService()
  }

  /**
   * {@inheritDoc}
   */
  def getPrivacyPolicy: String = {
    twitter4jObj.getPrivacyPolicy()
  }
  
  /**
   * {@inheritDoc}
   */
  def getRateLimitStatus(resources: String*): Map[String, twitter4j.RateLimitStatus] = {
    if(resources.isEmpty) twitter4jObj.getRateLimitStatus()
    else twitter4jObj.getRateLimitStatus(resources: _*)
  }
}