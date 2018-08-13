package org.test.bookpub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.test.bookpub.entity.Book;
import org.test.bookpub.repository.BookRepository;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookPubApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookPubApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BookRepository repository;

	@LocalServerPort
	private int port;

	private MockMvc mockMvc;
	private RestTemplate restTemplate = new RestTemplate();

	@Before
	public void setupMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() {
		assertEquals(1, repository.count());
	}

	@Test
	public void webappBookIsbnApi(){
		Book book = restTemplate.getForObject("http://localhost:" + port + "/books/978-1-78528-415-1", Book.class);
	}

	@Test
	public void webappPublisherApi() throws Exception {
		mockMvc.perform(get("/publishers/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
				.andExpect(content().string(containsString("Packt")))
				.andExpect(jsonPath("$.name").value("Packt"));
	}
}
