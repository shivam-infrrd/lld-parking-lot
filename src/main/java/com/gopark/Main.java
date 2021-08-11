package com.gopark;

import java.io.IOException;

import com.gopark.commands.CommandExecutorFactory;
import com.gopark.exception.InvalidModeException;
import com.gopark.mode.FileMode;
import com.gopark.mode.InteractiveMode;
import com.gopark.services.ParkingLotService;


public class Main
{

    public static void main( String args[] ) throws IOException
    {

        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory( parkingLotService );

        if ( isInteractiveMode( args ) ) {
            new InteractiveMode( commandExecutorFactory, outputPrinter ).process();
        } else if ( isFileInputMode( args ) ) {
            new FileMode( commandExecutorFactory, outputPrinter, args[0] ).process();
        } else {
            throw new InvalidModeException( "Invalid Mode");
        }
    }


    /**
     * Checks whether the program is running using file input mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in file input mode.
     */
    private static boolean isFileInputMode( final String[] args )
    {
        return args.length == 1;
    }


    /**
     * Checks whether the program is running using interactive shell mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in interactive shell mode.
     */
    private static boolean isInteractiveMode( final String[] args )
    {
        return args.length == 0;
    }
}
