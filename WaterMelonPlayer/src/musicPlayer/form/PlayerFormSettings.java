package musicPlayer.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class PlayerFormSettings extends JPanel implements PlayerForm_Interfaces{

	// 각종 컴포넌트들과 각종 컴포넌트 배치 메소드가 위치한 클래스 
	
	public GridBagConstraints gconstraint;
	
	public JTable MusicList;
	public MusicTable userPlayListTable;
	
	public DefaultTableModel model;
	
	public JPanel MusicItemListBtn;
	
	public JPanel SoundLengthBox;
	
	public JPanel TitleArea, CtrlArea, ButtonCtrl, MusicItems, AddDelBtnArea, MusicOptionArea1, MusicOptionArea2, MusicOptionArea3, AlbumArea;
	
	public JButton CtrlButton[] = new JButton[4]; //이전 노래, 현재 노래 일시정지 및 재생, 다음 노래 지정
	public JButton AddDelButton[] = new JButton[2];
	public JButton OptionButton[] = new JButton[2];
	public JButton SoundButton[] = new JButton[2];
	
	public JSlider SoundLength, MusicLength;
	public JLabel TitleMusicTitle, TitleMusicArtist, TitleMusicTimeL, TitleMusicTimeR, No, MusicTitle, Artist, MusicTime, Albumbar, Albumbar2, Overlay;
	public JLabel AlbumArt;
	
	public JScrollPane MusicListScroll;
	public JCheckBox MusicAllSelect;
	
	public JButton ArtworkButton;
	
	

	public ImageIcon RandomButtonImage01 = new ImageIcon("images/RndB.png");
	public ImageIcon RandomButtonImage02 = new ImageIcon("images/RndB2.png");
	public ImageIcon RandomButtonImage03 = new ImageIcon("images/RndB3.png");
	public ImageIcon ReButtonImage01 = new ImageIcon("images/ReB.png");
	public ImageIcon ReButtonImage02 = new ImageIcon("images/ReB2.png");
	public ImageIcon ReButtonImage03 = new ImageIcon("images/ReB3.png");
	public ImageIcon OverlayImage = new ImageIcon("images/OverlayBar.gif");
	public ImageIcon NoReButtonImage = new ImageIcon("images/ReBx.png");
	public ImageIcon NoReButtonImage2 = new ImageIcon("images/ReBx2.png");
	public ImageIcon NoReButtonImage3 = new ImageIcon("images/ReBx3.png");
	public ImageIcon NoRandomButtonImage = new ImageIcon("images/RndBx.png");
	public ImageIcon NoRandomButtonImage2 = new ImageIcon("images/RndBx2.png");
	public ImageIcon NoRandomButtonImage3 = new ImageIcon("images/RndBx3.png");
	public ImageIcon ButtonImage01 = new ImageIcon("images/B1.png");
	public ImageIcon ButtonRolloverImage01 = new ImageIcon("images/R1.png");
	public ImageIcon ButtonPressImage01 = new ImageIcon("images/P1.png");
	public ImageIcon ButtonImage02 = new ImageIcon("images/B2.png");
	public ImageIcon ButtonRolloverImage02 = new ImageIcon("images/R2.png");
	public ImageIcon ButtonPressImage02 = new ImageIcon("images/P2.png");
	public ImageIcon ButtonImage03 = new ImageIcon("images/B3.png");
	public ImageIcon ButtonRolloverImage03 = new ImageIcon("images/R3.png");
	public ImageIcon ButtonPressImage03 = new ImageIcon("images/P3.png");
	public ImageIcon ButtonImage22 = new ImageIcon("images/B22.png");
	public ImageIcon ButtonRolloverImage22 = new ImageIcon("images/R22.png");
	public ImageIcon ButtonPressImage22 = new ImageIcon("images/P22.png");
	public ImageIcon SoundUpButtonImage01 = new ImageIcon("images/SoundUpB.png");
	public ImageIcon SoundUpButtonImage02 = new ImageIcon("images/SoundUpB2.png");
	public 	ImageIcon SoundUpButtonImage03 = new ImageIcon("images/SoundUpB3.png");
	public ImageIcon SoundDnButtonImage01 = new ImageIcon("images/SoundDnB.png");
	public ImageIcon SoundDnButtonImage02 = new ImageIcon("images/SoundDnB2.png");
	public ImageIcon SoundDnButtonImage03 = new ImageIcon("images/SoundDnB3.png");
	public ImageIcon AddImage = new ImageIcon("images/AddB.png");
	public ImageIcon AddRolloverImage = new ImageIcon("images/AddB2.png");
	public ImageIcon AddPressImage = new ImageIcon("images/AddB3.png");
	public ImageIcon SubjectButtonImage = new ImageIcon("images/SubB.png");
	public ImageIcon SubjectRolloverImage = new ImageIcon("images/SubB2.png");
	public ImageIcon SubjectPressImage = new ImageIcon("images/SubB3.png");
	
	public void formComponentSetting() {
		
		
		this.MusicItemListBtn = this.userPlayListTable.getMusicItemListBtn();
		this.MusicItems = this.userPlayListTable.getMusicItems();
		this.MusicAllSelect = this.userPlayListTable.getMusicAllSelect();
		// 플레이어 폼 세팅하기 위한  각종 메소드들 실행
		
		this.titleAreaSet();
		this.albumAreaSet();
		this.musicOptionAreaSet();
		this.ctrlAreaSet();
		this.setSoundLength();
		this.setAddDelBtns();
	
		
		gconstraint.gridx = 0;
		gconstraint.gridy = 8;
		
		add(userPlayListTable,gconstraint);
		
		// 마지막 테이블 영역을 맨 아래에다가 넣음
		
		
	}
	

	public PlayerFormSettings(MusicTable userPlayListTable) {
		
		this.model = userPlayListTable.getModel();
		
		gconstraint = new GridBagConstraints();
		this.userPlayListTable = userPlayListTable;
		this.MusicList = userPlayListTable.getMusicList();
		this.MusicListScroll = userPlayListTable.getMusicListScroll();
		
		// 다른 곳에서 참조를 할 수 있도록 다음과 같이 정의
		
		setLayout(new GridBagLayout());
		setBackground(new Color(31,29,62));
		formComponentSetting();
		
	
		// 초기 레이아웃을 gridbaglayout 으로 설정
		
	}
		

	public void titleAreaSet() {
		TitleArea = new JPanel();
		TitleArea.setPreferredSize(new Dimension(450, 80));
		TitleArea.setLayout(new GridLayout(2,1));
		TitleArea.setOpaque(false);
		
		TitleMusicTitle = new JLabel("Music Player",JLabel.CENTER);
		TitleMusicTitle.setFont(new Font("맑은고딕",Font.BOLD,20));
		TitleMusicTitle.setForeground(Color.WHITE);
		TitleMusicTitle.setOpaque(false);
		
	
		TitleMusicArtist = new JLabel("ver 1.3c",JLabel.CENTER);
		TitleMusicArtist.setFont(new Font("맑은고딕",Font.ITALIC,10));
		TitleMusicArtist.setForeground(Color.WHITE);
		TitleMusicArtist.setOpaque(false);
			

		TitleArea.add(TitleMusicTitle);
		TitleArea.add(TitleMusicArtist);

		gconstraint.fill = GridBagConstraints.HORIZONTAL;
		gconstraint.gridx = 0;
		gconstraint.gridy = 0;
		
		add(TitleArea,gconstraint);
	}

	public void albumAreaSet() {
		AlbumArea = new JPanel();
		AlbumArea.setPreferredSize(new Dimension(450, 170));
		AlbumArea.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		AlbumArea.setLayout(new BorderLayout());
		AlbumArea.setOpaque(false);
		
		ImageIcon AlbumLine = new ImageIcon("images/albumbar.png");
		ImageIcon AlbumImageButton = new ImageIcon("images/AlbumImage.png");
		
		Albumbar = new JLabel(AlbumLine);
		Albumbar.setBounds(0, 0, AlbumLine.getIconWidth(), AlbumLine.getIconHeight());
		Albumbar2 = new JLabel(AlbumLine);
		Albumbar2.setBounds(0, 0, AlbumLine.getIconWidth(), AlbumLine.getIconHeight());
		
		ArtworkButton = new JButton(AlbumImageButton);
		ArtworkButton.setContentAreaFilled(false);
		ArtworkButton.setBorderPainted(false);
		ArtworkButton.setFocusPainted(false);
		
		AlbumArea.add(Albumbar, BorderLayout.NORTH);
		AlbumArea.add(ArtworkButton, BorderLayout.CENTER);
		AlbumArea.add(Albumbar2, BorderLayout.SOUTH);
		
		gconstraint.fill = GridBagConstraints.NONE;
		gconstraint.gridx = 0;
		gconstraint.gridy = 1;
		
		add(AlbumArea,gconstraint);
	}
	
	public void  musicOptionAreaSet() {
		
	
		MusicOptionArea1 = new JPanel();
		MusicOptionArea1.setPreferredSize(new Dimension(450,30));
		MusicOptionArea1.setLayout(new BorderLayout());
		MusicOptionArea1.setOpaque(false);
		
		MusicOptionArea2 = new JPanel();
		MusicOptionArea2.setPreferredSize(new Dimension(450,40));
		MusicOptionArea2.setLayout(new BorderLayout());
		MusicOptionArea2.setOpaque(false);
		
		MusicOptionArea3 = new JPanel();
		MusicOptionArea3.setPreferredSize(new Dimension(450,30));
		MusicOptionArea3.setLayout(new BorderLayout());
		MusicOptionArea3.setOpaque(false);
	
		MusicLength = new JSlider(JSlider.HORIZONTAL,0,100,0);
		MusicLength.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		MusicLength.setOpaque(false);
		
		
		Overlay = new JLabel(OverlayImage);
		Overlay.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		Overlay.setVisible(false);
	
		OptionButton[0] = new JButton(NoReButtonImage);
		OptionButton[0].setRolloverIcon(NoReButtonImage2);
		OptionButton[0].setPressedIcon(NoReButtonImage3);
		OptionButton[0].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		OptionButton[1] = new JButton(NoRandomButtonImage);
		OptionButton[1].setRolloverIcon(NoRandomButtonImage2);
		OptionButton[1].setPressedIcon(NoRandomButtonImage3);
		OptionButton[1].setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		
	
		OptionButton[0].setContentAreaFilled(false);
		OptionButton[0].setBorderPainted(false);
		OptionButton[0].setFocusPainted(false);
		OptionButton[1].setContentAreaFilled(false);
		OptionButton[1].setBorderPainted(false);
		OptionButton[1].setFocusPainted(false);
		
		TitleMusicTimeL = new JLabel("-- : --");
		TitleMusicTimeL.setFont(new Font("맑은고딕", Font.BOLD, 10));
		TitleMusicTimeL.setForeground(Color.WHITE);
		TitleMusicTimeL.setOpaque(false);
		TitleMusicTimeL.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
		
		TitleMusicTimeR = new JLabel("-- : --",JLabel.RIGHT);
		TitleMusicTimeR.setFont(new Font("맑은고딕", Font.BOLD, 10));
		TitleMusicTimeR.setForeground(Color.WHITE);
		TitleMusicTimeR.setOpaque(false);
		TitleMusicTimeR.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
		
		MusicOptionArea1.add(OptionButton[0], BorderLayout.WEST);
		MusicOptionArea1.add(Overlay, BorderLayout.CENTER);
		MusicOptionArea1.add(OptionButton[1], BorderLayout.EAST);
		MusicOptionArea2.add(MusicLength);
		MusicOptionArea3.add(TitleMusicTimeL, BorderLayout.WEST);
		MusicOptionArea3.add(TitleMusicTimeR, BorderLayout.EAST);
		
		gconstraint.fill = GridBagConstraints.NONE;
		
		gconstraint.gridx = 0;
		gconstraint.gridy = 2;
		add(MusicOptionArea1,gconstraint);
		
		gconstraint.fill = GridBagConstraints.HORIZONTAL;
		gconstraint.gridx = 0;
		gconstraint.gridy = 3;
		add(MusicOptionArea2,gconstraint);
		
		gconstraint.fill = GridBagConstraints.HORIZONTAL;
		gconstraint.gridx = 0;
		gconstraint.gridy = 4;
		add(MusicOptionArea3,gconstraint);
	}
	
	
	public void ctrlAreaSet() {
		CtrlArea = new JPanel();
		CtrlArea.setPreferredSize(new Dimension(450,150));
		
		CtrlArea.setLayout(new FlowLayout());
		CtrlArea.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		CtrlArea.setOpaque(false);
		//1행 2열로 나눔
		
	
		CtrlButton[0] = new JButton(ButtonImage01);
		CtrlButton[0].setRolloverIcon(ButtonRolloverImage01);
		CtrlButton[0].setPressedIcon(ButtonPressImage01);
		
		// 이전곡
	
		CtrlButton[1] = new JButton(ButtonImage22);
		CtrlButton[1].setRolloverIcon(ButtonRolloverImage22);
		CtrlButton[1].setPressedIcon(ButtonPressImage22);
	
		//재생
		
		CtrlButton[2] = new JButton(ButtonImage02);
		CtrlButton[2].setRolloverIcon(ButtonRolloverImage02);
		CtrlButton[2].setPressedIcon(ButtonPressImage02);
		CtrlButton[2].setVisible(false);
		
		//일시정지
		
		CtrlButton[3] = new JButton(ButtonImage03);
		CtrlButton[3].setRolloverIcon(ButtonRolloverImage03);
		CtrlButton[3].setPressedIcon(ButtonPressImage03);
		
		
		// 다음곡
	
		gconstraint.gridx = 2;
		gconstraint.gridy = 0;
		
		CtrlArea.add(CtrlButton[3], gconstraint);
		
		for(int i=0;i<CtrlButton.length;i++) {
			CtrlButton[i].setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
			
			CtrlButton[i].setContentAreaFilled(false);
			CtrlButton[i].setBorderPainted(false);
			CtrlButton[i].setFocusPainted(false);
			CtrlButton[i].setOpaque(false);
			CtrlArea.add(CtrlButton[i]);
		}
		
		gconstraint.fill = GridBagConstraints.HORIZONTAL;
		gconstraint.gridx = 0;
		gconstraint.gridy = 5;
		
		add(CtrlArea,gconstraint);
	}
	
	
	public void setSoundLength() {
		
		SoundLengthBox = new JPanel(new BorderLayout(10,10));
		SoundLengthBox.setPreferredSize(new Dimension(450,90));
		SoundLengthBox.setOpaque(false);
		
			SoundLength = new JSlider(JSlider.HORIZONTAL,0,100,50);
			SoundLength.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
			SoundLength.setOpaque(false);
			
			
			
			SoundButton[0] = new JButton(SoundUpButtonImage01);
			SoundButton[0].setRolloverIcon(SoundUpButtonImage02);
			SoundButton[0].setPressedIcon(SoundUpButtonImage03);
			SoundButton[0].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 60));
			
			SoundButton[1] = new JButton(SoundDnButtonImage01);
			SoundButton[1].setRolloverIcon(SoundDnButtonImage02);
			SoundButton[1].setPressedIcon(SoundDnButtonImage03);
			SoundButton[1].setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 0));
			
			SoundButton[0].setContentAreaFilled(false);
			SoundButton[0].setBorderPainted(false);
			SoundButton[0].setFocusPainted(false);
			SoundButton[1].setContentAreaFilled(false);
			SoundButton[1].setBorderPainted(false);
			SoundButton[1].setFocusPainted(false);
			
			
			SoundLengthBox.add(SoundButton[0], BorderLayout.EAST);
			SoundLengthBox.add(SoundLength, BorderLayout.CENTER);
			SoundLengthBox.add(SoundButton[1], BorderLayout.WEST);
			
		gconstraint.fill = GridBagConstraints.NONE;
		gconstraint.gridx = 0;
		gconstraint.gridy = 6;
		
		add(SoundLengthBox,gconstraint);
	}
	
	

	public void setAddDelBtns() {
		
		AddDelBtnArea = new JPanel();
		AddDelBtnArea.setLayout(new GridLayout(1,2));
		AddDelBtnArea.setPreferredSize(new Dimension(450,40));
		AddDelBtnArea.setBorder(BorderFactory.createEmptyBorder(0,370,0,0));
		AddDelBtnArea.setOpaque(false);
		
	
		AddDelButton[0] = new JButton(AddImage);
		AddDelButton[0].setRolloverIcon(AddRolloverImage);
		AddDelButton[0].setPressedIcon(AddPressImage);
		
		AddDelButton[1] = new JButton(SubjectButtonImage);
		AddDelButton[1].setRolloverIcon(SubjectRolloverImage);
		AddDelButton[1].setPressedIcon(SubjectPressImage);
		
		AddDelButton[0].setContentAreaFilled(false);
		AddDelButton[0].setBorderPainted(false);
		AddDelButton[0].setFocusPainted(false);
		AddDelButton[1].setContentAreaFilled(false);
		AddDelButton[1].setBorderPainted(false);
		AddDelButton[1].setFocusPainted(false);
		
		
		AddDelBtnArea.add(AddDelButton[0]);
		AddDelBtnArea.add(AddDelButton[1]);
				
		MusicItemListBtn.add("South",AddDelBtnArea);
		
		gconstraint.gridx = 0;
		gconstraint.gridy = 7;
		
		add(MusicItemListBtn,gconstraint);
		
	}
	
	
	
	//폼 띄우는 것을 테스트 하기 위한 메인 메소드
}
