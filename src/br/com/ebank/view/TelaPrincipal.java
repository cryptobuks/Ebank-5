/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ebank.view;

import br.com.ebank.business.BConta;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author Gerson
 */
public class TelaPrincipal extends JFrame{
    private JLabel lblEbank;
    private JButton btnLogin;   //criando o atributo botão
    private JButton btnCancelar;
    private JButton btnAdmin;
    private JLabel lblLogin;// Criando o atributo Label(texto)
    private JLabel lblSenha;
    private JTextField txtNumero;//Criando os atributos TextFields(Campos para o usuário digitar)
    private javax.swing.JPasswordField txtSenha;
    
    //método que monta a janela e exibe na tela(apenas quando este método for chamado)
    public void montarJanela(){
        
        //Atributos da Tela
        this.setSize(300,300);//Definindo o tamanho da tela
        this.setLocationRelativeTo(null);//Esse serva para abrir a janela no centro da tela
        this.setLayout(null);//Aqui você fala para o java que não vai usar um dos layouts prontos.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//esse você fala para o java fechar a execução do programa assim que esta janela for fechada
        this.getContentPane().setBackground(Color.lightGray);
        
        
        //Botão login(Mesma coisa para texto e campo de texto
        this.btnLogin = new JButton("Acessar");//Criar o objeto botão e salvá-lo no atributo btnLogin
        btnLogin.setBackground(Color.darkGray);  
        btnLogin.setForeground(Color.WHITE);
        this.btnLogin.setBounds(50,185,85,30);//tamanho e localização
        this.getContentPane().add(btnLogin);//adicionar o botão á tela.
        this.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BConta bconta = new BConta();
                try {
                    if (bconta.validar(Integer.parseInt(txtNumero.getText()),Integer.parseInt(txtSenha.getText()))){                      
                        Tp_Correntista window =new Tp_Correntista();                        
                        TelaPrincipal.this.dispose();
                        window.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizar a tela
                        window.setLocationRelativeTo(null);
                        window.setNconta(Integer.parseInt(txtNumero.getText()));
                        window.pegaSaldo(Integer.parseInt(txtNumero.getText()));
                        window.setSenha(Integer.parseInt(txtSenha.getText()));
                        BConta bc = new BConta();
                        bc.buscarTitular(Integer.parseInt(txtNumero.getText()));
                        JOptionPane.showMessageDialog(null,String.format("Bem vindo(a) %s", bc.buscarTitular(Integer.parseInt(txtNumero.getText())).getNome()));
                        window.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(null,String.format("Conta ou senha inválidos."));
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
        //Botão cancelar
        this.btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.darkGray);  
        btnCancelar.setForeground(Color.WHITE);
        this.btnCancelar.setBounds(150,185,85,30);
        this.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal.this.dispose();
            }
        });
        this.getContentPane().add(btnCancelar);
        

        //Texto Título
        this.lblEbank = new JLabel("Ebank");
        lblEbank.setForeground(Color.blue);
        lblEbank.setFont(new Font("Dialog", Font.PLAIN, 36));
        this.lblEbank.setBounds(90,20,140,30);
        this.getContentPane().add(lblEbank);
        
        
        
        //Texto login
         this.lblLogin = new JLabel("Conta:");
         lblLogin.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.lblLogin.setBounds(50,90,85,30);
        this.getContentPane().add(lblLogin);
        
        //Texto Senha
        this.lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.lblSenha.setBounds(50,115,85,30);
        this.getContentPane().add(lblSenha);
        
        //Campo de texto Login
        this.txtNumero = new JTextField("");
        this.txtNumero.setBounds(115,90,110,20);
        this.getContentPane().add(txtNumero);
        
        //Campo de texto Senha
        this.txtSenha = new JPasswordField("");
        this.txtSenha.setBounds(115,120,110,20);
        this.getContentPane().add(txtSenha);
        
        this.btnAdmin = new JButton("Admin");
        btnAdmin.setBackground(Color.blue);  
        btnAdmin.setForeground(Color.WHITE);
        this.btnAdmin.setBounds(50,220,186,30);
        this.btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal.this.dispose();
                FrmLoginAdm frm = new FrmLoginAdm();
                frm.setSize(500,500);
                frm.setLocationRelativeTo(null);
               // frm.setLocationRelativeTo(null);
                frm.setVisible(true);
               // window.setExtendedState(JFrame.MAXIMIZED_BOTH); maximizar a tela
                
            }
            });
        this.getContentPane().add(btnAdmin);//adicionar o botão á tela.
        this.setVisible(true);//ativar a exibição da janela na tela
        
    }
}
