package musicPlayer.musicFunc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import musicPlayer.controller.AllMusicListController;
import musicPlayer.controller.PlayerController;
import musicPlayer.domain.MusicVO;
import musicPlayer.form.MusicTable;
import musicPlayer.persistence.MusicDAO;

public class AddUserPlayListListener implements MouseListener{
	
	JTable musicList;
	MusicDAO allListDAO;
	AllMusicListController allMusicListController;
	MusicTable allMusicTable;
	PlayerController playerController;
	MusicFunc musicFuncAttribute;
	
	
	public AddUserPlayListListener(JTable musicList, MusicDAO allListDAO, AllMusicListController allMusicListController,MusicTable allMusicTable, PlayerController playerController) {
		// TODO Auto-generated constructor stub
		
		this.musicList = musicList;
		this.allListDAO = allListDAO;
		this.allMusicListController = allMusicListController;
		this.allMusicTable = allMusicTable;
		
		this.playerController = playerController;
		this.musicFuncAttribute = playerController.getMusicFuncElements();
	}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// 테이블 내에서 지정한 음악을 클릭하였을 경우 음악을 개인 유저 테이블로 옮기는 작업을 하는 기능
		
		if(e.getClickCount() == 2) { // 더블 클릭을 했을 시
			int row = musicList.getSelectedRow();
			
			int result = JOptionPane.showConfirmDialog(null, 
					"해당 음악을 재생 목록에 추가합니다.", "Confirm", 
					JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				// sql 문을 실행하여 개인 재생 목록에 추가
				
				MusicVO vo = new MusicVO();
				
				vo.setid(playerController.getId());
				vo.setMusicname(musicList.getValueAt(row, 1).toString());
				vo.setArtist(musicList.getValueAt(row, 2).toString());
				
				allListDAO.addUserPlayList(vo);
				// SQL 문을 통하여 플레이 리스트에 추가
				
				JOptionPane.showMessageDialog(null, "해당 음악이 재생목록에 추가되었습니다.");
				
				playerController.listUserPlayList(playerController.getUserMusicTable());
				// 여기서 플레이 타임하고 다 해서 추가가 됨
				
				
				
			}
			
		}
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
