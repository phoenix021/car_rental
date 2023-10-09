package com.accelex.sample.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.RentalCreationDTO;
import com.accelex.sample.exercise.dto.RentalDTO;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExerciseApplicationTests {
    @LocalServerPort
    private int port;
	
    private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void contextLoads() {

	}
	
    @Test
    void testRentVehicleToCustomer() {
        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        customerCreationDTO.setBirthDate("2000-01-01");
        customerCreationDTO.setDriverLicenceNumber("DL567");
        customerCreationDTO.setFirstName("John");
        customerCreationDTO.setLastName("Doe");

        ResponseEntity<CustomerCreationDTO> customerResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/customer/add", customerCreationDTO, CustomerCreationDTO.class);

        assertEquals(HttpStatus.CREATED, customerResponse.getStatusCode());
        assertEquals(customerResponse.getBody(),customerCreationDTO);
    	
        VehicleCreationDTO newVehicleDTO = new VehicleCreationDTO();
        newVehicleDTO.setBrand("Toyota");
        newVehicleDTO.setColour("Blue");
        newVehicleDTO.setYear("2022");
        newVehicleDTO.setRegistration("ABC567");
        newVehicleDTO.setModel("Yaris");

        // Send a POST request and get the response entity
        ResponseEntity<VehicleCreationDTO> vehicleResponse = restTemplate.exchange(
        		"http://localhost:" + port + "/api/vehicle/add",
                HttpMethod.POST,
                new HttpEntity<>(newVehicleDTO, new HttpHeaders()),
                VehicleCreationDTO.class
        );

        // Validate the response
        assertEquals(HttpStatus.CREATED, vehicleResponse.getStatusCode());
    	
    	
    	//TODO: add vehicle and customer
        // Create a RentalCreationDTO for testing
        RentalCreationDTO rentalCreationDTO = new RentalCreationDTO();
        rentalCreationDTO.setRegistration("ABC567");
        rentalCreationDTO.setDriverLicenceNumber("DL567");

        // Send a POST request and get the response entity
        ResponseEntity<RentalDTO> rentalResponse = restTemplate.exchange(
        		"http://localhost:" + port + "/api/rental/rent",
                HttpMethod.POST,
                new HttpEntity<>(rentalCreationDTO, new HttpHeaders()),
                RentalDTO.class
        );

        // Validate the response
        assertEquals(HttpStatus.CREATED, rentalResponse.getStatusCode());
        // Add more assertions based on your expected response
/*
        // Assuming your RentalService returns a RentalDTO entity
        RentalDTO rentedVehicle = rentalService.findRentalByRegistrationAndDriverLicence(
                rentalCreationDTO.getRegistration(),
                rentalCreationDTO.getDriverLicenceNumber()
        );

        assertEquals(rentalCreationDTO.getRegistration(), rentedVehicle.getRegistration());
        assertEquals(rentalCreationDTO.getDriverLicenceNumber(), rentedVehicle.getDriverLicenceNumber());
        */
    }
    
    @Test
    public void testAddCustomer() {
        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        customerCreationDTO.setBirthDate("2000-01-01");
        customerCreationDTO.setDriverLicenceNumber("DL123");
        customerCreationDTO.setFirstName("John");
        customerCreationDTO.setLastName("Doe");

        ResponseEntity<CustomerCreationDTO> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/customer/add", customerCreationDTO, CustomerCreationDTO.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(),customerCreationDTO);
        
    }
    
    @Test
    public void testGetAllCustomers() {
    	 ResponseEntity<List<CustomerCreationDTO>> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/customer/getAll", null, new ParameterizedTypeReference<List<CustomerCreationDTO>>() {});

         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testAddVehicle() {
        VehicleCreationDTO newVehicleDTO = new VehicleCreationDTO();
        newVehicleDTO.setBrand("Toyota");
        newVehicleDTO.setColour("Blue");
        newVehicleDTO.setYear("2022");
        newVehicleDTO.setRegistration("ABC123");
        newVehicleDTO.setModel("Camry");

        // Send a POST request and get the response entity
        ResponseEntity<VehicleCreationDTO> responseEntity = restTemplate.exchange(
        		"http://localhost:" + port + "/api/vehicle/add",
                HttpMethod.POST,
                new HttpEntity<>(newVehicleDTO, new HttpHeaders()),
                VehicleCreationDTO.class
        );

        // Validate the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Add more assertions based on your expected response

        /*
        // Assuming your VehicleService returns a Vehicle entity
        Vehicle savedVehicle = vehicleService.findByRegistration(newVehicleDTO.getRegistration());
        assertEquals(newVehicleDTO.getBrand(), savedVehicle.getBrand());
        assertEquals(newVehicleDTO.getColour(), savedVehicle.getColour());
        assertEquals(newVehicleDTO.getYear(), savedVehicle.getMakeYear());
        assertEquals(newVehicleDTO.getRegistration(), savedVehicle.getRegistration());
        assertEquals(newVehicleDTO.getModel(), savedVehicle.getModel());
        */
    }
    
    


}



