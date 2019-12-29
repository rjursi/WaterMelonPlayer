package musicPlayer.controller;

import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;

import musicPlayer.connector.DBConnection;
import musicPlayer.domain.MusicVO;
import musicPlayer.form.MusicListView;
import musicPlayer.form.MusicTable;
import musicPlayer.musicFunc.MusicListViewFunc;
import musicPlayer.persistence.MusicDAO;

public class AllMusicListController {
	
	MusicTable allMusicTable;
	// 사용자가 해당 음악 테이블에서 음악을 개인 재생 목록에 추가할 수 있도록 해주는 
	// 전체 노래 목록이 저장되어 있는 테이블
	
	
	MusicListView allMusicListView;
	// 전체 음악이 저장되어 있는 음악 테이블을 보여주는 ListView
	
	MusicDAO allListDAO;
	
	JButton admin_musicAddBtn;
	
	PlayerController playerController;
	
	
	MusicListViewFunc musicListViewFunc;
	
	JButton searchBtn;
	String id = "";
	
	
	public AllMusicListController(PlayerController playerController, String id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		
		this.playerController = playerController;
	
		allListDAO = new MusicDAO();
		allMusicTable = new MusicTable(id);
		searchBtn = allMusicTable.getSearchBtn();
		
		searchBtn.addActionListener(new searchHandler(this, allListDAO, allMusicTable));
		musicListViewFunc = new MusicListViewFunc(this, allMusicTable, allListDAO, playerController, id);
	
		// 후에 검색 기능도 여기에다 추가 필요
	}
	
	public void showAllMusicList() {
		allMusicTable.setSearchPanel();
		// 테이블 마다 필요의 경우 검색창을 추가할 수 있음
		
		allMusicListView = new MusicListView(allMusicTable);
		
		this.listAll(allMusicTable);
	}
	
	// list 를 테이블에 적용시키는 메소드
	public void listAll(MusicTable musicTableView) {
		Connection conn = DBConnection.getConn();
		
		allListDAO.setConn(conn);
		
		List<MusicVO> list = allListDAO.searchList();
		// 초기에 리스트를 가져옴
		
		musicTableView.setList(list); // 리스트 값을 DB에서 읽어와서 저장해 놓는 과정
		musicTableView.setTableList(id); // 읽어온 리스트 값을 테이블에 쓰는 과정
			
	}
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AllMusicListController allMusicListController = new AllMusicListController();
		allMusicListController.showAllMusicList();

	}
	
	*/
		
		
		
	
}
