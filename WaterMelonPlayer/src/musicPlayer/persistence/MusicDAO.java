package musicPlayer.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import musicPlayer.domain.MusicVO;

public class MusicDAO implements MusicDAOImpl{
	
	
	
	private Connection conn;
	private ResultSet rs;
	
	String colName = "musicname"; // �ʱ� �˻� ���� �ϴ� ���� ������ �ʱ� ���� �����
	String searchWord = "";
	String id = "";
	
	
	public void setUser_id(String id) {
		this.id = id;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public int signUpCheck(MusicVO vo) {
		
		String SQL = "SELECT * FROM watermelon_java.member WHERE id=?";
		int signUpCheckFlag = 1;
		try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		
		pstmt.setString(1, vo.getid());
		
		rs = pstmt.executeQuery();
		
		if(rs.first()) {
			signUpCheckFlag = 0;
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return signUpCheckFlag;
	}
	
	@Override
	public void signUp(MusicVO vo) {
		// TODO Auto-generated method stub
		
		String SQL = "INSERT INTO watermelon_java.member VALUES(?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getid());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(MusicVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public MusicVO read(int no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void update(MusicVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void uploadMusic(MusicVO vo) {
		
		String sql = "SELECT max(no) FROM music";
		int nextNo = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.first()) {
				nextNo = rs.getInt("max(no)")+1;
				
			}else {
				nextNo = 1;
				
			}
			
			sql = "INSERT INTO music"
					+ "  VALUES(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,nextNo);
			pstmt.setString(2, vo.getid());
			pstmt.setString(3, vo.getMusicname());
			pstmt.setString(4, vo.getArtist());
			pstmt.setString(5, vo.getFilepath());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public List<MusicVO> searchList() {
		// TODO Auto-generated method stub
		
		// �ʱ� ��� ���� ����Ʈ��, �׸��� �˻� ��� ����Ʈ�� ���� ������ ������
		
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		String sql = "SELECT no, musicname, artist, filepath" + 
					" FROM music" + 
					" WHERE " + colName + " LIKE ?";
		
		MusicVO vo = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new MusicVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setMusicname(rs.getString("musicname"));
				vo.setArtist(rs.getString("artist"));
				vo.setFilepath(rs.getString("filepath"));
				
				list.add(vo);
		
				
			}
			
			
			rs.close();
			//conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
		}
		
		return list;
	}
	
	
	
	
	@Override
	public List<MusicVO> getUserPlayList() {
		// TODO Auto-generated method stub
		
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		
		colName = "id";
		
		String sql = "SELECT listno, musicname, artist, filepath" + 
					" FROM playlist" + 
					" WHERE " + colName + " LIKE ?"
							+ " ORDER BY listno ASC";
		
		MusicVO vo = null;
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new MusicVO();
				
				vo.setNo(rs.getInt("listno"));
				vo.setMusicname(rs.getString("musicname"));
				vo.setArtist(rs.getString("artist"));
				vo.setFilepath(rs.getString("filepath"));
				
				
				list.add(vo);
		
				
			}
			
			
			rs.close();
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
		}
		
		return list;
	}
	
	public void deleteUserPlayList(MusicVO vo) {
		String sql = "DELETE FROM playlist" +
					" WHERE listno = ? AND id LIKE ?";
		System.out.println(vo.getNo());
		
		try {			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getid());
			
			pstmt.executeUpdate();
			
			
			sql = "SELECT @cnt:=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			
			sql = "UPDATE playlist SET listno=@cnt:=@cnt+1 WHERE id LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getid());
			pstmt.executeUpdate();
			// �����ͺ��̽����� ���� ��ƾ �� ���ġ ��ƾ
			pstmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	
	
	public void addUserPlayList(MusicVO vo) {
		
		String sql = "SELECT max(listno) FROM playlist WHERE id=?";
		int nextNo = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getid());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.first()) {
				nextNo = rs.getInt("max(listno)")+1;
				
			}else {
				nextNo = 1;
				
			}
			
			
			sql = "SELECT filepath FROM music"
					+ " WHERE musicname LIKE ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMusicname());
			
			rs = pstmt.executeQuery();
			
			
			if(rs.first()) {
				vo.setFilepath(rs.getString("filepath"));
			}
					
			// SQL ���Ƿ� ���� ���� ��� ����
			
			
			rs.close();
			
			sql = "INSERT INTO playlist"
					+ "  VALUES(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,nextNo);
			pstmt.setString(2, vo.getid());
			pstmt.setString(3, vo.getMusicname());
			pstmt.setString(4, vo.getArtist());
			pstmt.setString(5, vo.getFilepath());
			pstmt.executeUpdate();
		
			pstmt.close();
			//conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int login(String id, String password) {
		
		String SQL = "SELECT password FROM watermelon_java.member WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if (rs.getString(1).equals(password))
					return 1;         // �α��� ����
				else 
					return 0;        // ��й�ȣ ����ġ
			}
			return -1;         // ���̵� ����.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2;       // �����ͺ��̽� ����
	}
	
	public int distintion(String id) {    // ���������� ����ȸ������ �����ϴ� �Լ�
		
		String SQL = "SELECT id FROM watermelon_java.member WHERE id = 'manager'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {                     
				if(rs.getString(1).equals(id))  // ������
					return 1;
				else
					return 0;                    //����ȸ��
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;        // �����ͺ��̽� ����
	}


	
}
