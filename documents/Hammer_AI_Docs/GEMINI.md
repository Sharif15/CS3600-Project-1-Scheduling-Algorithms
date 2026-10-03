# Project Context: CS3600-001 Operating Systems - Project 1 (CPU Scheduling)

## 1. Course & Academic Alignment
- **Course**: CS3600-001 Operating Systems (Fall 2026)
- **Primary Source**: *Operating System Concepts* (10th Edition, Silberschatz, Galvin, Gagne), Chapter 5: CPU Scheduling.
- **Strict Course Separation**: This is an Operating Systems project. Keep all concepts, code, and documentation strictly separated from PPL.

---

## 2. Project Architecture & Environment
- **Language**: Java (JDK 17)
- **Source Directory**: `java/`
- **Compiler / Runtime**:
  - `javac *.java`
  - `java Driver <algorithm> <schedule>`
- **Team Remote**: `https://github.com/Sharif15/CS3600-Project-1-Scheduling-Algorithms.git`
- **Personal Fork Remote**: `https://github.com/thienngonguyenle/CS3600-001-F26-Project-1-Scheduling-Algorithms.git`

---

## 3. Scheduling Algorithms Required
1. **First-Come, First-Served (FCFS)**: Completed (`java/FCFS.java`).
2. **Shortest-Job-First (SJF)**: Completed (`java/SJF.java`). Non-preemptive, ordered ascending by CPU burst.
3. **Priority Scheduling (`java/Priority.java`)**:
   - Priority range: $1$ to $10$, where **10 is highest priority** and **1 is lowest**.
   - Non-preemptive execution.
   - All tasks arrive simultaneously at $t = 0$.
   - **Tie-Breaking**: FCFS (preserve relative order of arrival in input file).
4. **Round-Robin (RR) (`java/RR.java`)**:
   - Time quantum $q = 10\text{ ms}$.
   - Preemptive task rotation through ready queue.
5. **Priority with Round-Robin (`java/PriorityRR.java`)**:
   - Executes higher-priority tasks first.
   - Tasks sharing the same priority level rotate via Round-Robin with quantum $q = 10\text{ ms}$.

---

## 4. Performance Metrics (Required for All Algorithms)
For every scheduling run, the following averages must be reported formatted to two decimal places (`%.2f ms`):
- **Average Turnaround Time**: $\overline{T}_{\text{turnaround}} = \frac{1}{n} \sum_{i=1}^n (C_i - A_i)$
- **Average Waiting Time**: $\overline{T}_{\text{wait}} = \frac{1}{n} \sum_{i=1}^n (C_i - A_i - B_i)$
- **Average Response Time**: $\overline{T}_{\text{response}} = \frac{1}{n} \sum_{i=1}^n (t_{\text{first\_run}}(T_i) - A_i)$

*(Note: Under non-preemptive simultaneous arrival at $t=0$, $T_{\text{wait}} \equiv T_{\text{response}} = t_{\text{start}}$, but for preemptive algorithms like RR, waiting time and response time diverge).*

---

## 5. Team Git Workflow & Contribution Rules
- Do not commit `.class` files (`.gitignore` must contain `*.class`).
- Develop feature work on dedicated branches (`git checkout -b <branch-name>`).
- Keep branches synchronized with upstream `main` (`git fetch sharif; git merge sharif/main`).
- Submission format: Canvas Git bundle (`git bundle create <team_name>.bundle --all`).
