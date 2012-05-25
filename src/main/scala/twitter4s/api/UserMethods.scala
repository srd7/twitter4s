package twitter4s.api

import twitter4j.User
import twitter4j.ResponseList
import twitter4j.Query
import twitter4j.Category
import twitter4j.ProfileImage

trait UserMethods {
	def showUser(screenName: Option[String] = None, userId: Option[Long] = None): User
	
	def lookupUsers(screenNames: Option[Array[String]] = None, ids: Option[Array[Long]] = None): ResponseList[User]
	
	def searchUsers(query: Query, page: Int): ResponseList[User]
	
	def getSuggestedUserCategories: ResponseList[Category]
	
	def getUserSuggestions(categorySlug: String): ResponseList[User]
	
	def getMemberSuggestions(categorySlug: String): ResponseList[User]
	
	def getProfileImage(screenName: String, size: ProfileImage.ImageSize): ProfileImage
}