package io.github.codejanovic.cli;

import org.apache.commons.cli.CommandLine;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public interface Arguments {

    String zookeeper();
    String kafka();
    String groupId();
    String autoOffsetReset();
    Collection<String> topics();

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
        public Collection<String> topics() {
            return Arrays.asList(commandLine.getOptionValues("topics"));
        }
    }
}
