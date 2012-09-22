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
import twitter4j.Trend
import twitter4j.Location
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait Trends extends TwitterResponse {
  /**
   * Array of Trend object from twitter api response.
   * 
   * @return array of trend
   * @since Twitter4S 1.0.0
   */
  def trends: Array[Trend]

  /**
   * Location object from twitter api response.
   * 
   * @return location object.
   * @since Twitter4S 1.0.0
   */
  def location: Location

  def asOf: Date

  def trendAt: Date

  def apply(idx: Int):Trend
}