package machine.learning.knn;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {

	public static void main(String[] args) throws FileNotFoundException {
		InstanceSpace instanceSpace = new InstanceSpace();
		
		instanceSpace.loadInstancesFromCsv();
	
		instanceSpace.getRandomInstances(995);
		List<InstanceDistance> distances = new ArrayList<InstanceDistance>();
		
		int k=1;
		
		
		for(Instance instance:instanceSpace.TestInstances)
		{
			for(Instance neighbour:instanceSpace.LearnInstances)
			{
				distances.add(new InstanceDistance(neighbour,neighbour.distanceToInstace(instance)));
			}
			instance.TargetClass = getClass(distances,k);
			distances = new ArrayList<InstanceDistance>();	
		}
		System.out.println("Fertig");
	}
	
	private static boolean getClass(List<InstanceDistance> distances,int k)
	{
		int counttrue = 0;
		int countfalse = 0;
		
		Collections.sort(distances);
		
		for(int i=1;i<=k;i++)
		{
			if(distances.get(i-1).Instance.TargetClass == true)
			{
				counttrue++;
			}
			else
			{
				countfalse++;
			}
		}
		
		if(counttrue >= countfalse)return true;
		
		return false;
	}

}
