package twitter4s.dsl

import twitter4s.Twitter
import twitter4s.api.impl.DirectMessagesResourcesImpl

/**
 * @author mao.instantlife at gmail.com
 */
trait DirectMessagesResourcesDsl extends Twitter4sDslBase {
  type ResourcesType = DirectMessagesResourcesImpl
  protected val twitter4sResources = new Twitter(twitter4jResources) with ResourcesType
}
