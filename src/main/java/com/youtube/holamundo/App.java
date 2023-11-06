package com.youtube.holamundo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class App {

private static Logger log = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        log.debug( "Hello World!");
    }
}
