package musicPlayer.musicFunc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import musicPlayer.form.PlayerFormSettings;

class AllMusicSelect implements ActionListener{
	
	private PlayerFormSettings formComponents;
	public AllMusicSelect(PlayerFormSettings formComponents) {
		this.formComponents = formComponents;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		int i=0;
		
		boolean AllSelected = formComponents.MusicAllSelect.isSelected();
		if(formComponents.model != null) {
			
			if(AllSelected) {
				while(i<formComponents.model.getRowCount()) {
					formComponents.model.setValueAt(true, i, 0);
					i++;
				}
			}
		
			else {
				while(i<formComponents.model.getRowCount()) {	
					formComponents.model.setValueAt(false, i, 0);
					i++;
				}
				
			}
				
		}
	}
}