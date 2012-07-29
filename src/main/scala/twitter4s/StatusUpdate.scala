package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object StatusUpdate {
  /**
   * factory method for twitter4j.StatusUpdate class.
   * 
   * @since Twitter4S 1.0.0
   */
  def apply(status: String) = {
    new twitter4j.StatusUpdate(status)
  }
}