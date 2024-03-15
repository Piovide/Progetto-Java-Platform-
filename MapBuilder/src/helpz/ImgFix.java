package helpz;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgFix {

	// Rotate
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

	// Img build
	public static BufferedImage buildImg(BufferedImage[] imgs) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();

		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();

		for (BufferedImage img : imgs) {
			g2d.drawImage(img, 0, 0, null);
		}

		g2d.dispose();
		return newImg;

	}

	// Rotate Second img only
	public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
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

	}

	// Rotate Second img only + animation
	public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle) {
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

	}
	// Metodo per modificare l'immagine e incollare sopra un immagine trasparente
		public static BufferedImage getTransparentImageOnBackGround(BufferedImage background, BufferedImage overlay) {
	        // Prende la dimensione massima fra le due
	        int width = Math.max(background.getWidth(), overlay.getWidth());
	        int height = Math.max(background.getHeight(), overlay.getHeight());

	        // Crea un'immagine trasparente con le dimensioni massime delle due immagini
	        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	        // Ottieni un contesto grafico
	        Graphics2D g2d = result.createGraphics();

	        // Disegna l'immagine di background
	        g2d.drawImage(background, 0, 0, null);

	        // Disegna l'immagine overlay
	        g2d.drawImage(overlay, 0, 0, null);

	        // Rilascia le risorse del contesto grafico
	        g2d.dispose();

	        return result;
	    }
	
	// Metodo per modificare l'immagine e incollare sopra un immagine trasparente
	public static BufferedImage getTrasparentImage(BufferedImage img) {

        // Ottieni un contesto grafico
        Graphics2D g2d = img.createGraphics();

        // Imposta il colore di sfondo trasparente 
        img = g2d.getDeviceConfiguration().createCompatibleImage(img.getWidth(), img.getHeight(), java.awt.Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = img.createGraphics();
        
        return img;
	}

}
