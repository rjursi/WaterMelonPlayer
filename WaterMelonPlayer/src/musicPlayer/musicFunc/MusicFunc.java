package musicPlayer.musicFunc;


import musicPlayer.controller.AllMusicListController;
import musicPlayer.controller.PlayerController;
import musicPlayer.form.PlayerFormSettings;


import java.awt.Dimension;

import java.io.File;

import java.util.ArrayList;
import java.util.List;


//파일 종류에 맞게 파일을 가져올 경우 사용하는 라이브러리
import javax.swing.table.DefaultTableModel;


import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

//버튼을 클릭을 할 경우 실행되는 리스너 실행하기 위한 라이브러리
public class MusicFunc implements MusicFunc_Interface{
	
	
	public File AudioFile,Folder; //음악 파일 하나, 폴더 하나
	public File[] FileListInFolder; // 폴더 안에 파일 목록
	public String filePath, FolderPath; // 파일 경로, 폴더 경로
	public String title, artist; // 음악 이름, 아티스트
	public int musicPlayTime = 0;// 음악 시간 정수값
	public String strMusicPlayTime;// 음악 시간 문자열값
	public String onlyFileName; // 파일 이름만 추출하기 위한 클래스
	
	public int MusicSerial = 1; // 음악 일련번호 값
	public int ListRow = 0;
	public Artwork MusicArtwork;
	public List<Artwork> MusicArtworkList = new ArrayList<Artwork>();
	public List<String> filePathList = new ArrayList<String>(); //파일의 경로를 저장하는 동적 배열 추가
	public List<String> filePathListInFolder = new ArrayList<String>(); //폴더 안 파일 리스트 임시 저장, 지정한 파일 확장자(일단 mp3) 만 가져옴
	public List<String[]> MusicInfoList = new ArrayList<String[]>();

	
	public Dimension ScreenSize, FrameSize;
	
	PlayerFormSettings formComponents;
	AllMusicListController allMusicListController;
	PlayerController playerController;
	
	public MusicFunc(PlayerController playerController, PlayerFormSettings formComponents, AllMusicListController allMusicListController) {
		this.playerController = playerController;
		this.formComponents = formComponents;
		// 셋팅 값에 위치한 각종 컴포넌트들을 사용하기 위하여 다음과 같이 생성자를 통하여 가져옴
		this.allMusicListController = allMusicListController;
		
		this.setListener();
		
	}
	
	public void setListener() {
		// 기존 클라이언트에서 노래를 추가하였던 기능인 추가, 삭제 버튼 리스너 달아놓는 작업
		
		
		formComponents.AddDelButton[0].addActionListener(new AddMusic(allMusicListController));//추가 버튼을 누르면 뮤직 탐색기 창을 띄우면서 가져올 수 있도록 함
		formComponents.AddDelButton[1].addActionListener(new DeleteMusic(this.formComponents, this, playerController)); //삭제 버튼을 누르면 테이블에서 선택된 값을 테이블에서 삭제하도록 함
		formComponents.MusicAllSelect.addActionListener(new AllMusicSelect(this.formComponents));
		formComponents.ArtworkButton.addActionListener(new ShowSubtitleViewListener());
		
		// 체크박스에 리스너 다는 과정
	}
	
	

	// 아래는 공통으로 사용하는 함수들 목록
	
	
	public void MusicTableInsert() {
		/*
		formComponents.model = (DefaultTableModel)this.formComponents.MusicList.getModel();
		// 현재 클래스에서 정의된 model 에 부모 클래스의 현재 모델 값을 가져온다.
		formComponents.model.addRow(new Object[] {false,MusicSerial,title,this.artist,strMusicPlayTime});
		// 가져온 모델에 데이터를 추가를 한다.
		 
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
			
			//PlayerForm에 위치
			
			
	
			
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
