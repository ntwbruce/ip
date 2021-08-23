package command;

import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private boolean isBye;

    public Command() {
        isBye = false;
    }
    
    public Command(boolean isBye) {
        this.isBye = isBye;
    }
    
    public abstract void execute(Ui ui, TaskList taskList);
    
    public boolean isBye() {
        return isBye;
    }
}