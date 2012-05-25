package twitter4s.api

import twitter4j.User

trait NotificationMethods {
	def enableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User
	
	def disableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User
}