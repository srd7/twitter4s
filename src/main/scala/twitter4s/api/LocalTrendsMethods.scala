package twitter4s.api

import twitter4j.ResponseList
import twitter4j.Location
import twitter4j.GeoLocation
import twitter4j.Trends

trait LocalTrendsMethods {
	def getAvailableTrends(location: Option[GeoLocation] = None): ResponseList[Location]
	
	def getLocationTrends(woeid: Int): Trends
}