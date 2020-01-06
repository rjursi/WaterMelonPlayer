package musicPlayer.controller;

import java.sql.Connection;

import musicPlayer.connector.DBConnection;
import musicPlayer.domain.MusicVO;

import musicPlayer.form.PlayerFormLoginView;

import musicPlayer.persistence.MusicDAO;

public class MainController{

	MusicDAO loginDAO;
	// 음악 테이블 관련하여 데이터를 제어할 수 있도록 해주는 객체
	// Data Access Object
	
	
	MusicVO vo;
	
	PlayerController playerController;
	PlayerFormLoginView playerFormLoginView;
	
	// 다음에 플레이어 컨트롤러를 띄우기 위하여 다음과 같이 참조 변수를 선언
	
	String id = "";
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	// 계정을 반환 및 설정을 하기 위한 용도로 사용


	public MainController() {
		// TODO Auto-generated constructor stub
		
		// 미리 객채를 생성하는 역할을 주로 함
		
		loginDAO = new MusicDAO(); // 로그인 관련 데이터 액세스를 위한 DAO를 만들어 놓음
		playerController = new PlayerController();
		
		// 객체만 만들어 놓음

	}
	
	
	public void showLoginForm() {
		Connection conn = DBConnection.getConn();
		loginDAO.setConn(conn);
		
		playerFormLoginView = new PlayerFormLoginView(loginDAO, playerController);
		// 로그인 이후에 플레이어가 뜰 수 잇도록 다음과 같이 설정
		
		playerFormLoginView.setVisible(true);
		// 로그인 폼을 보여주도록 설정
	}
	
	public static void main(String[] args)	 {	
		
		MainController controller = new MainController();
		
		controller.showLoginForm();
		
	}
	
}

