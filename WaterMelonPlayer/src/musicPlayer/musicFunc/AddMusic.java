package musicPlayer.musicFunc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import musicPlayer.controller.AllMusicListController;
import musicPlayer.controller.PlayerController;


class AddMusic implements ActionListener{
	//implements : ActionListener 클래스에서 인터페이스로 가져와 상속 받음, 즉 내용은 직접 추가해야함
	
	//private int OpenFileChooseFlag;
	
	//private MusicFunc musicFuncAttribute;
	AllMusicListController allMusicListController;
	
	public AddMusic(AllMusicListController allMusicListController) {
		// TODO Auto-generated constructor stub
		
		this.allMusicListController = allMusicListController;
	}
	/*
	public AddMusic(MusicFunc musicFuncAttribute) {
		this.musicFuncAttribute = musicFuncAttribute;
	}
	
	*/
	
	
	public void actionPerformed(ActionEvent e) { //ActionListener 클래스에 해당 메소드가 존재, 즉 우리가 구현하여 사용해야함
		
	
		allMusicListController.showAllMusicList();
		
	
		/*
		OneOrFolderSelectWindow subWindow = new OneOrFolderSelectWindow();
		
		subWindow.FileChooseWindow.addActionListener(new ActionListener() { //파일 씩 가져오는 버튼을 눌렀을 경우
			public void actionPerformed(ActionEvent e) {
				//실행되는 메소드
				
				subWindow.setVisible(false);
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("파일 선택");
				chooser.setMultiSelectionEnabled(false);
				chooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files","mp3");
				
				
				chooser.setFileFilter(filter); //파일 필터 설정
				
				OpenFileChooseFlag = chooser.showOpenDialog(null);
				if(OpenFileChooseFlag != JFileChooser.APPROVE_OPTION) { //사용자가 창을 강제로 닫았거나 취소 버튼을 누른경우
					return;
				}
				//사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
				
				
				musicFuncAttribute.filePath = chooser.getSelectedFile().getPath(); // 파일 경로명을 알아옴
				musicFuncAttribute.filePathList.add(musicFuncAttribute.filePath);
				
				
				musicFuncAttribute.AudioFile = new File(musicFuncAttribute.filePath);
				musicFuncAttribute.MusicInfoParse(musicFuncAttribute.AudioFile,musicFuncAttribute.filePathList.get(musicFuncAttribute.filePathList.size()-1));
				musicFuncAttribute.MusicInfoListInsert(); //음악 정보 리스트에 노래 정보 삽입
				musicFuncAttribute.MusicTableInsert(); //테이블에 음악 목록 삽입
				

			}
			
		});
		
		
		subWindow.FolderChooseWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subWindow.setVisible(false);
				JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("폴더 선택");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					
					
					OpenFileChooseFlag = chooser.showOpenDialog(null);
					
					if(OpenFileChooseFlag != JFileChooser.APPROVE_OPTION) { //사용자가 창을 강제로 닫았거나 취소 버튼을 누른경우
						return;
					}
					//사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
					
					musicFuncAttribute.FolderPath = chooser.getSelectedFile().getPath();
					
					musicFuncAttribute.FileListParseInFolder(); // 폴더 안 파일리스트 값 가져옴
					
						
					for(int i=0;i<musicFuncAttribute.filePathListInFolder.size();i++) {
						musicFuncAttribute.MusicInfoParse(new File( musicFuncAttribute.filePathListInFolder.get(i)), musicFuncAttribute.filePathListInFolder.get(i));
						musicFuncAttribute.MusicInfoListInsert();
						musicFuncAttribute.MusicTableInsert();
					
					}
					
					musicFuncAttribute.filePathListInFolder.clear();
			}
			
		});

	
	}
	
	
	
	@SuppressWarnings("serial")
	private class OneOrFolderSelectWindow extends JFrame {
		
		private JPanel MainContainer; 
		private JButton FileChooseWindow, FolderChooseWindow;
		
		Toolkit tk;
		Dimension ScreenSize;
		Dimension FrameSize;


		private int subXLength, subYLength;
		
		
		private OneOrFolderSelectWindow() {
						
			MainContainer = new JPanel();
			setContentPane(MainContainer);
			MainContainer.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			MainContainer.setBackground(new Color(31,29,62));
			ImageIcon FileAddImage = new ImageIcon("images/FileAdd.png");
			ImageIcon FileAddImage2 = new ImageIcon("images/FileAdd2.png");
			ImageIcon FileAddImage3 = new ImageIcon("images/FileAdd3.png");
			ImageIcon FolderAddImage = new ImageIcon("images/FolderAdd.png");
			ImageIcon FolderAddImage2 = new ImageIcon("images/FolderAdd2.png");
			ImageIcon FolderAddImage3 = new ImageIcon("images/FolderAdd3.png");
			
			
			FileChooseWindow = new JButton(FileAddImage);
			FileChooseWindow.setRolloverIcon(FileAddImage2);
			FileChooseWindow.setPressedIcon(FileAddImage3);
		
			FolderChooseWindow = new JButton(FolderAddImage);
			FolderChooseWindow.setRolloverIcon(FolderAddImage2);
			FolderChooseWindow.setPressedIcon(FolderAddImage3);
			
			FileChooseWindow.setContentAreaFilled(false);
			FileChooseWindow.setBorderPainted(false);
			FileChooseWindow.setFocusPainted(false);
			FolderChooseWindow.setContentAreaFilled(false);
			FolderChooseWindow.setBorderPainted(false);
			FolderChooseWindow.setFocusPainted(false);
			FileChooseWindow.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			FolderChooseWindow.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			MainContainer.add(FileChooseWindow);
			MainContainer.add(FolderChooseWindow);
			
			
			tk = Toolkit.getDefaultToolkit();	
			ScreenSize = tk.getScreenSize();
			FrameSize = getSize();
			
			// 위에서 만들어진 레이아웃 표시 사이즈 값 가져옴
			
			subXLength = (int)(1.0/2 * (ScreenSize.width - FrameSize.width));
			subYLength = (int)(1.0/2 * (ScreenSize.height - FrameSize.height));
			
			setBounds(subXLength,subYLength+310, FrameSize.width, FrameSize.height);
			
			//setTitle("");
			
			setSize(216,135);
			setResizable(false);
			setVisible(true);
			
		}
		
	*/	
	}
	
}


