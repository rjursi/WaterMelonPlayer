package musicPlayer.form;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class InfoView implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		new InfoWindow();
	}

	@SuppressWarnings("serial")
	private class InfoWindow extends JFrame {
		
		
		private int FormXLength, FormYLength;
		private Toolkit tk;
		public Dimension ScreenSize, FrameSize;
		private JPanel MainContainer; 
		
		private InfoWindow() {
			
			MainContainer = new JPanel();
			
			ImageIcon TeamInfo = new ImageIcon("images/TeamInfo.gif");
			
			JLabel TeamInfoImg = new JLabel(TeamInfo);
			
			MainContainer.add(TeamInfoImg);
			
			setContentPane(MainContainer);
			setFormLocation_Center();
			
			setTitle("Engineers");
			setSize(700,540);
			setResizable(false);
			setVisible(true);
			
		}
		
		
		private void setFormLocation_Center() {
			tk = Toolkit.getDefaultToolkit();	
			ScreenSize = tk.getScreenSize();
			FrameSize = getSize();
			
			// 위에서 만들어진 레이아웃 표시 사이즈 값 가져옴
			
			FormXLength = (int)(1.0/2 * (ScreenSize.width - FrameSize.width));
			FormYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
			
			setBounds(FormXLength,FormYLength,FrameSize.width, FrameSize.height);
		}	
		
	}
}