package twitter4s.api

import twitter4s.User

trait NotificationMethods {
  /**
   * Enables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br />This method calls twitter4j.Twitter.enableNotification.
   * <br />enableNotification calls http://api.twitter.com/1/notifications/follow.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param screenName (optional) Specifies the screen name of the user to follow with device updates.
   * @param userId (optional) Specifies the ID of the user to follow with device updates.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenName and userId are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/follow">POST notifications/follow | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def enableNotification(screenName: String = null, userId: java.lang.Long = null): User

  /**
   * Disables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br />This method calls twitter4j.Twitter.disableNotification
   * <br />disableNotification calls http://api.twitter.com/1/notifications/leave.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param screenName (optional) Specifies the screen name of the user to disable device notifications.
   * @param userId (optional) Specifies the ID of the user to disable device notifications.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of screenName and userId are not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/leave">POST notifications/leave | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def disableNotification(screenName: String = null, userId: java.lang.Long = null): User
}