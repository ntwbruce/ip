package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private final boolean isTimeGiven;

    public Deadline(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        isTimeGiven = false;
    }

    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        isTimeGiven = true;
    }

    @Override
    public String listEntry() {
        if (isTimeGiven) {
            return "[D]" + super.listEntry() 
                    + " (by " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                    + " at " + deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
        } else {
            return "[D]" + super.listEntry() 
                    + " (by " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    @Override
    public String databaseEntry() {
        if (isTimeGiven) {
            return "D" + super.databaseEntry() + " " + deadlineDate + " " + deadlineTime;
        } else {
            return "D" + super.databaseEntry() + " " + deadlineDate;
        }
    }
    
    @Override
    public boolean isTodayTask(LocalDate l) {
        return l.isEqual(deadlineDate);
    }
}