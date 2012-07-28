package twitter4s.internal.json

import twitter4s._
import java.util.Date

case class StatusImpl(twt4jStatus: twitter4j.Status) extends Status {
  
  type Tw4jResponse = twitter4j.Status

  def isTruncated = twt4jStatus.isTruncated()

  def inReplyToUserId = twt4jStatus.getInReplyToUserId()

  def inReplyToScreenName = twt4jStatus.getInReplyToScreenName()

  def isFavorited = twt4jStatus.isFavorited()

  def user = UserImpl(twt4jStatus.getUser()) // TODO 暗黙の型変換の仕様確認

  def isRetweet = twt4jStatus.isRetweet()

  def retweetedStatus = StatusImpl(twt4jStatus.getRetweetedStatus()) // // TODO 暗黙の型変換の仕様確認

  def contributors = twt4jStatus.getContributors()

  def retweetCount = twt4jStatus.getRetweetCount()

  def isRetweetedByMe = twt4jStatus.isRetweetedByMe()

  def annotations = twt4jStatus.getAnnotations()

  def urlEntities = twt4jStatus.getURLEntities()

  def hashtagEntities = twt4jStatus.getHashtagEntities()

  def mediaEntities = twt4jStatus.getMediaEntities()

  def createdAt = twt4jStatus.getCreatedAt()

  def id = twt4jStatus.getId()

  def text = twt4jStatus.getText()

  def source = twt4jStatus.getSource()

  def inReplyToStatusId = twt4jStatus.getInReplyToStatusId()

  def place = twt4jStatus.getPlace()

  def geoLocation = twt4jStatus.getGeoLocation()

  def rateLimitStatus = twt4jStatus.getRateLimitStatus()

  def accessLevel = twt4jStatus.getAccessLevel()

  def tw4jObj = twt4jStatus

}