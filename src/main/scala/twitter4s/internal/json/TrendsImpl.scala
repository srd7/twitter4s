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
import twitter4s.Trends
import twitter4j.Trend
import twitter4j.RateLimitStatus
import twitter4j.Location
import java.util.Date
import twitter4s.AccessLevel

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class TrendsImpl(twt4jTrends: twitter4j.Trends) extends Trends {
  type Tw4jResponse = twitter4j.Trends
  
  def trends = twt4jTrends.getTrends()

  def location = twt4jTrends.getLocation()

  def asOf = twt4jTrends.getAsOf()

  def trendAt = twt4jTrends.getTrendAt()

  def rateLimitStatus = RateLimitStatusImpl(twt4jTrends.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jTrends.getAccessLevel())

  def tw4jObj = twt4jTrends

  def apply(idx: Int) = trends(idx)
}