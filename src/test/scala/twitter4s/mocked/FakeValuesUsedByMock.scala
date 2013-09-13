package twitter4s.mocked

import java.util

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

}
