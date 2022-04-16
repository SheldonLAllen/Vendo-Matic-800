package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendingMachineLog {

    public VendingMachineLog() throws Exception {}

    public static void log(String message) {
        String path = "log.txt";
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
        File file = new File(dateFormat.format(date) + path);

        try (PrintWriter messageOutput = new PrintWriter(new FileOutputStream(path, true))) {
            messageOutput.println( dateFormat.format((date)) + message);
        }
        catch (IOException e) {
            System.err.println("File was not found");
            System.exit(1);
        }
    }
}
