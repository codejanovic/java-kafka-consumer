package io.github.codejanovic;

import io.github.codejanovic.cli.Arguments;
import io.github.codejanovic.cli.Cli;
import io.github.codejanovic.kafka.KafkaProperties;
import com.google.common.collect.ImmutableMap;
import io.github.codejanovic.kafka.KafkaStreamConsumer;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) throws ParseException {
        final KafkaStreamConsumer logKafkaStream = new KafkaStreamConsumer.Logging();
        final Arguments arguments = new Cli.Standard().parse(args);
        final Map<String, Integer> topics = arguments.topics().stream().collect(Collectors.toMap(k -> k, v -> 1));
        Consumer.createJavaConsumerConnector(new ConsumerConfig(new KafkaProperties.FromArguments(arguments).get()))
                .createMessageStreams(topics)
                .entrySet()
                .stream()
                .parallel()
                .forEach(logKafkaStream);
    }
}
