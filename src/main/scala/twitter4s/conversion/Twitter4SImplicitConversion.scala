package twitter4s.conversion

/*
 * Copyright (C) 2014 Shinsuke Abe
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
import twitter4s.IDs
import twitter4s.PagableResponseList
import twitter4s.ResponseList
import twitter4s.Trends

// Declare implicit conversion
import scala.language.implicitConversions

/**
 * @author mao.instantlife at gmail.com
 */
trait Twitter4SImplicitConversion {
  // AccountTotals
  implicit def AccountTotalsWrapperJ2S(tw4jAccountTotals: twitter4j.AccountTotals) = twitter4s.internal.json.AccountTotalsImpl(tw4jAccountTotals)

  // AccountSettings
  implicit def AccountSettingsWrapperJ2S(twt4jAccountSettings: twitter4j.AccountSettings) = twitter4s.internal.json.AccountSettingsImpl(twt4jAccountSettings)

  // DirectMessage
  implicit def DirectMessageWrapperJ2S(twt4jDirectMessage: twitter4j.DirectMessage) = twitter4s.internal.json.DirectMessageImpl(twt4jDirectMessage)

  // Place
  implicit def PlaceWrapperJ2S(twt4jPlace: twitter4j.Place) = twitter4s.internal.json.PlaceImpl(twt4jPlace)

  // QueryResult
  implicit def QueryResultWrapperJ2S(twt4jQueryResult: twitter4j.QueryResult) = twitter4s.internal.json.QueryResultImpl(twt4jQueryResult)

  // RateLimitStatus
  implicit def RateLimitStatusWrapperJ2S(tw4jRateLimitStatus: twitter4j.RateLimitStatus) = twitter4s.internal.json.RateLimitStatusImpl(tw4jRateLimitStatus)

  // Relationship
  implicit def RelationshipWrapperJ2S(tw4jRelationship: twitter4j.Relationship) = twitter4s.internal.json.RelationshipImpl(tw4jRelationship)

  // SavedSearch
  implicit def SavedSearchWrapperJ2S(tw4jSavedSearch: twitter4j.SavedSearch) = twitter4s.internal.json.SavedSearchImpl(tw4jSavedSearch)

  // TwitterAPIConfiguration
  implicit def TwitterAPIConfigurationWrapperJ2S(tw4jApiConfiguration: twitter4j.TwitterAPIConfiguration) = twitter4s.internal.json.TwitterAPIConfigurationImpl(tw4jApiConfiguration)

  // User
  implicit def UserWrapperJ2S(tw4jUser: twitter4j.User) = twitter4s.internal.json.UserImpl(tw4jUser)

  // UserList
  implicit def UserListWrapperJ2S(tw4jUserList: twitter4j.UserList) = twitter4s.internal.json.UserListImpl(tw4jUserList)

  // Status
  implicit def StatusWrapperJ2S(tw4jStatus: twitter4j.Status) = twitter4s.internal.json.StatusImpl(tw4jStatus)

  // IDs
  implicit def idsWrapperS2Array(tw4sIDs: IDs) = tw4sIDs.ids
  implicit def idsWrapperJ2S(tw4jIDs: twitter4j.IDs) = twitter4s.internal.json.IDsImpl(tw4jIDs)

  // Trends
  implicit def trendsWrapperS2Array(tw4sTrends: Trends) = tw4sTrends.trends
  implicit def trendsWrapperJ2S(tw4jTrends: twitter4j.Trends) = twitter4s.internal.json.TrendsImpl(tw4jTrends)

  // ResponseList
  implicit def responseListWrapperS2Buffer[T](tw4sList: ResponseList[T]) = tw4sList.listAsScala
  implicit def responseListWrapperJ2S[T](tw4jList: twitter4j.ResponseList[T]) = twitter4s.internal.json.ResponseListImpl(tw4jList)

  // PagableResponseList
  implicit def pagableResponseListWrapperS2Buffer[T](tw4sPList: PagableResponseList[T]) = tw4sPList.listAsScala
  implicit def pagableResponseListWrapperJ2S[T <: twitter4j.TwitterResponse](tw4jPList: twitter4j.PagableResponseList[T]) = twitter4s.internal.json.PagableResponseListImpl(tw4jPList)

  // OEmved
  implicit def oEmbedWarpperJ2S[T](tw4jOEmbed: twitter4j.OEmbed) = twitter4s.internal.json.OEmbedImpl(tw4jOEmbed)
}
