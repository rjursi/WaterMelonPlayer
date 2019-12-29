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
	// ���� ��ü ���� ���̺��� �����ʸ� �����ϴ� �κ�
	
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
		// ������ �߰��ϴ� ���ۿ� ���� �����ʸ� ��ư�� ��Ƽ� ����
		
		musicList.addMouseListener(new AddUserPlayListListener(musicList, allListDAO ,allMusicListController, allMusicTable, playerController));
		
		// �ش� ���̺��� Ŭ���� �Ͽ��� ��� ������ ���� ���� ���� ���̺�� �ű�� �۾��� �ϴ� ���
	}
}
