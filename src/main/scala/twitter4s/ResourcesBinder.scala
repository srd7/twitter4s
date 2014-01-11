package twitter4s

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
import twitter4s.api.impl.HelpResourcesImpl
import twitter4s.api.HelpResources

/**
 * @author mao.instantlife at gmail.com
 */
// TODO warining...
trait ResourcesBinder[ApiResourcesInterface, ApiResourcesInterfaceImpl <: ApiResourcesInterface] {
  def apply(self: Twitter): ApiResourcesInterface = self match {
    case resource: ApiResourcesInterface => resource
    case _ => bind(self)
  }

  def bind(self: Twitter): ApiResourcesInterfaceImpl
}

object HelpResourcesBinder extends ResourcesBinder[HelpResources, HelpResourcesImpl]{
  def bind(self: Twitter): HelpResourcesImpl = new Twitter(self.twitter4jObj) with HelpResourcesImpl
}