package io.github.codejanovic.kafka;

import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface KafkaStreamConsumer extends Consumer<Map.Entry<String, List<KafkaStream<byte[], byte[]>>>> {

    final class Logging implements KafkaStreamConsumer {
        private final Logger logger = LoggerFactory.getLogger(Logging.class);
        private final String logMessage = "received data on topic %s : %s";

        @Override
        public void accept(Map.Entry<String, List<KafkaStream<byte[], byte[]>>> entry) {
            final String topic = entry.getKey();
            final List<KafkaStream<byte[], byte[]>> kafkaStreams = entry.getValue();
            kafkaStreams.parallelStream().forEach(logStreamFrom(topic));
        }

        private Consumer<? super KafkaStream<byte[], byte[]>> logStreamFrom(String topic) {
            return kafkaStream -> kafkaStream.forEach(logMessageFrom(topic));
        }

        private Consumer<? super MessageAndMetadata<byte[], byte[]>> logMessageFrom(String topic) {
            return (Consumer<MessageAndMetadata<byte[], byte[]>>)
                    data -> logger.debug(logMessage, topic, new String(data.message()));
        }
    }
}
