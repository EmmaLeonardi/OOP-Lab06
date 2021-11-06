package it.unibo.oop.lab06.generics1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;
import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
import java.util.Queue;

public class GraphImpl<N> implements Graph<N> {

	private final Map<N, Set<N>> graph = new HashMap<>();

	public void addNode(N node) {
		// Otherwise it'll fill up the graph with empty nodes or duplicate nodes
		graph.putIfAbsent(node, new HashSet<N>());
	}

	public void addEdge(N source, N target) {
		if (source != null && target != null) {
			// If the nodes already existed the map is unchanged, otherwise it creates the
			// nodes
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
			parent.put(elem, source);// Initializing the parent vector, every node has source as a parent
		}
		// Dovrebbe essere solo 1
		// System.out.println(parent);

		q.add(source);
		System.out.println("Queue now has:" + q);
		while (q.isEmpty() == false) {
			N t = q.remove();
			System.out.println("Removed t from queue: " + t);
			System.out.println("Looking at the nodes linked to " + t + ": "+ this.linkedNodes(t));
			for (N elem : graph.get(t)) {
				System.out.println("Looking at the nodes linked to " + t + " like, " + elem);
				if (parent.get(elem) == source && elem != source) {
					// If the element hasn't already been explored
					// If the element has source as a parent, t is the new parent
					q.add(elem);
					parent.put(elem, t);
					System.out.println(" " + elem + " has now parent " + parent.get(elem));
				}
				//System.out.println(parent);
				//System.out.println("Queue now has:" + q);

			}
			System.out.println(parent);
			System.out.println("Queue now has:" + q);
		}

		List<N> path = new ArrayList<>();
		System.out.println(path);
		path.add(target);
		N tmp = parent.get(target);
		while (tmp != source) {
			path.add(tmp);
			tmp = parent.get(tmp);
		}
		path.add(source);

		System.out.println("Reversed list");
		System.out.println(path);
		Collections.reverse(path);
		System.out.println("Ordered list");
		System.out.println(path);
		return path;
	}

}
