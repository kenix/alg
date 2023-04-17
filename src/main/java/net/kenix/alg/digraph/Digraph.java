package net.kenix.alg.digraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zzhao
 */
public class Digraph {

  private final Map<String, List<String>> adjList = new HashMap<>();

  public void addEdge(String v, String w) {
    if (v.equals(w)) {
      throw new IllegalStateException("self cycle: " + v + " -> " + w);
    }

    this.adjList.compute(v, (k, l) -> {
      final var list = l != null ? l : new ArrayList<String>();
      list.add(w);
      return list;
    });
  }

  public void addVertice(String v) {
    this.adjList.computeIfAbsent(v, k -> new ArrayList<>());
  }

  public List<String> adj(String v) {
    return this.adjList.getOrDefault(v, Collections.emptyList());
  }

  public Set<String> vertices() {
    return this.adjList.keySet();
  }
}
