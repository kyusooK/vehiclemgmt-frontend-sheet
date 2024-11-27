package com.posco.standardmanagement.s20g01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;
import s20g01.config.kafka.KafkaProcessor;
import s20g01.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class 누적거리계산Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        누적거리계산Test.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public VehicleRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Vehicle entity = new Vehicle();

        entity.setModel("그랜저");
        entity.setId(1L);
        entity.setStatus("ACTIVE");
        entity.setRegistrationNumber("서울1234");
        entity.setMake("현대");
        entity.setYear(2020L);
        entity.setDriverDistance(1000L);

        repository.save(entity);

        //when:

        PerformanceRegistered event = new PerformanceRegistered();

        event.setRegistrationId("12345");
        event.setVehicleNumber("서울1234");
        event.setAccumulatedDistanceAfter(2000L);
        event.setDrivingDistance(500L);
        event.setPurpose("BUSINESS");
        event.setPeriod("WEEKLY");
        event.setRegistrationDate("2022-07-01");

        StandardmanagementApplication.applicationContext = applicationContext;

        ObjectMapper objectMapper = new ObjectMapper()
            .configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
            );
        try {
            String msg = objectMapper.writeValueAsString(event);

            processor
                .inboundTopic()
                .send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build()
                );

            //then:

            Message<String> received = (Message<String>) messageCollector
                .forChannel(processor.outboundTopic())
                .poll();

            assertNotNull("Resulted event must be published", received);

            DriverDistanceUpdated outputEvent = objectMapper.readValue(
                (String) received.getPayload(),
                DriverDistanceUpdated.class
            );

            LOGGER.info("Response received: {}", received.getPayload());

            assertEquals(String.valueOf(outputEvent.getId()), 1L);
            assertEquals(String.valueOf(outputEvent.getStatus()), "ACTIVE");
            assertEquals(
                String.valueOf(outputEvent.getRegistrationNumber()),
                "서울1234"
            );
            assertEquals(String.valueOf(outputEvent.getMake()), "현대");
            assertEquals(String.valueOf(outputEvent.getModel()), "그랜저");
            assertEquals(String.valueOf(outputEvent.getYear()), 2020L);
            assertEquals(
                String.valueOf(outputEvent.getDriverDistance()),
                1500L
            );
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
