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
public class Extrato {
    private String movimentacao;
    private String valor;
    private String contaDest;

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getContaDest() {
        return contaDest;
    }

    public void setContaDest(String contaDest) {
        this.contaDest = contaDest;
    }
    
}
