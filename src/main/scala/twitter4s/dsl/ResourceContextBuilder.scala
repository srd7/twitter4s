package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */
class ResourceContextBuilder(val op: Operation) {

}

abstract class Operation(name: String)
case object Get extends Operation("Get")

