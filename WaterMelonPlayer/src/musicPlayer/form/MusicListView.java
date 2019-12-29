package musicPlayer.form;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MusicListView extends JFrame {
	
	
	private MusicTable musicListView;
	// 전체 리스트 뷰 설정된 값을 가져옴
	private int FormXLength, FormYLength;
	private Toolkit tk;
	public Dimension ScreenSize, FrameSize;
	
	public MusicListView(MusicTable musicListView) {
		
		this.musicListView = musicListView;
		
		
		add(this.musicListView, "Center");
		
		setTitle("전체 음악 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		setSize(510,600);
		setFormLocation_Left();
		setVisible(true);
		
	}
	
	private void setFormLocation_Left() {
		tk = Toolkit.getDefaultToolkit();	
		ScreenSize = tk.getScreenSize();
		FrameSize = getSize();
		
		// 위에서 만들어진 레이아웃 표시 사이즈 값 가져옴
		
		FormXLength = (int)(1.0/6 * (ScreenSize.width - FrameSize.width));
		FormYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
		
		setBounds(FormXLength,FormYLength,FrameSize.width, FrameSize.height);
	}	
	
}


// 전체 음악 목록 뷰