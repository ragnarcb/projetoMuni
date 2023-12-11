package model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DespesaVO {

    private int idDespesa;
    private int idUsuario;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;

    public DespesaVO(int idDespesa, int idUsuario, String descricao, BigDecimal valor, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
        super();
        this.idDespesa = idDespesa;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public DespesaVO() {
        super();
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
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

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "DespesaVO{" +
                "idDespesa=" + idDespesa +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
