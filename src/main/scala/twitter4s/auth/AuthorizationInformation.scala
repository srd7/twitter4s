package twitter4s
import twitter4j.auth.AccessToken

object AuthorizationInformation {
  type SpecificType = Either[twitter4j.auth.Authorization, AccessToken]
  
  def isSpecifiedBy(auth: twitter4j.auth.Authorization) = {
    require(auth != null)
    Left(auth)
  }
  
  def isSpecifiedBy(accessToken: AccessToken) = {
    require(accessToken != null)
    Right(accessToken)
  }
}