/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.dao;

import br.com.ebank.entity.Cliente;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author internet
 */
public class DCliente extends Dao{
     public boolean cadastrar (Cliente cli){
        String comando = "insert into cliente (nome, rg, cpf, endereco, numero) values (?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString (1, cli.getNome());
            this.stmt.setString (2, cli.getRg());
            this.stmt.setString (3, cli.getCpf());
            this.stmt.setString (4, cli.getEndereco());
            this.stmt.setInt (5, cli.getNumero());
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao inserir Cliente "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
     
     //alterar
     
          public boolean alterar(String nome , String rg, String cpf, String endereco, int numero){
              String comando = "update cliente set nome = ?, rg = ?, cpf = ? , endereco = ? where numero = ?";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString (1, nome);
            this.stmt.setString (2, rg);
            this.stmt.setString (3, cpf);
            this.stmt.setString (4, endereco);
            this.stmt.setInt (5, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao alterar Cliente "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
            
        public ArrayList<Cliente> lista(){
            ArrayList<Cliente> listCliente = new ArrayList<>();
        String comando = "select * from cliente";
        
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            ResultSet rs = this.stmt.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente(null,null,null,null);
                cliente.setNumero(rs.getInt("numero"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                listCliente.add(cliente);
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return listCliente;
    }
        
        public boolean excluirCliente(int numero) {
        String comando = "delete from cliente where numero = ?;"
                ;
        Cliente cliente = new Cliente(null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            this.stmt.execute();
            return true;
        }
        catch(SQLException ex){
              System.out.println("Erro ao excluir conta"+ex.getMessage());     
        }
        return false;
    }

   
}
