package musicPlayer.musicFunc;

import java.io.File;

import org.jaudiotagger.tag.Tag;

public interface MusicFunc_Interface {
	public void MusicTableInsert();
	public void MusicInfoListInsert();
	public void FileListParseInFolder();
	public void MusicInfoParse(File AudioFile, String filePath);
	public void addMusicIconList(Tag tag);
	String makeMusicPlayTimeStr(int musicPlayTime);
	
}
