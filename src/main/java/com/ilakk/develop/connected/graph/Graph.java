package com.ilakk.develop.connected.cities.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class Graph {

	// The graph is represented using adjacency list.
	// An adjacency list is an array of separate lists.
	// Each element of the array is a list,
	// which contains all the vertices that are adjacent to vertex i.
	// Size of array will be V (number of vertices in graph)

	int V;

	LinkedList<Integer> adjListArray[];

	public Graph(int V) {
		this.V = V;
		adjListArray = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	public void addEdge(Graph graph, int src, int dest) {
		graph.adjListArray[src].add(dest);
		graph.adjListArray[dest].add(src);
	}

	public boolean isConnectedDFS(Integer src, Integer dest) {
		List<Integer> visited = new ArrayList<Integer>();
		visited.add(src);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		return isConnDFS(visited, queue, dest);
	}

	private boolean isConnDFS(List<Integer> visited, Queue<Integer> queue, Integer dest) {
		boolean result = false;
		while (!queue.isEmpty()) {
			Integer curr = queue.remove();
			visited.add(curr);
			if (curr == dest) {
				result = true;
			}
			LinkedList<Integer> currAdjacents = adjListArray[curr];
			for (Integer adj : currAdjacents) {
				if (!(visited.contains(adj))) {
					queue.add(adj);
				}
			}
		}
		return result;
	}

	public boolean isConnectedBFS(Integer src, Integer dest) {
		List<Integer> visited = new ArrayList<Integer>();
		visited.add(src);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		boolean result2 = isConnBFS(visited, queue, dest);
		return result2;
	}

	private boolean isConnBFS(List<Integer> visited, Queue<Integer> queue, Integer dest) {
		while (!queue.isEmpty()) {
			Integer curr = queue.remove();
			visited.add(curr);
			if (curr == dest) {
				return true;
			}
			LinkedList<Integer> currAdjacents = adjListArray[curr];
			for (Integer adj : currAdjacents) {
				if (!(visited.contains(adj))) {
					queue.add(adj);
					// boolean result = isConnBFS(visited, queue, dest);
					// if (result == true) {
					// return true;
					// }
					if (isConnBFS(visited, queue, dest))
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean isConnectedDFS(City city1, City city2) {
		return isConnectedDFS(city1.getId(), city2.getId());
	}

	public boolean isConnectedBFS(City city1, City city2) {
		return isConnectedDFS(city1.getId(), city2.getId());
	}

}