/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.business;

import br.com.ebank.dao.DUsuario;
import br.com.ebank.entity.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author internet
 */
public class BUsuario {
     public boolean cadastrar(Usuario usu){
         return new DUsuario().cadastrar(usu);

     } 

    public Usuario buscar(int codigo) throws SQLException {
       return new DUsuario().buscar(codigo);
    }
    
    public boolean validar(String login, String senha) throws SQLException{
        return new DUsuario().validar(login, senha);
    }
     
    public Usuario pegaUsuario(String login) throws SQLException {
       return new DUsuario().pegaUsuario(login);
    }
    
    public boolean alterarSenha(String login, String senha) throws SQLException{
        return new DUsuario().alterarSenha(login, senha);
    }
     public boolean excluirUsuario(int id) {
         return new DUsuario().excluirUsuario(id);
     }
     public Usuario buscarUsuario(int numero) {
         return new DUsuario().buscarUsuario(numero);
     }
     public boolean alterarUsuario(Usuario usuario, int numero){
         return new DUsuario().alterarUsuario(usuario, numero);
     }
     
       public ArrayList<Usuario> listaUsuario(){
           return new DUsuario().listaUsuario();
       }
}
