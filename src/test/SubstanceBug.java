package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

public class SubstanceBug
{
	public static void main(String[] args)
	{
		//This one demos the default Look and Feel handling this
		testBug("Default LaF", 0, 0, false);
		
		//Install Substance's Graphite Look and Feel
		installLookAndFeel();
		
		//These two demo Substance's Graphite Look and Feel handling this
		//This does all kinds of weird stuff
		testBug("Substance/Graphite", 0, 1, false);
		//This works correctly, but constantly forces the current Look and Feel
	    testBug("Substance/Graphite (UpdateUI called)", 1, 1, true);
		
	}
	
	private static void testBug(
			String title, int xShift, int yShift, boolean updateUI)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(250, 250);
				frame.setLocation(275 * xShift, 275 * yShift);
				
				JProgressBar bar = new JProgressBar();
				//To give a clear indication of actual value, despite the bug
				bar.setStringPainted(true);
				//Off-screen components need an explicitly set size
				bar.setSize(bar.getPreferredSize());
				bar.setValue(50);
				
				//This BufferedImage is what we'll be rendering bar into
				BufferedImage image = new BufferedImage(
						bar.getWidth(), bar.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				
				//Setup an initial render
				paint(bar, image);
				
				JLabel label = new JLabel(new ImageIcon(image));
				label.setText(title);
				label.setHorizontalTextPosition(JLabel.CENTER);
				label.setVerticalTextPosition(JLabel.BOTTOM);
				frame.add(label);
				frame.setVisible(true);
				
				startUpdating(bar, image, label, updateUI);
			}
		});
	}
	
	private static void startUpdating(JProgressBar bar, BufferedImage image,
			JLabel label, boolean updateUI)
	{
		new Thread(new Runnable()
		{
			int v = 50;
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
					}
					
					v++;
					if (v > 100)
					{
						v = 0;
					}
					
					try
					{
						SwingUtilities.invokeAndWait(new Runnable()
						{
							public void run()
							{
								//Update the value of progress bar
								bar.setValue(v);
								//This is the kludgy work-around that seems to get
								// this to work properly with substance
								if (updateUI)
								{
									bar.updateUI();
								}
								//Finally, we can paint it
								paint(bar, image);
								
								label.repaint();
							}
						});
					}
					catch (InterruptedException | InvocationTargetException e)
					{
						throw new RuntimeException(e);
					}
				}
			}
		}).start();
	}
	
	private static void paint(JComponent jc, BufferedImage image)
	{
		Graphics g = image.getGraphics();
		jc.paint(g);
		g.dispose();
	}
	
	private static void installLookAndFeel()
	{
		try
		{
			//We're waiting to make sure the look and feel is installed before
			// moving on to tests
			SwingUtilities.invokeAndWait(new Runnable()
			{
				public void run()
				{
					try
					{
						UIManager.setLookAndFeel(
								"org.pushingpixels.substance.api.skin."
								+ "SubstanceGraphiteLookAndFeel");
					}
					//Best we can do in the circumstances
					catch (ClassNotFoundException
							| InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e)	
					{
						System.err.println("Unable to install Look & Feel");
						e.printStackTrace(System.err);
					}
				}
			});
		}
		catch (InterruptedException | InvocationTargetException e)
		{
			throw new RuntimeException(e);
		}
	}
}