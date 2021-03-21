package com.kafka.demo.kafkaApplication;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerWithCallback {
	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(ProducerWithCallback.class);
		
		String bootstrapServer = "127.0.0.1:9092";
		Properties properties = new Properties();
		
//		Set producer configurations
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
//		Create producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
//		Create producer record
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("firstTopic", "Hi");
		
//		Send data and see details using the Callback()
		producer.send(record, new Callback() {
			
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if(exception == null) {
//					Record was successfully sent
					logger.info("Recieved new metadata !!!!!!!!!!!");
					logger.info("Topic : " + metadata.topic());	
					logger.info("Partition : " + metadata.partition());
					logger.info("Offset : " + metadata.offset());
					logger.info("Timestamp : " + metadata.timestamp());
				} else {
//					Something went wrong
					logger.error("Error occured ", exception);
				}
			}
		});
		
//		Flush and close
		producer.flush();
		producer.close();
		
	}

}
