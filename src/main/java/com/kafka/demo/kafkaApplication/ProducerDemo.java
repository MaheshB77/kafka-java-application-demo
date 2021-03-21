package com.kafka.demo.kafkaApplication;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {
	public static void main(String[] args) {
//		Create Producer properties
		String bootstrapServers = "127.0.0.1:9092";
		Properties properties = new Properties();
//		properties.setProperty("bootstrap.servers", bootstrapServers);
//		properties.setProperty("key.serializer", StringSerializer.class.getName());
//		properties.setProperty("value.serializer", StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

//		Create Producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
//		Create producer record
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("firstTopic",
				"Hello world from java");

//		Send data (Asynchronously)
		producer.send(record);

//		Flush and close
		producer.flush();
		producer.close();
	}
}
