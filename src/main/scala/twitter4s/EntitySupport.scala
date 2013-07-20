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
import twitter4j.URLEntity
import twitter4j.MediaEntity
import twitter4j.HashtagEntity
import twitter4j.UserMentionEntity

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait EntitySupport {
  def userMentionEntities: Array[UserMentionEntity]
  
  def urlEntities: Array[URLEntity]
  
  def hashtagEntities: Array[HashtagEntity]
  
  def mediaEntities: Array[MediaEntity]
  
  // TODO Twitter4j 3.0.4対応時
//  def symbolEntities: Array[SymbolEntity]
}