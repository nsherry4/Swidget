package swidget.icons;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.MultiResolutionImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sun.awt.AppContext;
import swidget.widgets.buttons.ImageButton;


public class IconFactory {

	public static final String path = "/swidget/icons/";
	public static String customPath = null;
	
	public static ImageIcon getImageIcon(String imageName){
		return getImageIcon(imageName, null);
	}
	

	public static ImageIcon getMenuIcon(String imageName)
	{
		return getImageIcon(imageName, IconSize.BUTTON);
	}
	
	public static ImageIcon getMenuIcon(StockIcon stock)
	{
		return stock.toImageIcon(IconSize.BUTTON);
	}
	
	
	
	public static ImageIcon getImageIcon(String imageName, IconSize size){
		return getImageIcon(imageName, size, path);
	}
	
	public static ImageIcon getImageIcon(String imageName, IconSize iconSize, String path){
		//It looks like Java/Swing only supports HiDPI on macs for now
		if (isMac()) {
			ImageIcon x1 = getImageIcon(imageName, iconSize, path, 1);
			ImageIcon x2 = getImageIcon(imageName, iconSize, path, 2);
			BaseMultiResolutionImage icon;
			if (x2 != null) {
				icon = new BaseMultiResolutionImage(x1.getImage(), x2.getImage());
			} else {
				icon = new BaseMultiResolutionImage(x1.getImage());
			}
			ImageIcon imageIcon = new ImageIcon(icon);
			imageIcon.getImageLoadStatus();
			return new ImageIcon(icon);
		} else {
			return getImageIcon(imageName, iconSize, path, 1);
		}
	}
	
	private static boolean isMac()
	{

		String os = System.getProperty("os.name").toLowerCase();
		// Mac
		return (os.indexOf("mac") >= 0);

	}

	public static ImageIcon getImageIcon(String imageName, IconSize iconSize, String path, int scale){
		
		URL url = getImageIconURL(imageName, iconSize, path, scale);

		//if we can't find the image, look for it elsewhere
		if (url == null) { url = getImageIconURL(imageName, iconSize, path, scale); }
		if (url == null && customPath != null) { url = getImageIconURL(imageName, iconSize, customPath, scale); }
		
		
		if (url == null && scale == 1){
			if (!  (imageName == null || "".equals(imageName))  )
			{
				System.out.println("Image not found: " + imageName);
			}
			url = getImageIconURL("notfound", null, path, scale);	
		} else if (url == null) {
			return null;
		}
		
		ImageIcon icon;
		icon = new ImageIcon(url);
		return icon;
		
	}
	
	public static URL getImageIconURL(String imageName, IconSize iconSize, String path, int scale)
	{
		String iconDir = "";

		if (iconSize != null) {
			iconDir = iconSize.size() + "";
			if (scale > 1) {
				iconDir += "@" + scale + "x";
			}
			iconDir += "/";
		}		
		String resname = path + iconDir + imageName + ".png";
		return ImageButton.class.getResource(resname);
		
	}
	
	public static Image getImage(String imageName)
	{
		
		return getImageIcon(imageName, null).getImage();
		
	}
	
}
