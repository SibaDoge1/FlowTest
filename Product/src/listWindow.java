import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;

public class listWindow extends JFrame  { //���� â����, ���α׷� ������ ��Ʋ�� �Ǵ� Ŭ�����̴�.
	private JButton jbtAdd = new JButton("Add");
	private JButton jbtDel = new JButton("Delete");
	private JButton jbtMod = new JButton("Modify");
	private JButton jbtSave = new JButton("SaveFile");
	private JPanel panel = new JPanel(new GridLayout(4, 1));
	private String [] name = {"��ǰ��", "��ǰID", "ī�װ�", "����", "����", "�ּ����", "��Ÿ �޸�"};  //table�� ����� ���� �׸�
	private String [][] contents = {
	};
	private DefaultTableModel model = new DefaultTableModel(contents, name); //DefaultTableModel�� ���
	private JTable table = new JTable(model);
	private JScrollPane Spane = new JScrollPane(table);
	private String [] token = new String[6]; // �����͸� �����ϱ� ���� ������ ����
	private ProductList pList; //���α׷��� ��������� ��ǰ ����Ʈ�̴�.
	private File file;
	private BufferedOutputStream out; //�޸𸮻��� ������ �ӵ��� ���� Buffered�� ���
	private recordWindow recordWin;
	private int i;
	
	public listWindow(File _file) throws IOException{ //������ �޴� ������, ������ �����ϱ� ���� 2���� �����ڸ� �ξ���.
		this();
		file = _file;
		if(!file.exists()){ // ������ ������ ���� ����� �⺻���� ������ �Է��صд�.
			out = new BufferedOutputStream(new FileOutputStream(file));
            String data = "// Product List \r\n// Format:: Product Name:Product ID:Value:Current Stock:Safe Stock Level \r\n// Category Number Food:1 , Office:2 , Misc.:3 , Health:4, Clothing:5";
            out.write(data.getBytes());
            out.flush();
            out.close();
		}
		FileInputStream in = new FileInputStream(file); //��ǲ��Ʈ���� ������ �Է��Ѵ�.
		pList = new ProductList(in); // List����
		for(int i = 0; i < pList.List.size(); i++){ // table�� List�� �����͸� �Է��Ѵ�.
			model.addRow(pList.List.get(i).getAll());
		}
		in.close();
	}
	
	public listWindow(){ // gui�� �����ϴ� ������, ������ �����ϱ� ���� 2���� �����ڸ� �ξ���.
		panel.add(jbtAdd);
		panel.add(jbtDel);
		panel.add(jbtMod);
		panel.add(jbtSave);
		this.setTitle("Product List");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.EAST);
		this.add(Spane, BorderLayout.CENTER);
		jbtAdd.addActionListener(new clickAction());
		jbtDel.addActionListener(new clickAction());
		jbtMod.addActionListener(new clickAction());
		jbtSave.addActionListener(new clickAction());
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void refreshTable(){ //table�� ���ΰ�ħ �ϱ� ���� �Լ�
		model.setRowCount(0);
		for(int i = 0; i < pList.List.size(); i++){
			model.addRow(pList.List.get(i).getAll());
		}
		model.fireTableDataChanged();
	}
	
	class clickAction implements ActionListener{ //��� â���� ��Ÿ���� ��� ��ư�� ���̴� �׼Ǹ������̴�. ��� ��ư�� �׼��� ���α׷� ������ �ǵ帮�� ������ �߽��� �Ǵ� Ŭ������ ���Ƴ־���.
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbtAdd){ //Add��ư�� ���� ��
				recordWin = new recordWindow("Add", this); //Add�� ���� ������ â�� ����.
			}
			else if (e.getSource() == jbtDel){//Delete��ư�� ���� ��
				if(table.getSelectedRow() != -1){ // ���õ� ���� �����Ѵ�.
					pList.List.remove(table.getSelectedRow());
					refreshTable();
				}
			}
			else if (e.getSource() == jbtMod){//Modify��ư�� ���� ��
				i = table.getSelectedRow();
				token =pList.List.get(i).getAll();
				recordWin = new recordWindow("Modify", this, token); //���õ� ���� �����͸� �����ϰ�, Mod�� ���� ������ â�� ����.
				
			}
			else if (e.getSource() == jbtSave){//SaveFile ��ư�� ���� ��
				try { //�⺻���� �ȳ����� ���� ����ϰ�, List�� ��� �����͸� ü�������� ����Ѵ�.
					out = new BufferedOutputStream(new FileOutputStream(file));
					String data = "// Product List \r\n// Format:: Product Name:Product ID:Value:Current Stock:Safe Stock Level \r\n// Category Number Food:1 , Office:2 , Misc.:3 , Health:4, Clothing:5 \r\n";
					out.write(data.getBytes());
					out.flush();
					for(int i = 0; i < pList.List.size(); i++ ){	//List�� �����͸� �޾� ������ ���·� ���
						String[] token = pList.List.get(i).getAll(); 
						String s = token[0]+":"+token[1]+":"+token[3]+":"+token[4]+":"+token[5]+":"+token[6]+"\r\n";
						out.write(s.getBytes());
						out.flush();
					} 
					out.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			else if (recordWin.getName().equals("Add")){ //��� Addâ���� Done ��ư�� ���� ��
				if(!pList.set(-1, recordWin.getText(), false)){ recordWin.dispose(); }
				refreshTable();
			}
			else if (recordWin.getName().equals("Modify")){ //��� Modâ���� Done ��ư�� ���� ��
				if(!pList.set(i, recordWin.getText(), true)){ 
					recordWin.dispose(); 
					pList.List.remove(i+1); 
				}
				refreshTable();
			}
		}
	}
	

}
