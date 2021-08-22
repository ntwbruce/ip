import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            boolean isBye = false;
            ArrayList<Task> taskList = new ArrayList<>(100);
            int listLength = 0;

            File data = new File("data");
            File disk = new File("data/duke.txt");
            if (data.mkdir()) {
                System.out.println("data directory created!");
            }
            if (disk.createNewFile()) {
                System.out.println("Hard disk created!");
            }

            System.out.println("  ____________________________________________________________");
            System.out.print("  Hello! I'm Duck.\n  What's up?\n");
            System.out.println("  ____________________________________________________________\n");

            while (!isBye) {
                try {
                    System.out.print("> ");
                    String text = input.nextLine().trim();

                    System.out.println("  ____________________________________________________________");

                    if (text.split("\\s+")[0].equals("bye")) { // bye function: exits the loop, ends process
                        System.out.println("  See you next time!");
                        isBye = true;

                    } else if (text.split("\\s+")[0].equals("list")) { // list function: iterates through taskList, prints Tasks' listEntry
                        System.out.println("  Here are the tasks in your list:");
                        for (int i = 0; i < listLength; ++i) {
                            System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
                        }

                    } else if (text.split("\\s+")[0].equals("done")) { // done function: sets a task to done
                        if (text.split("\\s+").length == 1) {
                            throw new DukeException(DukeExceptionType.INVALIDDONE);
                        } else {
                            int toSet = Integer.parseInt(text.split("\\s+")[1]);
                            if (toSet > listLength || toSet < 1) {
                                throw new DukeException(DukeExceptionType.INVALIDDONE);
                            } else {
                                taskList.get(toSet - 1).setDone();
                                System.out.print("  Nice! I've marked this task as done:\n    "
                                        + taskList.get(toSet - 1).listEntry() + "\n");
                            }
                        }

                    } else if (text.split("\\s+")[0].equals("delete")) { // delete function: delete a task
                        if (text.split("\\s+").length == 1) {
                            throw new DukeException(DukeExceptionType.INVALIDDELETE);
                        } else {
                            int toDelete = Integer.parseInt(text.split("\\s+")[1]);
                            if (toDelete > listLength || toDelete < 1) {
                                throw new DukeException(DukeExceptionType.INVALIDDELETE);
                            } else {
                                Task deleted = taskList.remove(toDelete - 1);
                                System.out.print("  Noted. I've removed this task:\n    "
                                        + deleted.listEntry()
                                        + "\n  Now you have " + --listLength + " tasks in the list.\n");
                            }
                        }

                    } else { // task function: add tasks
                        if (text.split("\\s+").length == 1) { // task details not given or not valid task
                            switch (text) {
                            case "deadline":
                                throw new DukeException(DukeExceptionType.DEADLINEDESC);

                            case "event":
                                throw new DukeException(DukeExceptionType.EVENTDESC);

                            case "todo":
                                throw new DukeException(DukeExceptionType.TODODESC);

                            default:
                                throw new DukeException(DukeExceptionType.INVALIDINPUT);
                            }

                        } else {
                            Task newTask;

                            // split text string, first string will be the task type and second string will be task details
                            String[] taskString = text.split("\\s+", 2);
                            String taskType = taskString[0];
                            String taskDetails = taskString[1];

                            // determine type of task, create new task
                            switch (taskType) {
                            case "deadline": {
                                String[] details = taskDetails.split(" /by ");

                                if (details.length == 1) { // time of deadline not given
                                    throw new DukeException(DukeExceptionType.DEADLINETIME);
                                } else {
                                    newTask = new Deadline(details[0], details[1]);
                                }
                                break;
                            }
                            case "event": {
                                String[] details = taskDetails.split(" /at ");

                                if (details.length == 1) { // period of event not given
                                    throw new DukeException(DukeExceptionType.EVENTPERIOD);
                                } else {
                                    newTask = new Event(details[0], details[1]);
                                }
                                break;
                            }
                            case "todo":
                                newTask = new Todo(taskDetails);
                                break;
                            default:  // taskName is invalid
                                throw new DukeException(DukeExceptionType.INVALIDINPUT);
                            }
                            // add task to taskList
                            taskList.add(listLength++, newTask);
                            System.out.print("  Got it. I've added this task:\n    "
                                    + newTask.listEntry()
                                    + "\n  Now you have " + listLength + " tasks in the list.\n");
                        }
                    }

                } catch (DukeException e) {
                    System.out.println(e.getMessage());

                } catch (NumberFormatException e) { // throws if index given in done/delete functions is not an integer
                    System.out.println(new DukeException(DukeExceptionType.INVALIDDONE).getMessage());

                } finally {
                    System.out.println("  ____________________________________________________________\n");
                }
            }

            input.close();
            
        } catch (IOException e) {
            System.out.println(new DukeException(DukeExceptionType.DUKEIO).getMessage());
        }
    }
}
