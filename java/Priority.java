import java.util.*;

public class Priority implements Algorithm{

    private List<Task> queue;
    private int totalTasks;

    // constructor 
    public Priority (List<Task> queue){
        this.queue = new ArrayList<>(queue);
        this.totalTasks = queue.size();
    

    // Sort descending by priority (the higher numerical value, the higher the priority will be.
    // TimSqrt is stable: Equal Priority tasks are sorted in descending order by priority value.
        this.queue.sort(Comparator.comparingInt(Task::getPriority).reversed());
    }

    @Override
    public void schedule(){
        // Put the implementation code here
    

    System.out.println("Starting Priority Scheduling");

    int currentTime = 0;
    double totalTurnaroundTime = 0;
    double totalWaitingTime = 0;
    double totalResponseTime = 0;

    

    while (!queue.isEmpty()) {
        Task currentTask = pickNextTask();

    // Calculate wait and response time
        int respoonseTime = currentTime;
        int waitingTime = currentTime;

    // Dispatch to the virtual CPU for its full burst length
        CPU.run(currentTask, currentTask.getBurst());

        // Monotonically advance the clock by the tasks burst
        currentTime += currentTask.getBurst();

        // Turnaround time is completion time minus arrival time
        int turnaroundTime = currentTime;

        // Accumalate all totals
        totalTurnaroundTime += turnaroundTime;
        totalWaitingTime += waitingTime;
        totalResponseTime += respoonseTime;
    

    System.out.println("Task " + currentTask.getName() + " finished.");
    }

    // Print Performance Averages
    if (totalTasks > 0) {
        System.out.println("\n--- Priority Performance Metrics ---");
        System.out.printf("Average Turnaround Time: %.2f ms\n", (totalTurnaroundTime / totalTasks));
        System.out.printf("Average Waiting Time: %.2f ms \n", (totalWaitingTime / totalTasks));
        System.out.printf("Average Response Time: %.2f ms\n",(totalResponseTime / totalTasks));
        System.out.println("------------------------------------\n");
    }    
}
    @Override
    public Task pickNextTask(){
        if (!queue.isEmpty()) {
            return queue.remove(0);
        }
        return null;
    }
    
}
