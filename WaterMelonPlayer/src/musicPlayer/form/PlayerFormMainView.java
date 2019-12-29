package musicPlayer.form;

//music Player�� �⺻ ���� ����

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

//���̺��� ������ ���������� �����ϱ� ���Ͽ� model ����Ͽ� ����



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

		// ���̾ƿ� Ÿ��, ǥ�� ������ ����
		//this.formSetting.formComponentSetting(userPlayListView);
		add(this.formSetting); // ������Ʈ�� ������ �г� �߰�
		// �� �� ���õ��� ���� ������ ���Ͽ� ��ġ�� �ϰ� ��
		//add(this.userPlayListView);
		
		this.setFormLocation_Center();
		// ȭ����� �� ��ġ ����� ����
		
		menuBarSet();
		setTitle("Water Melon Player");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false); //â ũ�⸦ �������� ���ϵ��� ����
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
				
				// �÷��̾�� �������� �뷡�� ������ �ǵ��� ����
				// �α׾ƿ��� �ص� �뷡�� ��� ����Ǵ� ���� ����
				
				controller = new MainController();
				controller.showLoginForm();
				setVisible(false);
				
			}
		});
	}
	// �޴��� ���� �޼ҵ�
	
	private void setFormLocation_Center() {
		tk = Toolkit.getDefaultToolkit();	
		ScreenSize = tk.getScreenSize();
		FrameSize = getSize();
		
		// ������ ������� ���̾ƿ� ǥ�� ������ �� ������
		
		FormXLength = (int)(1.0/2 * (ScreenSize.width - FrameSize.width));
		FormYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
		
		setBounds(FormXLength,FormYLength,FrameSize.width, FrameSize.height);
	}	
	
	
}
	

