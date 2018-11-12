package swidget.widgets.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swidget.icons.IconSize;
import swidget.icons.StockIcon;
import swidget.widgets.Spacing;


public class TitledPanel extends JPanel
{

	private JLabel icon;
	private GridBagConstraints c;
	
	private boolean showBadge = false;
	private String caption = null;
	private JComponent component;
	
	public TitledPanel(JComponent component)
	{
		this(component, null);
	}

	public TitledPanel(JComponent component, String caption)
	{
		this(component, caption, true);
	}
	
	public TitledPanel(JComponent component, boolean showBadge)
	{
		this(component, null, showBadge);
	}

	public TitledPanel(JComponent component, String caption, boolean showBadge)
	{
		this.caption = caption;
		this.showBadge = showBadge;
		this.component = component;
		setLayout(new GridBagLayout());
		
		make();
		
	}
	

	private void make() {
		
		removeAll();
		
		c = new GridBagConstraints();
		c.ipadx = 8;
		c.ipady = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 2;
		c.weightx = 0;
		
		if (showBadge) {
			icon = new JLabel(StockIcon.BADGE_INFO.toImageIcon(IconSize.ICON));
			icon.setBorder(new EmptyBorder(0, 0, 0, Spacing.huge));
			add(icon, c);
		}
		
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1;
		c.gridheight = 1;
		c.gridy = 0;
		c.gridx = (showBadge ? 1 : 0);
		
		if (caption != null) 
		{
			c.gridwidth = 2;
			add(new JLabel("<html><big><b>" + caption + "</b></big></html>"), c);
			c.gridwidth = 1;
			c.gridy++;
			
		}
		
		add(component, c);

		this.repaint();
		
		
	}
	

	public void setBadge(ImageIcon imageIcon) {
		icon.setIcon(imageIcon);
		make();
	}
}