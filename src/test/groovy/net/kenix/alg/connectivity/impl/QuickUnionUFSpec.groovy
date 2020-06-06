package net.kenix.alg.connectivity.impl

import net.kenix.alg.connectivity.UnionFind

/**
 * @author zzhao
 */
class QuickUnionUFSpec extends UnionFindTCK {
  @Override
  UnionFind subjectUnderTest(Map<String, Object> ctx) {
    new QuickUnionUF(ctx['numberOfNodes'] as int)
  }
}
