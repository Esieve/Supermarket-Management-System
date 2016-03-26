import javax.swing.*;
import java.awt.*;

public class StatusBar extends JToolBar{
	JLabel status;
	String base;
	
	public StatusBar(String s) {
		// TODO Auto-generated constructor stub
		base = s;
		status = new JLabel(base);
		add(status);
		setFloatable(false);
		setOpaque(false);
	}
	public void refresh(String s) {
		status.setText(s);
		this.repaint();
	}
}
