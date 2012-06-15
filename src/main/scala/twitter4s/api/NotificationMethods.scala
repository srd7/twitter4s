package twitter4s.api

import twitter4j.User

trait NotificationMethods {
  /**
   * Enables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br/>This method calls twitter4j.Twitter.enableNotification(screenName) or enableNotification(userId)
   * <br>enableNotification calls http://api.twitter.com/1/notifications/follow.json
   *
   * @param screenName (optional) Specifies the screen name of the user to follow with device updates.
   * @param userId (optional) Specifies the ID of the user to follow with device updates.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/follow">POST notifications/follow | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def enableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User

  /**
   * Disables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br/>This method calls twitter4j.Twitter.disableNotification(screenName) or disableNotification(userId).
   * <br/>disableNotification calls http://api.twitter.com/1/notifications/leave.json
   *
   * @param screenName (optional) Specifies the screen name of the user to disable device notifications.
   * @param userId (optional) Specifies the ID of the user to disable device notifications.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/leave">POST notifications/leave | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def disableNotification(screenName: Option[String] = None, userId: Option[Long] = None): User
}