package musicPlayer.musicFunc;


import musicPlayer.controller.AllMusicListController;
import musicPlayer.controller.PlayerController;
import musicPlayer.form.PlayerFormSettings;


import java.awt.Dimension;

import java.io.File;

import java.util.ArrayList;
import java.util.List;


//���� ������ �°� ������ ������ ��� ����ϴ� ���̺귯��
import javax.swing.table.DefaultTableModel;


import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

//��ư�� Ŭ���� �� ��� ����Ǵ� ������ �����ϱ� ���� ���̺귯��
public class MusicFunc implements MusicFunc_Interface{
	
	
	public File AudioFile,Folder; //���� ���� �ϳ�, ���� �ϳ�
	public File[] FileListInFolder; // ���� �ȿ� ���� ���
	public String filePath, FolderPath; // ���� ���, ���� ���
	public String title, artist; // ���� �̸�, ��Ƽ��Ʈ
	public int musicPlayTime = 0;// ���� �ð� ������
	public String strMusicPlayTime;// ���� �ð� ���ڿ���
	public String onlyFileName; // ���� �̸��� �����ϱ� ���� Ŭ����
	
	public int MusicSerial = 1; // ���� �Ϸù�ȣ ��
	public int ListRow = 0;
	public Artwork MusicArtwork;
	public List<Artwork> MusicArtworkList = new ArrayList<Artwork>();
	public List<String> filePathList = new ArrayList<String>(); //������ ��θ� �����ϴ� ���� �迭 �߰�
	public List<String> filePathListInFolder = new ArrayList<String>(); //���� �� ���� ����Ʈ �ӽ� ����, ������ ���� Ȯ����(�ϴ� mp3) �� ������
	public List<String[]> MusicInfoList = new ArrayList<String[]>();

	
	public Dimension ScreenSize, FrameSize;
	
	PlayerFormSettings formComponents;
	AllMusicListController allMusicListController;
	PlayerController playerController;
	
	public MusicFunc(PlayerController playerController, PlayerFormSettings formComponents, AllMusicListController allMusicListController) {
		this.playerController = playerController;
		this.formComponents = formComponents;
		// ���� ���� ��ġ�� ���� ������Ʈ���� ����ϱ� ���Ͽ� ������ ���� �����ڸ� ���Ͽ� ������
		this.allMusicListController = allMusicListController;
		
		this.setListener();
		
	}
	
	public void setListener() {
		// ���� Ŭ���̾�Ʈ���� �뷡�� �߰��Ͽ��� ����� �߰�, ���� ��ư ������ �޾Ƴ��� �۾�
		
		
		formComponents.AddDelButton[0].addActionListener(new AddMusic(allMusicListController));//�߰� ��ư�� ������ ���� Ž���� â�� ���鼭 ������ �� �ֵ��� ��
		formComponents.AddDelButton[1].addActionListener(new DeleteMusic(this.formComponents, this, playerController)); //���� ��ư�� ������ ���̺��� ���õ� ���� ���̺��� �����ϵ��� ��
		formComponents.MusicAllSelect.addActionListener(new AllMusicSelect(this.formComponents));
		formComponents.ArtworkButton.addActionListener(new ShowSubtitleViewListener());
		
		// üũ�ڽ��� ������ �ٴ� ����
	}
	
	

	// �Ʒ��� �������� ����ϴ� �Լ��� ���
	
	
	public void MusicTableInsert() {
		/*
		formComponents.model = (DefaultTableModel)this.formComponents.MusicList.getModel();
		// ���� Ŭ�������� ���ǵ� model �� �θ� Ŭ������ ���� �� ���� �����´�.
		formComponents.model.addRow(new Object[] {false,MusicSerial,title,this.artist,strMusicPlayTime});
		// ������ �𵨿� �����͸� �߰��� �Ѵ�.
		 
		 */
		
		MusicSerial++;
		
	}
	
	public void MusicInfoListInsert() {
		MusicInfoList.add(new String[] {title, artist, strMusicPlayTime});
	}
	
	
	public void FileListParseInFolder() {
		Folder = new File(FolderPath);
		FileListInFolder = Folder.listFiles();
		
		for(File musicFile : FileListInFolder) {
			
			if((musicFile.getPath().substring(musicFile.getPath().lastIndexOf(".")+1).equals("mp3"))){
				filePathList.add(musicFile.getPath());
				filePathListInFolder.add(musicFile.getPath());			}
			
		}
		
	}
	
	public void MusicInfoParse(File AudioFile, String filePath) {
		
		try {
			MP3File mp3 = (MP3File) AudioFileIO.read(AudioFile);
			Tag tag = mp3.getTag();
			
			try {
			title = tag.getFirst(FieldKey.TITLE);
			}catch(NullPointerException e) {
				title = "";
			}
			
			if(title == "") {
				title = filePath.substring(filePath.lastIndexOf("\\")+1);
				
			}
			try {
			artist = tag.getFirst(FieldKey.ARTIST);
			}catch(NullPointerException e) {
				artist = "";
			}
			
		
			musicPlayTime = mp3.getAudioHeader().getTrackLength();
			
			strMusicPlayTime = this.makeMusicPlayTimeStr(musicPlayTime);
			if(musicPlayTime == 0) {
				strMusicPlayTime = "";
			}
			
			addMusicIconList(tag);
			
			//PlayerForm�� ��ġ
			
			
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMusicIconList(Tag tag) {
		try {
		MusicArtwork = tag.getFirstArtwork();
	
		MusicArtworkList.add(MusicArtwork);
		}catch(NullPointerException e) {
			MusicArtworkList.add(null);
		}
		
	}
	
	
	
	public String makeMusicPlayTimeStr(int musicPlayTime) {
		
		int minute;
		String strHour, strMinute, strSecond, returnTime;
		
		strSecond = (musicPlayTime % 60) + "";
		strMinute = (musicPlayTime / 60) + "";
		
		minute = Integer.parseInt(strMinute);
		
		strHour = (minute / 60)+"";
		
		if(strHour.equals("0")) {
			returnTime = strMinute + ":" + strSecond;
		}
		else
		{
			returnTime = strHour + ":" + strMinute + ":" + strSecond;
		}
		return returnTime;
		
	}
	
}
