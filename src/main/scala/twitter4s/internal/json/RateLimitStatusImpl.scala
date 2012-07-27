package twitter4s.internal.json
import twitter4s.RateLimitStatus

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class RateLimitStatusImpl(twt4jRateLimitStatus: twitter4j.RateLimitStatus) extends RateLimitStatus {
  def remainingHits = twt4jRateLimitStatus.getRemainingHits()

  def hourlyLimit = twt4jRateLimitStatus.getHourlyLimit()

  def resetTimeInSeconds = twt4jRateLimitStatus.getResetTimeInSeconds()

  def secondsUntilReset = twt4jRateLimitStatus.getSecondsUntilReset()

  def resetTime = twt4jRateLimitStatus.getResetTime()
  
  def tw4jObj = twt4jRateLimitStatus
}