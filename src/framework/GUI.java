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

public class GUI {

	private JFrame frmMineradorDeCorrupo;
	private TextArea textArea;

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
			textArea.append("Títiluo: " + j.getTitle()
						  + "PostDate: " + j.getPostDate()
						  + "Role: " + j.getRole()
						  + "Requirements: " + j.getRequirements()
						  + "Salary: " + j.getRem());
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMineradorDeCorrupo = new JFrame();
		frmMineradorDeCorrupo.setTitle("Minerador de corrup\u00E7\u00E3o");
		frmMineradorDeCorrupo.setBounds(100, 100, 800, 600);
		frmMineradorDeCorrupo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMineradorDeCorrupo.getContentPane().setLayout(null);
		

		final JLabel label = new JLabel("");
		label.setBounds(326, 17, 174, 16);
		frmMineradorDeCorrupo.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Atualizar Banco");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date dataHoje = new Date();
				SimpleDateFormat formataData = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
				String data = formataData.format(dataHoje);
				label.setText(data);	
				
				//textArea.setText("Atualizando a base de dados de bolsa familia...");
				//DadosDoSistema.getDados().getMiner().minerar(EnumPicaretas.B_FAMILIA); //Picareata de servidor
				textArea.append("Atualizando base de dados de servidores...");
				DadosGlobais.getDados().getMiner().minerar(EnumPicaretas.JOB); //Picareata de servidor
			}
		});
		btnNewButton.setBounds(10, 13, 183, 25);
		frmMineradorDeCorrupo.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buscar Irregularidades - Bolsa Fam\u00EDlia");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DadosGlobais.getDados().setMatcher(EnumMatchers.V_B_FAMILIA);
				attTextArea(DadosGlobais.getDados().getMatcher().allMatchs());
			}
		});
		btnNewButton_1.setBounds(512, 13, 260, 25);
		frmMineradorDeCorrupo.getContentPane().add(btnNewButton_1);
		
		textArea = new TextArea();
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBounds(10, 68, 762, 477);
		frmMineradorDeCorrupo.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("\u00DAltima atualiza\u00E7\u00E3o:");
		lblNewLabel.setBounds(205, 17, 121, 16);
		frmMineradorDeCorrupo.getContentPane().add(lblNewLabel);
		
		JLabel lblNomesDosServidores = new JLabel("Nomes dos servidores p\u00FAblicos da UFRN registrados no bolsa fam\u00EDlia:");
		lblNomesDosServidores.setBounds(10, 46, 433, 16);
		frmMineradorDeCorrupo.getContentPane().add(lblNomesDosServidores);
	}
}
