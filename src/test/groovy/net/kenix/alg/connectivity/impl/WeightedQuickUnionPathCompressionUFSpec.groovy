package net.kenix.alg.connectivity.impl

import net.kenix.alg.connectivity.UnionFind

/**
 * @author zzhao
 */
class WeightedQuickUnionPathCompressionUFSpec extends WeightedUnionFindTCK {

  @Override
  UnionFind subjectUnderTest(Map<String, Object> ctx) {
    new WeightedQuickUnionPathCompressionUF(ctx['numberOfNodes'] as int)
  }
}
