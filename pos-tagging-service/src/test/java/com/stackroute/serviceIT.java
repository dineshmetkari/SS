//package com.stackroute;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.amqp.AmqpException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.stackroute.domain.InputQuery;
//import com.stackroute.receiver.Receiver;
//import com.stackroute.service.PosTaggingService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class serviceIT {
//	
//	@Autowired
//	private InputQuery inputQuery;
//	
//	@Autowired
//	private PosTaggingService posTaggingService;
//	
//	@Autowired
//	private Receiver receiver;
//	
//	@Mock
//    private Producer producer;
//  
//	@Before
//	public void setupMock() throws AmqpException, IOException {
//		MockitoAnnotations.initMocks(this);
//		posTaggingService.setProducer(producer);
//	}
//	
//	@Test
//	public void posTaggingTest() throws IOException {
//		//Arrange
//		receiver.receiveMessage("{\"spelling\":\"wrong\",\"query\":\"what do you mean by ajax in java with table \",\"sessionId\":\"o5yahbhc\"}");
//		//String expectedResult = "What/wp Do/vbp You/prp Mean/vb By/in Ajax/nn In/in Java/nn With/in Table/nn";
//		  Gson gson = new Gson();
//          Type type = new TypeToken<InputQuery>() {}.getType();
//        
//          inputQuery = gson.fromJson(receiver.getMessage(), type);
//          
//          String query = inputQuery.getQuery();
//		//Act
//		 
//         String expectedResult = "What/wp Do/vbp You/prp Mean/vb By/in Ajax/nn In/in Java/nn With/in Table/nn";
//		 String actualResult = posTaggingService.posTagging(query);
//
//		 //Assert
//		 System.out.println(expectedResult+""+actualResult);
//	 assertEquals(expectedResult, actualResult);
//	}
//
//}
