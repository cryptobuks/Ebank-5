/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.business;

import br.com.ebank.dao.DConta;
import br.com.ebank.entity.Cliente;
import br.com.ebank.entity.Conta;
import br.com.ebank.entity.Extrato;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Positivo
 */
public class BConta {
     public boolean cadastrar(Conta conta){
         return new DConta().cadastrar(conta);
     }
     
        public boolean validar(int numero, int senha) throws SQLException{
        return new DConta().validar(numero, senha);
    }
        
       
        public double pegaSaldo(int numero) throws SQLException {
            return new DConta().pegaSaldo(numero);
        } 
   
         public boolean sacar(double valor, int numero, double saldo) throws SQLException{
             return new DConta().sacar(valor, numero, saldo);
         }
         
          public boolean depositar(double valor, int numero, double saldo) throws SQLException{
             return new DConta().depositar(valor, numero, saldo);
         }
          
          public boolean transferir(int numero, double valor, int ndest, double saldo,double saldo2) throws SQLException{
             return new DConta().transferir(numero, valor, ndest, saldo, saldo2);
         }
          
           public Conta buscar(int numero) throws SQLException {
       return new DConta().buscar(numero);
    }
           
    public Cliente buscarTitular(int numero) throws SQLException {
       return new DConta().buscarTitular(numero);
    }
    
    public Cliente pegaNumero(String cpf) throws SQLException {
       return new DConta().pegaNumero(cpf);
    }
    
     public boolean alterar(int senha, int numero){
         return new DConta().alterar(senha,numero);
     }
     
     public boolean gravaDep(double valor, int numero){
         return new DConta().gravaDep(valor, numero);
     }
     
      public boolean gravaSac(double valor, int numero){
         return new DConta().gravaSac(valor, numero);
     }
      
      public boolean gravaTransf(double valor, int numero, int contaDest){
          return new DConta().gravaTransf(valor, numero, contaDest);
      }
      
       public boolean gravaTransfR (double valor, int numero, int contaOrig){
           return new DConta().gravaTransfR(valor, numero, contaOrig);
       }
       
       public ArrayList<Extrato> pegaExtrato(int numero) {
           return new DConta().pegaExtrato(numero);
       }


    public boolean excluirConta(int n) {
     return new DConta().excluirConta(n);   
    }
    
     public boolean gravaPag (double valor, int nconta, String nfat){
           return new DConta().gravaPag(valor, nconta, nfat);
       }
        
}
