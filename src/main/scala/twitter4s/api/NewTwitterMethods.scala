package twitter4s.api

import twitter4j.RelatedResults

trait NewTwitterMethods {
	def getRelatedResults(statusId: Long): RelatedResults
}