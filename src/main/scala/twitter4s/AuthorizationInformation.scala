package twitter4s
import twitter4j.auth.Authorization
import twitter4j.auth.AccessToken

object AuthorizationInformation {
  type SpecificType = Either[Authorization, AccessToken]
  
  def isSpecifiedBy(auth: Authorization) = {
    require(auth != null)
    Left(auth)
  }
  
  def isSpecifiedBy(accessToken: AccessToken) = {
    require(accessToken != null)
    Right(accessToken)
  }
}