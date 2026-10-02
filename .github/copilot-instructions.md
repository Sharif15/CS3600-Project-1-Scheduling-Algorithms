# GitHub Copilot Custom Instructions: CS3600 Project 1 (CPU Scheduling)

You are assisting with CS3600 Operating Systems Project 1: CPU Scheduling Algorithms in Java (JDK 17).
Your suggestions must strictly adhere to the project constraints, existing architecture, and textbook principles (*Operating System Concepts*, 10th Ed., Silberschatz et al., Chapter 5).
DO NOT guess, hallucinate external dependencies, or introduce multi-threading, concurrency primitives, or GUI code. This project is a discrete-event, burst-level simulation.

---

## 1. Core Architecture & Classes
All classes live in the default package in `java/`:
- **`Algorithm.java`**: Interface defining `public void schedule()` and `public Task pickNextTask()`.
- **`Task.java`**: Process control block representation.
  - Getters: `getName()`, `getTid()`, `getPriority()`, `getBurst()`.
  - Setters: `setPriority(int)`, `setBurst(int)`.
- **`CPU.java`**: Virtual dispatcher.
  - Method: `CPU.run(Task task, int slice)` prints execution progress.
- **`Driver.java`**: Entry point reading input file and dispatching to algorithm.

---

## 2. Algorithm Constraints

### A. Priority Scheduling (`Priority.java`)
- **Priority Range**: $1$ to $10$. **Higher numerical value = higher priority** ($10$ is highest priority, $1$ is lowest).
- **Execution Model**: **Non-preemptive**. Tasks run to full burst completion.
- **Arrival**: All tasks arrive simultaneously at $t = 0$.
- **Tie-Breaking**: **First-Come, First-Served (FCFS)** based on initial input/file order.
- **Sorting Mechanism**:
  ```java
  this.queue = new ArrayList<>(queue);
  this.totalTasks = queue.size();
  this.queue.sort(Comparator.comparingInt(Task::getPriority).reversed());
  ```
  *(TimSort is stable; sorting descending by priority preserves arrival order for equal keys automatically).*

### B. Round-Robin (`RR.java`)
- **Time Quantum**: Fixed $q = 10\text{ ms}$.
- **Execution Model**: Preemptive round-robin queue rotation.
- A task runs for $\min(\text{remaining\_burst}, 10)$. If remaining burst $> 0$, it is re-queued at the tail.

### C. Priority with Round-Robin (`PriorityRR.java`)
- Higher priority tasks run first.
- If multiple tasks share the same highest priority, they rotate among themselves using Round-Robin ($q = 10\text{ ms}$).

---

## 3. Metric Calculation Standard
For every algorithm, record and report metrics matching the output formatting established in `FCFS.java` and `SJF.java`:
- **Average Turnaround Time**: Time from arrival ($t=0$) to process completion.
- **Average Waiting Time**: Total time spent in ready queue waiting for CPU.
- **Average Response Time**: Time from arrival ($t=0$) to first CPU burst dispatch.
- **Required Output Format**:
  ```java
  System.out.println("\n--- " + algorithmName + " Performance Metrics ---");
  System.out.printf("Average Turnaround Time: %.2f ms\n", (totalTurnaroundTime / totalTasks));
  System.out.printf("Average Waiting Time: %.2f ms\n", (totalWaitingTime / totalTasks));
  System.out.printf("Average Response Time: %.2f ms\n", (totalResponseTime / totalTasks));
  System.out.println("------------------------------------\n");
  ```
- **Division Guard**: Cache `totalTasks = queue.size()` in constructor. Never divide by `queue.size()` at termination as the queue will be empty.
