package twitter4s.api

import twitter4j.Paging
import twitter4j.ResponseList
import twitter4j.DirectMessage

trait DirectMessageMethods {
	def getDirectMessages(paging: Option[Paging] = None): ResponseList[DirectMessage]
	
	def getSentDirectMessages(paging: Option[Paging] = None): ResponseList[DirectMessage]
	
	def sendDirectMessage(screenName: Option[String] = None, userId:Option[Long] = None, text: String): DirectMessage
	
	def destroyDirectMessage(id: Long): DirectMessage
	
	def showDirectMessage(id: Long): DirectMessage
}