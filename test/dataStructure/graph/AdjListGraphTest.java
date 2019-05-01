package dataStructure.graph;

public class AdjListGraphTest extends GraphTest
{

	@Override
	public Graph<Integer> getTestInstance() {
		return new AdjListGraph<>();
	}

}
