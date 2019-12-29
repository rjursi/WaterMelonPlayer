package musicPlayer.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;

import musicPlayer.controller.PlayerController;
import musicPlayer.domain.MusicVO;
import musicPlayer.musicFunc.MusicFunc;

public class MusicTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	MusicFunc musicFuncAttribute;
	GridBagConstraints gconstraint;
	JTextField searchText = new JTextField(20);
	JButton searchBtn = new JButton("검색");
	
	JButton admin_musicAddBtn = new JButton("노래 추가");
	
	
	String id;
	JTable MusicList;
	List<MusicVO> list;
	
	DefaultTableModel model;
	DefaultTableCellRenderer Dtcr;
	TableCellRenderer renderer;
	TableCellRenderer chkboxrenderer;
	
	
	JPanel MusicItemListBtn, MusicItems;
			// MusicItemListBtn = 가장 밖 영역,
			// MusicItems = GridBagLayout 으로 구성
	JLabel No, MusicTitle, Artist, MusicTime;
	public JCheckBox MusicAllSelect;
			// 처음 테이블 제목 구분자
	
	JScrollPane MusicListScroll;
	

	String[] cols = { "선택","No","제목","아티스트","시간"};
	String[] searchOptionList = {"제목","아티스트"};
	
	JPanel searchPanel = new JPanel();
	JComboBox<String> searchCombo = new JComboBox<String>(searchOptionList);
	

	public JButton getSearchBtn() {
		return searchBtn;
	}


	public JComboBox<String> getSearchCombo() {
		return searchCombo;
	}


	public JTextField getSearchText() {
		return searchText;
	}


	public DefaultTableModel getModel() {
		return model;
	}


	public JButton getAdmin_musicAddBtn() {
		return admin_musicAddBtn;
	}

	
	public JCheckBox getMusicAllSelect() {
		return MusicAllSelect;
	}
	
	public JPanel getMusicItemListBtn() {
		return MusicItemListBtn;
	}


	public JPanel getMusicItems() {
		return MusicItems;
	}
	
	public JTable getMusicList() {
		return MusicList;
	}
	
	public void setSearchPanel() {
		searchPanel.add(searchCombo);
		searchPanel.add(searchText);
		searchPanel.add(searchBtn);
		
		if(id.equals("manager")) {
			searchPanel.add(admin_musicAddBtn);
		}
		
		searchPanel.setBackground(new Color(39,36,83));
		
		add(searchPanel, "North");
	}
	
	

	public JScrollPane getMusicListScroll() {
		return MusicListScroll;
	}


	public void setList(List<MusicVO> list) {
		this.list = list;
	}

	
	public MusicTable() {
		// TODO Auto-generated constructor stub
		// 음악 테이블이 생성되자마다 다음과 같이 바로 생성이 되도록 생성자로 추가
		
		setLayout(new BorderLayout());
		
		
		setMusicItems(); // 머리말을 적용하고 나서 아래 테이블 적용
		setMusicTable();
		
	}
	
	public MusicTable(String id) {
		// TODO Auto-generated constructor stub
		// 음악 테이블이 생성되자마다 다음과 같이 바로 생성이 되도록 생성자로 추가
		
		this.id = id;
		setLayout(new BorderLayout());
		
		
		setMusicItems(id); // 머리말을 적용하고 나서 아래 테이블 적용
		setMusicTable(id);
		
	}
	
	@SuppressWarnings("serial")
	public void setMusicTable(String id) {
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int col) {
				return false;
			};
			
			// 셀 내용 수정 가능 여부 지정, 첫번째 체크박스만 수정 가능
			
			
			
			// 아래는 테이블 셀의 데이터 스타일을 지정, 
			@SuppressWarnings({"unchecked", "rawtypes" })
			public Class getColumnClass(int column){
				switch(column) {
					case 0:
						return int.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;	
						
						default:
							return String.class;
					
				}
			}
				
		};
		
		model.addColumn("No");
		model.addColumn("Title");
		model.addColumn("Artist");
		model.addColumn("Time");
		
		
		MusicList = new JTable();
		// 해당 테이블에다 다음과 같이 모델을추가
		
		class TableCellColorChangeRenderer extends DefaultTableCellRenderer{
			
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component Cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if(!isSelected && column != 0) {
					Cell.setBackground(new Color(31,29,62));
					Cell.setForeground(Color.white);
				}
				
				if(column == 0) {
					Cell.setBackground(new Color(31,29,62));
					Cell.setForeground(Color.white);
				}
				
				
				if(Cell instanceof JLabel) {
					((JLabel) Cell).setHorizontalAlignment(JLabel.CENTER);
				}
				
				if(Cell instanceof JLabel && column == 2) {
					((JLabel) Cell).setHorizontalAlignment(JLabel.CENTER);
				}
				
				
				return Cell;
			}
			
		}
		
		
		renderer = new TableCellColorChangeRenderer();
		// 테이블 색상 변경 객체
		
		
		try {
			MusicList.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MusicList.setModel(model);
		MusicList.setShowGrid(false); //셀 선 지우기
		MusicList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		MusicList.getColumnModel().getColumn(0).setPreferredWidth(35);
		MusicList.getColumnModel().getColumn(0).setCellRenderer(renderer);
	
		MusicList.getColumnModel().getColumn(1).setPreferredWidth(220);
		MusicList.getColumnModel().getColumn(2).setPreferredWidth(140);
		MusicList.getColumnModel().getColumn(2).setCellRenderer(renderer);
	
		MusicList.getColumnModel().getColumn(3).setPreferredWidth(90);
		MusicList.getColumnModel().getColumn(3).setCellRenderer(renderer);
	
		MusicList.setTableHeader(null);
	
		MusicList.setBackground(new Color(31,29,62));
		MusicList.setOpaque(true);
		
		MusicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//리스트중에 하나만 선택되도록 수정
		
		
		MusicListScroll = new JScrollPane(MusicList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MusicListScroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		MusicListScroll.setPreferredSize(new Dimension(0,100));
		
		MusicListScroll.getViewport().setBackground(new Color(31,29,62));
		MusicListScroll.setBackground(new Color(31,29,62));
	
		//MusicListScroll.setOpaque(true);
		
		
		// 스크롤 패턴 안에 리스트가 버튼 형식으로 동작하도록 설정한 패털
		MusicItemListBtn.add("Center",MusicListScroll);	
		
	}
	// 노리 래스트에서 검색시 사용할 검색창 패널을 넣기 위한 용도
	
	
	public void setMusicItems(String id) {
		gconstraint = new GridBagConstraints();
	
		MusicItemListBtn = new JPanel(new BorderLayout());
		MusicItemListBtn.setPreferredSize(new Dimension(450,220));
		MusicItemListBtn.setOpaque(false);
		
			MusicItems = new JPanel();
			MusicItems.setLayout(new GridBagLayout());
			MusicItems.setBackground(new Color(39,36,83));
			
				
				
				No = new JLabel("No", JLabel.LEFT);
					No.setForeground(new Color(202,201,201));
					No.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
					
				MusicTitle = new JLabel("제목",JLabel.RIGHT);
					MusicTitle.setForeground(new Color(202,201,201));
					MusicTitle.setBorder(BorderFactory.createEmptyBorder(0,70,0,45));
				Artist = new JLabel("아티스트", JLabel.RIGHT);
					Artist.setForeground(new Color(202,201,201));
					Artist.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
				MusicTime = new JLabel("시간", JLabel.CENTER);
					MusicTime.setForeground(new Color(202,201,201));
					MusicTime.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
				
							
				
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				gconstraint.weightx = 0;
				gconstraint.weighty = 0;
				
				gconstraint.gridx = 0;
				gconstraint.gridy = 0;
						
				MusicItems.add(No,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				
				gconstraint.weightx = 1;
				gconstraint.weighty = 0;
				gconstraint.gridx = 1;
				gconstraint.gridy = 0;
				
				MusicItems.add(MusicTitle,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				
				gconstraint.gridx = 2;
				gconstraint.gridy = 0;
				
				MusicItems.add(Artist,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
			
				gconstraint.gridx = 3;
				gconstraint.gridy = 0;
				
				MusicItems.add(MusicTime,gconstraint);
				
				MusicItemListBtn.add("North",MusicItems); // 처음에 행 머리 영역

				add(MusicItemListBtn);
	}
	
	
	public void setMusicItems() {
		gconstraint = new GridBagConstraints();
	
		MusicItemListBtn = new JPanel(new BorderLayout());
		MusicItemListBtn.setPreferredSize(new Dimension(450,220));
		MusicItemListBtn.setOpaque(false);
		
			MusicItems = new JPanel();
			MusicItems.setLayout(new GridBagLayout());
			MusicItems.setBackground(new Color(39,36,83));
			
				MusicAllSelect = new JCheckBox();
				MusicAllSelect.setBorder(BorderFactory.createEmptyBorder(0,23,0,0));
				MusicAllSelect.setOpaque(false);
				
				No = new JLabel("No", JLabel.LEFT);
					No.setForeground(new Color(202,201,201));
					No.setBorder(BorderFactory.createEmptyBorder(0,13,0,0));
					
				MusicTitle = new JLabel("제목",JLabel.RIGHT);
					MusicTitle.setForeground(new Color(202,201,201));
					MusicTitle.setBorder(BorderFactory.createEmptyBorder(0,20,0,45));
				Artist = new JLabel("아티스트", JLabel.RIGHT);
					Artist.setForeground(new Color(202,201,201));
					Artist.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
				MusicTime = new JLabel("시간", JLabel.CENTER);
					MusicTime.setForeground(new Color(202,201,201));
					MusicTime.setBorder(BorderFactory.createEmptyBorder(0,0,0,8));
				
							
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				gconstraint.weightx = 0;
				gconstraint.weighty = 0;
				
				gconstraint.gridx = 0;
				gconstraint.gridy = 0;
	
				
				MusicItems.add(MusicAllSelect,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				gconstraint.weightx = 1;
				gconstraint.weighty = 0;
				
				gconstraint.gridx = 1;
				gconstraint.gridy = 0;
						
				MusicItems.add(No,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				
				gconstraint.weightx = 2;
				gconstraint.weighty = 0;
				gconstraint.gridx = 2;
				gconstraint.gridy = 0;
				
				MusicItems.add(MusicTitle,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
				
				gconstraint.gridx = 3;
				gconstraint.gridy = 0;
				
				MusicItems.add(Artist,gconstraint);
				
				gconstraint.fill = GridBagConstraints.HORIZONTAL;
			
				gconstraint.gridx = 4;
				gconstraint.gridy = 0;
				
				MusicItems.add(MusicTime,gconstraint);
				
				MusicItemListBtn.add("North",MusicItems); // 처음에 행 머리 영역

				add(MusicItemListBtn);
	}


	@SuppressWarnings("serial")
	public void setMusicTable() {
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int col) {
				switch(col) {
				case 0 :
					return true;
				default:
					return false;
				}
			};
			
			// 셀 내용 수정 가능 여부 지정, 첫번째 체크박스만 수정 가능
			
			
			
			// 아래는 테이블 셀의 데이터 스타일을 지정, 
			@SuppressWarnings({"unchecked", "rawtypes" })
			public Class getColumnClass(int column){
				switch(column) {
					case 0:
						return Boolean.class;
					case 1:
						return int.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;	
						
						default:
							return String.class;
					
				}
			}
				
		};
		
		model.addColumn("Select");
		model.addColumn("No");
		model.addColumn("Title");
		model.addColumn("Artist");
		model.addColumn("Time");
		
		
		MusicList = new JTable();
		// 해당 테이블에다 다음과 같이 모델을추가
		
		class TableCellColorChangeRenderer extends DefaultTableCellRenderer{
			
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component Cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if(!isSelected && column != 0) {
					//Cell.setBackground(new Color(31,29,62));
					Cell.setForeground(Color.white);
				}
				
				if(column == 0) {
					Cell.setBackground(new Color(31,29,62));
				}
				
				
				if(Cell instanceof JLabel) {
					((JLabel) Cell).setHorizontalAlignment(JLabel.CENTER);
				}
				
				if(Cell instanceof JLabel && column == 2) {
					((JLabel) Cell).setHorizontalAlignment(JLabel.LEFT);
				}
				
				
				return Cell;
			}
			
		}
		
		class CheckBoxRenderer extends JCheckBox implements TableCellRenderer{
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				if(!isSelected) {
					//setBackground(new Color(31,29,62));
					setOpaque(false);
				}
				
				setSelected((value != null && ((Boolean) value).booleanValue()));
				return this;
			}
		}
		
		
		renderer = new TableCellColorChangeRenderer();
		// 테이블 색상 변경 객체
		chkboxrenderer = new CheckBoxRenderer();
		
		try {
			MusicList.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MusicList.setModel(model);
		MusicList.setShowGrid(false); //셀 선 지우기
		MusicList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		MusicList.getColumnModel().getColumn(0).setPreferredWidth(21);
		MusicList.getColumnModel().getColumn(0).setCellRenderer(chkboxrenderer);
		MusicList.getColumnModel().getColumn(1).setPreferredWidth(35);
		MusicList.getColumnModel().getColumn(1).setCellRenderer(renderer);
	
		MusicList.getColumnModel().getColumn(2).setPreferredWidth(220);
		MusicList.getColumnModel().getColumn(3).setPreferredWidth(80);
		MusicList.getColumnModel().getColumn(3).setCellRenderer(renderer);
	
		MusicList.getColumnModel().getColumn(4).setPreferredWidth(58);
		MusicList.getColumnModel().getColumn(4).setCellRenderer(renderer);
	
		MusicList.setTableHeader(null);
	
		MusicList.setBackground(new Color(31,29,62));
		MusicList.setOpaque(true);
		
		MusicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//리스트중에 하나만 선택되도록 수정
		
		
		MusicListScroll = new JScrollPane(MusicList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MusicListScroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		MusicListScroll.setPreferredSize(new Dimension(0,100));
		
		MusicListScroll.getViewport().setBackground(new Color(31,29,62));
		MusicListScroll.setBackground(new Color(31,29,62));
	
		//MusicListScroll.setOpaque(true);
		
		
		// 스크롤 패턴 안에 리스트가 버튼 형식으로 동작하도록 설정한 패털
		MusicItemListBtn.add("Center",MusicListScroll);	
		
	}
	// 노리 래스트에서 검색시 사용할 검색창 패널을 넣기 위한 용도
		
	public void setTableList(String id) {
		
		model.setRowCount(list.size());
		// 받은 list의 길이 만큼 행 수를 지정
		
		MusicVO vo = null;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // 해당 열의 값을 가져옴
			
			
			MusicList.setValueAt(vo.getNo(), i, 0);
			MusicList.setValueAt(vo.getMusicname(), i, 1);
			MusicList.setValueAt(vo.getArtist(), i, 2);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 3);
			// 음악 플레이 타임을 띄우기 위한 용도로 사용
			// 해당 메소드는 모든 음악을 띄우는 용도로 사용
			
			
			
		}
	}	
	
	
	public void setTableList() {
		
		model.setRowCount(list.size());
		// 받은 list의 길이 만큼 행 수를 지정
		
		MusicVO vo = null;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // 해당 열의 값을 가져옴
			
			MusicList.setValueAt(false, i, 0);
			MusicList.setValueAt(vo.getNo(), i, 1);
			MusicList.setValueAt(vo.getMusicname(), i, 2);
			MusicList.setValueAt(vo.getArtist(), i, 3);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 4);
			// 음악 플레이 타임을 띄우기 위한 용도로 사용
			// 해당 메소드는 모든 음악을 띄우는 용도로 사용
			
			
			
		}
	}
	
	public void setUserTableList(PlayerController playerController) {
		
		
		this.musicFuncAttribute = playerController.getMusicFuncElements();
		
		
		model.setRowCount(list.size());
		// 받은 list의 길이 만큼 행 수를 지정
		
		MusicVO vo = null;
		
		musicFuncAttribute.MusicArtworkList.clear();
		musicFuncAttribute.filePathList.clear();
		musicFuncAttribute.MusicInfoList.clear();
		musicFuncAttribute.MusicSerial = 1;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // 해당 열의 값을 가져옴
			
			
			MusicList.setValueAt(false, i, 0);
			MusicList.setValueAt(vo.getNo(), i, 1);
			MusicList.setValueAt(vo.getMusicname(), i, 2);
			MusicList.setValueAt(vo.getArtist(), i, 3);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 4);
			// 음악 플레이 타임을 띄우기 위한 용도로 사용
	
			musicFuncAttribute.filePath = vo.getAbsoluteFilePath(); // 파일 경로명을 알아옴
			musicFuncAttribute.filePathList.add(musicFuncAttribute.filePath);
			
			
			musicFuncAttribute.AudioFile = new File(musicFuncAttribute.filePath);
			musicFuncAttribute.MusicInfoParse(musicFuncAttribute.AudioFile, musicFuncAttribute.filePathList.get(musicFuncAttribute.filePathList.size()-1));
			musicFuncAttribute.MusicInfoListInsert(); //음악 정보 리스트에 노래 정보 삽입
			musicFuncAttribute.MusicTableInsert(); //테이블에 음악 목록 삽입
		}
		System.out.println(musicFuncAttribute.filePathList);
	}
	
	
	// 파일 경로를 보내서 해당 파일에 대한 플레이 타임을 추출 및 문자열 형태로 리턴, 그리고 테이블의 플레이 타임 문자열에 넣음
	public String MusicInfoParse(String filePath) {
		
		int musicPlayTime = 0;
		String strMusicPlayTime = "";
		
		File AudioFile = new File(filePath);
		try {
			MP3File mp3 = (MP3File) AudioFileIO.read(AudioFile);
			
			
			musicPlayTime = mp3.getAudioHeader().getTrackLength();
			
			strMusicPlayTime = this.makeMusicPlayTimeStr(musicPlayTime);
			
			if(musicPlayTime == 0) {
				strMusicPlayTime = "";
			}
			
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return strMusicPlayTime;
	}
	
	
	public String makeMusicPlayTimeStr(int musicPlayTime) {
		
		int minute;
		String strHour, strMinute, strSecond, returnTime;
		
		strSecond = (musicPlayTime % 60) + "";
		strMinute = (musicPlayTime / 60) + "";
		
		minute = Integer.parseInt(strMinute);
		
		strHour = (minute / 60)+"";
		
		if(strHour.equals("0")) {
			returnTime = strMinute + ":" + strSecond;
		}
		else
		{
			returnTime = strHour + ":" + strMinute + ":" + strSecond;
		}
		return returnTime;
		
	}
	
	
	
	// 테이블 첫 행, 즉 테이블 제목 영역 설정하는 메소드

	
	
	
	

}
