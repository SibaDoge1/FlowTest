import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class recordWindow extends JFrame {
	private String name;
	private JButton jbtDone = new JButton("Done");
	private JTextField jtfName = new JTextField();
	private JTextField jtfId = new JTextField();
	private JTextField jtfCate = new JTextField();
	private JTextField jtfPrice = new JTextField();
	private JTextField jtfStock = new JTextField();
	private JTextField jtfMin = new JTextField();
	private JTextField jtfMemo = new JTextField();
	private JPanel panel = new JPanel(new GridLayout(2, 7));
	private String [] token = new String [7]; //�ؽ�Ʈ�ʵ��� �����͸� �޾� �����ϱ����� ����
	
	public recordWindow(String s, ActionListener Action){ //�⺻ ������. ����� ��ư�׼Ǹ����ʸ� ���ڷ� �޴´�.
		name = s;
		panel.add(new JLabel("��ǰ��"));
		panel.add(new JLabel("��ǰID"));
		panel.add(new JLabel("ī�װ�"));
		panel.add(new JLabel("����"));
		panel.add(new JLabel("����"));
		panel.add(new JLabel("�ּ����"));
		panel.add(new JLabel("��Ÿ �޸�"));
		panel.add(jtfName);
		panel.add(jtfId);
		panel.add(jtfCate);
		panel.add(jtfPrice);
		panel.add(jtfStock);
		panel.add(jtfMin);
		panel.add(jtfMemo);
		this.setTitle(s);
		this.setLayout(new BorderLayout());
		this.add(jbtDone, BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
		jbtDone.addActionListener(Action);
		this.setSize(800, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public recordWindow(String s, ActionListener Action, String[] token){ //��Ʈ���迭�� �߰��� �޴� ������, ���� �迭�� �ؽ�Ʈ�ʵ忡 ó������ ��½�Ų��
		this(s, Action);
		jtfName.setText(token[0]);
		jtfId.setText(token[1]);
		jtfCate.setText(token[2]);
		jtfPrice.setText(token[3]);
		jtfStock.setText(token[4]);
		jtfMin.setText(token[5]);
		jtfMemo.setText(token[6]);
	}
	
	public String getName(){ //â�� �̸��� �����Ѵ�.
		return name;
	}
	
	public String[] getText(){ // �ؽ�Ʈ�ʵ忡 �ִ� �����͸� �����Ͽ� ��Ʈ���迭���·� �����Ѵ�
		String [] token = new String [6];
		token[0] = jtfName.getText();
		token[1] = jtfId.getText();
		token[2] = jtfPrice.getText();
		token[3] = jtfStock.getText();
		token[4] = jtfMin.getText();
		token[5] = jtfMemo.getText();
		return token;
	}
	
}
