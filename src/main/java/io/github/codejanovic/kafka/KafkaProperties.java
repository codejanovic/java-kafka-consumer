package io.github.codejanovic.kafka;

import io.github.codejanovic.cli.Arguments;
import java.util.Properties;

public interface KafkaProperties {
    Properties get();

    final class FromArguments implements KafkaProperties {
        private final Arguments arguments;

        public FromArguments(Arguments arguments) {
            this.arguments = arguments;
        }

        @Override
        public Properties get() {
            final Properties properties = new Properties();
            properties.setProperty("zookeeper.connect", arguments.zookeeper());
            //properties.setProperty("bootstrap.servers", arguments.kafka());
            properties.setProperty("group.id", arguments.groupId());
            properties.setProperty("auto.offset.reset", arguments.autoOffsetReset());
            return properties;
        }
    }
}
