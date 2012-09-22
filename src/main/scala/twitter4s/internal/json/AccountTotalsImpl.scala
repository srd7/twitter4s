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
import twitter4s.AccountTotals
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class AccountTotalsImpl(twt4jAccountTotals: twitter4j.AccountTotals) extends AccountTotals {
  type Tw4jResponse = twitter4j.AccountTotals
  
  def updates = twt4jAccountTotals.getUpdates()
  
  def followers = twt4jAccountTotals.getFollowers()
  
  def favorites = twt4jAccountTotals.getFavorites()
  
  def friends = twt4jAccountTotals.getFriends()
  
  def rateLimitStatus = RateLimitStatusImpl(twt4jAccountTotals.getRateLimitStatus())
  
  def accessLevel = AccessLevel(twt4jAccountTotals.getAccessLevel())
  
  def tw4jObj = twt4jAccountTotals
}