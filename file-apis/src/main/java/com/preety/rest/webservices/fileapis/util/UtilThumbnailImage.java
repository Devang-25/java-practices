package com.preety.rest.webservices.fileapis.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UtilThumbnailImage {

	public static void createThumbnail(String imagePath) {

		try {
			Image img = ImageIO.read(new File(imagePath)).getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void createThumbnailImage(String imagePath, String thumbnailImagePath, String imageType) {
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		try {
			img.createGraphics().drawImage(
					ImageIO.read(new File(imagePath))
					.getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
			
			
			ImageIO.write(img, imageType, new File(thumbnailImagePath));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		createThumbnailImage("./images/test_image.jpg","./images/thumb_test_image.jpg", "jpg" );
	}
	
	
	/*
	 * the method described above is rather slow 
	 * use below methods if we want to scale many images
	 */
	private BufferedImage scale(BufferedImage source,double ratio) {
		  int w = (int) (source.getWidth() * ratio);
		  int h = (int) (source.getHeight() * ratio);
		  BufferedImage bi = getCompatibleImage(w, h);
		  Graphics2D g2d = bi.createGraphics();
		  double xScale = (double) w / source.getWidth();
		  double yScale = (double) h / source.getHeight();
		  AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
		  g2d.drawRenderedImage(source, at);
		  g2d.dispose();
		  return bi;
		}
	
	private BufferedImage getCompatibleImage(int w, int h) {
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  GraphicsDevice gd = ge.getDefaultScreenDevice();
		  GraphicsConfiguration gc = gd.getDefaultConfiguration();
		  BufferedImage image = gc.createCompatibleImage(w, h);
		  return image;
		}

}
