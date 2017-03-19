
/**
 *
 * @Filename lightingParams.java
 *
 * @Version $Id: lightingParams.java,v 1.0 2014/12/11 05:15:00 $
 *
 * @Revisions
 *     Initial Revision
 *
 */



import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*; 

/**
 * Simple class for setting up the viewing and projection transforms
 * for the Shading Assignment.
 * 
 * @author Sumedha Singh
 * 
 */

public class lightingParams
{
    // Add any global class variables you need here.
	private float[] lightpos = { 10.0f, -15.0f, 15.0f, 1.0f };
	private float[] lightcolor = {1.0f, 1.0f, 1.0f, 1.0f };
	private float[] ambient = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float ambientCoefficient = 0.5f;
	
	private float[] diffuse = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float diffuseCoefficient = 0.7f;
	
	private float[] specular = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float specularExponent = 10.0f;
	private float specularCoefficient = 1.0f;
	
	/**
     * constructor
     */
    public lightingParams()
    {
    	
    }
    /**
     * This functions sets up the lighting, material, and shading parameters
     * for the Phong shader.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     * parameter values are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpPhong (int program, GL2 gl2)
    {
    	//parameters to vertex and fragment shader
    	int lightp = gl2.glGetUniformLocation(program, "lightpos");
    	int lightc = gl2.glGetUniformLocation(program, "lightcolor");
    	int ambi = gl2.glGetUniformLocation(program, "ambient");
    	int ambientC = gl2.glGetUniformLocation(program, "ambientCoefficient");
    	int diff = gl2.glGetUniformLocation(program, "diffuse");
    	int diffuseC = gl2.glGetUniformLocation(program, "diffuseCoefficient");
    	int spec = gl2.glGetUniformLocation(program, "specular");
    	int specE = gl2.glGetUniformLocation(program, "specularExponent");
    	int specC = gl2.glGetUniformLocation(program, "specularCoefficient");
    	
    	gl2.glUniform4fv(lightp, 1, lightpos, 0);
    	gl2.glUniform4fv(lightc, 1, lightcolor, 0);
    	gl2.glUniform4fv(ambi, 1, ambient, 0);
    	gl2.glUniform4fv(diff, 1, diffuse, 0);
    	gl2.glUniform4fv(spec, 1, specular, 0);
    	
    	gl2.glUniform1f(ambientC, ambientCoefficient);
    	gl2.glUniform1f(diffuseC, diffuseCoefficient);
    	gl2.glUniform1f(specC, specularCoefficient);
    	gl2.glUniform1f(specE, specularExponent);
    	
    }
}
