package musicPlayer.form;

public interface PlayerForm_Interfaces {
	public void titleAreaSet();
	public void albumAreaSet();
	public void musicOptionAreaSet();
	public void ctrlAreaSet(); // 음악 선택 버튼 인터페이스

	public void setSoundLength(); // 음악 볼륨 조정 인터페이스
	//public void setMusicItems(); // 음악 테이블 최상위 속성 부분 
	
	//public void setMusicTable(); // 음악 테이블 구성 인터페이스 꼭 존재
	public void setAddDelBtns(); // 추가 제거 버튼 구성 인터페이스
	
}
