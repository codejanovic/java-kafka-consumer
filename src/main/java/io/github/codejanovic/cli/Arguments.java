package io.github.codejanovic.cli;

import org.apache.commons.cli.CommandLine;

import java.io.Serializable;

public interface Arguments {

    String zookeeper();
    String kafka();
    String groupId();
    String autoOffsetReset();
    String topic();

    final class Cli implements Arguments, Serializable {

        private final CommandLine commandLine;

        public Cli(CommandLine commandLine) {
            this.commandLine = commandLine;
        }

        @Override
        public String zookeeper() {
            return commandLine.getOptionValue("zookeeper", "");
        }

        @Override
        public String kafka() {
            return commandLine.getOptionValue("kafka", "");
        }

        @Override
        public String groupId() {
            return commandLine.getOptionValue("groupId", "");
        }

        @Override
        public String autoOffsetReset() {
            return commandLine.getOptionValue("offset", "smallest");
        }

        @Override
        public String topic() {
            return commandLine.getOptionValue("topic", "");
        }
    }
}
