package twitter4s.api

import twitter4j.TwitterAPIConfiguration
import twitter4j.ResponseList

trait HelpMethods {
	def test: Boolean
	
	def getAPIConfiguration: TwitterAPIConfiguration
	
	def getLanguages: ResponseList[Language]
	
	trait Language {
	  def getName: String
	  def getCode: String
	  def getStatus: String
	}
}