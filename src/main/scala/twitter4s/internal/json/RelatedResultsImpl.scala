package twitter4s.internal.json

import twitter4s.RelatedResults

case class RelatedResultsImpl(twt4jRelatedResults: twitter4j.RelatedResults) extends RelatedResults {
  type Tw4jResponse = twitter4j.RelatedResults

  def tweetsFromUser = ResponseListImpl(twt4jRelatedResults.getTweetsFromUser())

  def tweetsWithConversation = ResponseListImpl(twt4jRelatedResults.getTweetsWithConversation())

  def tweetsWithReply = ResponseListImpl(twt4jRelatedResults.getTweetsWithReply())

  def rateLimitStatus = RateLimitStatusImpl(twt4jRelatedResults.getRateLimitStatus())

  def accessLevel = twt4jRelatedResults.getAccessLevel()

  def tw4jObj = twt4jRelatedResults

}