package twitter4s.mocked

import java.util
import java.util.Date
import twitter4j._
import java.net.{URI, URL}
import twitter4j.MediaEntity.Size

object FakeValuesUsedByMock {
  def responseList[T]:twitter4j.ResponseList[T] = new twitter4j.ResponseList[T] {
    def getAccessLevel: Int = 3
    def size(): Int = 1
    def isEmpty: Boolean = ???
    def contains(o: scala.Any): Boolean = ???
    def iterator(): util.Iterator[T] = ???
    def toArray: Array[AnyRef] = ???
    def toArray[T](a: Array[T]): Array[T] = ???
    def add(e: T): Boolean = ???
    def remove(o: scala.Any): Boolean = ???
    def containsAll(c: util.Collection[_]): Boolean = ???
    def addAll(c: util.Collection[_ <: T]): Boolean = ???
    def addAll(index: Int, c: util.Collection[_ <: T]): Boolean = ???
    def removeAll(c: util.Collection[_]): Boolean = ???
    def retainAll(c: util.Collection[_]): Boolean = ???
    def clear() {}
    def get(index: Int): T = ???
    def set(index: Int, element: T): T = ???
    def add(index: Int, element: T) {}
    def remove(index: Int): T = ???
    def indexOf(o: scala.Any): Int = ???
    def lastIndexOf(o: scala.Any): Int = ???
    def listIterator(): util.ListIterator[T] = ???
    def listIterator(index: Int): util.ListIterator[T] = ???
    def subList(fromIndex: Int, toIndex: Int): util.List[T] = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def toArray[T](x: Array[T with Object]): Array[T with Object] = ???
  }

  def pagableResponseList[T <: twitter4j.TwitterResponse]:twitter4j.PagableResponseList[T] = new twitter4j.PagableResponseList[T] {
    def getAccessLevel: Int = 1
    def size(): Int = 50
    def isEmpty: Boolean = ???
    def contains(o: scala.Any): Boolean = ???
    def iterator(): util.Iterator[T] = ???
    def toArray: Array[AnyRef] = ???
    def toArray[T](a: Array[T]): Array[T] = ???
    def add(e: T): Boolean = ???
    def remove(o: scala.Any): Boolean = ???
    def containsAll(c: util.Collection[_]): Boolean = ???
    def addAll(c: util.Collection[_ <: T]): Boolean = ???
    def addAll(index: Int, c: util.Collection[_ <: T]): Boolean = ???
    def removeAll(c: util.Collection[_]): Boolean = ???
    def retainAll(c: util.Collection[_]): Boolean = ???
    def clear() {}
    def get(index: Int): T = ???
    def set(index: Int, element: T): T = ???
    def add(index: Int, element: T) {}
    def remove(index: Int): T = ???
    def indexOf(o: scala.Any): Int = ???
    def lastIndexOf(o: scala.Any): Int = ???
    def listIterator(): util.ListIterator[T] = ???
    def listIterator(index: Int): util.ListIterator[T] = ???
    def subList(fromIndex: Int, toIndex: Int): util.List[T] = ???
    def hasPrevious: Boolean = true
    def getPreviousCursor: Long = ???
    def hasNext: Boolean = true
    def getNextCursor: Long = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def toArray[T](x: Array[T with Object]): Array[T with Object] = ???
  }

  def ids = new twitter4j.IDs {
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = 3
    def getIDs: Array[Long] = ???
    def hasPrevious: Boolean = true
    def getPreviousCursor: Long = ???
    def hasNext: Boolean = true
    def getNextCursor: Long = ???
  }

