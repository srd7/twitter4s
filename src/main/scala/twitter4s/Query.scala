package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Query {
  def apply(query: String = null) = {
    Option(query) match {
      case Some(query) => new twitter4j.Query(query)
      case None => new twitter4j.Query()
    }
  }
}