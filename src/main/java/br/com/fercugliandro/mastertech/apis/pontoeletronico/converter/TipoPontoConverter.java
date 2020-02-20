package br.com.fercugliandro.mastertech.apis.pontoeletronico.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.TipoPonto;

@Converter(autoApply = true)
public class TipoPontoConverter implements AttributeConverter<TipoPonto, String> {
  
    @Override
    public String convertToDatabaseColumn(TipoPonto category) {
        if (category == null) {
            return null;
        }
        return category.getTipo();
    }
 
    @Override
    public TipoPonto convertToEntityAttribute(String code) { 	
        if (code == null) {
            return null;
        }
        
        return Stream.of(TipoPonto.values())
          .filter(c -> c.getTipo().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}