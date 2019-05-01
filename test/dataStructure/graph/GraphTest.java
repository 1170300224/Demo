package dataStructure.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

public abstract class GraphTest 
{
	abstract public Graph<Integer> getTestInstance();
	
	@SuppressWarnings("serial")
	@Test
	public void verticesOnlyTest()
	{
		Graph<Integer> graph = graph_1();
		
		assertEquals(new HashSet<Integer>() {{add(1);add(2);add(3);add(4);add(5);}},graph.vertices());
	}
	
	@Test
	public void edgeTest()
	{
		Graph<Integer> graph = graph_1();
		
		assertEquals(1,graph.weight(1, 2));
		assertEquals(2,graph.weight(1, 3));
		assertEquals(3,graph.weight(1, 4));
		assertEquals(0,graph.weight(1, 5));
		assertEquals(4,graph.weight(2, 3));
		assertEquals(0,graph.weight(3, 5));
		
		assertEquals(0,graph.weight(2, 1));
		assertEquals(0,graph.weight(2, 2));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void successorsTest()
	{
		Graph<Integer> graph = graph_1();
		
		assertEquals(new HashSet<Integer>() {{add(2);add(3);add(4);}},graph.successors(1));
		assertEquals(new HashSet<Integer>() {{add(3);add(4);}},graph.successors(2));
		assertEquals(new HashSet<Integer>() ,graph.successors(4));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void precursorTest()
	{
		Graph<Integer> graph = graph_1();
		
		assertEquals(new HashSet<Integer>() {{add(1);}},graph.precursor(2));
		assertEquals(new HashSet<Integer>() {{add(1);add(2);}},graph.precursor(3));
		assertEquals(new HashSet<Integer>() ,graph.precursor(5));
	}
	
	@Test
	public void containsTest()
	{
		Graph<Integer> graph = graph_1();
		
		assertTrue(graph.contains(2));
		assertTrue(graph.contains(4));
		assertFalse(graph.contains(123));
	}
	
	@Test
	public void removeVertexTest()
	{
		Graph<Integer> graph = graph_1();
		
		graph.remove(5);
		assertFalse(graph.contains(5));
		
		graph.remove(4);
		assertFalse(graph.contains(4));
		
		assertEquals(0,graph.weight(1, 4));
		assertEquals(0,graph.weight(2, 4));
	}
	
	@Test
	public void removeEdgeTest()
	{
		Graph<Integer> graph = graph_1();
		
		graph.removeEdge(1, 2);
		assertEquals(0,graph.weight(1, 2));
		
		graph.removeEdge(2, 3);
		assertEquals(0,graph.weight(2, 3));
	}
	
	@Test
	public void resetEdgeTest()
	{
		Graph<Integer> graph = graph_1();
		
		graph.setEdge(1, 2, 10);
		graph.setEdge(2, 3, 11);
		
		assertEquals(10,graph.weight(1, 2));
		assertEquals(11,graph.weight(2, 3));
	}
	
	
	
	
	
	private Graph<Integer> graph_1()
	{
		Graph<Integer> graph = getTestInstance();
		graph.add(1);
		graph.add(2);
		graph.add(3);
		graph.add(4);
		graph.add(5);
		
		graph.setEdge(1, 2, 1);
		graph.setEdge(1, 3, 2);
		graph.setEdge(1, 4, 3);
		graph.setEdge(2, 3, 4);
		graph.setEdge(2, 4, 5);
		return graph;
	}
	
	
}



















