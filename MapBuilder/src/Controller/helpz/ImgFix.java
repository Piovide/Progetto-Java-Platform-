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
