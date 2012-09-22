package twitter4s.conf
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
import java.util.Properties

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object PropertyConfiguration {
  /**
   * factory method for twitter4j.conf.PropertyConfiguration class
   * 
   * @param prop Properties object
   * @param treePath file path string
   * @return twitter4j.conf.PropertyConfiguration instance
   * @since Twitter4S 1.0.0
   */
  def apply(prop: Properties, treePath: String) = {
    new twitter4j.conf.PropertyConfiguration(prop, treePath)
  }
}