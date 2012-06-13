package twitter4s.api

trait LegalResources {
  // TODO コメント
	def getTermsOfService: String
	
	def getPrivacyPolicy: String
}