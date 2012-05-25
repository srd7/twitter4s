package twitter4s.api

trait LegalResources {
	def getTermsOfService: String
	
	def getPrivacyPolicy: String
}