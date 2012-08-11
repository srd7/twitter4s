package twitter4s
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SavedSearch extends TwitterResponse with Ordered[SavedSearch] {

  def createdAt: Date
  
  def query: String
  
  def position: Int
  
  def name: String
  
  def id: Int
}