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
	JButton searchBtn = new JButton("�˻�");
	
	JButton admin_musicAddBtn = new JButton("�뷡 �߰�");
	
	
	String id;
	JTable MusicList;
	List<MusicVO> list;
	
	DefaultTableModel model;
	DefaultTableCellRenderer Dtcr;
	TableCellRenderer renderer;
	TableCellRenderer chkboxrenderer;
	
	
	JPanel MusicItemListBtn, MusicItems;
			// MusicItemListBtn = ���� �� ����,
			// MusicItems = GridBagLayout ���� ����
	JLabel No, MusicTitle, Artist, MusicTime;
	public JCheckBox MusicAllSelect;
			// ó�� ���̺� ���� ������
	
	JScrollPane MusicListScroll;
	

	String[] cols = { "����","No","����","��Ƽ��Ʈ","�ð�"};
	String[] searchOptionList = {"����","��Ƽ��Ʈ"};
	
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
		// ���� ���̺��� �������ڸ��� ������ ���� �ٷ� ������ �ǵ��� �����ڷ� �߰�
		
		setLayout(new BorderLayout());
		
		
		setMusicItems(); // �Ӹ����� �����ϰ� ���� �Ʒ� ���̺� ����
		setMusicTable();
		
	}
	
	public MusicTable(String id) {
		// TODO Auto-generated constructor stub
		// ���� ���̺��� �������ڸ��� ������ ���� �ٷ� ������ �ǵ��� �����ڷ� �߰�
		
		this.id = id;
		setLayout(new BorderLayout());
		
		
		setMusicItems(id); // �Ӹ����� �����ϰ� ���� �Ʒ� ���̺� ����
		setMusicTable(id);
		
	}
	
	@SuppressWarnings("serial")
	public void setMusicTable(String id) {
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int col) {
				return false;
			};
			
			// �� ���� ���� ���� ���� ����, ù��° üũ�ڽ��� ���� ����
			
			
			
			// �Ʒ��� ���̺� ���� ������ ��Ÿ���� ����, 
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
		// �ش� ���̺��� ������ ���� �����߰�
		
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
		// ���̺� ���� ���� ��ü
		
		
		try {
			MusicList.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MusicList.setModel(model);
		MusicList.setShowGrid(false); //�� �� �����
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
		//����Ʈ�߿� �ϳ��� ���õǵ��� ����
		
		
		MusicListScroll = new JScrollPane(MusicList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MusicListScroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		MusicListScroll.setPreferredSize(new Dimension(0,100));
		
		MusicListScroll.getViewport().setBackground(new Color(31,29,62));
		MusicListScroll.setBackground(new Color(31,29,62));
	
		//MusicListScroll.setOpaque(true);
		
		
		// ��ũ�� ���� �ȿ� ����Ʈ�� ��ư �������� �����ϵ��� ������ ����
		MusicItemListBtn.add("Center",MusicListScroll);	
		
	}
	// �븮 ����Ʈ���� �˻��� ����� �˻�â �г��� �ֱ� ���� �뵵
	
	
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
					
				MusicTitle = new JLabel("����",JLabel.RIGHT);
					MusicTitle.setForeground(new Color(202,201,201));
					MusicTitle.setBorder(BorderFactory.createEmptyBorder(0,70,0,45));
				Artist = new JLabel("��Ƽ��Ʈ", JLabel.RIGHT);
					Artist.setForeground(new Color(202,201,201));
					Artist.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
				MusicTime = new JLabel("�ð�", JLabel.CENTER);
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
				
				MusicItemListBtn.add("North",MusicItems); // ó���� �� �Ӹ� ����

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
					
				MusicTitle = new JLabel("����",JLabel.RIGHT);
					MusicTitle.setForeground(new Color(202,201,201));
					MusicTitle.setBorder(BorderFactory.createEmptyBorder(0,20,0,45));
				Artist = new JLabel("��Ƽ��Ʈ", JLabel.RIGHT);
					Artist.setForeground(new Color(202,201,201));
					Artist.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
				MusicTime = new JLabel("�ð�", JLabel.CENTER);
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
				
				MusicItemListBtn.add("North",MusicItems); // ó���� �� �Ӹ� ����

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
			
			// �� ���� ���� ���� ���� ����, ù��° üũ�ڽ��� ���� ����
			
			
			
			// �Ʒ��� ���̺� ���� ������ ��Ÿ���� ����, 
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
		// �ش� ���̺��� ������ ���� �����߰�
		
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
		// ���̺� ���� ���� ��ü
		chkboxrenderer = new CheckBoxRenderer();
		
		try {
			MusicList.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MusicList.setModel(model);
		MusicList.setShowGrid(false); //�� �� �����
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
		//����Ʈ�߿� �ϳ��� ���õǵ��� ����
		
		
		MusicListScroll = new JScrollPane(MusicList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MusicListScroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		MusicListScroll.setPreferredSize(new Dimension(0,100));
		
		MusicListScroll.getViewport().setBackground(new Color(31,29,62));
		MusicListScroll.setBackground(new Color(31,29,62));
	
		//MusicListScroll.setOpaque(true);
		
		
		// ��ũ�� ���� �ȿ� ����Ʈ�� ��ư �������� �����ϵ��� ������ ����
		MusicItemListBtn.add("Center",MusicListScroll);	
		
	}
	// �븮 ����Ʈ���� �˻��� ����� �˻�â �г��� �ֱ� ���� �뵵
		
	public void setTableList(String id) {
		
		model.setRowCount(list.size());
		// ���� list�� ���� ��ŭ �� ���� ����
		
		MusicVO vo = null;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // �ش� ���� ���� ������
			
			
			MusicList.setValueAt(vo.getNo(), i, 0);
			MusicList.setValueAt(vo.getMusicname(), i, 1);
			MusicList.setValueAt(vo.getArtist(), i, 2);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 3);
			// ���� �÷��� Ÿ���� ���� ���� �뵵�� ���
			// �ش� �޼ҵ�� ��� ������ ���� �뵵�� ���
			
			
			
		}
	}	
	
	
	public void setTableList() {
		
		model.setRowCount(list.size());
		// ���� list�� ���� ��ŭ �� ���� ����
		
		MusicVO vo = null;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // �ش� ���� ���� ������
			
			MusicList.setValueAt(false, i, 0);
			MusicList.setValueAt(vo.getNo(), i, 1);
			MusicList.setValueAt(vo.getMusicname(), i, 2);
			MusicList.setValueAt(vo.getArtist(), i, 3);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 4);
			// ���� �÷��� Ÿ���� ���� ���� �뵵�� ���
			// �ش� �޼ҵ�� ��� ������ ���� �뵵�� ���
			
			
			
		}
	}
	
	public void setUserTableList(PlayerController playerController) {
		
		
		this.musicFuncAttribute = playerController.getMusicFuncElements();
		
		
		model.setRowCount(list.size());
		// ���� list�� ���� ��ŭ �� ���� ����
		
		MusicVO vo = null;
		
		musicFuncAttribute.MusicArtworkList.clear();
		musicFuncAttribute.filePathList.clear();
		musicFuncAttribute.MusicInfoList.clear();
		musicFuncAttribute.MusicSerial = 1;
		
		for(int i=0; i< list.size(); i++) {
			vo = list.get(i); // �ش� ���� ���� ������
			
			
			MusicList.setValueAt(false, i, 0);
			MusicList.setValueAt(vo.getNo(), i, 1);
			MusicList.setValueAt(vo.getMusicname(), i, 2);
			MusicList.setValueAt(vo.getArtist(), i, 3);
			MusicList.setValueAt(MusicInfoParse(vo.getAbsoluteFilePath()), i, 4);
			// ���� �÷��� Ÿ���� ���� ���� �뵵�� ���
	
			musicFuncAttribute.filePath = vo.getAbsoluteFilePath(); // ���� ��θ��� �˾ƿ�
			musicFuncAttribute.filePathList.add(musicFuncAttribute.filePath);
			
			
			musicFuncAttribute.AudioFile = new File(musicFuncAttribute.filePath);
			musicFuncAttribute.MusicInfoParse(musicFuncAttribute.AudioFile, musicFuncAttribute.filePathList.get(musicFuncAttribute.filePathList.size()-1));
			musicFuncAttribute.MusicInfoListInsert(); //���� ���� ����Ʈ�� �뷡 ���� ����
			musicFuncAttribute.MusicTableInsert(); //���̺� ���� ��� ����
		}
		System.out.println(musicFuncAttribute.filePathList);
	}
	
	
	// ���� ��θ� ������ �ش� ���Ͽ� ���� �÷��� Ÿ���� ���� �� ���ڿ� ���·� ����, �׸��� ���̺��� �÷��� Ÿ�� ���ڿ��� ����
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
	
	
	
	// ���̺� ù ��, �� ���̺� ���� ���� �����ϴ� �޼ҵ�

	
	
	
	

}
