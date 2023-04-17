package net.kenix.alg.digraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * @author zzhao
 */
public class DigraphDFS {

  private final Map<String, Boolean> marked = new HashMap<>();

  @Getter
  private final List<String> topoOrder = new ArrayList<>();

  private final List<String> path = new ArrayList<>();

  public DigraphDFS(Digraph g) {
    g.vertices().forEach(v -> {
      if (!isMarked(v)) {
        dfs(g, v);
      }
    });
  }

  private void dfs(Digraph g, String v) {
    mark(v);
    this.path.add(v);
    g.adj(v).forEach(w -> {
      if (this.path.contains(w)) {
        throw new IllegalStateException("cycle " + this.path + ", " + v + "->" + w);
      }
      if (!isMarked(w)) {
        dfs(g, w);
      }
    });
    this.topoOrder.add(v);
    this.path.remove(v);
  }

  private boolean isMarked(String v) {
    return Boolean.TRUE.equals(this.marked.get(v));
  }

  private void mark(String v) {
    this.marked.put(v, Boolean.TRUE);
  }
}
