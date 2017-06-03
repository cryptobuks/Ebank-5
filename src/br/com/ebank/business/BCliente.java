/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.business;

import br.com.ebank.dao.DCliente;
import br.com.ebank.dao.DUsuario;
import br.com.ebank.entity.Cliente;
import br.com.ebank.entity.Usuario;
import java.util.ArrayList;

/**
 *
 * @author internet
 */
public class BCliente {
      public boolean cadastrar(Cliente cli){
         return new DCliente().cadastrar(cli);

     } 
      
       public boolean alterar(String nome, String rg, String cpf, String endereco, int numero){
         return new DCliente().alterar(nome, rg, cpf, endereco, numero);
     }
       
       public ArrayList<Cliente> lista(){
           return new DCliente().lista();
       }
       
    public boolean excluirCliente(int n) {
     return new DCliente().excluirCliente(n);   
    }
}
