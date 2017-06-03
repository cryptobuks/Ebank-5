/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.entity;

/**
 *
 * @author Positivo
 */
public class Pagamento {
    private String numero;
    private String entidade;
    private String especificacao;
    private Double valor;
    private boolean paga;
    
    public Pagamento(String numero, String entidade, String especificacao, double valor, boolean paga){
        this.numero = numero;
        this.especificacao = especificacao;
        this.entidade = entidade;
        this.valor = valor;
        this.paga = paga;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
