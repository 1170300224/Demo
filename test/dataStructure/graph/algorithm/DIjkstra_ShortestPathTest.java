package dataStructure.graph.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dataStructure.graph.Graph;

public class DIjkstra_ShortestPathTest 
{
	
	@Test
	public void noEdgeWeightTest()
	{
		Graph<Integer> graph = graph_1();
		Dijkstra_ShortestPath<Integer> dij = new Dijkstra_ShortestPath<>(graph,1);
		
		assertEquals(0,dij.getDistance(1));
		assertEquals(1,dij.getDistance(2));
		assertEquals(1,dij.getDistance(3));
		assertEquals(1,dij.getDistance(4));
		assertEquals(2,dij.getDistance(5));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(6));
	}
	
	@Test
	public void noEdgeWeightAndchangeOriginTest()
	{
		Graph<Integer> graph = graph_1();
		Dijkstra_ShortestPath<Integer> dij = new Dijkstra_ShortestPath<>(graph,2);
		
		assertEquals(Integer.MAX_VALUE,dij.getDistance(1));
		assertEquals(0,dij.getDistance(2));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(3));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(4));
		assertEquals(1,dij.getDistance(5));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(6));
	}
	
	@Test
	public void withEdgeWeightTest()
	{
		Graph<Integer> graph = graph_2();
		Dijkstra_ShortestPath<Integer> dij = new Dijkstra_ShortestPath<>(graph,1);
		
		assertEquals(0,dij.getDistance(1));
		assertEquals(1,dij.getDistance(2));
		assertEquals(2,dij.getDistance(3));
		assertEquals(3,dij.getDistance(4));
		assertEquals(3,dij.getDistance(5));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(6));
	}
	
	@Test
	public void withEdgeWeightAndChangeOriginTest()
	{
		Graph<Integer> graph = graph_2();
		Dijkstra_ShortestPath<Integer> dij = new Dijkstra_ShortestPath<>(graph,2);
		
		assertEquals(Integer.MAX_VALUE,dij.getDistance(1));
		assertEquals(0,dij.getDistance(2));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(3));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(4));
		assertEquals(2,dij.getDistance(5));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(6));
	}
	
	@Test
	public void graph3_centre1Test()
	{
		Graph<Integer> graph = graph_3();
		Dijkstra_ShortestPath<Integer> dij = new Dijkstra_ShortestPath<>(graph,1);
		
		assertEquals(0,dij.getDistance(1));
		assertEquals(1,dij.getDistance(2));
		assertEquals(2,dij.getDistance(3));
		assertEquals(1,dij.getDistance(4));
		assertEquals(3,dij.getDistance(5));
		assertEquals(Integer.MAX_VALUE,dij.getDistance(6));
		
		System.out.println(Graph.singleSourceShortestPath(graph, 1));
		System.out.println(Graph.singleSourceShortestPath(Graph.noWeightGraph(graph), 1));
	}
	

	private Graph<Integer> graph_1()
	{
		Graph<Integer> graph = Graph.newInstance();
		
		graph.add(1);
		graph.add(2);
		graph.add(3);
		graph.add(4);
		graph.add(5);
		graph.add(6);
		
		graph.setEdge(1, 2, 1);
		graph.setEdge(1, 3, 1);
		graph.setEdge(1, 4, 1);
		graph.setEdge(2, 5, 1);
		
		return graph;
	}
	
	private Graph<Integer> graph_2()
	{
		Graph<Integer> graph = Graph.newInstance();
		
		graph.add(1);
		graph.add(2);
		graph.add(3);
		graph.add(4);
		graph.add(5);
		graph.add(6);
		
		graph.setEdge(1, 2, 1);
		graph.setEdge(1, 3, 2);
		graph.setEdge(1, 4, 3);
		graph.setEdge(2, 5, 2);
		
		return graph;
	}
	
	private Graph<Integer> graph_3()
	{
		Graph<Integer> graph = Graph.newInstance();
		
		graph.add(6);
		graph.add(5);
		graph.add(4);
		graph.add(3);
		graph.add(2);
		graph.add(1);

		graph.setEdge(1, 2, 1);
		graph.setEdge(1, 3, 4);
		graph.setEdge(1, 4, 1);
		graph.setEdge(2, 4, 1);
		graph.setEdge(2, 3, 1);
		graph.setEdge(4, 2, 1);
		graph.setEdge(4, 5, 2);
		
		return graph;
	}
}
