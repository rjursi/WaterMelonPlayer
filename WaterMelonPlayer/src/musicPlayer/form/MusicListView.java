package musicPlayer.form;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MusicListView extends JFrame {
	
	
	private MusicTable musicListView;
	// ��ü ����Ʈ �� ������ ���� ������
	private int FormXLength, FormYLength;
	private Toolkit tk;
	public Dimension ScreenSize, FrameSize;
	
	public MusicListView(MusicTable musicListView) {
		
		this.musicListView = musicListView;
		
		
		add(this.musicListView, "Center");
		
		setTitle("��ü ���� ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		setSize(510,600);
		setFormLocation_Left();
		setVisible(true);
		
	}
	
	private void setFormLocation_Left() {
		tk = Toolkit.getDefaultToolkit();	
		ScreenSize = tk.getScreenSize();
		FrameSize = getSize();
		
		// ������ ������� ���̾ƿ� ǥ�� ������ �� ������
		
		FormXLength = (int)(1.0/6 * (ScreenSize.width - FrameSize.width));
		FormYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
		
		setBounds(FormXLength,FormYLength,FrameSize.width, FrameSize.height);
	}	
	
}


// ��ü ���� ��� ��