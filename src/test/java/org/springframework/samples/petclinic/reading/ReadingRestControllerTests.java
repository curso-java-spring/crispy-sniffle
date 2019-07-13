/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.reading;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for {@link ReadingController}
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WithMockUser(roles = "OWNER_ADMIN")
public class ReadingRestControllerTests {

	@Autowired
	private ReadingRestController readingRestController;

	private MockMvc mockMvc;

	@Before
	public void initOwners() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(readingRestController).build();
	}

	@Test
	public void testAddReading() throws Exception {
		Reading read = new Reading();
		read.setHumidity(200);
		read.setCreationDateTime(null);
		// FIXME avoiding date serialization

		ObjectMapper mapper = new ObjectMapper();
		String newOwnerAsJSON = mapper.writeValueAsString(read);

		mockMvc.perform(post("/readings").content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());

	}
}
