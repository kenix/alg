package net.kenix.alg.collection.map.impl

import net.kenix.alg.collection.map.OrderedSymbolTable

/**
 * @author zzhao
 */
class BinarySearchTreeSTSpec extends OrderedSymbolTableTCK {
  @Override
  OrderedSymbolTable<String, Integer> subjectUnderTest(Map<String, Object> ctx) {
    return new BinarySearchTreeST<String, Integer>()
  }
}
