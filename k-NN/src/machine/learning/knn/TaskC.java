package machine.learning.knn;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {

	public static void main(String[] args) throws FileNotFoundException {
		InstanceSpace instanceSpace = new InstanceSpace();
		
		instanceSpace.loadInstancesFromCsv();
			
		List<InstanceSpace> workInstanceSpace = instanceSpace.getRandomSplitInstances(10);
		
		List<InstanceDistance> distances = new ArrayList<InstanceDistance>();
		
		int k=3;
		int error=0;
		
		for(InstanceSpace instanceSpaceSplit:workInstanceSpace)
		{
			for(Instance instance:instanceSpaceSplit.TestInstances)
			{
				for(Instance neighbour:instanceSpaceSplit.LearnInstances)
				{
					distances.add(new InstanceDistance(neighbour,neighbour.distanceToInstace(instance)));
				}
				if(instance.TargetClass != getClass(distances,k))
				{
					error++;
				}
				distances = new ArrayList<InstanceDistance>();	
			}			
		}
		
		error = error / workInstanceSpace.size();

		System.out.println("K: " + k + " Error: " + error);
	}
	
	private static boolean getClass(List<InstanceDistance> distances,int k)
	{
		int counttrue = 0;
		int countfalse = 0;
		
		Collections.sort(distances);
		
		for(int i=1;i<=1;i++)
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
