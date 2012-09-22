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
import api._

trait TwitterAPIs extends AnyRef
with AccountMethods
with BlockMethods
with DirectMessageMethods
with FavoriteMethods
with FriendsFollowersMethods
with FriendshipMethods
with GeoMethods
with HelpMethods
with LegalResources
with ListMembersMethods
with ListMethods
with LocalTrendsMethods
with NewTwitterMethods
with NotificationMethods
with SavedSearchesMethods
with SearchMethods
with SpamReportingMethods
with StatusMethods
with TimelineMethods
with TrendsMethods