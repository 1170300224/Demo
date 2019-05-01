package dataStructure.graph.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import dataStructure.graph.Graph;

public class Dijkstra_ShortestPath<T>
{
	private final Graph<T> graph;
	private final T origin;
	
	private List<T> vertices;
	private int[] dis;
	private boolean[] hasVisited;
	private int[] path;
	
	public Dijkstra_ShortestPath(Graph<T> graph,T origin)
	{
		this.graph = graph;
		this.origin = origin;
		init();
		
		DijkMain();
	}
	
	/**
	 * 计算单源最短路径
	 * @param graph
	 * @param origin
	 * @return <目标顶点,<最短路径长度，最短路径>>
	 */
	public static <T> Map<T,Pair<Integer,List<T>>> singleSourceShortestPath(Graph<T> graph,T origin)
	{
		Dijkstra_ShortestPath<T> dij = new Dijkstra_ShortestPath<>(graph,origin);
		
		Map<T,Pair<Integer,List<T>>> ans = new HashMap<>();
		
		for(T v :graph.vertices())
		{
			int dis = dij.getDistance(v);
			List<T> path = dij.getPath(v);
			Pair<Integer,List<T>> disAndPath = new ImmutablePair<>(dis,path);
			ans.put(v, disAndPath);
		}
		return ans;
	}
	
	public int getDistance(T to)
	{
		int index = this.vertices.indexOf(to);
		return dis[index];
	}
	
	@SuppressWarnings("serial")
	public List<T> getPath(T to)
	{	
		if(Objects.equals(to, origin))
			return new ArrayList<>() {{add(to);}};
		if(dis[this.vertices.indexOf(to)]==Integer.MAX_VALUE)
			return new ArrayList<>();
		
		Stack<T> reverse = new Stack<>();
		
		int index = this.vertices.indexOf(to);
		reverse.push(to);
		
		while((index = path[index]) != -1)
		{
			reverse.push(this.vertices.get(index));
		}
		
		List<T> ans = new ArrayList<>(reverse.size()+1);
		while(!reverse.isEmpty())
		{
			ans.add(reverse.pop());
		}
		return ans;
	}
	
	private void init()
	{
		initVerticesList();
		initDisArray();
		initHasVisitedArray();
		initPathArray();
	}
	
	private void initVerticesList()
	{
		this.vertices = new ArrayList<>(graph.vertices());
		int k = this.vertices.indexOf(origin);
		Collections.swap(vertices, k, 0);
	}
	
	private void initDisArray()
	{
		dis = new int[graph.size()];
		dis[0] = 0;
		for(int i = 1; i<vertices.size();i++)
		{
			dis[i] = graph.weight(origin, vertices.get(i));
			if(dis[i] == 0)
				dis[i] = Integer.MAX_VALUE;
		}
	}
	
	private void initHasVisitedArray()
	{
		hasVisited = new boolean[graph.size()];
		Arrays.fill(hasVisited, false);
		hasVisited[0] = true;
	}
	
	private void initPathArray()
	{
		this.path = new int[graph.size()];
		Arrays.fill(path, 0);
		path[0] = -1;
	}
	
	private void DijkMain()
	{
		int theVertex;
		while((theVertex = findNext()) != -1)
		{
			hasVisited[theVertex] = true;
			updateDisAndPathArray(theVertex);
		}
	}
	
	/**
	 * 在开启集中寻找一个到关闭集中权值最小的顶点索引，作为下一个算法焦点
	 * 若开启集与关闭集不连通，返回-1
	 * @return
	 */
	private int findNext()
	{
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 1; i<graph.size(); i++)
		{
			if(!hasVisited[i])
			{
				if(dis[i] < min)
				{
					min = dis[i];
					index = i;
				}
			}
		}
		return index;
	}
	
	private void updateDisAndPathArray(int theVertex)
	{
		for(int i = 1; i<graph.size(); i++)
		{
			if(!hasVisited[i])
			{
				int bridegWeight = graph.weight(vertices.get(theVertex), vertices.get(i));
				if(bridegWeight != 0)
				{
					int newDis = dis[theVertex] + bridegWeight;
					if(newDis<dis[i])
					{
						dis[i] = newDis;
						path[i] = theVertex;
					}
				}
			}
		}
	}
}


















