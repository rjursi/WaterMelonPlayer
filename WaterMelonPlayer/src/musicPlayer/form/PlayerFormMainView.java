package musicPlayer.form;

//music Player의 기본 폼을 정의

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javafx.scene.media.MediaPlayer;
import musicPlayer.controller.MainController;
import musicPlayer.controller.PlayerController;
import musicPlayer.playcontrol.SoundPlay;

//테이블의 내용을 지속적으로 변경하기 위하여 model 사용하여 변경



@SuppressWarnings("serial")
public class PlayerFormMainView extends JFrame {

	private int FormXLength, FormYLength;
	private Toolkit tk;
	public Dimension ScreenSize, FrameSize;
	
	private PlayerFormSettings formSetting;
	
	public JMenuBar MenuBar;
	public JMenu Menu;
	public JMenuItem Info, Logout;
	
	public SoundPlay soundPlayElements;
	
	

	public MainController controller;
	public PlayerFormLoginView playerFormLoginView;
	
	public void setSoundPlayElements(SoundPlay soundPlayElements) {
		this.soundPlayElements = soundPlayElements;
	}
	
	
	public PlayerFormMainView(PlayerFormSettings formSetting) {
		
		
		this.formSetting = formSetting;
		
		setSize(510,880);

		// 레이아웃 타입, 표시 사이즈 지정
		//this.formSetting.formComponentSetting(userPlayListView);
		add(this.formSetting); // 컴포넌트들 설정한 패널 추가
		// 각 폼 셋팅들이 위의 과정을 통하여 위치를 하게 됨
		//add(this.userPlayListView);
		
		this.setFormLocation_Center();
		// 화면상의 폼 위치 가운데로 수정
		
		menuBarSet();
		setTitle("Water Melon Player");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false); //창 크기를 수정하지 못하도록 설정
		setVisible(true);
	
	}


	public void menuBarSet() {
		
		MenuBar = new JMenuBar();
		Menu = new JMenu("Menu");
		Info = new JMenuItem("Show Made By.....");
		Logout = new JMenuItem("Logout");
		Menu.add(Info);
		Menu.add(Logout);
		MenuBar.add(Menu);
		setJMenuBar(MenuBar);
		
		Info.addActionListener(new InfoView());
	
		Logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				if(soundPlayElements.Player != null) {
					soundPlayElements.Player.stop();
				}
				
				// 플레이어에서 실행중인 노래가 중지가 되도록 설정
				// 로그아웃을 해도 노래가 계속 재생되는 버그 수정
				
				controller = new MainController();
				controller.showLoginForm();
				setVisible(false);
				
			}
		});
	}
	// 메뉴바 설정 메소드
	
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
	

