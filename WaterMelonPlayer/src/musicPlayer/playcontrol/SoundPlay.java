package musicPlayer.playcontrol;

import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.Timer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import musicPlayer.controller.PlayerController;
import musicPlayer.form.PlayerFormSettings;
import musicPlayer.musicFunc.MusicFunc;


import java.awt.image.BufferedImage;



public class SoundPlay {
	
	
	public Media media;
	public MediaPlayer Player;
	public Timer MusicTimer; 
	

	public int RandomFlag = 0;
	public int RepeatFlag = 0; 
	
	public Random RandomMaker;
	
	public ActionEvent BtnStatus = null;
	public String PlayStatus = "Pause";
	public BufferedImage MusicArtworkImg;
	
	public int tempListRow = -1;
	
	private PlayerFormSettings formComponents;
	private MusicFunc musicFuncElements;
	
	public MusicControl soundControl;
	
	public SoundPlay(PlayerFormSettings formComponents, MusicFunc musicFuncElements) {
		this.formComponents = formComponents;
		this.musicFuncElements = musicFuncElements;
		

		soundControl = new MusicControl(this, this.formComponents, this.musicFuncElements);
		
		setListener();
		
	}
	
	public void setListener() {
		
		formComponents.CtrlButton[1].addActionListener(soundControl.new MusicPlay());
		formComponents.CtrlButton[2].addActionListener(soundControl.new MusicPlay());
			
		formComponents.CtrlButton[0].addActionListener(soundControl.new MusicChange());
		formComponents.CtrlButton[3].addActionListener(soundControl.new MusicChange());
			
		formComponents.OptionButton[1].addActionListener(soundControl.new RandomPlay());
		formComponents.OptionButton[0].addActionListener(soundControl.new isRepeat());
			
		formComponents.SoundLength.addChangeListener(this.soundControl.new SoundControl());
			
		formComponents.MusicList.addMouseListener(this.soundControl.new MusicSelectOnList());
			
		formComponents.MusicLength.addMouseListener(this.soundControl.new MusicSliderControl());
		formComponents.MusicLength.addMouseMotionListener(this.soundControl.new MusicSliderControlPlus());
	}
}
	
	

