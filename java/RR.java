import java.util.*;

public class RR implements Algorithm {

    private List<Task> queue;
    private int totalTasks;
    private final int TIME_QUANTUM = 10; // Explicitly set to 10 ms per specifications

    // Performance metric tracking structures
    private Map<String, Integer> originalBursts = new HashMap<>();
    private Map<String, Integer> responseTimes = new HashMap<>();

    // Constructor 
    public RR(List<Task> queue) {
        // Simple shallow copy of the list matches FCFS.java and Priority.java
        this.queue = new ArrayList<>(queue);
        this.totalTasks = queue.size();

        // Snapshot the original burst before any preemption mutations occur
        for (Task task : queue) {
            originalBursts.put(task.getName(), task.getBurst());
        }
    }

    @Override
    public void schedule() {
        System.out.println("Starting Round Robin Scheduling\n");

        int currentTime = 0;
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;
        double totalResponseTime = 0;

        // Core discrete-event simulation loop
        while (!queue.isEmpty()) {
            Task currentTask = pickNextTask();
            String taskName = currentTask.getName();

            // Track Response Time: Log timestamp only on the task's very first CPU interaction
            if (!responseTimes.containsKey(taskName)) {
                responseTimes.put(taskName, currentTime);
                totalResponseTime += currentTime; // R_i = t_first_run - A_i (where arrival A_i = 0)
            }

            int currentBurst = currentTask.getBurst();

            // Evaluate if task finishes within the active time slice
            if (currentBurst <= TIME_QUANTUM) {
                // Task finishes
                CPU.run(currentTask, currentBurst);
                currentTime += currentBurst;

                int turnaroundTime = currentTime; // C_i - A_i (Arrival is 0)
                int originalBurst = originalBursts.get(taskName);
                int waitingTime = turnaroundTime - originalBurst; // W_i = C_i - A_i - B_i

                totalTurnaroundTime += turnaroundTime;
                totalWaitingTime += waitingTime;

                System.out.println("Task " + taskName + " finished.");
            } else {
                // Task is preempted
                CPU.run(currentTask, TIME_QUANTUM);
                currentTime += TIME_QUANTUM;

                // Mutate remaining burst capacity and cycle back to the tail of the ready queue
                currentTask.setBurst(currentBurst - TIME_QUANTUM);
                queue.add(currentTask);
            }
        }

        // Print Performance Metrics matching the formatting of your other completed classes
        if (totalTasks > 0) {
            System.out.println("\n--- RR Performance Metrics ---");
            System.out.printf("Average Turnaround Time: %.2f ms\n", (totalTurnaroundTime / totalTasks));
            System.out.printf("Average Waiting Time: %.2f ms\n", (totalWaitingTime / totalTasks));
            System.out.printf("Average Response Time: %.2f ms\n", (totalResponseTime / totalTasks));
            System.out.println("--------------------------------\n");
        }
    }

    @Override
    public Task pickNextTask() {
        if (!queue.isEmpty()) {
            return queue.remove(0); // Dequeue from the front (FIFO execution)
        }
        return null;
    }
}
