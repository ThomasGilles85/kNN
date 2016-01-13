package machine.learning.knn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InstanceSpace {
	
	public List<Instance> Instances;
	public List<Instance> LearnInstances;
	public List<Instance> TestInstances;
	
	public InstanceSpace()
	{
		Instances = new ArrayList<Instance>();
		LearnInstances = new ArrayList<Instance>();
		TestInstances = new ArrayList<Instance>();
	}
	
	public void loadInstancesFromCsv() throws FileNotFoundException
	{
		
		List<String> attributes = new ArrayList();
		Map<Integer, Integer> rangeValues = new HashMap<Integer, Integer>();

		String currentLine;
		Instance tempInstance;
		
		Scanner scanner = new Scanner(new File("D:\\data_exercise_8.csv"));
        scanner.useDelimiter(",");
        
        currentLine = scanner.nextLine();
        
        for(String current:currentLine.split(","))
        {
        if(!current.split(":")[1].replaceAll("\r\n24", "").matches("t")){
        	attributes.add(current.split(":")[1]);
        	
        	if(current.split(":")[1].matches("n"))
        	{
        		rangeValues.put(attributes.size()-1, 0);
        	}
        }
        }
        
        int count = -1;
        
        tempInstance = new Instance();
        
        String current;
        
        while(scanner.hasNextLine()){
        	currentLine = scanner.nextLine().replaceAll(" ", "");
            
        	while(count < attributes.size())
            {
            	count ++;
        		current = currentLine.split(",")[count];
        		

        	
        	if(count < attributes.size()-1)
        	{
        		if(attributes.get(count).matches("n"))
        		{
        			tempInstance.Attributes.add(new Attribute(Integer.parseInt(current),"n"));
        			if(Integer.parseInt(current) > rangeValues.get(count))
        			{
        				rangeValues.put(count, Integer.parseInt(current));
        			}
        		}
        		else if(attributes.get(count).matches("c"))
        		{
        			tempInstance.Attributes.add(new Attribute(current,"c"));
        		}
        		else if(attributes.get(count).matches("b"))
        		{
        			if(current.matches("yes"))
            		{
        				tempInstance.Attributes.add(new Attribute(true,"b"));
            		}
            		else
            		{
            			tempInstance.Attributes.add(new Attribute(false,"b"));
            		}     			
        		}
        	}
        	else
        	{
        		if(current.matches("yes"))
        		{
        			tempInstance.TargetClass = true;
        		}
        		else
        		{
        			tempInstance.TargetClass = false;
        		}
            	Instances.add(tempInstance);
        	}
        	System.out.println(count);
            }
        	tempInstance = new Instance();
        	count = -1;
        }   
        scanner.close();
        
        for(Instance instance:Instances)
        {
        	
        	count=0;
        	for(Attribute attribute:instance.Attributes)
        	{
        		if(attribute.Type.matches("n"))
        		{
        			attribute.Range =  rangeValues.get(count);
        		}
        		count ++;
        	}
        }

	}
	
	public void getRandomInstances(int count)
	{
		List<Instance> randomInstances = new ArrayList<Instance>();
		
		Random r = new Random();
		int Low = 0;
		int High = Instances.size();		
		int Result = 0;
		
		for(int i=1; i <= count;i++)
		{
			Result = r.nextInt(High-Low) + Low;
			if(randomInstances.contains(Instances.get(Result)))
			{
				i--;
			}
			else
			{
				randomInstances.add(Instances.get(Result));
			}
		}
		
		LearnInstances = randomInstances;
		
		for(Instance instance: Instances)
		{
			if(!randomInstances.contains(instance))
			{
				TestInstances.add(instance.Copy());
			}
		}				
	}
	
	public List<InstanceSpace> getRandomSplitInstances(int numberSplit) {
		List<InstanceSpace> instanceSpaces = new ArrayList<InstanceSpace>();	
		List<Instance> restInstances = CopyInstances();
		List<Instance> randomInstances;
		
		
		
		Random r = new Random();
		int Low;
		int High;		
		int Result;
		
		InstanceSpace instanceSpace;
		
		for(int split=1;split <= numberSplit;split++)
		{
			Low = 0;
			High = restInstances.size();		
			Result = 0;
			
			instanceSpace = new InstanceSpace();
			instanceSpace.Instances = CopyInstances();
			
			randomInstances = new ArrayList<Instance>();
			
			for(int i=1; i <= Instances.size() / numberSplit;i++)
			{
				Result = r.nextInt(High-Low) + Low;
				if(randomInstances.contains(Instances.get(Result)))
				{
					i--;
				}
				else
				{
					randomInstances.add(Instances.get(Result));
				}
			}
			
			restInstances.removeAll(randomInstances);
			
			instanceSpace.TestInstances = CopyInstances(randomInstances);
			
			for(Instance instance: Instances)
			{
				if(!randomInstances.contains(instance))
				{
					instanceSpace.LearnInstances.add(instance.Copy());
				}
			}	
			
			instanceSpaces.add(instanceSpace);
		}
		
		return instanceSpaces;
	}
	
	public List<Instance> CopyInstances()
	{
		List<Instance> CopyInstances = new ArrayList<Instance>();
		for(Instance instance:Instances)
		{
			CopyInstances.add(instance.Copy());
		}
		
		return CopyInstances;
	}
	
	public List<Instance> CopyInstances(List<Instance> instances)
	{
		List<Instance> CopyInstances = new ArrayList<Instance>();
		for(Instance instance:instances)
		{
			CopyInstances.add(instance.Copy());
		}
		
		return CopyInstances;
	}
}
