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
	// ����� ���� ��� ��� ���̺� ��
	
	MusicDAO playerDAO;
	// ���� ���̺� �����Ͽ� �����͸� ������ �� �ֵ��� ���ִ� ��ü
	// Data Access Object
	
	


	PlayerFormSettings playerFormSettings; // Mp3 �÷��̾� ���� �����ϴµ� �ʿ��� �� Element ���� ���� ����
	

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
		
		
	
		playerDAO = new MusicDAO(); // ������ �׼����� ���� DAO�� ����� ����
		
		// ��ü ���� ��� ���̺��� �Ŀ� ����ϱ� ���� ������ �� ����
		
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
		// ���� ��� ���� ���̺귯��
		
		this.id = id;
		
		
		playerFormSettings = new PlayerFormSettings(userMusicTable); // �⺻ ���� �÷��̾� ���� �����ϴ� �޼ҵ�
		playerFormMainView = new PlayerFormMainView(playerFormSettings);
		// �⺻ �÷��̾� ���� �並 ������ ���� ���� �� ����
		
		
		allMusicListController = new AllMusicListController(this, id);
		// �÷��̾�� ��ü�� ����� ���� ���߿� ����ϱ� ����
		
		
		musicFuncElements = new MusicFunc(this, playerFormSettings, allMusicListController);
		
		listUserPlayList(userMusicTable);
		// listall �� ���� ������ ������
		
		soundPlay = new SoundPlay(playerFormSettings, musicFuncElements);
		// �� �÷��̾�� ����
		

		
		playerFormMainView.setSoundPlayElements(soundPlay);
		
		
		
	}


	public MusicFunc getMusicFuncElements() {
		return musicFuncElements;
	}


	
	
	


	
	
	

}
