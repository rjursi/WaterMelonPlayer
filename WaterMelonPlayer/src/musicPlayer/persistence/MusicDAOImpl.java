package musicPlayer.persistence;

import java.util.List;

import musicPlayer.domain.MusicVO;

public interface MusicDAOImpl {
	
	
	//public abstract void create(MusicVO vo); // insert
	// ������ VO �� ������, �Ű��������� BookVO �� ����
	
	public abstract MusicVO read(int no); // PK �� �÷� ������ ���� ����
	// select (������� �ϳ��� ��� ����)
	// �ش� isbn�� �ش��ϴ� ���� ������ ����
	
	public abstract void update(MusicVO vo); // �ش� BookVO ��ü�� ����
	
	public abstract void delete(MusicVO vo);
	// isbn pk �� �ش��ϴ� �� ���� �����ϱ� ���Ͽ� 
	
	public abstract List<MusicVO> searchList();
	
	
	// ���� ������ ���� ��� ����� �������� SQL ���� ����Ǿ��ִ� �޼ҵ�
	public abstract List<MusicVO> getUserPlayList();

	public abstract void signUp(MusicVO vo);
}
