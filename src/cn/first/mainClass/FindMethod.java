package cn.first.mainClass;

import java.util.ArrayList;
import java.util.List;

import cn.first.toolClass.AbstractGraph;
import cn.first.toolClass.Edge;
import cn.first.toolClass.UnweightedGraph;

public class FindMethod {
	public static final int NUMBER_OF_VERTICALS = 16;
	private AbstractGraph<Integer>.Tree tree;

	public FindMethod() {
		List<Edge> edges = getEdges();
		UnweightedGraph<Integer> weight = new UnweightedGraph<>(edges,
				NUMBER_OF_VERTICALS);
		tree = weight.dfs(NUMBER_OF_VERTICALS - 1);
	}

	public List<Edge> getEdges() {
		List<Edge> edge = new ArrayList<Edge>();
		for (int i = 0; i < NUMBER_OF_VERTICALS; i++) {
			for (int j = 0; j < NUMBER_OF_VERTICALS; j++) {
				if (i != j && isArrive(j)) {
					edge.add(new Edge(i, j));
				}
			}
		}
		return edge;
	}

	/**
	 * 判断能够到达,从左到右依次为白菜、羊、狼、农夫
	 */
	public boolean isArrive(int end) {
		if (aboutNorth(end)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断运了动物过后南岸和北岸的状态
	 * 
	 * @param north
	 * @return
	 */
	public boolean aboutNorth(int station) {
		String result = Integer.toBinaryString(station & 15);
		int length = result.length();
		// 将result转化为字符数组
		for (int i = 0; i < 4 - length; i++) {
			result = "0" + result;
		}
		char[] resultChar = result.toCharArray();
		if (((resultChar[0] == '0' && resultChar[1] == '0') || (resultChar[1] == '0' && resultChar[2] == '0'))
				&& resultChar[3] != '0') {
			return false;
		}
		return true;
	}

	// /**
	// * 判断运了动物过后南岸和北岸的状态
	// *
	// * @param north
	// * @return
	// */
	// public boolean aboutSouth(int station) {
	// String result = Integer.toBinaryString(station & 15);
	// int length = result.length();
	// // 将result转化为字符数组
	// for (int i = 0; i < 4 - length; i++) {
	// result += "0";
	// }
	// char[] resultChar = result.toCharArray();
	// if (((resultChar[0] == '1' && resultChar[1] == '1') || (resultChar[1] ==
	// '1' && resultChar[2] == '1'))
	// && resultChar[3] != '1') {
	// return false;
	// }
	// return true;
	// }

	public List<Integer> getShortestPath(int nodeIndex) {
		return tree.getPath(nodeIndex);
	}

	public AbstractGraph<Integer>.Tree getTree() {
		return tree;
	}

	public void setTree(AbstractGraph<Integer>.Tree tree) {
		this.tree = tree;
	}

}
