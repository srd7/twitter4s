package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait IDs extends TwitterResponse with CursorSupport {
  /**
   * get id array.
   * 
   * @return id array
   * @since Twitter4S 1.0.0
   */
  def ids: Array[Long]
  
  /**
   * @since Twitter4S 1.0.0
   */
  def apply(idx: Int): Long
}