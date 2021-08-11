package com.gopark.commands;

import com.gopark.OutputPrinter;
import com.gopark.exception.NoFreeSlotAvailableException;
import com.gopark.models.Command;
import com.gopark.models.Vechile;
import com.gopark.services.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor
{
    
    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(
        final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
      super(parkingLotService, outputPrinter);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final Command command) {
      return command.getParams().size() == 2;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Command command) {
      final Vechile vehicle = new Vechile(command.getParams().get(0), command.getParams().get(1), command.getParams().get( 2 ));
      try {
        parkingLotService.park(vehicle);
      } catch (NoFreeSlotAvailableException exception) {
        outputPrinter.parkingLotFull();
      }
    }
    
}