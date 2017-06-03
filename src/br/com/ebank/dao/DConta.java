/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.dao;

import br.com.ebank.entity.Cliente;
import br.com.ebank.entity.Conta;
import br.com.ebank.entity.Extrato;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Positivo
 */


public class DConta extends Dao{
    public boolean cadastrar (Conta conta){
        DConta dconta = new DConta();
        //dconta.criaExtrato(conta.getCpf());
        String comando = "insert into conta (numero, senha, saldo) values (?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, conta.getNumero());
            this.stmt.setInt (2, conta.getSenha());
            this.stmt.setInt (3, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao inserir Conta "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
    
    //gravar depósito
    public boolean gravaDep (Double valor, int numero){
        DConta dconta = new DConta();
        String comando = "insert into extrato values(?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, numero);
            this.stmt.setString (2, "Depósito");
            this.stmt.setString (3, String.format("R$ %s+",Double.toString(valor)));
            this.stmt.setString (4, "Caixa Eletrônico");
            this.stmt.setInt (5, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao gravar deposito: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
    
    //gravar saque
    public boolean gravaSac (double valor, int numero){
        DConta dconta = new DConta();
        String comando = "insert into extrato values(?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, numero);
            this.stmt.setString (2, "Saque");
            this.stmt.setString (3, String.format("R$ %s-",Double.toString(valor)));
            this.stmt.setString (4, "Caixa Eletrônico");
            this.stmt.setInt (5, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao gravar Saque: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
    
        public boolean gravaTransf (Double valor, int numero, int contaDest){
        DConta dconta = new DConta();     
        String comando = "insert into extrato values(?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, numero);
            this.stmt.setString (2, "Transferência");
            this.stmt.setString (3, String.format("R$ %s-",Double.toString(valor)));
            this.stmt.setString (4, String.format("Para: %s", new DConta().buscarTitular(contaDest).getNome()));
            this.stmt.setInt (5, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao gravar Saque: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
        
        //grava transferencia recebida
          public boolean gravaTransfR (Double valor, int numero, int contaOrig){
        DConta dconta = new DConta();        
        String comando = "insert into extrato values(?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, numero);
            this.stmt.setString (2, "Transferência");
            this.stmt.setString (3, String.format("R$ %s+",Double.toString(valor)));
            this.stmt.setString (4, String.format("De: %s", new DConta().buscarTitular(contaOrig).getNome()));
            this.stmt.setInt (5, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao gravar Transferencia: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
  
    //pega o numero para exibir quando o administrador criar uma nova conta.
    
    public Cliente pegaNumero(String cpf) {
        String comando = "select * from cliente where cpf = ?";
        Cliente cliente = new Cliente(null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setString(1, cpf);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                cliente.setNumero(rs.getInt("numero"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return cliente;
    }
    

        // método excluir conta
        public boolean excluirConta(int numero) {
        String comando = "delete from conta where numero = ?;"
                ;
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
    
    //Buscar
    public Conta buscar(int numero) {
        String comando = "select * from conta where numero= ?";
        Conta conta = new Conta(0);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                conta.setNumero(rs.getInt("numero"));
                conta.setSenha(rs.getInt("senha"));
                conta.setSaldo(rs.getDouble("saldo"));
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return conta;
    }
    
    
    public Cliente buscarTitular(int numero) throws SQLException {
        String comando = "select * from cliente where numero= ?";
        Cliente cliente = new Cliente(null, null, null, null);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return cliente;
    }
    
    //Autenticar
    
     public boolean validar(int numero, int senha) throws SQLException {
        String comando = "select * from conta where numero = ? and senha = ?";
        Conta conta = new Conta(0);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            this.stmt.setInt(2, senha);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                conta.setNumero(rs.getInt("numero"));
                conta.setSenha(rs.getInt("senha"));
                return true;
            }else 
            {
                return false;
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
        }
    return false;
     }
     
         public double pegaSaldo(int numero) throws SQLException {
        String comando = "select saldo from conta where numero = ?";
        Conta conta = new Conta(0);
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            if(rs.next()){
                conta.setSaldo(rs.getDouble("saldo"));
                return conta.getSaldo();
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
        } 
        return conta.getSaldo();
   }
          
          //sacar
           
      public boolean sacar (double valor, int numero, double saldo){
        String comando = "update conta set saldo = ? where numero = ?";
        double nsaldo = saldo - valor;
        if (nsaldo>=0){
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setDouble (1, nsaldo);
            this.stmt.setInt (2, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro no saque: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
        else return false;
      }
      
      //depositar
      
       public boolean depositar(double valor, int numero, double saldo){
        String comando = "update conta set saldo = ? where numero = ?";
        double nsaldo = saldo + valor;
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setDouble (1, nsaldo);
            this.stmt.setInt (2, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro no saque: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
       
       public boolean transferir (int numero, double valor, int ndest, double saldo, double saldo2){
           if(this.sacar(valor, numero, saldo)){
               this.depositar(valor, ndest, saldo2);
               return true;
           }
           else 
               return false;
       }
       
       //Alterar
       
           public boolean alterar (int senha, int numero){
               String comando = "update conta set senha = ? where numero = ?";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, senha);
            this.stmt.setInt (2, numero);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao Alterar Conta "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
           
        public ArrayList<Extrato> pegaExtrato(int numero) {
            ArrayList<Extrato> listExtrato = new ArrayList<>();
        String comando = "select * from extrato where numero = ?";
        
        try{
            this.conectar();
            this.stmt=(PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt(1, numero);
            ResultSet rs = this.stmt.executeQuery();
            while(rs.next()){
                Extrato extrato = new Extrato();
                extrato.setMovimentacao(rs.getString("movimentacao"));
                extrato.setValor(rs.getString("valor"));
                extrato.setContaDest(rs.getString("contaDest"));
                listExtrato.add(extrato);
            }
        }
        catch(SQLException ex){
              System.out.println("Erro"+ex.getMessage());
              
        }
        return listExtrato;
    }
        
                  public boolean gravaPag (double valor, int nconta, String nfat){
        Extrato extrato = new Extrato();       
        String comando = "insert into extrato values(?,?,?,?,?)";
        try{
            this.conectar();
            this.stmt = (PreparedStatement) this.cnn.prepareStatement(comando);
            this.stmt.setInt (1, nconta);
            this.stmt.setString (2, "Pagamento");
            this.stmt.setString (3, String.format("R$ %s-",Double.toString(valor)));
            this.stmt.setString (4, String.format("%s: %s", new DPagamento().buscar(nfat).getEntidade(), new DPagamento().buscar(nfat).getEspecificacao()));
            this.stmt.setInt(5, 0);
            this.stmt.execute();
        }
            catch(SQLException ex){
                    System.out.println("Erro ao gravar Pagamento: "+ex.getMessage());
                    return false;
                    }
            return true;
        
        }
           
           

   
}
