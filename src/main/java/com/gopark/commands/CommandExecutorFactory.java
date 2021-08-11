package com.gopark.commands;

import java.util.HashMap;
import java.util.Map;

import com.gopark.OutputPrinter;
import com.gopark.exception.InvalidCommandException;
import com.gopark.models.Command;
import com.gopark.services.ParkingLotService;

/**
 * Factory to get correct {@link CommandExecutor} from a given command.
 */
public class CommandExecutorFactory {
  private Map<String, CommandExecutor> commands = new HashMap<>();

  public CommandExecutorFactory(final ParkingLotService parkingLotService) {
    final OutputPrinter outputPrinter = new OutputPrinter();
    
    commands.put(
        ParkCommandExecutor.COMMAND_NAME,
        new ParkCommandExecutor(parkingLotService, outputPrinter));
    commands.put(
        ExitCommandExecutor.COMMAND_NAME,
        new ExitCommandExecutor(parkingLotService, outputPrinter));
  }

  /**
   * Gets {@link CommandExecutor} for a particular command. It basically uses name of command to
   * fetch its corresponding executor.
   *
   * @param command Command for which executor has to be fetched.
   * @return Command executor.
   */
  public CommandExecutor getCommandExecutor(final Command command) {
    final CommandExecutor commandExecutor = commands.get(command.getCommandName());
    if (commandExecutor == null) {
      throw new InvalidCommandException();
    }
    return commandExecutor;
  }
}
