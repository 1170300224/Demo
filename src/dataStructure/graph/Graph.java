package dataStructure.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import dataStructure.graph.algorithm.Dijkstra_ShortestPath;


/**
 * ����ߴ�������Ȩͼ
 * ������뻥��
 * @author s
 *
 * @param <T>	�������ͣ�Ҫ��Ϊ���ɱ����ͣ�hashcodeӦ���㶨��
 */
public interface Graph<T> 
{

	static <T> Graph<T> newInstance()
	{
		return new AdjListGraph<T>();
	}
	
	/**
	 * ��Ӷ���
	 * @param vertex 
	 * @return ��Ӹı���ͼ����
	 */
	boolean add(T vertex);
	
	/**
	 * ɾ�����㼰���ڽӱ�
	 * @param vertex
	 * @return	ɾ���ı���ͼ����
	 */
	boolean remove(T vertex);
	
	/**
	 * ��ȡ��Ȩֵ
	 * @param form
	 * @param to
	 * @return ��Ȩֵ��0��ʾ�ޱ�
	 */
	int weight(T from,T to);
	
	/**
	 * �����е���������ô�Ȩ�����
	 * @param form
	 * @param to
	 * @param weight ��
	 * @return ͼ���ı�����
	 */
	boolean setEdge(T from,T to,int weight);
	
	/**
	 * ����ɾ����
	 * @param from
	 * @param to
	 * @return ͼ���ı�����
	 */
	boolean removeEdge(T from,T to);
	
	/**
	 * ��ȡ���ж���Ĳ����޸���ͼ
	 * @return
	 */
	Set<T> vertices();
	
	/**
	 * ��ȡvertice�ĺ�̶���
	 * @param vertice
	 * @return
	 */
	Set<T> successors(T vertice);
	
	/**
	 * ��ȡvertice��ǰ������
	 * @param vertice
	 * @return
	 */
	Set<T> precursor(T vertice);
	
	/**
	 * ��ȡ������
	 * @return
	 */
	int size();
	
	/**
	 * �ж��Ƿ񺬶���vertex
	 * @param vertex
	 * @return
	 */
	boolean contains(T vertex);
	
	/**
	 * ������Ȩͼ�Ĳ����޸���Ȩͼ��ͼ
	 * ԭͼ�ı�Ȩ����Ϊ1���бߣ���0���ޱߣ�
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
				throw new RuntimeException("��ͼ�Բ����޸���ͼ�����޸���");
			}

			@Override
			public boolean remove(T vertex) {
				throw new RuntimeException("��ͼ�Բ����޸���ͼ�����޸���");
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
				throw new RuntimeException("��ͼ�Բ����޸���ͼ�����޸���");
			}

			@Override
			public boolean removeEdge(T from, T to) {
				throw new RuntimeException("��ͼ�Բ����޸���ͼ�����޸���");
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
	 * ���㵥Դ���·��
	 * @param graph
	 * @param origin
	 * @return <Ŀ�궥��,<���·�����ȣ����·��>>
	 */
	static <T> Map<T,Pair<Integer,List<T>>> singleSourceShortestPath(Graph<T> graph,T origin)
	{
		return Dijkstra_ShortestPath.singleSourceShortestPath(graph, origin);
	}
}




