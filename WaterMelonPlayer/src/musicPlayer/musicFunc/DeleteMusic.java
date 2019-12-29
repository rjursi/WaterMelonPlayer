package musicPlayer.musicFunc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import musicPlayer.controller.PlayerController;
import musicPlayer.domain.MusicVO;
import musicPlayer.form.MusicTable;
import musicPlayer.form.PlayerFormSettings;
import musicPlayer.persistence.MusicDAO;

class DeleteMusic implements ActionListener{
	//implements : ActionListener Ŭ�������� �������̽��� ������ ��� ����, �� ������ ���� �߰��ؾ���

	private MusicFunc musicFuncElements;
	private PlayerFormSettings formComponents;
	private PlayerController playerController;
	
	JTable musicList;
	MusicTable userMusicTable;
	MusicDAO playerDAO;
	
	
	public DeleteMusic(PlayerFormSettings formComponents, MusicFunc musicFuncAttribute, PlayerController playerController) {
		this.musicFuncElements = musicFuncAttribute;
		this.formComponents = formComponents;
		
		this.playerController = playerController;
		
		userMusicTable = playerController.getUserMusicTable();
		musicList = userMusicTable.getMusicList();
		playerDAO = playerController.getPlayerDAO();
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) { //ActionListener Ŭ������ �ش� �޼ҵ尡 ����, �� �츮�� �����Ͽ� ����ؾ���
	
		if(formComponents.model != null) {
			
			int result = JOptionPane.showConfirmDialog(null, 
					"������ ������ ��� ��Ͽ��� �����մϴ�.", "����", 
					JOptionPane.YES_NO_OPTION);
			
			
			if(result == JOptionPane.YES_OPTION) {
			
				int i=0;
				MusicVO vo = new MusicVO();
				
				while(i < formComponents.model.getRowCount()) {
					
					Boolean checked = Boolean.valueOf(formComponents.model.getValueAt(i, 0).toString());
				
					
					if(checked) {
						
						musicFuncElements.MusicSerial--;
						musicFuncElements.filePathList.remove(i);
						musicFuncElements.MusicInfoList.remove(i);
						musicFuncElements.MusicArtworkList.remove(i);
						
						
						vo.setid(playerController.getId());
						vo.setNo((int)musicList.getValueAt(i, 1));
						vo.setMusicname(musicList.getValueAt(i, 2).toString());
						vo.setArtist(musicList.getValueAt(i, 3).toString());
						
						playerDAO.deleteUserPlayList(vo);
						// SQL ���� ���Ͽ� �÷��� ����Ʈ�� �߰�
						
						
			
						playerController.listUserPlayList(playerController.getUserMusicTable());
						// ���⼭ �÷��� Ÿ���ϰ� �� �ؼ� �߰��� ��
						i = 0;
						
					
					}
					else {
						i++;
		
					}				
				
				}
			
			
			//�ʱ�ȭ�ϰ� �ٽ� ����
			/*
			 * 
			
			musicFuncElements.MusicSerial = 1;
			formComponents.model.setRowCount(0);
			
			musicFuncElements.ListRow = (musicFuncElements.MusicSerial-2)/2;
			
			for(int j=0;j<musicFuncElements.filePathList.size();j++) {
				musicFuncElements.MusicInfoParse(new File(musicFuncElements.filePathList.get(j)), musicFuncElements.filePathList.get(j));
				musicFuncElements.MusicTableInsert();
			}
			
			
			*/
		
				JOptionPane.showMessageDialog(null, "������ ������ �����Ǿ����ϴ�.");
			}
		
		}
	}
}