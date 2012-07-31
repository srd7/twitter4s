package twitter4s
import java.net.URI

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait UserList extends TwitterResponse with Ordered[UserList] {
  def id: Int

  def name: String

  def fullName: String

  def slug: String

  def description: String

  def subscriberCount: Int

  def memberCount: Int

  def uri: URI

  def isPublic: Boolean

  def user: User

  def isFollowing: Boolean
}