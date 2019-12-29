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
	//implements : ActionListener Ŭ�������� �������̽��� ������ ��� ����, �� ������ ���� �߰��ؾ���
	
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
	
	
	public void actionPerformed(ActionEvent e) { //ActionListener Ŭ������ �ش� �޼ҵ尡 ����, �� �츮�� �����Ͽ� ����ؾ���
		
	
		allMusicListController.showAllMusicList();
		
	
		/*
		OneOrFolderSelectWindow subWindow = new OneOrFolderSelectWindow();
		
		subWindow.FileChooseWindow.addActionListener(new ActionListener() { //���� �� �������� ��ư�� ������ ���
			public void actionPerformed(ActionEvent e) {
				//����Ǵ� �޼ҵ�
				
				subWindow.setVisible(false);
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("���� ����");
				chooser.setMultiSelectionEnabled(false);
				chooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files","mp3");
				
				
				chooser.setFileFilter(filter); //���� ���� ����
				
				OpenFileChooseFlag = chooser.showOpenDialog(null);
				if(OpenFileChooseFlag != JFileChooser.APPROVE_OPTION) { //����ڰ� â�� ������ �ݾҰų� ��� ��ư�� �������
					return;
				}
				//����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				
				
				musicFuncAttribute.filePath = chooser.getSelectedFile().getPath(); // ���� ��θ��� �˾ƿ�
				musicFuncAttribute.filePathList.add(musicFuncAttribute.filePath);
				
				
				musicFuncAttribute.AudioFile = new File(musicFuncAttribute.filePath);
				musicFuncAttribute.MusicInfoParse(musicFuncAttribute.AudioFile,musicFuncAttribute.filePathList.get(musicFuncAttribute.filePathList.size()-1));
				musicFuncAttribute.MusicInfoListInsert(); //���� ���� ����Ʈ�� �뷡 ���� ����
				musicFuncAttribute.MusicTableInsert(); //���̺� ���� ��� ����
				

			}
			
		});
		
		
		subWindow.FolderChooseWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subWindow.setVisible(false);
				JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("���� ����");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					
					
					OpenFileChooseFlag = chooser.showOpenDialog(null);
					
					if(OpenFileChooseFlag != JFileChooser.APPROVE_OPTION) { //����ڰ� â�� ������ �ݾҰų� ��� ��ư�� �������
						return;
					}
					//����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
					
					musicFuncAttribute.FolderPath = chooser.getSelectedFile().getPath();
					
					musicFuncAttribute.FileListParseInFolder(); // ���� �� ���ϸ���Ʈ �� ������
					
						
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
			
			// ������ ������� ���̾ƿ� ǥ�� ������ �� ������
			
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


