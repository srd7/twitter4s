package twitter4s

object OEmbedRequest {
  def apply(statusId: Long, url: String) = {
    new twitter4j.OEmbedRequest(statusId, url)
  }
}