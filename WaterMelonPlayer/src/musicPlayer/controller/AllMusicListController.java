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
	// ����ڰ� �ش� ���� ���̺��� ������ ���� ��� ��Ͽ� �߰��� �� �ֵ��� ���ִ� 
	// ��ü �뷡 ����� ����Ǿ� �ִ� ���̺�
	
	
	MusicListView allMusicListView;
	// ��ü ������ ����Ǿ� �ִ� ���� ���̺��� �����ִ� ListView
	
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
	
		// �Ŀ� �˻� ��ɵ� ���⿡�� �߰� �ʿ�
	}
	
	public void showAllMusicList() {
		allMusicTable.setSearchPanel();
		// ���̺� ���� �ʿ��� ��� �˻�â�� �߰��� �� ����
		
		allMusicListView = new MusicListView(allMusicTable);
		
		this.listAll(allMusicTable);
	}
	
	// list �� ���̺� �����Ű�� �޼ҵ�
	public void listAll(MusicTable musicTableView) {
		Connection conn = DBConnection.getConn();
		
		allListDAO.setConn(conn);
		
		List<MusicVO> list = allListDAO.searchList();
		// �ʱ⿡ ����Ʈ�� ������
		
		musicTableView.setList(list); // ����Ʈ ���� DB���� �о�ͼ� ������ ���� ����
		musicTableView.setTableList(id); // �о�� ����Ʈ ���� ���̺� ���� ����
			
	}
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AllMusicListController allMusicListController = new AllMusicListController();
		allMusicListController.showAllMusicList();

	}
	
	*/
		
		
		
	
}
