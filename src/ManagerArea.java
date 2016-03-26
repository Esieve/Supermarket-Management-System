import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.io.Reader;

public class ManagerArea extends JPanel{
	JButton checkBill;
	JTable table;
	Object a[][] = new Object[100][3];
	Object listName[] = {"����","��Ʒ����","�۸�"};
	int income = 0,expense = 0;
	
	public ManagerArea() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	void init(){
		checkBill = new JButton("ͳ��");
		
		try {
			File file = new File("bill.txt");
			Reader in = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(in);
			String lines = null;
			for(int i = 0;(lines = bufferedReader.readLine())!=null;i++){
				a[i][0] = lines.split(" ")[0];
				a[i][1] = lines.split(" ")[1];
				a[i][2] = lines.split(" ")[2];
				if(a[i][0].toString().equals("����")){
					income += Integer.parseInt(a[i][2].toString());
				}else if(a[i][0].toString().equals("֧��")){
					expense += Integer.parseInt(a[i][2].toString());
				}
			}
			in.close();
			table = new JTable(a, listName);
			add(new JScrollPane(table),BorderLayout.CENTER);
			add(checkBill, BorderLayout.SOUTH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�ļ�������");
		}
		
		checkBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(ManagerArea.this, "�����룺"+income+"\n��֧����"+expense+"\nӯ�������"+(income-expense));
			}
		});
		
		setSize(600, 200);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		   int x = 0, y = 0;
		   ImageIcon icon = new ImageIcon("background1.jpg");
		   g.drawImage(icon.getImage(), x, y, getSize().width,
		   getSize().height, this);
//		   g.drawImage(icon.getImage(), x, y,this);//ͼƬ�����Զ�����
	}
}
