package twitter4s.internal.json
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
import twitter4s.RelatedResults
import twitter4s.AccessLevel

// TODO コンパイルエラー対処 -> 未使用につき削除
case class RelatedResultsImpl(twt4jRelatedResults: twitter4j.RelatedResults) extends RelatedResults {
  type Tw4jResponse = twitter4j.RelatedResults

  def tweetsFromUser = ResponseListImpl(twt4jRelatedResults.getTweetsFromUser())

  def tweetsWithConversation = ResponseListImpl(twt4jRelatedResults.getTweetsWithConversation())

  def tweetsWithReply = ResponseListImpl(twt4jRelatedResults.getTweetsWithReply())

  def rateLimitStatus = RateLimitStatusImpl(twt4jRelatedResults.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jRelatedResults.getAccessLevel())

  def tw4jObj = twt4jRelatedResults

}