package musicPlayer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import musicPlayer.form.MusicTable;
import musicPlayer.persistence.MusicDAO;

public class searchHandler implements ActionListener {

	AllMusicListController allMusicListController;
	MusicDAO allListDAO;
	MusicTable allMusicTable;
	public searchHandler(AllMusicListController allMusicListController,
						MusicDAO allListDAO,
						MusicTable allMusicTable
						) {
		// TODO Auto-generated constructor stub
		
		this.allMusicListController = allMusicListController;
		this.allListDAO = allListDAO;
		this.allMusicTable = allMusicTable;
				
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JComboBox<String> combo = allMusicTable.getSearchCombo();
		JTextField text = allMusicTable.getSearchText();
		
		
		switch(combo.getSelectedIndex()) {
		case 0:
			allListDAO.setColName("musicname");
			break;
		case 1:
			allListDAO.setColName("artist");
		default:
			break;
		}
		
		allListDAO.setSearchWord(text.getText());
		allMusicListController.listAll(allMusicTable);

	}

}
