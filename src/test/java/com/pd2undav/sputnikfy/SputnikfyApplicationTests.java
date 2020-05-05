package com.pd2undav.sputnikfy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SputnikfyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testValidXMLFileUpload() throws Exception {
		MockMultipartFile testFile = new MockMultipartFile("file",
				"file.xml",
				"application/xml",
				new FileInputStream(new File("resources/actividad_valid.xml")));

		this.mockMvc.perform(multipart("/")
				.file(testFile))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.validation").value("true"))
				.andExpect(jsonPath("$.error").value((String)null));
	}

	@Test
	public void testNotValidXMLFileUpload() throws Exception {
		MockMultipartFile testFile = new MockMultipartFile("file",
				"file.xml",
				"application/xml",
				new FileInputStream(new File("resources/actividad_not_valid.xml")));

		this.mockMvc.perform(multipart("/")
				.file(testFile))
				.andExpect(status().is(406))
				.andExpect(jsonPath("$.validation").value("false"))
				.andExpect(jsonPath("$.error").exists());
	}

	@Test
	public void testNotXMLFileUpload() throws Exception {
		MockMultipartFile testFile = new MockMultipartFile("file", "file.txt", "text/plain", "txt".getBytes());

		this.mockMvc.perform(multipart("/")
				.file(testFile))
				.andExpect(status().is(406));
	}

}
