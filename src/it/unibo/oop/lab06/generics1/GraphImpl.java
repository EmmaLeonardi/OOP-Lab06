package it.unibo.oop.lab06.generics1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

public class GraphImpl<N> implements Graph<N> {

	private final Map<N, Set<N>> graph = new HashMap<>();

	public void addNode(N node) {
		// Otherwise it'll fill up the graph with empty nodes or duplicate nodes
		graph.putIfAbsent(node, new HashSet<N>());
	}

	public void addEdge(N source, N target) {
		if (source != null && target != null) {
			// If the nodes already existed the map is unchanged,
			// otherwise it creates the nodes
			this.addNode(target);
			this.addNode(source);
			graph.get(source).add(target);
		}

	}

	public Set<N> nodeSet() {
		return new HashSet<>(graph.keySet());
	}

	public Set<N> linkedNodes(N node) {
		return new HashSet<>(graph.get(node));
	}

	public List<N> getPath(N source, N target) {
		// I'm using a bfs approach
		Queue<N> q = new ArrayDeque<>();
		Map<N, N> parent = new HashMap<>();

		var i = graph.keySet().iterator();
		while (i.hasNext()) {
			var elem = i.next();
			// Initializing the parent vector, every node has source as a parent
			parent.put(elem, source);
		}
		q.add(source);
		while (q.isEmpty() == false) {
			N t = q.remove();
			for (N elem : graph.get(t)) {
				if (parent.get(elem) == source && elem != source) {
					// If the element hasn't already been explored OR
					// If the element isn't source, t is the new parent
					q.add(elem);
					parent.put(elem, t);
				}
			}
		}

		/*
		 * Path is the list that tracks the path to take in the graph,
		 * using the parent map
		 */
		List<N> path = new ArrayList<>();

		// First node is target
		path.add(target);
		N tmp = parent.get(target);
		while (tmp != source) {
			path.add(tmp);
			tmp = parent.get(tmp);
		}
		// Last node is source
		path.add(source);
		// Path is from target to source
		Collections.reverse(path);
		return path;
	}

}
