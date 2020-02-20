package br.com.fercugliandro.mastertech.apis.pontoeletronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoDTO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.service.PontoEletronicoService;

@RestController
@RequestMapping("/pontoeletronico")
public class PontoEletronicoRestController {

    @Autowired
    private PontoEletronicoService pontoEletronicoService;

    @GetMapping(path="/find/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable Long idUsuario) {

        try {
            List<PontoEletronicoDTO> pontoEletronico = pontoEletronicoService.findByUser(idUsuario);
            return new ResponseEntity<>(pontoEletronico, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/register", consumes="application/json")
    public ResponseEntity<?> save(@RequestBody PontoEletronico pontoEletronico) {

        try {
        	PontoEletronico ponto = pontoEletronicoService.save(pontoEletronico);
            return new ResponseEntity<>(ponto, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
