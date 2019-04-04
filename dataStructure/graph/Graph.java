package dataStructure.graph;

import java.util.Set;

/**
 * ����ߴ�������Ȩͼ
 * ������뻥��
 * @author s
 *
 * @param <T>	�������ͣ�Ҫ��Ϊ���ɱ����ͣ�hashcodeӦ���㶨��
 */
public interface Graph<T> 
{
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
	 * ��ȡ���ж���
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
}




