package twitter4s.api

import twitter4j.GeoQuery
import twitter4s.ResponseList
import twitter4j.Place
import twitter4j.GeoLocation
import twitter4j.SimilarPlaces

trait GeoMethods {
	def searchPlaces(query: GeoQuery): ResponseList[Place]
	
	def getSimilarPlaces(location: GeoLocation, name: String, containedWithin: String, streetAddress: String): SimilarPlaces
	
	def reverseGeoCode(query: GeoQuery): ResponseList[Place]
	
	def getGeoDetails(id: String): Place
	
	def createPlace(name: String, containedWithin: String, token: String, location: GeoLocation, streetAddress: String): Place
}