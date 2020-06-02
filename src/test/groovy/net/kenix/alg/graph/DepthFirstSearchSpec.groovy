package net.kenix.alg.graph

import net.kenix.alg.collection.CollectionSupport
import net.kenix.alg.graph.impl.AdjListGraphSpec
import spock.lang.Specification

/**
 * @author zzhao
 */
class DepthFirstSearchSpec extends Specification {

  /**
   * 0-1>   4=5
   *   |\    \|
   *   2-3    6>>
   */
  def 'DFS works'() {
    given:
    def g = AdjListGraphSpec.create()

    when:
    def dfs = new DepthFirstSearch(g, startVertex)
    then:
    dfs.hasPathTo(v) == hasPath
    CollectionSupport.toString(dfs.pathTo(v)) == path

    where:
    startVertex || v | hasPath | path
    0           || 1 | true    | '[0,1]'
    0           || 2 | true    | '[0,1,3,2]'
    0           || 3 | true    | '[0,1,3]'
    0           || 4 | false   | '[]'
    0           || 5 | false   | '[]'
    0           || 6 | false   | '[]'

    1           || 0 | true    | '[1,0]'
    1           || 2 | true    | '[1,3,2]'
    1           || 3 | true    | '[1,3]'
    1           || 4 | false   | '[]'
    1           || 5 | false   | '[]'
    1           || 6 | false   | '[]'

    2           || 0 | true    | '[2,3,1,0]'
    2           || 1 | true    | '[2,3,1]'
    2           || 3 | true    | '[2,3]'
    2           || 4 | false   | '[]'
    2           || 5 | false   | '[]'
    2           || 6 | false   | '[]'

    3           || 0 | true    | '[3,1,0]'
    3           || 1 | true    | '[3,1]'
    3           || 2 | true    | '[3,1,2]'
    3           || 4 | false   | '[]'
    3           || 5 | false   | '[]'
    3           || 6 | false   | '[]'

    4           || 0 | false   | '[]'
    4           || 1 | false   | '[]'
    4           || 2 | false   | '[]'
    4           || 3 | false   | '[]'
    4           || 5 | true    | '[4,6,5]'
    4           || 6 | true    | '[4,6]'

    5           || 0 | false   | '[]'
    5           || 1 | false   | '[]'
    5           || 2 | false   | '[]'
    5           || 3 | false   | '[]'
    5           || 4 | true    | '[5,6,4]'
    5           || 6 | true    | '[5,6]'

    6           || 0 | false   | '[]'
    6           || 1 | false   | '[]'
    6           || 2 | false   | '[]'
    6           || 3 | false   | '[]'
    6           || 4 | true    | '[6,4]'
    6           || 5 | true    | '[6,4,5]'
  }
}
