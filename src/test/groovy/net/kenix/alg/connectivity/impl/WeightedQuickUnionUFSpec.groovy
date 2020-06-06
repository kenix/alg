package net.kenix.alg.connectivity.impl

import net.kenix.alg.connectivity.UnionFind

/**
 * @author zzhao
 */
class WeightedQuickUnionUFSpec extends WeightedUnionFindTCK {

  @Override
  UnionFind subjectUnderTest(Map<String, Object> ctx) {
    new WeightedQuickUnionUF(ctx['numberOfNodes'] as int)
  }
}
