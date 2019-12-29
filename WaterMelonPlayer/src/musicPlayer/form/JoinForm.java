package musicPlayer.form;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import musicPlayer.domain.MusicVO;
import musicPlayer.persistence.MusicDAO;

public class JoinForm extends JFrame{
	
	MusicVO vo;
	JLabel label[] = new JLabel[5];
	JTextField textfield[] = new JTextField[2];
	JPasswordField pw;
	JButton signup, cancel;

	JPanel panel4;
	MusicDAO dao;
	
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
		
		g.setColor(Color.white);
		g.fillRoundRect(66, 419, 293, 53, 10, 10);
		g.setColor(new Color(218, 220, 224));
		g.drawRoundRect(65, 418, 295, 55, 10, 10);
	}
	
	public JoinForm(MusicDAO dao) {
		
		this.dao = dao;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GridLayout frameLayout = new GridLayout(6,1);   // 전체적인 큰 틀은 그리드레이아웃!
		frameLayout.setVgap(3);   // GridLayout간의 간격 설정
		this.setLayout(frameLayout);  
		
		Font lblfont = new Font("나눔고딕", Font.BOLD, 24);
		Font lblfont2 = new Font("나눔고딕", Font.PLAIN, 18);
		Font lblfont3 = new Font("나눔고딕", Font.PLAIN, 14);
		
		label[0] = new JLabel("회원가입");
		label[0].setFont(lblfont);
		label[0].setBounds(160, 20, 100, 50);
		label[1] = new JLabel("account");
		label[1].setFont(lblfont2);
		label[1].setBounds(170, 50, 100, 50);
		label[2] = new JLabel("아이디");
		label[3] = new JLabel("비밀번호");
		label[4] = new JLabel("이름");
		
		label[2].setFont(lblfont3);
		label[3].setFont(lblfont3);
		label[4].setFont(lblfont3);
		
		label[2].setForeground(new Color(133, 133, 133));
		label[3].setForeground(new Color(133, 133, 133));
		label[4].setForeground(new Color(133, 133, 133));
		
		label[2].setBounds(60, 170, 100, 50);
		label[3].setBounds(60, 300, 100, 50);
		label[4].setBounds(60, 430, 100, 50);
		
		textfield[0] = new JTextField(8);               // 이메일 아이디
		textfield[0].setBounds(60, 130, 290, 50);
		textfield[0].setBorder(new EmptyBorder(0, 0, 0, 0));
		pw = new JPasswordField(8);             // 비밀번호
		pw.setBounds(60, 260, 290, 50);
		pw.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		textfield[1] = new JTextField(8);            // 이름
		textfield[1].setBounds(60, 390, 290, 50);
		textfield[1].setBorder(new EmptyBorder(0, 0, 0, 0));
		
		signup = new JButton("회원가입");
		signup.setBounds(250, 510, 100, 40);
		signup.setBackground(new Color(0, 104, 255));
		signup.setForeground(Color.white);
		signup.setBorderPainted(false);
		signup.setFocusPainted(false);
		signup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
					int signUpCheckFlag = 1;
					
					String password = String.valueOf(pw.getPassword());
					vo = new MusicVO();
					vo.setid(textfield[0].getText());
					vo.setName(textfield[1].getText());
					vo.setPassword(password);
					
					
					signUpCheckFlag = dao.signUpCheck(vo);
					
					if(signUpCheckFlag == 0) {
						JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.");
					}
					else {
					
						dao.signUp(vo);
						dispose();
						JOptionPane.showMessageDialog(null, "회원가입을 환영합니다.");
					}
				}
		});
		
		cancel = new JButton("취  소");
		cancel.setBounds(40, 520, 97, 23);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
		cancel.setFocusPainted(false);
		cancel.setForeground(new Color(133, 133, 133));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			}
			
		});
		

		panel4 = new JPanel();
		
		panel4.add(label[0]);
		panel4.add(label[1]);
		panel4.add(label[2]);
		panel4.add(label[3]);
		panel4.add(label[4]);
		panel4.add(textfield[0]);
		panel4.add(pw);
		panel4.add(textfield[1]);
		
		

		

		
		panel4.add(signup);
		panel4.add(cancel);

		this.add(panel4);
		
		setTitle("회원가입 폼");
		panel4.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(panel4);
		panel4.setLayout(null);
		setBounds(150, 150, 420, 630);
		panel4.setBackground(new Color(242, 244, 247));
		
		setVisible(true);
	}
}
