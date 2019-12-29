package musicPlayer.domain;

public class MusicVO {

	private int no;
	private String id;
	private String musicname;
	private String artist;
	private String filepath;
	private String name;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getMusicname() {
		return musicname;
	}
	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAbsoluteFilePath() {
		return System.getProperty("user.dir") + filepath;
	}
	
	// ���� ������ ���� ���� ��η� �������� AO
	
	// �Ʒ��� ��� ����η� ���� ��θ� ������, �� DB�� �ִ� �� �״�� ������
	
	public String getFilepath() {
		return filepath;
	}
	
	// ��� ��η� ���� ������ ���ε� ��
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}
