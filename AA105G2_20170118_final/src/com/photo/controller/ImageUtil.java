package com.photo.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static byte[] shrink(byte[] srcImageData, int scaleSize) {
		ByteArrayInputStream bais = new ByteArrayInputStream(srcImageData);
//		System.out.println("baisbaisbaisbaisbaisbaisbaisbais"+bais);

		int sampleSize = 1;
		int imageWidth = 0;
		int imageHeight = 0;

		if (scaleSize <= 1) {
			return srcImageData;
		}

		try {
			BufferedImage srcBufferedImage = ImageIO.read(bais);
//			System.out.println("srcBufferedImagesrcBufferedImagesrcBufferedImage"+srcBufferedImage);
			int type = srcBufferedImage.getType();
	
			String format = "";
			if (type == BufferedImage.TYPE_4BYTE_ABGR || type == BufferedImage.TYPE_4BYTE_ABGR_PRE
					|| type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_ARGB_PRE) {
				format = "png";
			} else {
				format = "jpg";
			}
				imageWidth = srcBufferedImage.getWidth();
			imageHeight = srcBufferedImage.getHeight();
			if (imageWidth == 0 || imageHeight == 0) {
				return srcImageData;
			}
			int longer = Math.max(imageWidth, imageHeight);
			if (longer > scaleSize) {
				sampleSize = longer / scaleSize;
				imageWidth = srcBufferedImage.getWidth() / sampleSize;
				imageHeight = srcBufferedImage.getHeight() / sampleSize;
			}

			BufferedImage scaledBufferedImage = new BufferedImage(imageWidth, imageHeight, type);
			Graphics graphics = scaledBufferedImage.createGraphics();
			graphics.drawImage(srcBufferedImage, 0, 0, imageWidth, imageHeight, null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledBufferedImage, format, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return srcImageData;
		}
	}
}
