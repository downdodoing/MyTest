package cn.first.toolClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.first.toolClass.myInterface.Graph;

public abstract class AbstractGraph<V> implements Graph<V> {
	protected List<V> vertices;// 存储顶点
	protected List<List<Integer>> neighbors;// 存储邻接顶点

	protected AbstractGraph(int[][] edges, V[] vertices) {
		this.vertices = new ArrayList<V>();

		for (int i = 0; i < vertices.length; i++) {
			this.vertices.add(vertices[i]);
		}

		createAdjacencyLists(edges, vertices.length);
	}

	protected AbstractGraph(List<Edge> edges, List<V> vertices) {
		this.vertices = vertices;
		createAdjacencyLists(edges, vertices.size());
	}

	@SuppressWarnings("unchecked")
	protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
		vertices = new ArrayList<V>();

		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i)));
		}

		createAdjacencyLists(edges, numberOfVertices);

	}

	@SuppressWarnings("unchecked")
	protected AbstractGraph(int[][] edges, int numberOfVertices) {
		vertices = new ArrayList<V>();

		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i)));
		}
		createAdjacencyLists(edges, numberOfVertices);
	}

	private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
		neighbors = new ArrayList<List<Integer>>();

		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			neighbors.get(u).add(v);
		}
	}

	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
		neighbors = new ArrayList<List<Integer>>();

		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<Integer>());
		}

		for (Edge edge : edges) {
			neighbors.get(edge.getU()).add(edge.getV());
		}

	}

	@Override
	public int getSize() {
		return vertices.size();
	}

	@Override
	public List<V> getVertices() {
		return vertices;
	}

	@Override
	public V getVertex(int index) {
		return vertices.get(index);
	}

	@Override
	public int getIndex(V v) {
		return vertices.indexOf(v);
	}

	@Override
	public List<Integer> getNeighbors(int index) {
		return neighbors.get(index);
	}

	@Override
	public int getDegree(int v) {
		return neighbors.get(v).size();
	}

	/**
	 * 邻接矩阵
	 */
	@Override
	public int[][] getAdjacencyMatrix() {

		int[][] adjacencyMatrix = new int[getSize()][getSize()];

		for (int i = 0; i < neighbors.size(); i++) {
			for (int j = 0; j < neighbors.get(i).size(); j++) {
				int v = neighbors.get(i).get(j);
				adjacencyMatrix[i][v] = 1;
			}
		}

		return adjacencyMatrix;
	}

	@Override
	public void printAdjacenMatric() {

		int[][] adjacencyMatrix = getAdjacencyMatrix();

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}

			System.out.println();
		}
	}

	public void printEdges() {
		for (int u = 0; u < neighbors.size(); u++) {

			System.out.print("Vertext " + u + ": ");
			for (int j = 0; j < neighbors.get(u).size(); j++) {
				System.out.print("(" + u + "," + neighbors.get(u).get(j) + ")");
			}
		}
	}

	/**
	 * 深度优先搜索
	 * 
	 */
	public Tree dfs(int v) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}

		boolean[] isVisited = new boolean[vertices.size()];

		dfs(v, parent, searchOrders, isVisited);

		return new Tree(v, parent, searchOrders);
	}

	public void dfs(int v, int[] parent, List<Integer> searchOrders,
			boolean[] isVisited) {
		searchOrders.add(v);
		isVisited[v] = true;

		for (int i : neighbors.get(v)) {
			if (!isVisited[i]) {
				parent[i] = v;
				dfs(i, parent, searchOrders, isVisited);
			}
		}

	}

	/**
	 * 广度优先遍历
	 * 
	 */
	public Tree bfs(int v) {
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[vertices.size()];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}

		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[vertices.size()];
		queue.offer(v);
		isVisited[v] = true;

		while (!queue.isEmpty()) {
			int u = queue.poll();
			searchOrders.add(u);

			for (int i : neighbors.get(u)) {
				if (!isVisited[i]) {
					queue.offer(i);
					parent[i] = u;
					isVisited[i] = true;
				}
			}
		}

		return new Tree(v, parent, searchOrders);
	}

	public class Tree {
		private int root;
		private int[] parent;
		private List<Integer> searchOrders;

		public Tree(int root, int[] parent, List<Integer> searchOrders) {
			this.root = root;
			this.parent = parent;
			this.searchOrders = searchOrders;
		}

		public Tree(int root, int[] parent) {
			this.root = root;
			this.parent = parent;
		}

		public int getRoot() {
			return root;
		}

		public int[] getParent() {
			return parent;
		}

		public List<Integer> getSearchOrders() {
			return searchOrders;
		}

		public int getNumberOfVerticesFound() {
			return searchOrders.size();
		}

		public List<V> getPath(int index) {
			ArrayList<V> path = new ArrayList<V>();

			do {
				path.add(vertices.get(index));
				index = parent[index];
			} while (index != -1);

			return path;
		}

		public void printPath(int index) {
			List<V> path = getPath(index);
			System.out.println("从" + vertices.get(root) + "到 "
					+ vertices.get(index) + ":");
			for (int i = 0; i < path.size(); i++) {
				System.out.print(path.get(i) + " ");
			}
		}

	}
}
