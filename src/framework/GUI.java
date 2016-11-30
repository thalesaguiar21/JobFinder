package framework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import db.mannager.*;
import framework.match.EnumMatchers;
import framework.webcrawler.EnumPicaretas;

import java.util.List;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Label;

public class GUI {

	private JFrame frmMineradorDeCorrupo;
	private TextArea textArea;
	private JTextField txtDigiteAProfisso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMineradorDeCorrupo.setVisible(true);
					window.frmMineradorDeCorrupo.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Recebe lista e atualiza textArea.
	 */	
	public void attTextArea(List<Job> l){
		for (Job j : l) {
			textArea.append("Título: " + j.getTitle()
						  + "Role: " + j.getRole());
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMineradorDeCorrupo = new JFrame();
		frmMineradorDeCorrupo.setTitle("Job Finder");
		frmMineradorDeCorrupo.setBounds(100, 100, 800, 600);
		frmMineradorDeCorrupo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMineradorDeCorrupo.getContentPane().setLayout(null);
		

		final JLabel label = new JLabel("");
		label.setBounds(326, 17, 174, 16);
		frmMineradorDeCorrupo.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Atualizar Banco");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//textArea.setText("Atualizando a base de dados de bolsa familia...");
				//DadosDoSistema.getDados().getMiner().minerar(EnumPicaretas.B_FAMILIA); //Picareata de servidor
				textArea.append("Atualizando base de dados de servidores...");
				DadosGlobais.getDados().getMiner().minerar(EnumPicaretas.JOB); //Picareta de emprego
			}
		});
		btnNewButton.setBounds(10, 13, 183, 25);
		frmMineradorDeCorrupo.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buscar Emprego");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DadosGlobais.getDados().setMatcher(EnumMatchers.V_JOB);
				String profissao = txtDigiteAProfisso.getText();
				if (profissao.isEmpty()){
					attTextArea(DadosGlobais.getDados().getMatcher().allMatchs());
				}
				else{
					attTextArea(DadosGlobais.getDados().getMatcher().matchByName(profissao));
				}
			}
		});
		btnNewButton_1.setBounds(512, 13, 260, 25);
		frmMineradorDeCorrupo.getContentPane().add(btnNewButton_1);
		
		textArea = new TextArea();
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBounds(10, 94, 762, 451);
		frmMineradorDeCorrupo.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("\u00DAltima atualiza\u00E7\u00E3o:");
		lblNewLabel.setBounds(205, 17, 121, 16);
		frmMineradorDeCorrupo.getContentPane().add(lblNewLabel);
		
		txtDigiteAProfisso = new JTextField();
		txtDigiteAProfisso.setBounds(512, 54, 260, 26);
		frmMineradorDeCorrupo.getContentPane().add(txtDigiteAProfisso);
		txtDigiteAProfisso.setColumns(10);
		
		Label label_1 = new Label("Profiss\u00E3o:");
		label_1.setBounds(426, 54, 82, 27);
		frmMineradorDeCorrupo.getContentPane().add(label_1);
	}
}
