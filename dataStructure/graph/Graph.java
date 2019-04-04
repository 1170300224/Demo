package dataStructure.graph;

import java.util.Set;

/**
 * 有向边带（正）权图
 * 顶点必须互异
 * @author s
 *
 * @param <T>	顶点类型，要求为不可变类型（hashcode应当恒定）
 */
public interface Graph<T> 
{
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
	 * 获取所有顶点
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
}




