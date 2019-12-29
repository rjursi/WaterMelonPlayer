package musicPlayer.persistence;

import java.util.List;

import musicPlayer.domain.MusicVO;

public interface MusicDAOImpl {
	
	
	//public abstract void create(MusicVO vo); // insert
	// 값들은 VO 에 저장함, 매개변수들을 BookVO 로 선언
	
	public abstract MusicVO read(int no); // PK 인 컬럼 값으로 값을 구분
	// select (결과행이 하나일 경우 실행)
	// 해당 isbn에 해당하는 값만 실행이 가능
	
	public abstract void update(MusicVO vo); // 해당 BookVO 객체로 수정
	
	public abstract void delete(MusicVO vo);
	// isbn pk 에 해당하는 값 만을 삭제하기 위하여 
	
	public abstract List<MusicVO> searchList();
	
	
	// 개인 유저의 음악 재생 목록을 가져오는 SQL 문이 내장되어있는 메소드
	public abstract List<MusicVO> getUserPlayList();

	public abstract void signUp(MusicVO vo);
}
