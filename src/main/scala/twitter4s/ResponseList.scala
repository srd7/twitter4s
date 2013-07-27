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
import scala.collection.mutable.Buffer

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait ResponseList[T] extends TwitterResponse {
  /**
   * get converted scala list from twitter4j.ResponseList
   * 
   * @return converted list
   * @since Twitter4S 1.0.0
   */
  def listAsScala:Buffer[T]
  
  /**
   * apply method for get item specified by index.
   * 
   * @param idx index of list.
   * @return item specified by index
   * @since Twitter4S 1.0.0
   */
  def apply(idx: Int):T
}