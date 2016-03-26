import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainArea extends JFrame{
	JMenuBar topBar;
	JMenu home,instrument;
	JMenuItem calc,note;
	LoginArea lArea;
	
	CardLayout cLayout = null;
	JPanel center;
	
	public MainArea() {
		// TODO Auto-generated constructor stub
		init();
		setTitle("超市管理系统");
		setLocationByPlatform(true);
		setSize(600, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
	}
	void init(){
		home = new JMenu("主页");
		home.setToolTipText("快捷键Alt+H");
		home.setMnemonic('h');
		home.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				cLayout.show(center, "LoginArea");
				lArea.user.setText("");
				lArea.pwd.setText("");
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		instrument = new JMenu("工具");
		instrument.setToolTipText("快捷键Alt+i");
		instrument.setMnemonic('i');
		
		calc = new JMenuItem("计算器");
		calc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		calc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Runtime rt=Runtime.getRuntime();
				try {
					rt.exec("calc");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		note = new JMenuItem("记事本");
		note.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		note.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Runtime rt=Runtime.getRuntime();
				try {
					rt.exec("notepad");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		topBar = new JMenuBar();
		topBar.add(home);
		instrument.add(calc);
		instrument.add(note);
		topBar.add(instrument);
		setJMenuBar(topBar);
		
		center = new JPanel();
		cLayout = new CardLayout();
		lArea = new LoginArea(cLayout, center);
		center.setLayout(cLayout);
		center.add("LoginArea", lArea);
		add(center,BorderLayout.CENTER);
	}
	
}
