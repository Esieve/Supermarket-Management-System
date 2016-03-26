import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SalesmanArea extends JPanel{
	JTextField goods,price;
	JButton confirm;
	StatusBar sBar;
	Box baseBox,box1,box2;
	
	public SalesmanArea() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	void init(){
		goods = new JTextField(10);
		price = new JTextField(10);
		confirm = new JButton("确认");
		sBar = new StatusBar("");
		baseBox = Box.createHorizontalBox();
		box1 = Box.createVerticalBox();
		box2 = Box.createVerticalBox();
		
		box1.add(Box.createVerticalStrut(100));
		box1.add(new JLabel("商品名称："));
		box1.add(Box.createVerticalStrut(30));
		box1.add(new JLabel("收入："));
		box1.add(Box.createVerticalStrut(30));
		box1.add(new JLabel("单击录入"));

		box2.add(Box.createVerticalStrut(100));
		box2.add(goods);
		box2.add(Box.createVerticalStrut(20));
		box2.add(price);
		box2.add(Box.createVerticalStrut(20));
		box2.add(confirm);
		
		baseBox.add(box1);
		baseBox.add(Box.createHorizontalStrut(40));
		baseBox.add(box2);
		add(baseBox);
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file = new File("bill.txt");
				try {
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(                        
		                    new FileOutputStream(file, true)));
					bWriter.write("收入 ");
					bWriter.write(goods.getText()+" ");
					bWriter.write(price.getText());
					bWriter.newLine();
					bWriter.close();
					sBar.refresh("录入成功");
					goods.setText("");
					price.setText("");
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("文件不存在");
				}
			}
		});
		
		goods.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				sBar.refresh("");
			}
		});
		
		price.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				sBar.refresh("");
			}
		});
		add(sBar);
		setSize(600,400);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		   int x = 0, y = 0;
		   ImageIcon icon = new ImageIcon("background1.jpg");
		   g.drawImage(icon.getImage(), x, y, getSize().width,
		   getSize().height, this);
//		   g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
	}
}
