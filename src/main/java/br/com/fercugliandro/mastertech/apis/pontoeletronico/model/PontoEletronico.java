package br.com.fercugliandro.mastertech.apis.pontoeletronico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.converter.TipoPontoConverter;
import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

@Entity(name = "tb_ponto_eletronico")

public class PontoEletronico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = true)
    private Usuario usuario;
    
    @Column(name = "dt_marcacao_ponto")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraPonto;
	
	@Column(name = "tp_batida_ponto")
	@Convert(converter = TipoPontoConverter.class)
    private TipoPonto tipoBatidaPonto;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	public Date getDataHoraPonto() {
		return dataHoraPonto;
	}

	public void setDataHoraPonto(Date dataHoraPonto) {
		this.dataHoraPonto = dataHoraPonto;
	}

	public TipoPonto getTipoBatidaPonto() {
		return tipoBatidaPonto;
	}

	public void setTipoBatidaPonto(TipoPonto tipoBatidaPonto) {
		this.tipoBatidaPonto = tipoBatidaPonto;
	}
}
