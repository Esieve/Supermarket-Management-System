import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class LoginArea extends JPanel{
	JComboBox identify;
	JTextField user;
	JPasswordField pwd;
	JButton confirm;
	Box baseBox,box1,box2;
	File file = null;
	int iden = 0;//用于标记身份，确认跳转哪个页面
	ImageIcon icon;
	
	ManagerArea mArea;
	SalesmanArea sArea;
	KeeperArea kArea;
	
	public LoginArea(CardLayout cL,JPanel c) {
		// TODO Auto-generated constructor stub
		init(cL, c);
	}
	
	void init(CardLayout cL,JPanel c){
		identify = new JComboBox();
		user = new JTextField(12);
		pwd = new JPasswordField(12);
		confirm = new JButton("确认");
		baseBox = Box.createHorizontalBox();
		box1 = Box.createVerticalBox();
		box2 = Box.createVerticalBox();
		icon = new ImageIcon("login1.jpg");
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = null;
				String name = user.getText();
				String password = String.valueOf(pwd.getPassword());
				try {
					Reader in = new FileReader(file);
					BufferedReader bReader = new BufferedReader(in);
					str = bReader.readLine();
					bReader.close();
					String[] lines = str.split("#");
					int flag = 0;
					for(int i = 0;i<lines.length;i++){
						if(lines[i].split(",")[0].equals(name) &&
								lines[i].split(",")[1].equals(password)){
							if(iden == 1){
								mArea = new ManagerArea();
								c.add("ManagerArea",mArea);
								cL.show(c, "ManagerArea");
							}else if(iden == 2){
								sArea = new SalesmanArea();
								c.add("SalesmanArea", sArea);
								cL.show(c, "SalesmanArea");
							}else if(iden == 3){
								kArea = new KeeperArea();
								c.add("KeeperArea",kArea);
								cL.show(c, "KeeperArea");
							}
							flag = 1;
							break;
						}
					}
					if(flag == 0){
						JOptionPane.showMessageDialog(LoginArea.this, "该用户不存在");	
						pwd.setText("");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("文件不存在");
				}
			}
		});
		
		identify.addItem("请选择");
		identify.addItem("管理员");
		identify.addItem("收银员");
		identify.addItem("库管员");
		identify.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String shenfen = e.getItem().toString();
				if(shenfen.equals("管理员")){
					file = new File("managerInformation.txt");
					iden = 1;
				}else if(shenfen.equals("收银员")){
					file = new File("salesmanInformation.txt");
					iden = 2;
				}else if(shenfen.equals("库管员")){
					file = new File("keeperInformation.txt");
					iden = 3;
				}
				user.setText("");
				pwd.setText("");
			}
		});
		
		pwd.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				icon = new ImageIcon("login1.jpg");
				repaint();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				icon = new ImageIcon("login2.jpg");
				repaint();
			}
		});
		
		box1.add(Box.createVerticalStrut(100));
		box1.add(new JLabel("身份："));
		box1.add(Box.createVerticalStrut(30));
		box1.add(new JLabel("用户名："));
		box1.add(Box.createVerticalStrut(30));
		box1.add(new JLabel("密码："));
		box1.add(Box.createVerticalStrut(30));
		box1.add(new JLabel("单击录入"));

		box2.add(Box.createVerticalStrut(100));
		box2.add(identify);
		box2.add(Box.createVerticalStrut(20));
		box2.add(user);
		box2.add(Box.createVerticalStrut(20));
		box2.add(pwd);
		box2.add(Box.createVerticalStrut(20));
		box2.add(confirm);
		
		baseBox.add(box1);
		baseBox.add(Box.createHorizontalStrut(20));
		baseBox.add(box2);
		add(baseBox);
	}
	
	@Override
	public void paintComponent(Graphics g) {
			
		   int x = 0, y = 0;
		   //ImageIcon icon = new ImageIcon("login1.jpg");
		   g.drawImage(icon.getImage(), x, y, getSize().width,
		   getSize().height, this);
//		   g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
	}
}
