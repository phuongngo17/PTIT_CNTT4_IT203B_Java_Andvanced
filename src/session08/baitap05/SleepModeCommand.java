package session08.baitap05;

import java.util.*;

public class SleepModeCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command c) {
        commands.add(c);
    }

    @Override
    public void execute() {
        for (Command c : commands) {
            c.execute();
        }
    }
}
