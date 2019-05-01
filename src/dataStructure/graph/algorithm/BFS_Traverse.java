package dataStructure.graph.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import dataStructure.graph.Graph;

/**
 * 图的广度优先遍历
 * @author s
 *
 * @param <T>
 */
public class BFS_Traverse<T>
{
	private final Graph<T> graph;
	private final Set<T> hasVisit = new HashSet<>();
	
	private final Consumer<T> dealWithVertex;
	
	/**
	 * 先广遍历图
	 * @param graph
	 * @param dealWithVertex
	 */
	public static <T> void traverse(Graph<T> graph,Consumer<T> dealWithVertex)
	{
		BFS_Traverse<T> aBFS = new BFS_Traverse<>(graph,dealWithVertex);
		aBFS.traverseGraph();
	}
	
	/**
	 * 先广遍历以origin为起点的连通分量
	 * @param graph
	 * @param origin
	 * @param dealWithVertex
	 */
	public static <T> void traverseConnectedComponent(Graph<T> graph,T origin,Consumer<T> dealWithVertex)
	{
		BFS_Traverse<T> aBFS = new BFS_Traverse<>(graph,dealWithVertex);
		aBFS.traverseConnectedComponent(origin);
	}
	
	/**
	 * 获得以origin为起点的连通分量的遍历序列
	 * @param graph
	 * @param origin
	 * @return
	 */
	public static<T> List<T> getList(Graph<T> graph,T origin)
	{
		List<T> ans = new ArrayList<>();
		traverseConnectedComponent(graph,origin,v->{
			ans.add(v);
		});
		return ans;
	}
	
	private BFS_Traverse(Graph<T> graph,Consumer<T> dealWithVertex)
	{
		this.graph = graph;
		this.dealWithVertex = dealWithVertex;
	}
	
	private void traverseGraph()
	{
		for(T v:graph.vertices())
		{
			if(!hasVisit(v))
			{
				traverseConnectedComponent(v);
			}
		}
	}
	
	/**
	 * 先广遍历连通分支
	 * 该分支上的顶点应未被BFS访问
	 * @param origin
	 */
	private void traverseConnectedComponent(T origin)
	{
		assert !hasVisit(origin);
		hasVisit.add(origin);
		
		Queue<T> tobeVisit = new LinkedList<>();
		tobeVisit.addAll(graph.successors(origin));
		while(!tobeVisit.isEmpty())
		{
			T theVertex = tobeVisit.remove();
			dealWithVertex.accept(theVertex);
			hasVisit.add(theVertex);
			
			for(T v:graph.successors(theVertex))
			{
				if(!hasVisit(v))
				{
					tobeVisit.add(v);
				}
			}
		}
	}
	
	private boolean hasVisit(T vertex)
	{
		return this.hasVisit.contains(vertex);
	}
	
}
