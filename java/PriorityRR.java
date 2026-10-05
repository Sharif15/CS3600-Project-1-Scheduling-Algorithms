import java.util.*;

public class PriorityRR implements Algorithm{

    private List<Task> queue;
    
    private static final int QUANTUM = 10;

		
	private Map<Integer, Integer> originalBurst = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> responseTime = new HashMap<Integer, Integer>();

    // constructor 
    public PriorityRR (List<Task> queue){
        this.queue =  new ArrayList<>(queue);

		for (Task task : queue) {
    		originalBurst.put(task.getTid(), task.getBurst());
    	}
    }

    // implementing the functions from Algorithm 

    @Override
    public void schedule(){
    	
    	int currentTime = 0;
    	
    
    	double totalTurnaroundTime = 0;
    	double totalWaitingTime = 0;
    	double totalResponseTime = 0;
    	
    	int numberOfTasks = queue.size();
    	
    	
		// The for loop through the priority level is wasting time calculating unnecessary priority levels 
		// Moving the logic to pick next task int the pickNextTask function
    	
    	// for (int priority = 10; priority >= 1; priority--) {
    	// 	List<Task> priorityQueue = new ArrayList<Task>();
    		
    	// 	for (Task task : queue) {
    	// 		if (task.getPriority() == priority) {
    	// 			priorityQueue.add(task);
    	// 		}
    	// 	}
    		
    		
    		
    		// now only runs until queue is empty
    		
    		while (!queue.isEmpty()){
    			
    		
    			// Task task = priorityQueue.remove(0);

				// Using pickNextTask to get the task

				Task task = pickNextTask();
    			
				int taskID = task.getTid();
    			
    			if(!responseTime.containsKey(taskID)) {		
    				responseTime.put(taskID,  currentTime);
    				totalResponseTime += currentTime;		
    			}
    			

				// Using Math min to get smaller of the two value task burst time or QUANTUM

    			int runTime = Math.min(task.getBurst(),QUANTUM);
    			
    			// if(task.getBurst() <= QUANTUM) {
    			// 	runTime = task.getBurst();
    			// } else {
    			// 	runTime = QUANTUM;
    			// }    			
    			
    			
    			CPU.run(task,  runTime);
    			currentTime += runTime;
    			task.setBurst(task.getBurst() - runTime);
    			
    			if (task.getBurst() == 0) {
    				int turnaroundTime = currentTime;
    				
    				int waitingTime = turnaroundTime - originalBurst.get(taskID);
    				totalTurnaroundTime += turnaroundTime;
    				totalWaitingTime += waitingTime;

					// Output to follow the outline in Run.txt 

					System.out.println(
						"Task " + task.getName() + " finished."
					);
    				
    			} else {
    				queue.add(task);
    				
    			}
    			
    		}
    	//}
    	
    	
         if (numberOfTasks > 0) {
            System.out.println("\n--- RR Performance Metrics ---");
            System.out.printf("Average Turnaround Time: %.2f ms\n", (totalTurnaroundTime / numberOfTasks));
            System.out.printf("Average Waiting Time: %.2f ms\n", (totalWaitingTime / numberOfTasks));
            System.out.printf("Average Response Time: %.2f ms\n", (totalResponseTime / numberOfTasks));
            System.out.println("--------------------------------\n");
        }
    		
    }

   
  
    @Override    
    public Task pickNextTask(){
        // dummy return value remove when implementation is done
        if (queue.isEmpty()) {
        	return null;
        }

        Task highestPriorityTask = queue.get(0);

		// Pick the first task of the highest priority in the queue

        for (Task task : queue)
        {
        	if (task.getPriority() > highestPriorityTask.getPriority()) {
        		highestPriorityTask = task;
        		
        	}
        }

		// Removes the highest priority task from the queue since it would be added to the back of the queue if the task has Burst time remaining
		queue.remove(highestPriorityTask);
        
        return highestPriorityTask;
    }
    
}
