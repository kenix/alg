package net.kenix.alg.graph

import net.kenix.alg.collection.CollectionSupport
import net.kenix.alg.graph.impl.AdjListGraphSpec
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author zzhao
 */
class BreadthFirstSearchSpec extends Specification {

  /**
   * 0-1>   4=5
   *   |\    \|
   *   2-3    6>>
   */
  @Unroll
  def 'BFS works from #startVertex to #v'() {
    given:
    def g = AdjListGraphSpec.create()

    when:
    def dfs = new BreadthFirstSearch(g, startVertex)
    then:
    dfs.hasPathTo(v) == hasPath
    CollectionSupport.toString(dfs.pathTo(v)) == path
    dfs.distTo(v) == dist

    where:
    startVertex || v | hasPath | path      | dist
    0           || 1 | true    | '[0,1]'   | 1
    0           || 2 | true    | '[0,1,2]' | 2
    0           || 3 | true    | '[0,1,3]' | 2
    0           || 4 | false   | '[]'      | -1
    0           || 5 | false   | '[]'      | -1
    0           || 6 | false   | '[]'      | -1

    1           || 0 | true    | '[1,0]'   | 1
    1           || 2 | true    | '[1,2]'   | 1
    1           || 3 | true    | '[1,3]'   | 1
    1           || 4 | false   | '[]'      | -1
    1           || 5 | false   | '[]'      | -1
    1           || 6 | false   | '[]'      | -1

    2           || 0 | true    | '[2,1,0]' | 2
    2           || 1 | true    | '[2,1]'   | 1
    2           || 3 | true    | '[2,3]'   | 1
    2           || 4 | false   | '[]'      | -1
    2           || 5 | false   | '[]'      | -1
    2           || 6 | false   | '[]'      | -1

    3           || 0 | true    | '[3,1,0]' | 2
    3           || 1 | true    | '[3,1]'   | 1
    3           || 2 | true    | '[3,2]'   | 1
    3           || 4 | false   | '[]'      | -1
    3           || 5 | false   | '[]'      | -1
    3           || 6 | false   | '[]'      | -1

    4           || 0 | false   | '[]'      | -1
    4           || 1 | false   | '[]'      | -1
    4           || 2 | false   | '[]'      | -1
    4           || 3 | false   | '[]'      | -1
    4           || 5 | true    | '[4,5]'   | 1
    4           || 6 | true    | '[4,6]'   | 1

    5           || 0 | false   | '[]'      | -1
    5           || 1 | false   | '[]'      | -1
    5           || 2 | false   | '[]'      | -1
    5           || 3 | false   | '[]'      | -1
    5           || 4 | true    | '[5,4]'   | 1
    5           || 6 | true    | '[5,6]'   | 1

    6           || 0 | false   | '[]'      | -1
    6           || 1 | false   | '[]'      | -1
    6           || 2 | false   | '[]'      | -1
    6           || 3 | false   | '[]'      | -1
    6           || 4 | true    | '[6,4]'   | 1
    6           || 5 | true    | '[6,5]'   | 1
  }
}
