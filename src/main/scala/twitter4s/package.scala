package twitter4s

object twitter4s {
  // AccountTotals
  implicit def AccountTotalsWrapperJ2S(tw4jAccountTotals: twitter4j.AccountTotals) = internal.json.AccountTotalsImpl(tw4jAccountTotals)
  
  // AccountSettings
  implicit def AccountSettingsWrapperJ2S(twt4jAccountSettings: twitter4j.AccountSettings) = internal.json.AccountSettingsImpl(twt4jAccountSettings)
  
  // RateLimitStatus
  implicit def RateLimitStatusWrapperJ2S(tw4jRateLimitStatus: twitter4j.RateLimitStatus) = internal.json.RateLimitStatusImpl(tw4jRateLimitStatus)
  
  // User
  implicit def UserWrapperJ2S(tw4jUser: twitter4j.User) = internal.json.UserImpl(tw4jUser)
  
  // Status
  implicit def StatusWrapperJ2S(tw4jStatus: twitter4j.Status) = internal.json.StatusImpl(tw4jStatus)
  
  // Trends
  implicit def trendsWrapperS2Array(tw4sTrends: Trends) = tw4sTrends.trends
  implicit def trendsWrapperJ2S(tw4jTrends: twitter4j.Trends) = internal.json.TrendsImpl(tw4jTrends)
  
  // ResponseList
  implicit def responseListWrapperS2Buffer[T](tw4sList: ResponseList[T]) = tw4sList.listAsScala
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = internal.json.ResponseListImpl(tw4jList)
  
  // PagableResponseList
  implicit def pagableResponseListWrapperS2Buffer[T](tw4sPList: PagableResponseList[T]) = tw4sPList.listAsScala
  implicit def pagableResponseListWrapperJ2S[T <: twitter4j.TwitterResponse](tw4jPList: twitter4j.PagableResponseList[T]) = internal.json.PagableResponseListImpl(tw4jPList)
}