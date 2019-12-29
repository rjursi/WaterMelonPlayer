package musicPlayer.musicFunc;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import musicPlayer.controller.AllMusicListController;
import musicPlayer.controller.PlayerController;
import musicPlayer.form.MusicTable;
import musicPlayer.persistence.MusicDAO;

public class MusicListViewFunc {
	// 음악 전체 보는 테이블에서 리스너를 정의하는 부분
	
	JButton admin_musicAddBtn, admin_musicDelBtn;
	JTable musicList;
	
	MusicTable allMusicTable;
	MusicDAO allListDAO;
	TableColumnModel model;
	AllMusicListController allMusicListController;
	PlayerController playerController;
	JCheckBox MusicAllSelect;
	String id = "";
	

	public MusicListViewFunc(AllMusicListController allMusicController, MusicTable allMusicTable, MusicDAO allListDAO, PlayerController playerController, String id) {
		// TODO Auto-generated constructor stub
		
		this.id = id;
		this.allMusicTable = allMusicTable;
		this.allListDAO = allListDAO;
		this.musicList = allMusicTable.getMusicList();
		this.playerController = playerController;
		this.allMusicListController = allMusicController;
		
		
		admin_musicAddBtn = allMusicTable.getAdmin_musicAddBtn();
		
		setListener();
	}
	
	public void setListener() {
		admin_musicAddBtn.addActionListener(new MusicAddListener(allMusicListController, allListDAO, allMusicTable, id));
		// 음악을 추가하는 동작에 대한 리스너를 버튼에 담아서 실행
		
		musicList.addMouseListener(new AddUserPlayListListener(musicList, allListDAO ,allMusicListController, allMusicTable, playerController));
		
		// 해당 테이블에서 클릭을 하였을 경우 음악을 개인 유저 음악 테이블로 옮기는 작업을 하는 기능
	}
}
