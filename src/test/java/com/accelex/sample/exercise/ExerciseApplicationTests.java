package com.accelex.sample.exercise;

import java.util.List;

import com.accelex.sample.exercise.entity.Customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ExerciseApplicationTests {
	
    private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void contextLoads() {
	}
	
    @Test
    public void getAllCustomersEndpoint() {
        String url = "http://localhost:8080/api/customer/getAll";

        // Make a GET request
        //ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        // Assert the response
       // Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}



