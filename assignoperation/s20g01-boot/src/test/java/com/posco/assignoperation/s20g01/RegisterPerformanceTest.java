
package com.posco.assignoperation.s20g01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

import javax.inject.Inject;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import s20g01.config.kafka.KafkaProcessor;
import s20g01.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class RegisterPerformanceTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPerformanceTest.class);
   
   @Autowired
   private KafkaProcessor processor;
   @Autowired
   private MessageCollector messageCollector;
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   ObjectMapper objectMapper;

   @Autowired
   private MessageVerifier<Message<?>> messageVerifier;

   @Autowired
   public VehiclePerformanceRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      VehiclePerformance existingEntity = new VehiclePerformance();

      existingEntity.setRegistrationId("N/A");
      existingEntity.setVehicleNumber("N/A");
      existingEntity.setRegistrationDate("N/A");
      existingEntity.setDeparture("N/A");
      existingEntity.setDepartureTime("N/A");
      existingEntity.setAccumulatedDistanceBefore("N/A");
      existingEntity.setDestination("N/A");
      existingEntity.setArrivalTime("N/A");
      existingEntity.setAccumulatedDistanceAfter("N/A");
      existingEntity.setDrivingDistance("N/A");
      existingEntity.setPurpose("N/A");
      existingEntity.setPeriod(new Object[]{[object Object]});

      repository.save(existingEntity);

      //when:  

  
      
      try {


      VehiclePerformance newEntity = new VehiclePerformance();

         newEntity.setRegistrationId("N/A");
         newEntity.setVehicleNumber("N/A");
         newEntity.setRegistrationDate("N/A");
         newEntity.setDeparture("N/A");
         newEntity.setDepartureTime("N/A");
         newEntity.setAccumulatedDistanceBefore("N/A");
         newEntity.setDestination("N/A");
         newEntity.setArrivalTime("N/A");
         newEntity.setAccumulatedDistanceAfter("N/A");
         newEntity.setDrivingDistance("N/A");
         newEntity.setPurpose("N/A");
         newEntity.setPeriod("N/A");

      repository.save(newEntity);


   
           

         this.messageVerifier.send(MessageBuilder
                .withPayload(newEntity)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(), "s20g01");

         Message<?> receivedMessage = this.messageVerifier.receive("s20g01", 5000, TimeUnit.MILLISECONDS);
         assertNotNull("Resulted event must be published", receivedMessage);

      //then:
         String receivedPayload = (String) receivedMessage.getPayload();

         PerformanceRegistered outputEvent = objectMapper.readValue(receivedPayload, PerformanceRegistered.class);


         LOGGER.info("Response received: {}", outputEvent);

         assertEquals(outputEvent.getRegistrationId(), "N/A");


      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
