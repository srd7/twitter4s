package twitter4s.auth

object RequestToken {
  def apply(token: String, tokenSecret: String) = {
    new twitter4j.auth.RequestToken(token, tokenSecret)
  }
}