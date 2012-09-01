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
  
  val MILES = twitter4j.Query.MILES
  val KILOMETERS = twitter4j.Query.KILOMETERS
  
  val MIXED = twitter4j.Query.MIXED
  val POPULAR = twitter4j.Query.POPULAR
  val RECENT = twitter4j.Query.RECENT
}