package net.kenix.alg.connectivity.impl

import net.kenix.alg.TCK
import net.kenix.alg.connectivity.UnionFind
import spock.lang.Specification

/**
 * @author zzhao
 */
abstract class UnionFindTCK extends Specification implements TCK<UnionFind> {

  def 'union find works'() {
    when:
    def uf = subjectUnderTest([numberOfNodes: 10])
    uf.union(4, 3)
    then:
    uf.find(4) == 3
    uf.find(3) == 3

    when:
    uf.union(3, 8)
    then:
    uf.find(4) == 8
    uf.find(3) == 8
    uf.find(8) == 8

    when:
    uf.union(6, 5)
    uf.union(9, 4)
    uf.union(2, 1)
    then:
    uf.connected(8, 9)

    when:
    uf.union(5, 0)
    uf.union(7, 2)
    uf.union(6, 1)
    then:
    uf.connected(1, 0)
    uf.connected(6, 7)

    println uf.id
  }
}
