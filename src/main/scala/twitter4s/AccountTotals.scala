package twitter4s

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait AccountTotals extends TwitterResponse {
  /**
   * get status update count.
   * 
   * @return update count.
   * @since Twitter4S 1.0.0
   */
  def updates: Int

  /**
   * get users followers count.
   * 
   * @return followers count.
   * @since Twitter4S 1.0.0
   */
  def followers: Int

  /**
   * get user favorites tweet count
   * 
   * @return favorites count
   * @since Twitter4S 1.0.0
   */
  def favorites: Int

  /**
   * get user's friend count
   * 
   * @return friends count
   * @since Twitter4S 1.0.0
   */
  def friends: Int
}