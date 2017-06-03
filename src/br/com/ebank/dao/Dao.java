/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Positivo
 */
public class Dao {
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    protected PreparedStatement stmt;//protacted o dono acessa, as classes que herdarem acessam apenas se estiverem no mesmo pacote.   / Statmente é uma frase que serve para enviar instruções sql
    protected Connection cnn;//a conexão é o cano que liga a aplicação com o banco;
    public Dao(){
    this.servidor="ebankbd.mysql.dbaas.com.br";// localhost porque o código está na mesma máquina do banco
    this.banco="ebankbd";
    this.usuario="ebankbd";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    this.senha="loca@1020";
    }
    public void conectar(){
    String url="jdbc:mysql://"+this.servidor+"/"+this.banco;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.cnn=(Connection)DriverManager.getConnection(url,this.usuario,this.senha);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
