package org.camunda.emulator;

import jline.console.ConsoleReader;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

public class main {

    private static final Logger logger = LoggerFactory.getLogger("CamundaUserTaskDebugLogger");

    public static void main(String args[]) throws Exception {

        logger.info("User task emulator started", main.class.getSimpleName());

        // subscribe to nats topics
        Nats.subscribe(MessageRouter.getMapping());

        InputStream inStream = new FileInputStream(FileDescriptor.in);
        ConsoleReader reader = new ConsoleReader(inStream, System.out, null);

        reader.setPrompt("> ");

        reader.addCompleter(new FileNameCompleter());
        reader.addCompleter(new StringsCompleter(Arrays.asList("q", "exit", "quit")));

        String line;
        PrintWriter out = new PrintWriter(reader.getOutput());

        while ((line = reader.readLine()) != null) {

            String[] cmd = line.split("\\s+");

            if (cmd.length == 2 && cmd[0].equalsIgnoreCase("start")){
                logger.info(String.format("starting process '%s'", cmd[1]));
            } else {
                help(out);
            }
        }

        // ensure that all content is written to the screen at the end to make unit tests stable
        reader.flush();

        logger.info("User task emulator stopped", main.class.getSimpleName());

    }

    static void help(PrintWriter out) {
        out.println("incorrect input");
        System.exit(-1);
    }

}


