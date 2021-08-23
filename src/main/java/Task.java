import java.time.LocalDate;

public abstract class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public String listEntry() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
    
    public abstract boolean isTodayTask(LocalDate l);
}
