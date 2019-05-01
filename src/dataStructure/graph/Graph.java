package dataStructure.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import dataStructure.graph.algorithm.Dijkstra_ShortestPath;


/**
 * 有向边带（正）权图
 * 顶点必须互异
 * @author s
 *
 * @param <T>	顶点类型，要求为不可变类型（hashcode应当恒定）
 */
public interface Graph<T> 
{

	static <T> Graph<T> newInstance()
	{
		return new AdjListGraph<T>();
	}
	
	/**
	 * 添加顶点
	 * @param vertex 
	 * @return 添加改变了图则真
	 */
	boolean add(T vertex);
	
	/**
	 * 删除顶点及其邻接边
	 * @param vertex
	 * @return	删除改变了图则真
	 */
	boolean remove(T vertex);
	
	/**
	 * 获取边权值
	 * @param form
	 * @param to
	 * @return 边权值，0表示无边
	 */
	int weight(T from,T to);
	
	/**
	 * 在已有的两点间设置带权有向边
	 * @param form
	 * @param to
	 * @param weight 正
	 * @return 图被改变则真
	 */
	boolean setEdge(T from,T to,int weight);
	
	/**
	 * 尝试删除边
	 * @param from
	 * @param to
	 * @return 图被改变则真
	 */
	boolean removeEdge(T from,T to);
	
	/**
	 * 获取所有顶点的不可修改视图
	 * @return
	 */
	Set<T> vertices();
	
	/**
	 * 获取vertice的后继顶点
	 * @param vertice
	 * @return
	 */
	Set<T> successors(T vertice);
	
	/**
	 * 获取vertice的前驱顶点
	 * @param vertice
	 * @return
	 */
	Set<T> precursor(T vertice);
	
	/**
	 * 获取顶点数
	 * @return
	 */
	int size();
	
	/**
	 * 判断是否含顶点vertex
	 * @param vertex
	 * @return
	 */
	boolean contains(T vertex);
	
	/**
	 * 返回有权图的不可修改无权图视图
	 * 原图的边权被视为1（有边）或0（无边）
	 * @param graph
	 * @return
	 */
	static <T> Graph<T> noWeightGraph(Graph<T> graph)
	{
		class NoWeightGraph implements Graph<T>
		{
			private final Graph<T> graph;
			
			public NoWeightGraph(Graph<T> graph)
			{
				this.graph = graph;
			}
			
			@Override
			public boolean add(T vertex) {
				throw new RuntimeException("试图对不可修改视图调用修改器");
			}

			@Override
			public boolean remove(T vertex) {
				throw new RuntimeException("试图对不可修改视图调用修改器");
			}

			@Override
			public int weight(T from, T to) {
				int weight = graph.weight(from, to);
				if(weight > 0)
					return 1;
				else
					return 0;
			}

			@Override
			public boolean setEdge(T from, T to, int weight) {
				throw new RuntimeException("试图对不可修改视图调用修改器");
			}

			@Override
			public boolean removeEdge(T from, T to) {
				throw new RuntimeException("试图对不可修改视图调用修改器");
			}

			@Override
			public Set<T> vertices() {
				return graph.vertices();
			}

			@Override
			public Set<T> successors(T vertice) {
				return graph.successors(vertice);
			}

			@Override
			public Set<T> precursor(T vertice) {
				return graph.precursor(vertice);
			}

			@Override
			public int size() {
				return graph.size();
			}

			@Override
			public boolean contains(T vertex) {
				return graph.contains(vertex);
			}
			
		}
		
		return new NoWeightGraph(graph);
	}
	
	/**
	 * 计算单源最短路径
	 * @param graph
	 * @param origin
	 * @return <目标顶点,<最短路径长度，最短路径>>
	 */
	static <T> Map<T,Pair<Integer,List<T>>> singleSourceShortestPath(Graph<T> graph,T origin)
	{
		return Dijkstra_ShortestPath.singleSourceShortestPath(graph, origin);
	}
}




