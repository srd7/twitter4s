package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */
class ResourceContextBuilder[ParameterType, ReturnType](val context: ResourceContext, var conditions: ParameterType, val f: (ParameterType) => ReturnType) {

}

trait ResourceContext {
  type GetParameterType
  type GetReturnType

  def getDefaultParameters: GetParameterType

  val getFunc: (GetParameterType) => GetReturnType
}

sealed trait Operation
case object Get extends Operation

object ParameterNothing

