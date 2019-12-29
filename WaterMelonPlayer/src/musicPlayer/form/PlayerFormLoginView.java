package musicPlayer.form;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javafx.scene.layout.Border;
import musicPlayer.controller.PlayerController;
import musicPlayer.persistence.MusicDAO;

	
	@SuppressWarnings("serial")
	public class PlayerFormLoginView extends JFrame {

		JPanel contentpane;
		JTextField idField;
		JPasswordField pw;
		JButton joinBtn;
		MusicDAO loginDAO;
		PlayerController playerController;
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.white);
			g.fillRoundRect(66, 159, 293, 53, 10, 10);
			g.setColor(new Color(218, 220, 224));
			g.drawRoundRect(65, 158, 295, 55, 10, 10);
			
			g.setColor(Color.white);
			g.fillRoundRect(66, 289, 293, 53, 10, 10);
			g.setColor(new Color(218, 220, 224));
			g.drawRoundRect(65, 288, 295, 55, 10, 10);
		}
		
		
		
		public PlayerFormLoginView(MusicDAO loginDAO, PlayerController playerController) {
			
			this.playerController = playerController;
			this.loginDAO = loginDAO;
			
			
			Font lblfont = new Font("나눔고딕", Font.BOLD, 24);
			Font lblfont2 = new Font("나눔고딕", Font.PLAIN, 18);
			Font lblfont3 = new Font("나눔고딕", Font.PLAIN, 14);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(100, 100, 420, 500);
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5,5,5,5));
			setContentPane(contentpane);
			contentpane.setLayout(null);
			JLabel titlelbl = new JLabel("로그인");
			JLabel engtitlelbl = new JLabel("LOGIN");
			JLabel idlbl = new JLabel("아이디");
			JLabel pwdlbl = new JLabel("비밀번호");
			titlelbl.setFont(lblfont);
			engtitlelbl.setFont(lblfont2);
			idlbl.setFont(lblfont3);
			pwdlbl.setFont(lblfont3);
			idlbl.setForeground(new Color(133, 133, 133));
			pwdlbl.setForeground(new Color(133, 133, 133));
			titlelbl.setBounds(170, 20, 100, 50);
			engtitlelbl.setBounds(177, 50, 100, 50);
			idlbl.setBounds(60, 170, 100, 50);
			pwdlbl.setBounds(60, 300, 100, 50);
			contentpane.add(titlelbl);
			contentpane.add(engtitlelbl);
			contentpane.add(idlbl);
			contentpane.add(pwdlbl);
			
		/*
		 * JLabel lblNewLabel = new JLabel("아이디2"); lblNewLabel.setBounds(12, 34, 57,
		 * 15); contentpane.add(lblNewLabel);
		 */
			
			idField = new JTextField();
			idField.setBounds(60, 130, 290, 50);
			idField.setBorder(new EmptyBorder(0, 0, 0, 0));
			contentpane.add(idField);
			idField.setColumns(10);
			
		/*
		 * JLabel label = new JLabel("비밀번호"); label.setBounds(12, 80, 57, 15);
		 * contentpane.add(label);
		 */
			
			pw = new JPasswordField();
			pw.setBounds(60, 260, 290, 50);
			pw.setBorder(new EmptyBorder(0, 0, 0, 0));
			contentpane.add(pw);
			
			JButton btnNewButton = new JButton("로그인");
			btnNewButton.setBackground(new Color(0, 104, 255));
			btnNewButton.setForeground(Color.white);
			btnNewButton.setBorderPainted(false);
			btnNewButton.setFocusPainted(false);
			btnNewButton.addActionListener(new ActionListener() {
				
				
				String id;
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String password = String.valueOf(pw.getPassword());
					int result = loginDAO.login(idField.getText(), password);
					int distinct = loginDAO.distintion(idField.getText());
					
					if (result == 1) {
						if(distinct == 1) 
							JOptionPane.showMessageDialog(null, "관리자 로그인을 환영합니다.");
						else
							JOptionPane.showMessageDialog(null, "개인회원 로그인을 환영합니다.");
						setVisible(false);
						this.id = idField.getText();
//						dispose();
						playerController.playerRun(id);
					}
					else if (result == 0) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
					}
					else if (result == -1) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
					}
					else if (result == -2) {
						JOptionPane.showMessageDialog(null, "데이터베이스 오류가 발생했습니다.");
					}
				}
			});
			
			
			btnNewButton.setBounds(250, 380, 100, 40);
			contentpane.add(btnNewButton);
			
			
			joinBtn = new JButton("회원가입");
			joinBtn.setBorderPainted(false);
			joinBtn.setContentAreaFilled(false);
			joinBtn.setFocusPainted(false);
			joinBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JoinForm joinForm = new JoinForm(loginDAO);
					joinForm.setVisible(true);
				}
			});
			
			joinBtn.setBounds(40, 390, 97, 23);
			joinBtn.setForeground(new Color(133, 133, 133));
			contentpane.setBackground(new Color(242, 244, 247));
			contentpane.add(joinBtn);
		}
	}


