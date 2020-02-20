package br.com.fercugliandro.mastertech.apis.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import br.com.fercugliandro.mastertech.apis.usuarios.service.UsuarioService;
import br.com.fercugliandro.mastertech.apis.usuarios.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioIntegratedTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean(value = UsuarioServiceImpl.class)
	private UsuarioService usuarioService;
	
	@Test
	void testFindAllUsers() throws JsonProcessingException, Exception {
		List<Usuario> usuariosMock = new ArrayList<>();
		Usuario u1 = new Usuario();
		u1.setCpf("31625162840");
		u1.setNomeCompleto("Gabriel Kazuo");
		u1.setEmail("gabriel.kazuo@gmail.com");
		u1.setDataCadastro(new Date());
		
		Usuario u2 = new Usuario();
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Fernando Cugliandro");
		u2.setEmail("fernando.cugliandro@gmail.com");
		u2.setDataCadastro(new Date());
		
		Usuario u3 = new Usuario();
		u2.setCpf("31625162876");
		u2.setNomeCompleto("Camilla T Y ");
		u2.setEmail("camillaty@gmail.com");
		u2.setDataCadastro(new Date());
		
		usuariosMock.add(u1);
		usuariosMock.add(u2);
		usuariosMock.add(u3);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/usuarios/find")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usuariosMock)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(usuarioService.findAll()).thenReturn(usuariosMock);
		
		List<Usuario> usuarios = usuarioService.findAll();
		assertNotNull(usuarios.size());		
	}
	
	@Test
	void testFindUserById() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();
		u2.setId(1L);
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Fernando Cugliandro");
		u2.setEmail("fernando.cugliandro@gmail.com");
		u2.setDataCadastro(new Date());
		
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/usuarios/find/{idUsuario}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(usuarioService.findById(1L)).thenReturn(u2);
		
		Usuario user = usuarioService.findById(1L);
		
		assertEquals(user.getId(), u2.getId());		
	}
	
	@Test
	void testSaveSuccess() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();		
		u2.setCpf("35193937861");
		u2.setNomeCompleto("Steve Rogers");
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(usuarioService.save(u2)).thenReturn(u2);
		
		Usuario user = usuarioService.save(u2);	
		assertEquals(user.getCpf(), u2.getCpf());		
	}
	
	@Test
	void testSaveWithValidationCPFNullError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();			
		u2.setNomeCompleto("Steve Rogers");
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationCPFEmptyError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();		
		u2.setCpf("");
		u2.setNomeCompleto("Steve Rogers");
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationNomeNullError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();	
		u2.setCpf("31625162855");		
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationNomeEmptyError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();		
		u2.setCpf("31625162855");
		u2.setNomeCompleto("");
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationEmailNullError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();	
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Steve Rogers");		
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationEmailEmptyError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();		
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Steve Rogers");
		u2.setEmail("");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationDataCadastroNullError() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();			
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Steve Rogers");
		u2.setEmail("steve.rogers@gmail.com");
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	
	@Test
	void testSaveEditSuccess() throws JsonProcessingException, Exception {
		Usuario u2 = new Usuario();		
		u2.setCpf("35193937861");
		u2.setNomeCompleto("Steve Rogers - T");
		u2.setEmail("steve.rogers@gmail.com");
		u2.setDataCadastro(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.put("/usuarios/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(u2)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(usuarioService.save(u2)).thenReturn(u2);
		
		Usuario user = usuarioService.save(u2);	
		assertEquals(user.getCpf(), u2.getCpf());		
	}
	
	
}
