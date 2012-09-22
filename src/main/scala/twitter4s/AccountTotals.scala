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
trait AccountTotals extends TwitterResponse {
  /**
   * get status update count.
   * 
   * @return update count.
   * @since Twitter4S 1.0.0
   */
  def updates: Int

  /**
   * get users followers count.
   * 
   * @return followers count.
   * @since Twitter4S 1.0.0
   */
  def followers: Int

  /**
   * get user favorites tweet count
   * 
   * @return favorites count
   * @since Twitter4S 1.0.0
   */
  def favorites: Int

  /**
   * get user's friend count
   * 
   * @return friends count
   * @since Twitter4S 1.0.0
   */
  def friends: Int
}