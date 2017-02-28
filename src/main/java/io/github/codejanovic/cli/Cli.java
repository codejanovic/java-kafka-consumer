package io.github.codejanovic.cli;

import org.apache.commons.cli.*;

public interface Cli {
    Arguments parse(String[] args) throws ParseException;

    final class Standard implements Cli {

        @Override
        public Arguments parse(String[] args) throws ParseException {
            Options options = new Options();

            options.addOption(Option.builder().argName("zookeeper").longOpt("zookeeper").hasArg().required().desc("define zookeeper hostname and port").build())
                    .addOption(Option.builder().argName("kafka").longOpt("kafka").hasArg().required().desc("define kafka hostname and port").build())
                    .addOption(Option.builder().argName("groupId").longOpt("groupId").hasArg().required().desc("define kafka consumer groupid").build())
                    .addOption(Option.builder().argName("offset").longOpt("offset").hasArg().required(false).desc("define auto.offset.reset").build())
                    .addOption(Option.builder().argName("topic").longOpt("topic").hasArg().required().desc("define kafka topic to listen to").build());

            CommandLineParser parser = new DefaultParser();
            return new Arguments.Cli(parser.parse( options, args));
        }
    }
}
