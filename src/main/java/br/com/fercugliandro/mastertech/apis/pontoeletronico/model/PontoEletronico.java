package br.com.fercugliandro.mastertech.apis.pontoeletronico.model;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_ponto_eletronico")
public class PontoEletronico implements Serializable {

    private enum TipoPonto {
        ENTRADA("E"),
        SAIDA("S");

        private String tipo;

        TipoPonto(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "dt_batida_ponto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraPonto;

    @Column(name = "tp_batida_ponto")
    private TipoPonto tipoBatidaPonto;

    @Transient
    private long totalHorasTrabalhadas;

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

    public long getTotalHorasTrabalhadas() {
        return totalHorasTrabalhadas;
    }

    public void setTotalHorasTrabalhadas(long totalHorasTrabalhadas) {
        this.totalHorasTrabalhadas = totalHorasTrabalhadas;
    }
}
