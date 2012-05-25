package twitter4s.api

import twitter4j.ResponseList
import twitter4j.SavedSearch

trait SavedSearchedMethods {
	def getSavedSearches: ResponseList[SavedSearch]
	
	def showSavedSearch(id: Int): SavedSearch
	
	def createSavedSearch(query: String): SavedSearch
	
	def destroySavedSearch(id: Int): SavedSearch
}