  val userName = "fake_return_user"
  val friendName = "fake_friend_user"
  def user = new twitter4j.User {
    def getAccessLevel: Int = ???
    def getBiggerProfileImageURL: String = ???
    def isProtected: Boolean = ???
    def isTranslator: Boolean = ???
    def getProfileLinkColor: String = ???
    def getProfileImageURL: String = ???
    def getProfileBackgroundImageUrl: String = ???
    def getProfileBannerIPadRetinaURL: String = ???
    def getMiniProfileImageURLHttps: String = ???
    def getProfileSidebarFillColor: String = ???
    def getScreenName: String = userName
    def getListedCount: Int = ???
    def getOriginalProfileImageURLHttps: String = ???
    def isProfileBackgroundTiled: Boolean = ???
    def isProfileUseBackgroundImage: Boolean = ???
    def getUtcOffset: Int = ???
    def getProfileSidebarBorderColor: String = ???
    def isContributorsEnabled: Boolean = ???
    def getTimeZone: String = ???
    def getName: String = ???
    def getCreatedAt: Date = ???
    def getDescriptionURLEntities: Array[URLEntity] = ???
    def getURL: String = ???
    def getLang: String = ???
    def getId: Long = 200
    def getProfileImageURLHttps: String = ???
    def getStatus: twitter4j.Status = ???
    def getMiniProfileImageURL: String = ???
    def getDescription: String = ???
    def getProfileBannerRetinaURL: String = ???
    def getFollowersCount: Int = ???
    def isGeoEnabled: Boolean = ???
    def getURLEntity: URLEntity = ???
    def getProfileBackgroundColor: String = ???
    def isFollowRequestSent: Boolean = ???
    def getProfileBannerMobileURL: String = ???
    def compareTo(o: twitter4j.User): Int = ???
    def getFavouritesCount: Int = ???
    def getProfileBannerURL: String = ???
    def getProfileBackgroundImageUrlHttps: String = ???
    def getProfileBackgroundImageURL: String = ???
    def isVerified: Boolean = ???
    def getProfileImageUrlHttps: URL = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getLocation: String = ???
    def getFriendsCount: Int = ???
    def getProfileBannerMobileRetinaURL: String = ???
    def getProfileTextColor: String = ???
    def getStatusesCount: Int = ???
    def isShowAllInlineMedia: Boolean = ???
    def getProfileBannerIPadURL: String = ???
    def getOriginalProfileImageURL: String = ???
    def getBiggerProfileImageURLHttps: String = ???
  }

  def relationShip  = new twitter4j.Relationship {
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = 1
    def getSourceUserId: Long = ???
    def getTargetUserId: Long = ???
    def isSourceBlockingTarget: Boolean = ???
    def getSourceUserScreenName: String = userName
    def getTargetUserScreenName: String = friendName
    def isSourceFollowingTarget: Boolean = ???
    def isTargetFollowingSource: Boolean = ???
    def isSourceFollowedByTarget: Boolean = ???
    def isTargetFollowedBySource: Boolean = ???
    def isSourceNotificationsEnabled: Boolean = ???
    def isSourceWantRetweets: Boolean = ???
    def canSourceDm: Boolean = ???
  }

  val statusText = "returned by mocking object"
  def status = new twitter4j.Status {
    def getAccessLevel: Int = ???
    def getHashtagEntities: Array[HashtagEntity] = ???
    def getURLEntities: Array[URLEntity] = ???
    def getPlace: twitter4j.Place = ???
    def isRetweet: Boolean = ???
    def isFavorited: Boolean = ???
    def getCreatedAt: Date = ???
    def getUser: twitter4j.User = ???
    def getContributors: Array[Long] = ???
    def getRetweetedStatus: twitter4j.Status = ???
    def getInReplyToScreenName: String = ???
    def isTruncated: Boolean = ???
    def getId: Long = ???
    def getCurrentUserRetweetId: Long = ???
    def isPossiblySensitive: Boolean = ???
    def getRetweetCount: Int = ???
    def compareTo(o: twitter4j.Status): Int = ???
    def getMediaEntities: Array[MediaEntity] = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getGeoLocation: GeoLocation = ???
    def getInReplyToUserId: Long = ???
    def getSource: String = ???
    def getText: String = statusText
    def getInReplyToStatusId: Long = ???
    def getUserMentionEntities: Array[UserMentionEntity] = ???
    def isRetweetedByMe: Boolean = ???
    def isRetweeted: Boolean = ???
    def getFavoriteCount: Int = ???
    def getIsoLanguageCode: String = ???
    def getSymbolEntities: Array[SymbolEntity] = ???
  }

