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
		
		// ���̺� ������ ������ ������ Ŭ���Ͽ��� ��� ������ ���� ���� ���̺�� �ű�� �۾��� �ϴ� ���
		
		if(e.getClickCount() == 2) { // ���� Ŭ���� ���� ��
			int row = musicList.getSelectedRow();
			
			int result = JOptionPane.showConfirmDialog(null, 
					"�ش� ������ ��� ��Ͽ� �߰��մϴ�.", "Confirm", 
					JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				// sql ���� �����Ͽ� ���� ��� ��Ͽ� �߰�
				
				MusicVO vo = new MusicVO();
				
				vo.setid(playerController.getId());
				vo.setMusicname(musicList.getValueAt(row, 1).toString());
				vo.setArtist(musicList.getValueAt(row, 2).toString());
				
				allListDAO.addUserPlayList(vo);
				// SQL ���� ���Ͽ� �÷��� ����Ʈ�� �߰�
				
				JOptionPane.showMessageDialog(null, "�ش� ������ �����Ͽ� �߰��Ǿ����ϴ�.");
				
				playerController.listUserPlayList(playerController.getUserMusicTable());
				// ���⼭ �÷��� Ÿ���ϰ� �� �ؼ� �߰��� ��
				
				
				
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
