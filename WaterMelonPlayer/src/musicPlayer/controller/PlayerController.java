package musicPlayer.controller;

import java.sql.Connection;
import java.util.List;

import musicPlayer.connector.DBConnection;
import musicPlayer.domain.MusicVO;
import musicPlayer.form.MusicTable;
import musicPlayer.form.PlayerFormMainView;
import musicPlayer.form.PlayerFormSettings;
import musicPlayer.musicFunc.MusicFunc;
import musicPlayer.persistence.MusicDAO;
import musicPlayer.playcontrol.SoundPlay;

public class PlayerController {
	
	
	MusicTable userMusicTable;
	// 사용자 개인 재생 목록 테이블 뷰
	
	MusicDAO playerDAO;
	// 음악 테이블 관련하여 데이터를 제어할 수 있도록 해주는 객체
	// Data Access Object
	
	


	PlayerFormSettings playerFormSettings; // Mp3 플레이어 폼을 세팅하는데 필요한 각 Element 들이 속해 있음
	

	PlayerFormMainView playerFormMainView; //
	
	MusicFunc musicFuncElements;
	SoundPlay soundPlay;
	
	AllMusicListController allMusicListController;
	
	String id = "";
	
	public String getId() {
		return id;
	}
	
	public MusicDAO getPlayerDAO() {
		return playerDAO;
	}
	

	public MusicTable getUserMusicTable() {
		return userMusicTable;
	}
	
	
	public PlayerController() {
		// TODO Auto-generated constructor stub
		
		
	
		playerDAO = new MusicDAO(); // 데이터 액세스를 위한 DAO를 만들어 놓음
		
		// 전체 음악 목록 테이블을 후에 사용하기 위해 생성을 해 놓음
		
		userMusicTable = new MusicTable();
		
		
		
	}
	
	

	public void listUserPlayList(MusicTable musicTableView) {
		Connection conn = DBConnection.getConn();
		playerDAO.setConn(conn);
		
		playerDAO.setUser_id(id);
		List<MusicVO> list = playerDAO.getUserPlayList();
		
		musicTableView.setList(list);
		musicTableView.setUserTableList(this);
		
		
	}
	
	
	public void playerRun(String id) {
		// 음악 재생 관련 라이브러리
		
		this.id = id;
		
		
		playerFormSettings = new PlayerFormSettings(userMusicTable); // 기본 음악 플레이어 폼을 정의하는 메소드
		playerFormMainView = new PlayerFormMainView(playerFormSettings);
		// 기본 플레이어 메인 뷰를 다음과 같이 생성 및 정의
		
		
		allMusicListController = new AllMusicListController(this, id);
		// 플레이어에서 객체만 만들어 놓고 나중에 사용하기 위함
		
		
		musicFuncElements = new MusicFunc(this, playerFormSettings, allMusicListController);
		
		listUserPlayList(userMusicTable);
		// listall 과 같은 역할을 수행함
		
		soundPlay = new SoundPlay(playerFormSettings, musicFuncElements);
		// 각 플레이어와 연동
		

		
		playerFormMainView.setSoundPlayElements(soundPlay);
		
		
		
	}


	public MusicFunc getMusicFuncElements() {
		return musicFuncElements;
	}


	
	
	


	
	
	

}
