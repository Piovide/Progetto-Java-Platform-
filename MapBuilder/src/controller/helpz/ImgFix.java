package controller.helpz;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
	/**
	 * contiene i metodi per modificare le immagini 
	 */
public class ImgFix {
	
	// Ruota l'immagine
	/**
	 * ruota l'immagine in ingresso
	 * @param img
	 * @param rotAngle
	 * @return BufferedImage 
	 */
	public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {

		int w = img.getWidth();
		int h = img.getHeight();

		BufferedImage newImg = new BufferedImage(w, h, img.getType());
		Graphics2D g2d = newImg.createGraphics();

		g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();

		return newImg;

	}

	// Sovrappone 2 immagini
	/*public static BufferedImage buildImg(BufferedImage[] imgs) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();

		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();

		for (BufferedImage img : imgs) {
			g2d.drawImage(img, 0, 0, null);
		}

		g2d.dispose();
		return newImg;

	}*/

	// Ruota l'immagine mantenendo l'immagine di background
	/*public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();

		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();

		for (int i = 0; i < imgs.length; i++) {
			if (rotAtIndex == i)
				g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
			g2d.drawImage(imgs[i], 0, 0, null);
			if (rotAtIndex == i)
				g2d.rotate(Math.toRadians(-rotAngle), w / 2, h / 2);
		}

		g2d.dispose();
		return newImg;

	}*/

	// Ruota l'immagine mantenendo l'animazione
	/*public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();

		BufferedImage[] arr = new BufferedImage[imgs.length];

		for (int i = 0; i < imgs.length; i++) {

			BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
			Graphics2D g2d = newImg.createGraphics();

			g2d.drawImage(imgs[i], 0, 0, null);
			g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
			g2d.drawImage(secondImage, 0, 0, null);
			g2d.dispose();

			arr[i] = newImg;
		}

		return arr;

	}*/
	
	
	
	/*public static BufferedImage[] getTrasparentImage(BufferedImage[] img) {

//        // Ottieni un contesto grafico
//		for(int i = 0; i < img.length; i++) {
//			BufferedImage image = img[i]; 
//	        Graphics2D g2d = image.createGraphics();
//	        // Imposta il colore di sfondo a trasparente 
//	        image = g2d.getDeviceConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), java.awt.Transparency.TRANSLUCENT);
//	        g2d.dispose();
//	        g2d = image.createGraphics();
//	        img[i] = image;
//		}
        return img;
	}*/
	/**
	 * specchia l'immagine
	 * @param img
	 * @return BufferedImage
	 */
	public static BufferedImage getMirroredImage(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        // Crea una trasformazione orizzontale
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-width, 0);

        // Crea un operatore di trasformazione per eseguire la trasformazione
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        // Applicare l'operatore di trasformazione all'immagine per specchiare l'immagine
        BufferedImage mirroredImg = new BufferedImage(width, height, img.getType());
        op.filter(img, mirroredImg);

        return mirroredImg;
    }

}
