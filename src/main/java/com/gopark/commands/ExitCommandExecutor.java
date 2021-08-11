package com.gopark.commands;

import com.gopark.OutputPrinter;
import com.gopark.models.Command;
import com.gopark.services.ParkingLotService;


/**
 * Executor to handle command of exiting from the parking lot service. This is used in interactive
 * mode to exit.
 */
public class ExitCommandExecutor extends CommandExecutor
{
    public static final String COMMAND_NAME = "unpark";

    public ExitCommandExecutor( final ParkingLotService parkingLotService, final OutputPrinter outputPrinter )
    {
        super( parkingLotService, outputPrinter );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate( final Command command )
    {
        return command.getParams().isEmpty();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void execute( final Command command )
    {
        parkingLotService.unPark( command.getParams().get( 0 ) );
    }
}
