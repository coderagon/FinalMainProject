package com.hitesh.springsecurityissue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.hitesh.finalmainproject.SpringSecurityIssueApplication;
import com.hitesh.finalmainproject.models.BankDetails;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityIssueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSecurityIssueApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port;
	}
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testGetAllBranches() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/admin/branches", HttpMethod.GET,entity,String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
    public void testCreateBranch() {
        BankDetails branch = new BankDetails();
        branch.setId(111);
        branch.setName("testBranch");
        branch.setAddress("test address");
        ResponseEntity<BankDetails> postResponse = restTemplate.postForEntity(getRootUrl() + "/contactus/add", branch, BankDetails.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateBranch() {
        int id = 111;
        BankDetails branch = restTemplate.getForObject(getRootUrl() + "/contactus/edit/" + id, BankDetails.class);
        branch.setName("testUpdatedName");
        branch.setAddress("testUpdatedAddress");
        restTemplate.put(getRootUrl() + "/contactus/edit/" + id, branch);
        BankDetails updatedEmployee = restTemplate.getForObject(getRootUrl() + "/contactus/edit/" + id, BankDetails.class);
        assertNotNull(updatedEmployee);
    }
	/*If rest pass and following fails it might be due @PathVariable and @RequstParam discrepancy*/
    @Test
    public void testDeleteBranch() {
         int id = 111;
         BankDetails branch = restTemplate.getForObject(getRootUrl() + "/contactus/delete/" + id, BankDetails.class);
         assertNotNull(branch);
         restTemplate.delete(getRootUrl() + "/contactus/delete/" + id);
         try {
              branch = restTemplate.getForObject(getRootUrl() + "/contactus/delete/" + id, BankDetails.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
	
	

}
