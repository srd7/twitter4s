package twitter4s

object twitter4s {
  // ResponseList
  implicit def responseListWrapperS2Buffer[T](tw4sList: ResponseList[T]) = tw4sList.listAsScala
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = internal.json.ResponseListImpl(tw4jList)
  
  // PagableResponseList
  implicit def pagableResponseListWrapperS2Buffer[T](tw4sPList: PagableResponseList[T]) = tw4sPList.listAsScala
  implicit def pagableResponseListWrapperJ2S[T <: twitter4j.TwitterResponse](tw4jPList: twitter4j.PagableResponseList[T]) = internal.json.PagableResponseListImpl(tw4jPList)
  
  // Trends
  implicit def trendsWrapperS2Array(tw4sTrends: Trends) = tw4sTrends.trends
  implicit def trendsWrapperJ2S(tw4jTrends: twitter4j.Trends) = internal.json.TrendsImpl(tw4jTrends)
}