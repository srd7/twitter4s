package twitter4s.implicits

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter4SImplicits {
  // ResponseList
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = twitter4s.ResponseList(tw4jList)
  implicit def responseListWrapperS2J[T](tw4sList: twitter4s.ResponseList[T]) = tw4sList.twt4jResponseList
}