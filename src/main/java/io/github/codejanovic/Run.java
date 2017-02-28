package io.github.codejanovic;

import io.github.codejanovic.cli.Arguments;
import io.github.codejanovic.cli.Cli;
import io.github.codejanovic.kafka.KafkaProperties;
import com.google.common.collect.ImmutableMap;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.commons.cli.ParseException;

import java.util.List;

public class Run {
    public static void main(String[] args) throws ParseException {
        final Arguments arguments = new Cli.Standard().parse(args);
        final ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(new KafkaProperties.FromArguments(arguments).get()));

        final List<KafkaStream<byte[], byte[]>> streams = consumer.createMessageStreams(ImmutableMap.of(arguments.topic(), 1)).get(arguments.topic());
        for (final KafkaStream stream : streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                System.out.println(new String(it.next().message()));
            }
        }
    }
}
