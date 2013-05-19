package twitter4s.api.impl

import twitter4s._
import api.HelpResources
import twitter4j.api.HelpResources.Language

trait HelpResourcesImpl extends HelpResources {
  self: Twitter => 
  
  /**
   * {@inheritDoc}
   */
  def getAPIConfiguration: TwitterAPIConfiguration = {
    twitter4s.TwitterAPIConfigurationWrapperJ2S(twitter4jObj.getAPIConfiguration())
  }
  
  def getLanguages: ResponseList[Language] = {
    twitter4s.responseListWrapperJ2S(twitter4jObj.getLanguages())
  }
}