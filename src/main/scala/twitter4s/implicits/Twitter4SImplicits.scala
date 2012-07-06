package twitter4s.implicits
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter4SImplicits {
  // ResponseList
  implicit def responseListWrapperS2Buffer[T](tw4sList: twitter4s.ResponseList[T]) = tw4sList.listAsScala
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = twitter4s.internal.json.ResponseListImpl(tw4jList)
  // not required?
  //implicit def responseListWrapperS2J[T](tw4sList: twitter4s.ResponseList[T]) = tw4sList.twt4jResponseList
  
  // PagableResponseList
  implicit def pagableResponseListWrapperS2Buffer[T](tw4sPList: twitter4s.PagableResponseList[T]) = tw4sPList.listAsScala
  implicit def pagableResponseListWrapperJ2S[T <: twitter4j.TwitterResponse](tw4jPList: twitter4j.PagableResponseList[T]) = twitter4s.internal.json.PagableResponseListImpl(tw4jPList)
}