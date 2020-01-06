package musicPlayer.controller;

import java.sql.Connection;

import musicPlayer.connector.DBConnection;
import musicPlayer.domain.MusicVO;

import musicPlayer.form.PlayerFormLoginView;

import musicPlayer.persistence.MusicDAO;

public class MainController{

	MusicDAO loginDAO;
	// ���� ���̺� �����Ͽ� �����͸� ������ �� �ֵ��� ���ִ� ��ü
	// Data Access Object
	
	
	MusicVO vo;
	
	PlayerController playerController;
	PlayerFormLoginView playerFormLoginView;
	
	// ������ �÷��̾� ��Ʈ�ѷ��� ���� ���Ͽ� ������ ���� ���� ������ ����
	
	String id = "";
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	// ������ ��ȯ �� ������ �ϱ� ���� �뵵�� ���


	public MainController() {
		// TODO Auto-generated constructor stub
		
		// �̸� ��ä�� �����ϴ� ������ �ַ� ��
		
		loginDAO = new MusicDAO(); // �α��� ���� ������ �׼����� ���� DAO�� ����� ����
		playerController = new PlayerController();
		
		// ��ü�� ����� ����

	}
	
	
	public void showLoginForm() {
		Connection conn = DBConnection.getConn();
		loginDAO.setConn(conn);
		
		playerFormLoginView = new PlayerFormLoginView(loginDAO, playerController);
		// �α��� ���Ŀ� �÷��̾ �� �� �յ��� ������ ���� ����
		
		playerFormLoginView.setVisible(true);
		// �α��� ���� �����ֵ��� ����
	}
	
	public static void main(String[] args)	 {	
		
		MainController controller = new MainController();
		
		controller.showLoginForm();
		
	}
	
}

