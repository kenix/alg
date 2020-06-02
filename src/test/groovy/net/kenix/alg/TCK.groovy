package net.kenix.alg
/**
 * @author zzhao
 */
interface TCK<T> {
  T subjectUnderTest(Map<String, Object> ctx)
}
