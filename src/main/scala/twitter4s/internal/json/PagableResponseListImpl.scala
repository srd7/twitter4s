package twitter4s.internal.json

import twitter4j.PagableResponseList
import twitter4s.PagableResponseList
import scala.collection.JavaConverters._

case class PagableResponseListImpl[T <: twitter4j.TwitterResponse](twt4jPagableResponseList: twitter4j.PagableResponseList[T])
extends twitter4s.PagableResponseList[T] {
  type Tw4jResponse = twitter4j.PagableResponseList[T]
  
  def hasPrevious = twt4jPagableResponseList.hasPrevious()

  def previousCursor = twt4jPagableResponseList.getPreviousCursor()

  def hasNext = twt4jPagableResponseList.hasNext()

  def nextCursor = twt4jPagableResponseList.getNextCursor()

  def listAsScala = twt4jPagableResponseList asScala

  def rateLimitStatus = twt4jPagableResponseList.getRateLimitStatus()

  def featureSpecificRateLimitStatus = twt4jPagableResponseList.getFeatureSpecificRateLimitStatus()

  def accessLevel = twt4jPagableResponseList.getAccessLevel()
  
  def apply(idx: Int) = listAsScala(idx)
  
  def tw4jObj = twt4jPagableResponseList

}