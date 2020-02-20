package br.com.fercugliandro.mastertech.apis.pontoeletronico.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.MarcacaoVO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoDTO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoVO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.repository.PontoEletronicoRepository;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.service.PontoEletronicoService;
import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import br.com.fercugliandro.mastertech.util.DateUtils;

@Service
public class PontoEletronicoServiceImpl implements PontoEletronicoService {

    @Autowired
    private PontoEletronicoRepository repository;

    @Override
    public PontoEletronico save(PontoEletronico pontoEletronico) {	
        return repository.saveAndFlush(pontoEletronico);
    }

	@Override
	public List<PontoEletronicoDTO> findByUser(Long idUsuario) {
		
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		List<PontoEletronico> pontos = repository.findByUsuario(usuario);
		
		List<PontoEletronicoDTO> pontosEletronicos = null;
		if (pontos != null && !pontos.isEmpty()) {			
			return popularPontoEletronico(pontos);
		}
	
		return null;
	}
    
	
	private List<PontoEletronicoDTO> popularPontoEletronico(List<PontoEletronico> pontosEletronicos) {
		Map<Usuario, Map<String, List<PontoEletronico>>> map = generateMapPontoEletronico(pontosEletronicos);
		
		return popularPontoEletronicoDTO(map);
	}
	
	private List<PontoEletronicoDTO> popularPontoEletronicoDTO(Map<Usuario, Map<String, List<PontoEletronico>>> map) {
		
		List<PontoEletronicoDTO> dtos = new ArrayList<>();
		PontoEletronicoDTO dto = null;
		
		for(Entry<Usuario, Map<String, List<PontoEletronico>>> entry : map.entrySet()) {
			dto = new PontoEletronicoDTO();
			dto.setUsuario(entry.getKey());
			dto.setPontoEletronico(popularPontoEletronicoVO(entry.getValue()));
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	private List<PontoEletronicoVO> popularPontoEletronicoVO(Map<String, List<PontoEletronico>> map) {
		
		List<PontoEletronicoVO> vos = new ArrayList<>();
		PontoEletronicoVO vo = null;
		for(Entry<String, List<PontoEletronico>> entry : map.entrySet()) {
			vo = new PontoEletronicoVO(entry.getKey(), popularMarcacoes(entry.getValue()));
			vo.setHorasTrabalhadas(calculoTotalHorasTrabalhadas(vo.getMarcacoes()));
			vos.add(vo);	
		}
		
		return vos;
	}

	private List<MarcacaoVO> popularMarcacoes(List<PontoEletronico> pontosEletronicos) {
		List<MarcacaoVO> vos = new ArrayList<>();
		MarcacaoVO vo = null;
		for (PontoEletronico p : pontosEletronicos) {
			vo = new MarcacaoVO(DateUtils.formatTime(p.getDataHoraPonto()), p.getTipoBatidaPonto());
			vos.add(vo);
		}
		return vos;
	}
	
	private LocalTime calculoTotalHorasTrabalhadas(List<MarcacaoVO> marcacoes) {
		
		List<Date> times = new ArrayList<>();
		Date t = null;
		for (MarcacaoVO vo : marcacoes) {
			t = DateUtils.parseTime(vo.getMarcacao());
			times.add(t);
		}
		
		Date dtEntradaA = times.get(0);
		Date dtSaidaA = times.get(1);
		Date dtEntradaP = times.get(2);
		Date dtSaidaP = times.get(3);
		
		long diffA = dtSaidaA.getTime() - dtEntradaA.getTime();
		long diffP = dtSaidaP.getTime() - dtEntradaP.getTime();
		
		long diffHours = (diffA/(60*60*1000) % 24) + (diffP/(60*60*1000) % 24);
		long diffMin = (diffA/(60*1000) % 60) + (diffP/(60*1000) % 60);
		
		return LocalTime.of(Long.valueOf(diffHours).intValue(), Long.valueOf(diffMin).intValue());
	}

	private Map<Usuario, Map<String, List<PontoEletronico>>> generateMapPontoEletronico(List<PontoEletronico> pontosEletronicos) {
		Map<Usuario, Map<String, List<PontoEletronico>>> map = new HashMap();
		Map<String, List<PontoEletronico>> mapPontoUsuario = null;
		
		Usuario usuario = null;
		for(PontoEletronico ponto : pontosEletronicos) {
			usuario = ponto.getUsuario();
			
			if (map.get(ponto.getUsuario()) != null) {
				mapPontoUsuario = map.get(ponto.getUsuario());
				popularPontoUsuario(mapPontoUsuario, ponto);							
			} else {
				mapPontoUsuario = new HashMap<String, List<PontoEletronico>>();					 
				popularPontoUsuario(mapPontoUsuario, ponto);
			}
			map.put(usuario, mapPontoUsuario);
		}
		
		return map;
	}

	private void popularPontoUsuario(Map<String, List<PontoEletronico>> map, PontoEletronico ponto) {
		String dataPonto = DateUtils.formatDate(ponto.getDataHoraPonto());
		
		List<PontoEletronico> listPonto = null;
		if (map.get(dataPonto) != null) {
			listPonto = map.get(dataPonto);
			listPonto.add(ponto);
		} else {
			listPonto = new ArrayList();
			listPonto.add(ponto);
		}
		 
		map.put(dataPonto, listPonto);	
	}
}
