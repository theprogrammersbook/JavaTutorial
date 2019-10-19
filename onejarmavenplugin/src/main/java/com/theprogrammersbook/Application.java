package com.theprogrammersbook;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

public class Application {
   public static final Logger LOG= Logger.getLogger(Application.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(getLocalCurrentDate());
    }

    public static String getLocalCurrentDate(){
        if(LOG.isDebugEnabled()){
            LOG.debug("DEBUG IS ENABLED.");
        }
        LocalDate date = new LocalDate();
       return date.toString();


    }
}
