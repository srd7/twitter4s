package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */
class ResourceContextBuilder[ParameterType, ReturnType](val context: ResourceContext, val op: Operation, var conditions: ParameterType) {

}

trait ResourceContext {
  type GetParameterType
  type GetReturnType

  def getDefaultParameters: GetParameterType
}

sealed trait Operation
case object Get extends Operation

object ParameterNothing

