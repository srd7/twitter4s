package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Paging {
  /**
   * factory method for twitter4j.Paging class.
   * 
   * @since Twitter4S 1.0.0
   */
  def apply(
      page:java.lang.Integer = null,
      count:java.lang.Integer = null,
      sinceId:java.lang.Long = null,
      maxId: java.lang.Long = null) = {
    (Option(page), Option(count), Option(sinceId), Option(maxId)) match {
      case (None, None, None, None) => new twitter4j.Paging()
      case (Some(page), None, None, None) => new twitter4j.Paging(page)
      case (None, None, Some(sinceId), None) => new twitter4j.Paging(sinceId)
      case (Some(page), Some(count), None, None) => new twitter4j.Paging(page, count)
      case (Some(page), None, Some(sinceId), None) => new twitter4j.Paging(page, sinceId)
      case (Some(page), Some(count), Some(sinceId), None) => new twitter4j.Paging(page, count, sinceId)
      case (Some(page), Some(count), Some(sinceId), Some(maxId)) => new twitter4j.Paging(page, count, sinceId, maxId)
    }
  }
}