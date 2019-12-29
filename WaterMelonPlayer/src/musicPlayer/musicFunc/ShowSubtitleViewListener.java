package musicPlayer.musicFunc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import musicPlayer.form.SubtitleView;

public class ShowSubtitleViewListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SubtitleView();
	}

}
	