package twitter4s.api

import twitter4j.User

trait SpamReportingMethods {
	def reportSpam(userId: Option[Long] = None, screenName: Option[String] = None): User
}