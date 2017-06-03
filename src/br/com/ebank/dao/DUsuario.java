/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.dao;

import br.com.ebank.entity.Usuario;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author internet
 */
public class DUsuario extends Dao{
    public boolean cadastrar(Usuario usu){
        String comando = "insert into usuarios(id, nome, login,email, senha) values (?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, usu.getId());
            this.stmt.setString (3, usu.getLogin());
            this.stmt.setString (4, usu.getEmail());
            this.stmt.setString (5, usu.getSenha());
            this.stmt.setString(2, usu.getNome()); 
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao inserir Usuario"+ex.getMessage());
                    return false;
                    }
            return true;
        
        }

    public Usuario buscar(int codigo) throws SQLException {
        String comando = "select * from usuarios where id= ?";
        Usuario usuario = new Usuario(0, null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, codigo);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return usuario;
    }
        public boolean validar(String login, String senha) throws SQLException {
        String comando = "select * from usuarios where login = ? and senha = ?";
        Usuario usuario = new Usuario(0,null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, login);
            this.stmt.setString(2, senha);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
               // usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
              //usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return true;
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
        }
        return false;
    }
        
            public Usuario pegaUsuario(String login) throws SQLException {
        String comando = "select * from conta where login = ?";
        Usuario usuario = new Usuario(0, null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, login);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
        } 
        return usuario;
   }
        
        //Alterar a senha
        
      public boolean alterarSenha(String login, String senha) throws SQLException {
        String comando = "update usuarios set senha = ? where login = ?";
        Usuario usuario = new Usuario(0, null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, senha);
            this.stmt.setString(2, login);
            this.stmt.execute();
                return true; 
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
        }
        return false;
    }
      
       
      //falta testar


    public boolean excluirUsuario(int id) {
           String comando = "delete from usuarios where id = ?;"
                ;
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, id);
            this.stmt.execute();
            return true;
        }
        catch(SQLException ex){
              System.out.println("Erro ao excluir Usuario"+ex.getMessage());     
        }
        return false;
    }
    
        public Usuario buscarUsuario(int numero) {
        String comando = "select * from usuarios where id= ?";
        Usuario usuario = new Usuario(0, null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return usuario;
    }
        
        public boolean alterarUsuario(Usuario usuario, int numero){
              String comando = "update usuarios set nome = ?, email = ? , login = ?, senha = ? where id = ?";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString (1, usuario.getNome());
            this.stmt.setString (2, usuario.getEmail());
            this.stmt.setString (3, usuario.getLogin());
            this.stmt.setString (4, usuario.getSenha());
            this.stmt.setInt (5, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao alterar Cliente "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
        
        public ArrayList<Usuario> listaUsuario(){
            ArrayList<Usuario> listUsers = new ArrayList<>();
        String comando = "select * from usuarios";
        
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            ResultSet rs = this.stmt.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario(0,null,null,null,null);
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setLogin(rs.getString("login"));
                listUsers.add(user);
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return listUsers;
    }
        

    
   
}
    
    
    
    

