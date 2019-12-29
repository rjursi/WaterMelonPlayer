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
	//implements : ActionListener 클래스에서 인터페이스로 가져와 상속 받음, 즉 내용은 직접 추가해야함

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
	
	
	public void actionPerformed(ActionEvent e) { //ActionListener 클래스에 해당 메소드가 존재, 즉 우리가 구현하여 사용해야함
	
		if(formComponents.model != null) {
			
			int result = JOptionPane.showConfirmDialog(null, 
					"선택한 음악을 재생 목록에서 삭제합니다.", "삭제", 
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
						// SQL 문을 통하여 플레이 리스트에 추가
						
						
			
						playerController.listUserPlayList(playerController.getUserMusicTable());
						// 여기서 플레이 타임하고 다 해서 추가가 됨
						i = 0;
						
					
					}
					else {
						i++;
		
					}				
				
				}
			
			
			//초기화하고 다시 삽입
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
		
				JOptionPane.showMessageDialog(null, "선택한 음악이 삭제되었습니다.");
			}
		
		}
	}
}