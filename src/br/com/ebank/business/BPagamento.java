/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.business;

import br.com.ebank.dao.DPagamento;
import br.com.ebank.entity.Pagamento;

/**
 *
 * @author Positivo
 */
public class BPagamento {
 

    public Pagamento buscar(String numero) {
     return new DPagamento().buscar(numero);
    }
     public boolean pagar (String numero){
         return new DPagamento().pagar(numero);
     }
}
