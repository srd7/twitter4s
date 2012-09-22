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
trait Place extends TwitterResponse with Ordered[Place] {
  def name: String
  
  def streetAddress: String
  
  def countryCode: String
  
  def id: String
  
  def country: String
  
  def placeType: String
  
  def url: String
  
  def fullName: String
  
  def boundingBoxType: String
  
  def boundingBoxCoordinates: Array[Array[twitter4j.GeoLocation]]
  
  def geometryType: String
  
  def geometryCoordinates: Array[Array[twitter4j.GeoLocation]]
  
  def containedWithIn: Array[Place]
}