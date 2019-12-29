package musicPlayer.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SubtitleView extends JFrame {
	
	
	
	
	private int FormXLength, FormYLength;
	private Toolkit tk;
	public Dimension ScreenSize, FrameSize;
	
	JPanel subtitlePanel;
	JScrollPane subtitleScrollPane;
	JTextPane subtitleTextPane;
	String subtitle = "";
	JViewport jViewPort;
	
	public SubtitleView() {
		// TODO Auto-generated constructor stub
		setTitle("음악 가사");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		subtitlePanel = new JPanel();
		subtitleTextPane = new JTextPane();
		
		subtitlePanel.setLayout(new BorderLayout());
		subtitlePanel.setBackground(new Color(39,36,83));
		
		
		StyledDocument doc = subtitleTextPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		subtitleTextPane.setBackground(new Color(39,36,83));
		subtitleTextPane.setForeground(Color.white);
		subtitleTextPane.setFont(new Font("Dialog", Font.BOLD, 15));
		
		readSubtitle();
		
		subtitleTextPane.setText(subtitle);
		subtitleTextPane.setEditable(false);
		subtitleTextPane.setSelectionStart(0);
		subtitleTextPane.setSelectionEnd(0);
		
		subtitleScrollPane = new JScrollPane(subtitleTextPane);
		
		
		subtitlePanel.add(subtitleScrollPane);
		add(subtitlePanel, "Center");
		
		
		
		setSize(300,300);
		setFormLocation_Left();
		setVisible(true);
		
	}
		
		
	public void readSubtitle() {
		File file = new File("./resource/subTitle.txt"); 
		if(file.exists()) { 
			BufferedReader inFile;
			try {
				inFile = new BufferedReader(new FileReader(file));
				String sLine = null; 
				
				while( (sLine = inFile.readLine()) != null ) {
					subtitle += sLine + "\r\n";
				}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //읽어들인 문자열을 출력 합니다. }
			
		}

		
	}
	
	
	private void setFormLocation_Left() {
		tk = Toolkit.getDefaultToolkit();	
		ScreenSize = tk.getScreenSize();
		FrameSize = getSize();
		
		// 위에서 만들어진 레이아웃 표시 사이즈 값 가져옴
		
		FormXLength = (int)(1.0/4 * (ScreenSize.width - FrameSize.width));
		FormYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
		
		setBounds(FormXLength,FormYLength,FrameSize.width, FrameSize.height);
	}	
	
}
