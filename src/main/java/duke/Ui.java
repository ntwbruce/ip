package duke;

import exception.DukeException;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deals with user interactions, mostly printing messages to the console following user commands.
 */
public class Ui {
    /**
     * Displays the program closing message.
     *
     * @return The program closing message.
     */
    public String showBye() {
        return "See you next time!";
    }

    /**
     * Displays the current task list.
     *
     * @param taskList The task list to be displayed.
     * @return The task list in string representation.
     */
    public String showList(ArrayList<Task> taskList) {
        StringBuilder list = new StringBuilder("  Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            String entry = "  " + (i + 1) + "." + taskList.get(i).listEntry() + "\n";
            list.append(entry);
        }
        return list.toString();
    }

    /**
     * Displays a message to show that the given task has been set to done.
     *
     * @param toSetDone The task that has been set to done.
     * @return String representing the task being done.
     */
    public String showDone(Task toSetDone) {
        return "Nice! I've marked this task as done:\n"
                + toSetDone.listEntry() + "\n";
    }

    /**
     * Displays a message to show that the given task has been deleted.
     *
     * @param deleted The task that has been deleted.
     * @param listLength The new length of the task list after the deletion.
     * @return String representing the task being deleted.
     */
    public String showDelete(Task deleted, int listLength) {
        return "Noted. I've removed this task:\n"
                + deleted.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Displays a list of tasks in the current task list that takes place on the given date.
     *
     * @param taskList The task list.
     * @param listLength The length of the current task list.
     * @param desiredDate The date used to find tasks.
     * @return A list of tasks taking place/to be done on the given date.
     */
    public String showDateFind(ArrayList<Task> taskList, int listLength, LocalDate desiredDate) {
        StringBuilder findList = new StringBuilder("  Here are the tasks for the given day:\n");
        for (int i = 0; i < listLength; ++i) {
            Task currTask = taskList.get(i);
            if (currTask.isTodayTask(desiredDate)) {
                String entry = "  " + (i + 1) + "." + taskList.get(i).listEntry() + "\n";
                findList.append(entry);
            }
        }
        return findList.toString();
    }

    /**
     * Displays a list of tasks in the current task list that contain the given keyword.
     *
     * @param taskList The task list.
     * @param listLength The length of the current task list.
     * @param keyword The keyword to look for in the task names.
     * @return A list of tasks containing the given keyword.
     */
    public String showKeywordFind(ArrayList<Task> taskList, int listLength, String keyword) {
        StringBuilder findList = new StringBuilder("Here are the tasks with the given keyword:\n");
        for (int i = 0; i < listLength; ++i) {
            Task currTask = taskList.get(i);
            if (currTask.containsKeyword(keyword)) {
                String entry = "  " + (i + 1) + "." + taskList.get(i).listEntry() + "\n";
                findList.append(entry);
            }
        }
        return findList.toString();
    }

    /**
     * Displays a message to show that the given task has been added to the task list.
     *
     * @param newTask The task that has been added to the task list.
     * @param listLength The new length of the task list after the addition.
     * @return String representing the task being added.
     */
    public String showAdd(Task newTask, int listLength) {
        return "Got it. I've added this task:\n"
                + newTask.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Displays the error message of the given DukeException.
     *
     * @param e The DukeException whose error message is to be displayed.
     * @return The DukeException error message.
     */
    public String showException(DukeException e) {
        return e.getMessage();
    }
}
