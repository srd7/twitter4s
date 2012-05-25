package twitter4s.api

import twitter4j.Query
import twitter4j.QueryResult

trait SearchMethods {
	def search(query: Query): QueryResult
}