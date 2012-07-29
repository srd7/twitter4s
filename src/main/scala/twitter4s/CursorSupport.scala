package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait CursorSupport {
  def START = 1L
  
  /**
   * return response has previous cursor.
   * 
   * @return has(true) or not has(false)
   * @since Twitter4S 1.0.0
   */
  def hasPrevious: Boolean
  
  /**
   * get previous cursor.
   * 
   * @return cursor number.
   * @since Twitter4S 1.0.0
   */
  def previousCursor: Long
  
  /**
   * return response has next cursor.
   * 
   * @return has(true) or not has(false)
   * @since Twitter4S 1.0.0
   */
  def hasNext: Boolean
  
  /**
   * get next cursor.
   * 
   * @return cursor number.
   * @since Twitter4S 1.0.0
   */
  def nextCursor: Long
}