package machine.learning.knn;

import java.util.ArrayList;
import java.util.List;

public class Instance {
	public List<Attribute> Attributes;
	public boolean TargetClass;
	
	public Instance()
	{
		Attributes = new ArrayList<Attribute>();
	}
	
	public double distanceToInstace(Instance instance)
	{
		
		double distance = 0;
		
		for(int i=0;i < Attributes.size();i++)
		{
			distance += Attributes.get(i).distanceToAttribute(instance.Attributes.get(i));
		}
		
		distance = distance / Attributes.size();
		
		return distance;
	}

	public Instance Copy() {
		Instance instance = new Instance();
		
		instance.TargetClass = this.TargetClass;
		
		for(Attribute attribute:Attributes)
		{
			instance.Attributes.add(attribute.Copy());
		}		
		return instance;
	}
	
}
