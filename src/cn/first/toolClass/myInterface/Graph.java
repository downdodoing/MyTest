package cn.first.toolClass.myInterface;

import java.util.List;

import cn.first.toolClass.AbstractGraph;

public interface Graph<V> {
	public int getSize();

	public List<V> getVertices();

	public V getVertex(int index);

	public int getIndex(V v);

	public List<Integer> getNeighbors(int index);

	public int getDegree(int v);

	public int[][] getAdjacencyMatrix();

	public void printAdjacenMatric();

	// 深度优先搜素
	public AbstractGraph<V>.Tree dfs(int v);

	// 广度优先搜索
	public AbstractGraph<V>.Tree bfs(int v);
}
