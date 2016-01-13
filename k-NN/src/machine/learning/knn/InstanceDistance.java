package machine.learning.knn;

public class InstanceDistance implements Comparable<InstanceDistance>{
	public Instance Instance;
	public double Distance;
	
	public InstanceDistance(Instance instance, double distance)
	{
		Instance = instance;
		Distance = distance;
	}

	@Override
	public int compareTo(InstanceDistance arg0) {
		return Double.compare(this.Distance,arg0.Distance);
	}
}
