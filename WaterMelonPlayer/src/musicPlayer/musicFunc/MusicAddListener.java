package musicPlayer.musicFunc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import musicPlayer.connector.DBConnection;
import musicPlayer.controller.AllMusicListController;
import musicPlayer.domain.MusicVO;
import musicPlayer.form.MusicTable;
import musicPlayer.persistence.MusicDAO;

public class MusicAddListener implements ActionListener {

	private int OpenFileChooseFlag;
	
	FileInputStream	fileInputStream;
	FileOutputStream fileOutputStream;
	
	
	JFileChooser chooser;
	String musicname, artist;
	
	String musicFileDirectory, uploadFilePath, relativeUploadPath;
	File uploadFile;
	
	AllMusicListController controller;
	
	MusicDAO allMusicDAO;
	MusicTable allMusicTable;
	String id = "";
	public MusicAddListener(AllMusicListController controller, MusicDAO allListDAO, MusicTable allMusicTable, String id) {
		// �����ڰ� ������ �߰��� �� ����ϴ� �޼ҵ� 
		
		// TODO Auto-generated constructor stub
		
		this.id = id;
		this.controller = controller;
		this.allMusicDAO = allListDAO;
		this.allMusicTable = allMusicTable;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.musicFileUpload();
		
		
	}
	
	public void tableSetting() {
		
		MusicVO vo = new MusicVO();
		vo.setid(id);
		vo.setMusicname(musicname);
		vo.setArtist(artist);
		vo.setFilepath(relativeUploadPath);
		
		allMusicDAO.uploadMusic(vo);
		controller.listAll(allMusicTable);
	}
	
	
	
	public void musicFileUpload() {
		musicFileDirectory = System.getProperty("user.dir").toString() + "/music";
		// ���� ��� ����
		
		chooser = new JFileChooser();
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
		
		
		try {
			fileInputStream = new FileInputStream(chooser.getSelectedFile());
			uploadFilePath = musicFileDirectory + "/" + chooser.getSelectedFile().getName();
			relativeUploadPath = "/music/" + chooser.getSelectedFile().getName();
			uploadFile = new File(uploadFilePath);
			fileOutputStream = new FileOutputStream(uploadFile);
			
			
			int i = 0;
			byte[] buffer = new byte[1024];
			
			try {
				while((i = fileInputStream.read(buffer,0,1024)) != -1){
					fileOutputStream.write(buffer, 0, i);
				}
				
				
				fileInputStream.close();
				fileOutputStream.close();
				
				
				
				this.MusicInfoParse(uploadFile, uploadFilePath);
				this.tableSetting();
				
				
				// ������ �Ѿ�;� �Ʒ� �ܰ踦 ����
				
			
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void MusicInfoParse(File AudioFile, String filePath) {
		
		
		try {
			MP3File mp3 = (MP3File) AudioFileIO.read(AudioFile);
			Tag tag = mp3.getTag();
			
			try {
			musicname = tag.getFirst(FieldKey.TITLE);
			}catch(NullPointerException e) {
				musicname = "";
			}
			
			if(musicname == "") {
				musicname = filePath.substring(filePath.lastIndexOf("\\")+1);
				
			}
			try {
			artist = tag.getFirst(FieldKey.ARTIST);
			}catch(NullPointerException e) {
				artist = "";
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
