package twitter4s.api

import java.util.Date
import twitter4j.ResponseList
import twitter4j.Trends

trait TrendsMethods {
	def getDailyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends]
	
	def getWeeklyTrends(date: Option[Date] = None, excludeHashTags: Option[Boolean] = None): ResponseList[Trends]
}