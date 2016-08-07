package cn.first.toolClass;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {

	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
		// TODO Auto-generated constructor stub
	}

	public UnweightedGraph(int[][] edges, V[] vertices) {
		super(edges, vertices);
		// TODO Auto-generated constructor stub
	}

	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
		// TODO Auto-generated constructor stub
	}

	public UnweightedGraph(List<Edge> edges, List<V> vertices) {
		super(edges, vertices);
		// TODO Auto-generated constructor stub
	}

}
