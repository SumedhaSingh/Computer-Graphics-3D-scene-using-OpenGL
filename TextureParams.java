
/**
 *
 * @Filename TextureParams.java
 *
 * @Version $Id: TextureParams.java,v 1.0 2014/12/11 05:15:00 $
 *
 * @Revisions
 *     Initial Revision
 *
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * This class implements Texture to objects
 * 
 * @author Sumedha Singh
 * 
 */
public class TextureParams {
	
	BufferedImage image;
	IntBuffer intBuffer;
	
	public TextureParams() {
		
	}
	
	public void textureLoader(String filename) {
		File file = new File(filename);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int pixels[] = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		intBuffer = IntBuffer.wrap(pixels);
	}
	
	public void setUpTextures(int program, GL2 gl2) {
		gl2.glUseProgram(program);		
		int location = gl2.glGetUniformLocation(program, "myTexture");
		gl2.glUniform1i(location, 0);
		

		
		gl2.glGenTextures(location, intBuffer);
		gl2.glBindTexture(GL.GL_TEXTURE_2D, location);
		
		
		gl2.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER,
				GL.GL_LINEAR);
		gl2.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
				GL.GL_LINEAR);
		gl2.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, image.getWidth(),
				image.getHeight(), 0, GL.GL_BGRA, GL.GL_UNSIGNED_BYTE, intBuffer);
		gl2.glActiveTexture(GL.GL_TEXTURE0 + 0);
	}
}
