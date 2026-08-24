package corejava;

//Implement a priority queue-based task scheduler.

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Task {

    private final int id;
    private final String name;
    private final int priority;
    private final LocalDateTime executionTime;

    public Task(int id, String name, int priority,
                LocalDateTime executionTime) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.executionTime = executionTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", executionTime=" + executionTime +
                '}';
    }
}

class TaskScheduler {

    /*
     * Higher priority first.
     *
     * If priority is same:
     * Earlier execution time first.
     */
    private final Queue<Task> taskQueue =
            new PriorityQueue<>(
                    Comparator
                            .comparingInt(Task::getPriority)
                            .reversed()
                            .thenComparing(Task::getExecutionTime)
            );

    public void addTask(Task task) {
        taskQueue.offer(task);
        System.out.println("Added: " + task);
    }

    public Task getNextTask() {
        return taskQueue.peek();
    }

    public Task executeNextTask() {

        Task task = taskQueue.poll();

        if (task == null) {
            System.out.println("No tasks available.");
            return null;
        }

        System.out.println("Executing: " + task);

        // Simulate task execution
        System.out.println(
                "Task " + task.getId() +
                " - " + task.getName() +
                " completed."
        );

        return task;
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public int size() {
        return taskQueue.size();
    }
}

public class PriorityTaskScheduler {

    public static void main(String[] args) {

        TaskScheduler scheduler = new TaskScheduler();

        LocalDateTime now = LocalDateTime.now();

        scheduler.addTask(
                new Task(
                        1,
                        "Generate Report",
                        2,
                        now.plusMinutes(30)
                )
        );

        scheduler.addTask(
                new Task(
                        2,
                        "Process Payment",
                        5,
                        now.plusMinutes(10)
                )
        );

        scheduler.addTask(
                new Task(
                        3,
                        "Send Email",
                        1,
                        now.plusMinutes(5)
                )
        );

        scheduler.addTask(
                new Task(
                        4,
                        "Fraud Detection",
                        5,
                        now.plusMinutes(20)
                )
        );

        scheduler.addTask(
                new Task(
                        5,
                        "Database Backup",
                        3,
                        now.plusMinutes(15)
                )
        );

        System.out.println("\nNext Task:");
        System.out.println(scheduler.getNextTask());

        System.out.println("\nExecuting Tasks:");

        while (!scheduler.isEmpty()) {
            scheduler.executeNextTask();
        }
    }
}
