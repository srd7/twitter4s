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
object Paging {
  /**
   * factory method for twitter4j.Paging class.
   * 
   * @since Twitter4S 1.0.0
   */
  def apply(
      page:java.lang.Integer = null,
      count:java.lang.Integer = null,
      sinceId:java.lang.Long = null,
      maxId: java.lang.Long = null) = {
    (Option(page), Option(count), Option(sinceId), Option(maxId)) match {
      case (None, None, None, None) => new twitter4j.Paging()
      case (Some(page), None, None, None) => new twitter4j.Paging(page)
      case (None, None, Some(sinceId), None) => new twitter4j.Paging(sinceId)
      case (Some(page), Some(count), None, None) => new twitter4j.Paging(page, count)
      case (Some(page), None, Some(sinceId), None) => new twitter4j.Paging(page, sinceId)
      case (Some(page), Some(count), Some(sinceId), None) => new twitter4j.Paging(page, count, sinceId)
      case (Some(page), Some(count), Some(sinceId), Some(maxId)) => new twitter4j.Paging(page, count, sinceId, maxId)
    }
  }
}