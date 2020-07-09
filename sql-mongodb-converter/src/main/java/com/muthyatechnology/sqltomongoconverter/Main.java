package com.muthyatechnology.sqltomongoconverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static final int DEFAULT_RESULT_BATCH_SIZE = 50;
    public static final String ENTER_SQL_TEXT = "Enter input sql:\n\n ";
    private static final String DEFAULT_MONGO_PORT = "27017";

    public static Options buildOptions() {
        Options options = new Options();


        final OptionGroup sourceOptionGroup = new OptionGroup();
        sourceOptionGroup.setRequired(false);

        sourceOptionGroup.addOption(Option.builder("s")
                .longOpt("sourceFile")
                .hasArg(true)
                .required(false)
                .desc("the source file.")
                .build());

        sourceOptionGroup.addOption(Option.builder("i")
                .longOpt("interactiveMode")
                .hasArg(false)
                .required(false)
                .desc("interactive mode")
                .build());

        sourceOptionGroup.addOption(Option.builder("sql")
                .longOpt("sql")
                .hasArg(true)
                .required(false)
                .desc("the sql select statement")
                .build());


        options.addOption(Option.builder("d")
                .longOpt("destinationFile")
                .hasArg(true)
                .required(false)
                .desc("the destination file.  Defaults to System.out")
                .build());

        options.addOption(Option.builder("h")
                .longOpt("host")
                .hasArg(true)
                .required(false)
                .desc("hosts and ports in the following format (host:port) default port is " + DEFAULT_MONGO_PORT)
                .build());

        options.addOption(Option.builder("db")
                .longOpt("database")
                .hasArg(true)
                .required(false)
                .desc("mongo database")
                .build());

        options.addOption(Option.builder("a")
                .longOpt("auth database")
                .hasArg(true)
                .required(false)
                .desc("auth mongo database")
                .build());

        options.addOption(Option.builder("u")
                .longOpt("username")
                .hasArg(true)
                .required(false)
                .desc("usename")
                .build());

        options.addOption(Option.builder("p")
                .longOpt("password")
                .hasArg(true)
                .required(false)
                .desc("password")
                .build());

        options.addOption(Option.builder("b")
                .longOpt("batchSize")
                .hasArg(true)
                .required(false)
                .desc("batch size for query results")
                .build());

        options.addOptionGroup(sourceOptionGroup);

        return options;
    }

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, org.apache.commons.cli.ParseException {
            Options options = buildOptions();


            CommandLineParser parser = new DefaultParser();
            HelpFormatter help = new HelpFormatter();
            help.setOptionComparator(new OptionComparator(Arrays.asList("s","sql","i","d","h","db","a","u","p","b")));

            CommandLine cmd = null;
            try {
                cmd = parser.parse(options, args);

                String source = cmd.getOptionValue("s");
                boolean interactiveMode = cmd.hasOption('i');

                String[] hosts = cmd.getOptionValues("h");
                String db = cmd.getOptionValue("db");
                String username = cmd.getOptionValue("u");
                String password = cmd.getOptionValue("p");
                String destination = cmd.getOptionValue("d");
                String authdb = cmd.getOptionValue("a");
                String sql = cmd.getOptionValue("sql");
                final int batchSize = Integer.parseInt(cmd.getOptionValue("b", ""+DEFAULT_RESULT_BATCH_SIZE));

                isFalse(hosts!=null && db==null,"provided option h, but missing db");
                isFalse(username!=null && (password==null || authdb==null),"provided option u, but missing p or a");

                isTrue(interactiveMode || source!=null || sql !=null,"Missing required option: s or i or sql");

               

            } catch (org.apache.commons.cli.ParseException e) {
                System.err.println(e.getMessage());
                help.printHelp(Main.class.getName(), options, true);
               // throw e;
            }

            System.exit(0);
        }

    private static void isTrue(boolean expression, String message) throws org.apache.commons.cli.ParseException {
        if (!expression) {
            throw new org.apache.commons.cli.ParseException(message);
        }
    }

    private static void isFalse(boolean expression, String message) throws org.apache.commons.cli.ParseException {
        if (expression) {
            throw new org.apache.commons.cli.ParseException(message);
        }
    }


    private static class OptionComparator implements Comparator<Option> {
        private final List<String> orderList;

        public OptionComparator(List<String> orderList) {
            this.orderList = orderList;
        }


        @Override
        public int compare(Option o1, Option o2) {
            int index1 = orderList.indexOf(o1.getOpt());
            int index2 = orderList.indexOf(o2.getOpt());
            return index1 - index2;
        }
    }

}



