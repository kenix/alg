package net.kenix.alg.connectivity.impl

import net.kenix.alg.connectivity.UnionFind

/**
 * @author zzhao
 */
class QuickFindUFSpec extends UnionFindTCK {

  @Override
  UnionFind subjectUnderTest(Map<String, Object> ctx) {
    new QuickFindUF(ctx['numberOfNodes'] as int)
  }
}
