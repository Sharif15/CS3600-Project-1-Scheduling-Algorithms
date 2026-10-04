import java.util.*;
import java.io.*;

public class PriorityRR implements Algorithm{

    private List<Task> queue;
    
    private static final int QUANTUM = 10;

    // constructor 
    public PriorityRR (List<Task> queue){
        this.queue = queue;
    }

    // implementing the functions from Algorithm 

    @Override
    public void schedule(){
    	
    	int currentTime = 0;
    	
 
    	
    	double totalTurnaroundTime = 0;
    	double totalWaitingTime = 0;
    	double totalResponseTime = 0;
    	
    	int numberOfTasks = queue.size();
    	
    	
    	Map<Task, Integer> originalBurst = new HashMap<Task, Integer>();
    	Map<Task, Integer> responseTime = new HashMap<Task, Integer>();
    	
    	for (Task task : queue) {
    		originalBurst.put(task, task.getBurst());
    	
	
    	}
    	
    	
    	
    	for (int priority = 10; priority >= 1; priority--) {
    		List<Task> priorityQueue = new ArrayList<Task>();
    		
    		for (Task task : queue) {
    			if (task.getPriority() == priority) {
    				priorityQueue.add(task);
    			}
    		}
    		
    		
    		
    		
    		
    		while (!priorityQueue.isEmpty()){
    			
    		
    			Task task = priorityQueue.remove(0);
    			
    			
    			if(!responseTime.containsKey(task)) {		
    				responseTime.put(task,  currentTime);
    				totalResponseTime += currentTime;		
    			}
    			
    			
    			
    			int runTime;
    			
    			if(task.getBurst() <= QUANTUM) {
    				runTime = task.getBurst();
    			} else {
    				runTime = QUANTUM;
    			}
    			
    			
    			
    			
    			
    			CPU.run(task,  runTime);
    			currentTime += runTime;
    			task.setBurst(task.getBurst() - runTime);
    			
    			if (task.getBurst() == 0) {
    				int turnaroundTime = currentTime;
    				
    				int waitingTime = turnaroundTime - originalBurst.get(task);
    				totalTurnaroundTime += turnaroundTime;
    				totalWaitingTime += waitingTime;
    				
    			} else {
    				priorityQueue.add(task);
    				
    			}
    			
    			
    			
    			
    			
    		}
    	}
    	
    	
    	
    		System.out.println("The average turnaround time =" + totalTurnaroundTime / numberOfTasks);
    		System.out.println("The average waiting time =" + totalWaitingTime / numberOfTasks);
    		System.out.println("The average response time =" + totalResponseTime / numberOfTasks);
    		
    }

   
  
    
    
    
    
    
    
    
    @Override
    
    
    
    
    
    
    public Task pickNextTask(){
        // dummy return value remove when implementation is done
        if (queue.isEmpty()) {
        	return null;
        }
        Task highestPriorityTask = queue.get(0);
        
        for (Task task : queue)
        {
        	if (task.getPriority() > highestPriorityTask.getPriority()) {
        		highestPriorityTask = task;
        		
        	}
        }
        
        return highestPriorityTask;
    }
    
}
