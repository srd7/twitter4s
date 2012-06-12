package twitter4s.api

import twitter4j.Status
import twitter4j.StatusUpdate
import twitter4s.ResponseList
import twitter4j.User
import twitter4j.Paging
import twitter4j.IDs

trait StatusMethods {
	def showStatus(id: Long): Status
	
	def updateStatus(status: Option[String] = None, latestStatus: Option[StatusUpdate] = None): Status
	
	def destroyStatus(statusId: Long): Status
	
	def retweetStatus(statusId: Long): Status
	
	def getRetweets(statusId: Long): Status
	
	def getRetweetedBy(statusId: Long, paging: Option[Paging] = None): ResponseList[User]
	
	def getRetweetedByIDs(statusId: Long, paging: Option[Paging] = None): IDs
}