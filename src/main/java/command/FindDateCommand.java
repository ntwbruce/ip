package command;

import duke.TaskList;

import java.time.LocalDate;

/**
 * Represents the command that finds tasks on the task list that take place on a specific date.
 */
public class FindDateCommand extends Command {
    private final LocalDate desiredDate;

    /**
     * Constructor for a FindDateCommand.
     *
     * @param desiredDate Find tasks that take place on this date.
     */
    public FindDateCommand(LocalDate desiredDate) {
        super();
        this.desiredDate = desiredDate;
    }

    /**
     * Executes the command of finding tasks on the given task list that take place on the given date.
     *
     * @param taskList The task list from which tasks taking place on the given date are taken to be listed.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.findTasksByDate(desiredDate);
    }
}
