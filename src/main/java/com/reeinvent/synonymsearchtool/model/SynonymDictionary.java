package com.reeinvent.synonymsearchtool.model;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SynonymDictionary {

  private final DirectedGraph <String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

  public Set<String> getSynonyms(String word) {
    if (!graph.containsVertex(word)) {
      return Collections.emptySet();
    }

    DepthFirstIterator<String, DefaultEdge> depthFirstIterator = new DepthFirstIterator<>(graph, word);
    Set<String> synonyms = new HashSet<>();
    // skip first element, because that is actually requested vertex "word"
    if(depthFirstIterator.hasNext()) {
      depthFirstIterator.next();
    }
    while (depthFirstIterator.hasNext()) {
      synonyms.add(depthFirstIterator.next());
    }
    return synonyms;
  }

  public void addSynonyms(String word, Set<String> synonyms) {
    synonyms.stream().filter(Objects::nonNull).forEach(synonym -> {
      addSynonym(word, synonym);
      addSynonym(synonym, word);
    });
  }

  public void addSynonym(String word, String synonym) {
    if (!graph.containsVertex(word)) {
      graph.addVertex(word);
    }
    if (!graph.containsVertex(synonym)) {
      graph.addVertex(synonym);
    }
    graph.addEdge(word, synonym);
  }
}
