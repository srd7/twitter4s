package twitter4s.implicits
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter4SImplicits {
  // ResponseList
  implicit def responseListWrapperS2Buffer[T](tw4sList: twitter4s.ResponseList[T]) = tw4sList.listAsScala
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = twitter4s.ResponseList(tw4jList)
  // not required?
  //implicit def responseListWrapperS2J[T](tw4sList: twitter4s.ResponseList[T]) = tw4sList.twt4jResponseList
}