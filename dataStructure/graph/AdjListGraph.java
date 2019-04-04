package dataStructure.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 图接口的邻接表（出边表）实现
 * @author s
 *
 * @param <T>
 */
public class AdjListGraph<T> implements Graph<T> 
{
	Map<T,Set<Node<T>>> list = new HashMap<>();
	
	
	@Override
	public boolean add(T vertex) {
		return list.putIfAbsent(vertex, new HashSet<>()) == null;
	}

	@Override
	public boolean remove(T vertex) {
		if(list.remove(vertex) == null)
			return false;

		for(Map.Entry<T, Set<Node<T>>> entry : list.entrySet())
		{
			if(entry.getValue().contains(vertex))
			{
				list.get(entry.getKey()).remove(vertex);
			}
		}
		
		return true;
	}

	@Override
	public int weight(T from, T to) {
		
		if(!list.containsKey(from) || !list.containsKey(to))
			return 0;
		Set<Node<T>> successors = list.get(from);
		for(Node<T> n : successors)
		{
			if(n.to.equals(to))
			{
				return n.weight;
			}
		}
		return 0;
	}

	@Override
	public boolean setEdge(T from, T to, int weight) {
		if(weight <= 0 || from == null || to == null)
			throw new RuntimeException("参数非法");
		if(!list.containsKey(from) || !list.containsKey(to))
			throw new RuntimeException("试图在不存在的两点间设置边");
		
		Set<Node<T>> successors = list.get(from);
		
		successors.removeIf((n)->{
			return n.to.equals(to);
		});
		boolean flag = successors.add(new Node<T>(to,weight));
		assert flag;
		
		return true;
	}
	
	@Override
	public boolean removeEdge(T from, T to) 
	{
		Set<Node<T>> successors = list.get(from);
		if(successors == null)
			return false;
		return successors.removeIf((n)->{
			return n.to.equals(to);
		});
	}

	@Override
	public Set<T> vertices() {
		return Collections.unmodifiableSet(list.keySet());
	}

	@Override
	public Set<T> successors(T vertice) {
		Set<Node<T>> nodes = list.get(vertice);
		Set<T> ans = new HashSet<>();
		for(Node<T> n : nodes)
		{
			ans.add(n.to);
		}
		return ans;
	}

	@Override
	public Set<T> precursor(T vertice) {
		//TODO 可优化
		Set<T> ans = new HashSet<>();
		
		for(T ver:vertices())
		{
			if(successors(ver).contains(vertice))
				ans.add(ver);
		}
		return ans;
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean contains(T vertex) {
		return list.containsKey(vertex);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(getClass().toString());
		sb.append(":\n");
		sb.append(list.toString());
		return sb.toString();
	}
	
	/**
	 * 出边表项
	 * 不可变类型
	 * 代表目标节点的一个后继节点以及出边权值
	 * @author s
	 *
	 * @param <T>
	 */
	private static class Node<T>
	{
		/**
		 * 后继顶点
		 */
		public final T to;
		
		/**
		 * 出边权值
		 * 正值
		 */
		public final int weight;
		
		public Node(T to,int weight)
		{
			this.to = to;
			this.weight = weight;
			
			assert to != null;
			assert weight > 0;
		}
		
		@Override
		public boolean equals(Object otherObject)
		{
			if(this == otherObject)
				return true;
			if(otherObject == null)
				return false;
			if(this.getClass() != otherObject.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Node<T> other = (Node<T>)otherObject;
			return other.weight == weight && Objects.equals(other.to, to);
		}
		
		@Override
		public int hashCode()
		{
			return Objects.hash(weight,to);
		}
		
		@Override
		public String toString()
		{
			return "(" + to.toString() + "," + weight + ")";
		}
	}


	public static void main(String[] args)
	{
		Graph<String> graph = new AdjListGraph<>();
		
		graph.add("A");
		graph.add("B");
		graph.add("C");
		graph.add("D");
		graph.add("E");
		
		graph.setEdge("A", "B", 1);
		graph.setEdge("A", "B", 2);
		graph.setEdge("A", "C", 3);
		graph.setEdge("D", "E", 4);
		
		System.out.println(graph);
		
		graph.removeEdge("A","B");
		graph.removeEdge("D","E");
		System.out.println(graph);
	}


}











