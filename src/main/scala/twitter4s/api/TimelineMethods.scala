package twitter4s.api

import twitter4j.Paging
import twitter4s.ResponseList
import twitter4j.Status

trait TimelineMethods {
	def getHomeTimeline(paging: Option[Paging] = None): ResponseList[Status]
	
	def getUserTimeline(screenName: Option[String] = None, userId: Option[Long] = None, paging: Option[Paging] = None): ResponseList[Status]
	
	def getMentions(paging: Option[Paging] = None): ResponseList[Status]
	
	def getRetweetedByMe(paging: Option[Paging] = None): ResponseList[Status]
	
	def getRetweetedToMe(paging: Option[Paging] = None): ResponseList[Status]
	
	def getRetweetedOfMe(paging: Option[Paging] = None): ResponseList[Status]
	
	def getRetweetedToUser(paging: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status]
	
	def getRetweetedByUser(pagind: Paging, screenName: Option[String] = None, userId: Option[Long] = None): ResponseList[Status]
}