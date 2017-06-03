/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.dao;

import br.com.ebank.entity.Pagamento;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Positivo
 */
public class DPagamento extends Dao{
         public boolean pagar(String numero){
        String comando = "update pagamento set paga = true where numero = ?";
       
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro no pagamento: "+ex.getMessage());
                    return false;
                    }
            return true;
      }
         
             public Pagamento buscar(String numero) {
        String comando = "select * from pagamento where numero= ?";
        Pagamento conta = new Pagamento(null,null,null,0, false);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
        
                conta.setNumero(rs.getString("numero"));
                conta.setEspecificacao(rs.getString("especificacao"));
                conta.setEntidade(rs.getString("entidade"));
                conta.setValor(rs.getDouble("valor"));
                conta.setPaga(rs.getBoolean("paga"));
            }
        }
        catch(SQLException ex){
              System.out.println("Erro na busca do pagamento: "+ex.getMessage());
              
        }
        return conta;
    }
}
