package twitter4s.api.impl

import twitter4s._
import api.HelpResources
import twitter4j.api.HelpResources.Language
import api.impl._

trait HelpResourcesImpl extends HelpResources {
  self: Twitter => 
  
  /**
   * {@inheritDoc}
   */
  def getAPIConfiguration: TwitterAPIConfiguration = {
    twitter4jObj.getAPIConfiguration()
  }
  
  def getLanguages: ResponseList[Language] = {
    twitter4jObj.getLanguages()
  }
}