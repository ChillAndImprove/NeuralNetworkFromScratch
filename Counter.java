import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Counter extends JFrame
{
	int cnt = 0;

	public Counter () {
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				cnt++;
				System.out.println(cnt);
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});
		setVisible(true);
		setSize(400, 400);
	}

	public static void main(String[] args)
	{
		new Counter();
	}
}
