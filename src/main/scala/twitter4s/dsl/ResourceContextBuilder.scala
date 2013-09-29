package twitter4s.dsl

/**
 * @author mao.instantlife at gmail.com
 */
class ResourceContextBuilder[ParameterType, ReturnType](var conditions: ParameterType, val f: (ParameterType) => ReturnType) {

  def execute: ReturnType = f(conditions)
}

trait ResourceContext {
  type GetParameterType
  type GetReturnType

  def getDefaultParameters: GetParameterType

  val getFunc: (GetParameterType) => GetReturnType
}

object ParameterNothing

