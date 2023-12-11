package model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceitaVO {

    private int idReceita;
    private int idUsuario;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataReceita;

    public ReceitaVO() {
    }

    public ReceitaVO(int idUsuario, String descricao, BigDecimal valor, LocalDateTime dataReceita) {
        super();
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.dataReceita = dataReceita;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(LocalDateTime dataReceita) {
        this.dataReceita = dataReceita;
    }

    @Override
    public String toString() {
        return "ReceitaVO{" +
                "idReceita=" + idReceita +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", dataReceita=" + dataReceita +
                '}';
    }
}
