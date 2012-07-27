package twitter4s
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait RateLimitStatus {
  def remainingHits: Int

  def hourlyLimit: Int

  def resetTimeInSeconds: Int

  def secondsUntilReset: Int

  def resetTime: Date
  
  def tw4jObj: twitter4j.RateLimitStatus
}