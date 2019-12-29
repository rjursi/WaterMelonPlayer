package musicPlayer.playcontrol;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import musicPlayer.form.PlayerFormSettings;
import musicPlayer.musicFunc.MusicFunc;



public class MusicControl {
	
	private SoundPlay soundPlayElements;
	private PlayerFormSettings formComponents;
	private MusicFunc musicFuncElements;
	OverlayThread overlayThread;
	Thread OTE;
	
	public MusicControl(SoundPlay soundPlayElements, PlayerFormSettings formComponents, MusicFunc musicFuncElements) {
		
		
		this.musicFuncElements = musicFuncElements;
		this.formComponents = formComponents;
		this.soundPlayElements = soundPlayElements;
		
		
		overlayThread = new OverlayThread(formComponents);
		OTE = null;
		//OTE = new Thread(overlayThread, "test");
	}
	


	public class MusicPlay implements ActionListener {
		
		
		@SuppressWarnings("unused")
		private JFXPanel panel;
		
		public MusicPlay() {
			this.panel = new JFXPanel();
		}
	
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MusicPlay(e,soundPlayElements.PlayStatus);
		}
	}
	
	public class MusicChange implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(soundPlayElements.Player != null) {	
				if(formComponents.model.getRowCount() == 0){
					soundPlayElements.Player.stop();
					formComponents.CtrlButton[2].setVisible(false);
					formComponents.CtrlButton[1].setVisible(true);
					formComponents.Overlay.setVisible(false);
					formComponents.TitleMusicTimeL.setText("-- : --");
					formComponents.TitleMusicTimeR.setText("-- : --");
					formComponents.TitleMusicArtist.setText("ver.1a");
					formComponents.TitleMusicTitle.setText("MusicPlayer");
					
					formComponents.ArtworkButton.setIcon(new ImageIcon("images/AlbumImage.png"));
					
					
					return;
				}
				
				soundPlayElements.Player.stop();
				
				
				if(e.getSource() == formComponents.CtrlButton[0]) {
					formComponents.CtrlButton[2].setVisible(true);
					formComponents.CtrlButton[1].setVisible(false);
					
					
					musicFuncElements.ListRow--;
					
					if(musicFuncElements.ListRow < 0) {
						musicFuncElements.ListRow = musicFuncElements.MusicSerial - 2;
					}
					
					setMedia();
					
					soundPlayElements.Player = new MediaPlayer(soundPlayElements.media);
					soundPlayElements.tempListRow = musicFuncElements.ListRow;
					VolumeInit();
					
					soundPlayElements.Player.play();
					
					setMusicTimer();
					
				}
				
				if(e.getSource() == formComponents.CtrlButton[3]) {
					
					formComponents.CtrlButton[2].setVisible(true);
					formComponents.CtrlButton[1].setVisible(false);
					
					musicFuncElements.ListRow = RandomOrSerialPlaySet() ;
			
					setMedia();
					soundPlayElements.Player = new MediaPlayer(soundPlayElements.media);
					soundPlayElements.tempListRow = musicFuncElements.ListRow;
					VolumeInit();
					
					soundPlayElements.Player.play();
					setMusicTimer();
					
				}
			}
		}
	}
	
	
	
	public class SoundControl implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			if(soundPlayElements.Player != null) {
				soundPlayElements.Player.setVolume((double)formComponents.SoundLength.getValue()/100);
				soundPlayElements.Player.getVolume();
			}
		}
	}
	
	
	public class MusicSliderControl implements MouseListener{
		
		int musicTotalTime = 0;
		
		public void mouseClicked(MouseEvent me) {
			
			if(soundPlayElements.Player != null) {
				
				musicTotalTime = (int)soundPlayElements.Player.getTotalDuration().toSeconds();
				
				soundPlayElements.MusicTimer.stop();
				soundPlayElements.Player.pause();
				
				
				formComponents.MusicLength.setValue((int)(((me.getX() - formComponents.MusicLength.getX())-26)/((float)440)*100));

				soundPlayElements.Player.seek(Duration.seconds(musicTotalTime*formComponents.MusicLength.getValue()/100));
				
				soundPlayElements.MusicTimer.start();
				soundPlayElements.Player.play();
			
			}
			
		
		}
		public void mouseEntered(MouseEvent me) {}
		
		public void mousePressed(MouseEvent me) {
			if(soundPlayElements.Player != null) {
				musicTotalTime = (int)soundPlayElements.Player.getTotalDuration().toSeconds();
				
				soundPlayElements.Player.seek(Duration.seconds(musicTotalTime*formComponents.MusicLength.getValue()/100));
				soundPlayElements.MusicTimer.stop();
				soundPlayElements.Player.pause();
			}
		}
		
		public void mouseReleased(MouseEvent me) {
			if(soundPlayElements.Player != null) {
				musicTotalTime = (int)soundPlayElements.Player.getTotalDuration().toSeconds();
				soundPlayElements.Player.seek(Duration.seconds(musicTotalTime*formComponents.MusicLength.getValue()/100));
				soundPlayElements.MusicTimer.start();
				soundPlayElements.Player.play();
				
			}
		}
		
		public void mouseExited(MouseEvent me) {}
	}
	
	
	public class MusicSelectOnList implements MouseListener{
		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent me) {
			musicFuncElements.ListRow = formComponents.MusicList.getSelectedRow();
			NowPlayingMusicSet();
			if(me.getClickCount() == 2) {
				
				if(soundPlayElements.Player != null) {
					soundPlayElements.Player.stop();
				}
				
				soundPlayElements.PlayStatus = "Playing";
			
				
				OTE = new Thread(overlayThread, "test");
				overlayThread.setStop(false);
				OTE.start();	// 쓰레드 시작
				MusicPlay(soundPlayElements.BtnStatus,soundPlayElements.PlayStatus);
				
				//두번 눌렀을 경우 실행
			}
		}
		
		
		public void mouseEntered(MouseEvent me) {
				
		}
		
		public void mousePressed(MouseEvent me) {
			
		}
		
		public void mouseReleased(MouseEvent me) {
			
		}
		
		public void mouseExited(MouseEvent me) {
			
		}
		
	}
	
	
	public class MusicSliderControlPlus implements MouseMotionListener{
		int musicTotalTime = 0;
		
		public void mouseDragged(MouseEvent me) {
			if(soundPlayElements.Player != null) {
				musicTotalTime = (int)soundPlayElements.Player.getTotalDuration().toSeconds();
				
				soundPlayElements.Player.seek(Duration.seconds(musicTotalTime*formComponents.MusicLength.getValue()/100));
				soundPlayElements.MusicTimer.stop();
				soundPlayElements.Player.pause();
			}
		}
		public void mouseMoved(MouseEvent me) {}
	}
	
	
	
	public class RandomPlay implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			RandomPlaySet();			
		}
	}
	
	public class isRepeat implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			RepeatPlaySet();
		}
	}
	
	
	// 아래서부터 위 내장 클래스에서 이용되는 함수들
	
	
	
	public void VolumeInit() {
			
		int SliderValue = 0;
		
		
		formComponents.SoundLength.requestFocus();
		SliderValue = formComponents.SoundLength.getValue();
		
		soundPlayElements.Player.setVolume((double)SliderValue/100);
	}
		
	public void setMedia() {
		if(formComponents.model != null) {
			NowPlayingMusicSet();
			soundPlayElements.media = new Media(new File(musicFuncElements.filePathList.get(musicFuncElements.ListRow)).toURI().toString());
		}
	}
	
	
	public void setMusicIcon() {
		try {
				
				if(musicFuncElements.MusicArtworkList.get(musicFuncElements.ListRow) != null) {
					soundPlayElements.MusicArtworkImg = (BufferedImage) musicFuncElements.MusicArtworkList.get(musicFuncElements.ListRow).getImage();
					formComponents.ArtworkButton.setIcon(new ImageIcon(soundPlayElements.MusicArtworkImg));
				}else {
					
					formComponents.ArtworkButton.setIcon(new ImageIcon("images/AlbumImage.png"));
				}
			//이미지 결정 하는 곳
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(IndexOutOfBoundsException e) {
			musicFuncElements.ListRow = 0;
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void MusicPlay(ActionEvent e, String PlayStatus) {
		
		
	
		if(formComponents.model != null) {
			
			if(formComponents.model.getRowCount() == 0){
				
				if(soundPlayElements.Player != null) {
					soundPlayElements.Player.stop();
				}
				formComponents.CtrlButton[2].setVisible(false);
				formComponents.CtrlButton[1].setVisible(true);
				formComponents.Overlay.setVisible(false);
				formComponents.TitleMusicTimeL.setText("-- : --");
				formComponents.TitleMusicTimeR.setText("-- : --");
				formComponents.TitleMusicArtist.setText("ver.1a");
				formComponents.TitleMusicTitle.setText("MusicPlayer");
				
				formComponents.ArtworkButton.setIcon(new ImageIcon("images/AlbumImage.png"));
				
				
				return;
			}
			
			setMedia();
			
			if(e != null) {
				if(e.getSource() == formComponents.CtrlButton[1]) {
					
					if(soundPlayElements.tempListRow != musicFuncElements.ListRow) {
						soundPlayElements.Player = new MediaPlayer(soundPlayElements.media);
						soundPlayElements.tempListRow = musicFuncElements.ListRow;
					}
					VolumeInit();
					
					
					soundPlayElements.Player.play();
					formComponents.Overlay.setVisible(true);
					
					
					overlayThread.setStop(false);
					OTE = new Thread(overlayThread, "test");
					OTE.start();	// 쓰레드 시작
					
					PlayStatus = "Playing";
					setMusicTimer();
					
					formComponents.CtrlButton[1].setVisible(false);
					formComponents.CtrlButton[2].setVisible(true);
					
				}
				
				else if(e.getSource() == formComponents.CtrlButton[2]) {
					soundPlayElements.Player.pause();
					formComponents.Overlay.setVisible(false);
					formComponents.CtrlButton[1].setVisible(true);
					formComponents.CtrlButton[2].setVisible(false);
					
					
					overlayThread.setStop(true);
					OTE = null;
					
					PlayStatus = "Pause";
					
					
					formComponents.setBackground(new Color(31, 29, 62));
					formComponents.MusicList.setBackground(new Color(31, 29, 62));
					formComponents.MusicItems.setBackground(new Color(39,36,83));
					formComponents.MusicListScroll.setBackground(new Color(31, 29, 62));
					formComponents.MusicListScroll.getViewport().setBackground(new Color(31, 29, 62));
					
					overlayThread.setInitColor(31, 29, 62, 0, 0, 0);
					
				}
			}
			
			else {
				if(PlayStatus.equals("Playing")) {
					if(soundPlayElements.tempListRow != musicFuncElements.ListRow) {
						soundPlayElements.Player = new MediaPlayer(soundPlayElements.media);
						soundPlayElements.tempListRow = musicFuncElements.ListRow;
					}
					VolumeInit();
					
					
					soundPlayElements.Player.play();
					formComponents.Overlay.setVisible(true);
					setMusicTimer();
					
					formComponents.CtrlButton[1].setVisible(false);
					formComponents.CtrlButton[2].setVisible(true);
					PlayStatus = "Pause";
				}
				
				else if(PlayStatus.equals("Pause")) {
					
					soundPlayElements.Player.pause();
					formComponents.Overlay.setVisible(false);
					formComponents.CtrlButton[1].setVisible(true);
						PlayStatus = "Playing";
						formComponents.CtrlButton[2].setVisible(false);
					
				}
				
				
			}
		}
	
	}
	

	public void NowPlayingMusicSet() {
		setMusicIcon();
		if(formComponents.model.getRowCount() != 0) {
			formComponents.TitleMusicTitle.setText(musicFuncElements.MusicInfoList.get(musicFuncElements.ListRow)[0]);
			formComponents.TitleMusicArtist.setText(musicFuncElements.MusicInfoList.get(musicFuncElements.ListRow)[1]);
			formComponents.TitleMusicTimeR.setText(musicFuncElements.MusicInfoList.get(musicFuncElements.ListRow)[2]);
			
			
			subTitleParseAndSetFile();	
			
		}
		// 한번 클릭되었을때 실행되는 것들, 노래 제목, 아티스트, 시간 세팅, 열값 설정
	}
	
	

	public void subTitleParseAndSetFile() {
		
		try {
			MP3File mp3 = (MP3File) AudioFileIO.read(new File(musicFuncElements.filePathList.get(musicFuncElements.ListRow)));
			
			AbstractID3v2Tag TagInfo = mp3.getID3v2Tag();
			
			String subTitle = TagInfo.toString();
			String parseList[];
			//System.out.println(subTitle);
			parseList = subTitle.split("Lyrics=\"");
			subTitle = parseList[1].split("\";")[0];
			
			
			FileWriter fw = new FileWriter("./resource/subTitle.txt");
			
			// 새로 파일을 쓰게 만듬, 해당 경로에 자막을 로드
			
			fw.write(subTitle);
		    fw.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void setMusicTimer() {
		

		soundPlayElements.MusicTimer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Duration nowPlayTime = soundPlayElements.Player.getCurrentTime();
				//현재 노래 플레이 타임
				Duration TotalPlayTime = soundPlayElements.Player.getTotalDuration();
				//노래 총 플레이 타임
				
				
				String timeL = String.format("%d:%02d%n", (int)nowPlayTime.toMinutes(), (int)nowPlayTime.toSeconds()%60);
				String timeR = String.format("%d:%02d%n", (int)TotalPlayTime.toMinutes(), (int)TotalPlayTime.toSeconds()%60);
				
				formComponents.TitleMusicTimeL.setText(timeL);
				formComponents.TitleMusicTimeR.setText(timeR);
				
				formComponents.MusicLength.setValue((int)(100*(nowPlayTime.toSeconds()/TotalPlayTime.toSeconds())));
				
				if(nowPlayTime.equals(TotalPlayTime)) {
					
					nowPlayTime = soundPlayElements.Player.getStartTime();
					timeL = String.format("%d:%02d%n", (int)nowPlayTime.toMinutes(), (int)nowPlayTime.toSeconds()%60);
					formComponents.TitleMusicTimeL.setText(timeL);
					
					formComponents.MusicLength.setValue(0);
					soundPlayElements.MusicTimer.stop();
					MusicNonCheck();
					AutoNextPlay();
				}
				
			}
		});
		
		soundPlayElements.MusicTimer.start();
		
	}
	
	
	public void MusicNonCheck(){
		if((formComponents.model == null && formComponents.model.getRowCount() == 0) || formComponents.model.getRowCount() == 0) {
			soundPlayElements.Player.stop();
			formComponents.CtrlButton[2].setVisible(false);
			formComponents.CtrlButton[1].setVisible(true);
			formComponents.Overlay.setVisible(false);
			return;
		}
	}
	
	public void AutoNextPlay() {
		
		Duration nowPlayTime = soundPlayElements.Player.getCurrentTime();
		//현재 노래 플레이 타임
		Duration TotalPlayTime =soundPlayElements.Player.getTotalDuration();
		//노래 총 플레이 타임
		
		
		soundPlayElements.Player.stop();
		
		if(soundPlayElements.RepeatFlag == 0 && musicFuncElements.ListRow == (musicFuncElements.MusicSerial - 2) ) {
			if(nowPlayTime.equals(TotalPlayTime)) {
				
				if(soundPlayElements.RandomFlag == 1) {
					NextPlay();
				}
				else {
				
					formComponents.CtrlButton[2].setVisible(false);
					formComponents.CtrlButton[1].setVisible(true);
					formComponents.Overlay.setVisible(false);
					return;
				}
			}
		}
		
		NextPlay();
		
	}
	
	public void NextPlay() {
		musicFuncElements.ListRow = RandomOrSerialPlaySet();
		soundPlayElements.PlayStatus = "Playing";
		soundPlayElements.Player.seek(soundPlayElements.Player.getStartTime());
		MusicPlay(soundPlayElements.BtnStatus,soundPlayElements.PlayStatus);
	}
	
	
	
	
	
		
		
	public int RandomOrSerialPlaySet() {
		int RandomListRow = 0;
		
		if(soundPlayElements.RandomFlag == 1) {
			soundPlayElements.RandomMaker = new Random();
			
			do{
				RandomListRow = soundPlayElements.RandomMaker.nextInt(musicFuncElements.MusicSerial-1);
			}while(musicFuncElements.ListRow == RandomListRow);
			
			return RandomListRow;
			
		}else {
		
			musicFuncElements.ListRow++;
			
			if(musicFuncElements.ListRow > musicFuncElements.MusicSerial-2) {
				musicFuncElements.ListRow = 0;
			}
			
			return musicFuncElements.ListRow;
		}
		
	}
		
	public void RandomPlaySet() {
		
		if(soundPlayElements.Player != null) {
			if(soundPlayElements.RandomFlag == 0) {
				soundPlayElements.RandomFlag = 1;
				formComponents.OptionButton[1].setIcon(formComponents.RandomButtonImage01);
				formComponents.OptionButton[1].setRolloverIcon(formComponents.RandomButtonImage02);
				formComponents.OptionButton[1].setPressedIcon(formComponents.RandomButtonImage03);
				
			}else {
				soundPlayElements.RandomFlag = 0;
				
				formComponents.OptionButton[1].setIcon(formComponents.NoRandomButtonImage);
				formComponents.OptionButton[1].setRolloverIcon(formComponents.NoRandomButtonImage2);
				formComponents.OptionButton[1].setPressedIcon(formComponents.NoRandomButtonImage3);
			}
		}
	}
		
	public void RepeatPlaySet() {
		if(soundPlayElements.Player != null) {
			if(soundPlayElements.RepeatFlag == 0){
				soundPlayElements.RepeatFlag = 1;
				formComponents.OptionButton[0].setIcon(formComponents.ReButtonImage01);
				formComponents.OptionButton[0].setRolloverIcon(formComponents.ReButtonImage02);
				formComponents.OptionButton[0].setPressedIcon(formComponents.ReButtonImage03);
				
				
			}
			else if(soundPlayElements.RepeatFlag == 1) {
				soundPlayElements.RepeatFlag = 0;
				formComponents.OptionButton[0].setIcon(formComponents.NoReButtonImage);
				formComponents.OptionButton[0].setRolloverIcon(formComponents.NoReButtonImage2);
				formComponents.OptionButton[0].setPressedIcon(formComponents.NoReButtonImage3);
			}
			
		}
	}
		


}
