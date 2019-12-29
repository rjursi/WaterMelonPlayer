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
	
	// 파일 실행을 위한 절대 경로로 가져오는 AO
	
	// 아래는 모두 상대경로로 파일 경로를 가져옴, 즉 DB에 있는 값 그대로 가져옴
	
	public String getFilepath() {
		return filepath;
	}
	
	// 상대 경로로 음악 파일을 업로드 함
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}
