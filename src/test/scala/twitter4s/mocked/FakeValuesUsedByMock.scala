package twitter4s.mocked

import java.util
import java.util.Date
import twitter4j._
import java.net.URL

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
    def getId: Long = ???
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
    def getRetweetCount: Long = ???
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
  }
}
