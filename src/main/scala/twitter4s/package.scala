package twitter4s
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object twitter4s {
  // This object has implicit type cast methods.
  // In client code, if type "import twitter4s._" import bellow type cast methods.
  
  // AccountTotals
  implicit def AccountTotalsWrapperJ2S(tw4jAccountTotals: twitter4j.AccountTotals) = internal.json.AccountTotalsImpl(tw4jAccountTotals)
  
  // AccountSettings
  implicit def AccountSettingsWrapperJ2S(twt4jAccountSettings: twitter4j.AccountSettings) = internal.json.AccountSettingsImpl(twt4jAccountSettings)
  
  // DirectMessage
  implicit def DirectMessageWrapperJ2S(twt4jDirectMessage: twitter4j.DirectMessage) = internal.json.DirectMessageImpl(twt4jDirectMessage)
  
  // Place
  implicit def PlaceWrapperJ2S(twt4jPlace: twitter4j.Place) = internal.json.PlaceImpl(twt4jPlace)
  
  // QueryResult
  implicit def QueryResultWrapperJ2S(twt4jQueryResult: twitter4j.QueryResult) = internal.json.QueryResultImpl(twt4jQueryResult)
  
  // RateLimitStatus
  implicit def RateLimitStatusWrapperJ2S(tw4jRateLimitStatus: twitter4j.RateLimitStatus) = internal.json.RateLimitStatusImpl(tw4jRateLimitStatus)
  
  // Relationship
  implicit def RelationshipWrapperJ2S(tw4jRelationship: twitter4j.Relationship) = internal.json.RelationshipImpl(tw4jRelationship)
  
  // RelatedResults
  implicit def RelatedResultsWrapperJ2S(tw4jRelatedResults: twitter4j.RelatedResults) = internal.json.RelatedResultsImpl(tw4jRelatedResults)
  
  // SavedSearch
  implicit def SavedSearchWrapperJ2S(tw4jSavedSearch: twitter4j.SavedSearch) = internal.json.SavedSearchImpl(tw4jSavedSearch)
  
  // TwitterAPIConfiguration
  implicit def TwitterAPIConfigurationWrapperJ2S(tw4jApiConfiguration: twitter4j.TwitterAPIConfiguration) = internal.json.TwitterAPIConfigurationImpl(tw4jApiConfiguration)
  
  // User
  implicit def UserWrapperJ2S(tw4jUser: twitter4j.User) = internal.json.UserImpl(tw4jUser)
  
  // UserList
  implicit def UserListWrapperJ2S(tw4jUserList: twitter4j.UserList) = internal.json.UserListImpl(tw4jUserList)
  
  // Status
  implicit def StatusWrapperJ2S(tw4jStatus: twitter4j.Status) = internal.json.StatusImpl(tw4jStatus)
  
  // IDs
  implicit def idsWrapperS2Array(tw4sIDs: IDs) = tw4sIDs.ids
  implicit def idsWrapperJ2S(tw4jIDs: twitter4j.IDs) = internal.json.IDsImpl(tw4jIDs)
  
  // SimilarPlaces
  implicit def similarPlacesWrapperS2Buffer(tw4sSimilarPlaces: SimilarPlaces) = tw4sSimilarPlaces.listAsScala
  implicit def similarPlacesWrapperS2J(tw4jSimilarPlaces: twitter4j.SimilarPlaces) = internal.json.SimilarPlacesImpl(tw4jSimilarPlaces)
  
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