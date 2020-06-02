/*
 * Created at 19:05 on 31.05.20
 */
package net.kenix.alg.collection;

/** @author zzhao */
public final class CollectionSupport {

  private CollectionSupport() {
    throw new AssertionError("not for instantiation or inheritance");
  }

  public static <T> String toString(Iterable<T> it) {
    final StringBuilder sb = new StringBuilder(64);
    sb.append("[");
    for (T t : it) {
      if (sb.length() > 1) {
        sb.append(",");
      }
      sb.append(t.toString());
    }
    return sb.append("]").toString();
  }
}