  def directMessage = new twitter4j.DirectMessage(){
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = ???
    def getUserMentionEntities: Array[twitter4j.UserMentionEntity] = ???
    def getURLEntities: Array[twitter4j.URLEntity] = ???
    def getHashtagEntities: Array[twitter4j.HashtagEntity] = ???
    def getMediaEntities: Array[twitter4j.MediaEntity] = ???
    def getId: Long = ???
    def getText: String = "fake direct message text"
    def getSenderId: Long = ???
    def getRecipientId: Long = ???
    def getCreatedAt: Date = ???
    def getSenderScreenName: String = userName
    def getRecipientScreenName: String = friendName
    def getSender: twitter4j.User = ???
    def getRecipient: twitter4j.User = ???
    def getSymbolEntities: Array[SymbolEntity] = ???
  }

  def accountSettings = new twitter4j.AccountSettings {
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = 2
    def isSleepTimeEnabled: Boolean = ???
    def getSleepStartTime: String = ???
    def getSleepEndTime: String = ???
    def getTrendLocations: Array[Location] = ???
    def isGeoEnabled: Boolean = ???
    def getTimeZone: TimeZone = ???
    def getLanguage: String = ???
    def isDiscoverableByEmail: Boolean = ???
    def isAlwaysUseHttps: Boolean = ???
    def getScreenName: String = ???
  }

  val listName = "fake list name"
  def userList = new twitter4j.UserList {
    def getAccessLevel: Int = 2
    def getMemberCount: Int = ???
    def getSubscriberCount: Int = ???
    def getName: String = ???
    def getUser: twitter4j.User = ???
    def getSlug: String = ???
    def isPublic: Boolean = ???
    def getId: Int = 100
    def getDescription: String = ???
    def getURI: URI = ???
    def compareTo(o: twitter4j.UserList): Int = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def isFollowing: Boolean = ???
    def getFullName: String = listName
  }

  val placeName = "fake place"
  def place = new twitter4j.Place{
    def getName: String = ???
    def getStreetAddress: String = ???
    def getCountryCode: String = ???
    def getId: String = ???
    def getCountry: String = ???
    def getPlaceType: String = ???
    def getURL: String = ???
    def getFullName: String = placeName
    def getBoundingBoxType: String = ???
    def getBoundingBoxCoordinates: Array[Array[twitter4j.GeoLocation]] = ???
    def getGeometryType: String = ???
    def getGeometryCoordinates: Array[Array[twitter4j.GeoLocation]] = ???
    def getContainedWithIn: Array[twitter4j.Place] = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = ???
    def compareTo(o: twitter4j.Place): Int = ???
  }

  val searchQueryString = "fake search query"
  def savedSearch = new twitter4j.SavedSearch {
    def getPosition: Int = ???
    def getAccessLevel: Int = ???
    def getName: String = ???
    def getCreatedAt: Date = ???
    def getQuery: String = searchQueryString
    def getId: Int = ???
    def compareTo(o: twitter4j.SavedSearch): Int = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
  }

  def trends = new twitter4j.Trends{
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getAccessLevel: Int = 1
    def getTrends: Array[twitter4j.Trend] = ???
    def getLocation: twitter4j.Location = ???
    def getAsOf: Date = ???
    def getTrendAt: Date = ???
    def compareTo(o: twitter4j.Trends): Int = ???
  }

  def apiConfiguration = new twitter4j.TwitterAPIConfiguration {
    def getAccessLevel: Int = 3 // Read Write DM
    def getMaxMediaPerUpload: Int = ???
    def getPhotoSizeLimit: Int = ???
    def getShortURLLengthHttps: Int = ???
    def getRateLimitStatus: twitter4j.RateLimitStatus = ???
    def getNonUsernamePaths: Array[String] = ???
    def getShortURLLength: Int = ???
    def getPhotoSizes: util.Map[Integer, Size] = ???
    def getCharactersReservedPerMedia: Int = ???
  }

  val statusUrl = "https://www.test.url"
  def oembed = new twitter4j.OEmbed {
    def getAccessLevel: Int = ???
    def getAuthorName: String = ???
    def getCacheAge: Long = ???
    def getWidth: Int = ???
    def getURL: String = statusUrl
    def getAuthorURL: String = ???
    def getHtml: String = ???
    def getVersion: String = ???
    def getRateLimitStatus: RateLimitStatus = ???
  }
}
