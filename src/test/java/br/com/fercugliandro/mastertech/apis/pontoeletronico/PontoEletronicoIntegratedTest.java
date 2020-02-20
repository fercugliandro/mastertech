package br.com.fercugliandro.mastertech.apis.pontoeletronico;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.repository.PontoEletronicoRepository;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.service.impl.PontoEletronicoServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.MarcacaoVO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoDTO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoVO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.TipoPonto;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.service.PontoEletronicoService;
import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PontoEletronicoIntegratedTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private static PontoEletronicoService pontoEletronicoSerice;

	@BeforeAll
	static void setup() {
		pontoEletronicoSerice = new PontoEletronicoServiceImpl();
	}

	@Test
	void testFindByUser() throws JsonProcessingException, Exception {
		List<PontoEletronicoDTO> dtos = new ArrayList<>();
		List<PontoEletronico> pontoEletronicosMock = new ArrayList<>();
		Usuario u2 = new Usuario();
		u2.setId(1L);
		u2.setCpf("31625162855");
		u2.setNomeCompleto("Fernando Cugliandro");
		u2.setEmail("fernando.cugliandro@gmail.com");
		u2.setDataCadastro(new Date());

		PontoEletronicoDTO dto = new PontoEletronicoDTO();
		dto.setUsuario(u2);
		dto.setPontoEletronico(new ArrayList<PontoEletronicoVO>());
		dto.getPontoEletronico().add(new PontoEletronicoVO("20-02-2020", new ArrayList<>()));
		dto.getPontoEletronico().get(0).getMarcacoes().add(new MarcacaoVO("07:00", TipoPonto.ENTRADA));
		dto.getPontoEletronico().get(0).getMarcacoes().add(new MarcacaoVO("12:00", TipoPonto.SAIDA));
		dto.getPontoEletronico().get(0).getMarcacoes().add(new MarcacaoVO("13:00", TipoPonto.ENTRADA));
		dto.getPontoEletronico().get(0).getMarcacoes().add(new MarcacaoVO("16:00", TipoPonto.SAIDA));

		dtos.add(dto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/pontoeletronico/find/{idUsuario}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(pontoEletronicoSerice.findByUser(Mockito.anyLong())).thenReturn(dtos);

		List<PontoEletronicoDTO> pontoEletronico = pontoEletronicoSerice.findByUser(1L);
		assertNotNull(pontoEletronico.get(0));
		assertNotNull(pontoEletronico.get(0).getPontoEletronico());

		Mockito.verify(pontoEletronicoSerice, VerificationModeFactory.atLeastOnce()).findByUser(Mockito.anyLong());
	}
}
