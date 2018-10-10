package com.wanczy.tmall.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
//要想操作一张图片，首先必须将其从磁盘加载到内存中，然后才能对图片做进一步的处理。
	/*change2jpg
	确保图片文件的二进制格式是jpg
	仅仅通过ImageIO.write(img, "jpg", file);
	不足以保证转换出来的jpg文件显示正常。
	这段转换代码，可以确保转换后jpg的图片显示正常，而不会出现暗红色( 有一定几率出现)。*/
	public static BufferedImage change2jpg(File f) {
		//BufferedImage是Image的一个子类，Image和BufferedImage的主要作用就是将一副图片加载到内存中
		Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());//采用ToolKit读取硬盘上的图片
		PixelGrabber pg = new PixelGrabber(i,0,0,-1,-1,true);// 创建一个 PixelGrabber 对象，以从指定图像将像素矩形部分 (x, y, w, h) 抓取到给定的数组中,PixelGrabber(Image img, int x, int y, int w, int h, boolean forceRGB) 
		try {
			pg.grabPixels();
			//请求 Image 或 ImageProducer 开始传递像素，并等待传递完相关矩形中的所有像素。
			int width = pg.getWidth();
			int height = pg.getHeight();//图片的宽高
			final int[] RGB_MASKS = { 0xFF0000, 0xFF00 , 0xFF };
			final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
	        /**
	         *       ColorModel 抽象类封装了将像素值转换为颜色分量（例如，红色、绿色和蓝色）和 alpha 分量的方法。
	         *       为了将图像呈现到屏幕、打字机或其他图像上，必须将像素值转换为颜色和 alpha 分量。
	         *       与此类方法的参数或返回值一样，可以把像素表示为 32 位 int，或表示为基本类型的数组。
	         *       ColorModel 颜色分量的数量、顺序和解释由其 ColorSpace 指定。
	         *       与未包含 alpha 信息的像素数据一起使用的 ColorModel 将所有像素视为不透明的（alpha 值为 1.0）。 
	                        DirectColorModel(int bits, int rmask, int gmask, int bmask)  根据指定的指示 int 像素表示形式中哪些位包含红色、绿色和蓝色颜色样本的掩码构造 DirectColorModel。
	                        由 getRGBdefault 方法指定的默认 RGB ColorModel 是一个具有以下参数的 DirectColorModel：

	 					位数：        32
						红色掩码：0x00ff0000
						绿色掩码：0x0000ff00
						蓝色掩码：0x000000ff
	         */
			DataBuffer buffer = new DataBufferInt((int[])pg.getPixels(),pg.getHeight() * pg.getWidth());//使用指定数组构造具有单个存储单元且基于整数的 DataBuffer。
		     //DateBuffer用于包装一个或多个数据数组。DataBuffer 中的每个数据数组都可称作存储单元。
			 WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
			/*Raster表示像素矩形数组的类。
			 * Raster 定义了占据特定平面矩形区域的像素值，该区域不一定包括 (0, 0)。
			 * Raster.createPackedRaster(DataBuffer dataBuffer, int w, int h, int scanlineStride, int[] bandMasks, Point location) 
	           创建一个具有指定 DataBuffer、宽度、高度、扫描行跨度和 band 掩码的 Raster。
			 * */
			 BufferedImage img = new BufferedImage(RGB_OPAQUE, raster ,false ,null);
			 return img;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	
	//resizeImage用于改变图片大小
	 public static void resizeImage(File srcFile, int width,int height, File destFile) {
	        try {
	            if(!destFile.getParentFile().exists())
	                destFile.getParentFile().mkdirs();
	            Image i = ImageIO.read(srcFile);
	            i = resizeImage(i, width, height);
	            ImageIO.write((RenderedImage) i, "jpg", destFile);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	      
	    public static Image resizeImage(Image srcImage, int width, int height) {
	        try {
	  
	            BufferedImage buffImg = null;
	            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
	  
	            return buffImg;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }
	  
}
