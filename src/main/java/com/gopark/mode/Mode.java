package com.gopark.mode;

import java.io.IOException;

import com.gopark.OutputPrinter;
import com.gopark.commands.CommandExecutor;
import com.gopark.commands.CommandExecutorFactory;
import com.gopark.models.Command;

public abstract class Mode
{

    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;
    
    public Mode(
        final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
      this.commandExecutorFactory = commandExecutorFactory;
      this.outputPrinter = outputPrinter;
    }
    
    /**
     * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
     * command.
     *
     * @param command Command to be processed.
     */
    protected void processCommand(final Command command) {
      final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
      commandExecutor.execute(command);
      
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    public abstract void process() throws IOException;
}
