package machine.learning.knn;

public class Attribute<T> {
	public T Value;
	public String Type;
	public double Range;
	
	public Attribute(T value, String type)
	{
		Value = value;
		Type = type;
	}

	//The Function will calculate the distence to an other attribute
	public double distanceToAttribute(Attribute<T> attribute)
	{
		double distance=-1;
		
		if(Type.matches("n"))
		{
			distance = ((double)Math.abs((int)Value - (int)attribute.Value)) / Range;
			
		}
		else if(Type.matches("c") || Type.matches("b"))
		{
			if(Value == attribute.Value)
			{
				distance = 0;
			}
			else
			{
				distance = 1;
			}
		}
		
		return distance;
		
	}

	//Deep Copy the Attribute
	public Attribute<T> Copy() {
		Attribute<T> attribute = new Attribute<T>(Value, Type);
		attribute.Range = Range;	 
		return attribute;
	}
}
