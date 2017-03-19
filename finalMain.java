/**
 *
 * @Filename finalMain.java
 *
 * @Version $Id: finalMain.java,v 1.0 2014/12/10 05:15:00 $
 *
 * @Revisions
 *     Initial Revision
 *
 */

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.Animator;

/**
 * This class implements Main method, It will execute the project
 * 
 * @author Sumedha Singh
 * 
 */
public class finalMain implements GLEventListener, KeyListener
{

  
    private int vbuffer[][];
    private int ebuffer[][];
    private int numVerts[][];

    /**
     * Animation control
     */
    Animator anime;
    boolean animating;

    /**
     * Initial animation rotation angles
     */
    float angles[];

    /**
     * Current shader type:  flat vs. non-flat
     */
    int currentShader;

    /**
     * Program IDs - current, and all variants
     */
    public int program;
    public int flat;
    public int phong;
    public int gouraud;

    /**
     * Shape info
     */
    shapes myShape;

    /**
     * Lighting information
     */
    lightingParams myPhong;

    /**
     * Viewing information
     */
    viewParams myView;

    
    //texture params
    
    TextureParams myTexture;
    
    /**
     * My canvas
     */
    GLCanvas myCanvas;

    /**
     * Constructor
     */
    public finalMain( GLCanvas G )
    {
    	//number of rows are equal to number of objects
    	vbuffer = new int[9][2];
        ebuffer = new int[9][2];
        numVerts = new int[9][2];

        angles = new float[2];

        animating = false;
        currentShader = shapes.SHADE_NOT_FLAT;

        angles[0] = 0.0f;
        angles[1] = 0.0f;

        myCanvas = G;

        // Initialize lighting and view
        myPhong = new lightingParams();
        myView = new viewParams();
        myTexture = new TextureParams();
        
        // Set up event listeners
        G.addGLEventListener (this);
        G.addKeyListener (this);
    }

    private void errorCheck (GL2 gl2)
    {
        int code = gl2.glGetError();
        if (code == GL.GL_NO_ERROR)
            System.err.println ("All is well");
        else
            System.err.println ("Problem - error code : " + code);

    }


    /**
     * Simple animate function
     */
    public void animate() {
       
    }

    /**
     * Called by the drawable to initiate OpenGL rendering by the client.
     */
    public void display(GLAutoDrawable drawable)
    {
        // get GL
        GL2 gl2 = (drawable.getGL()).getGL2();

        // clear and draw params..
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        // use the correct program
        gl2.glUseProgram( program );

        // set up Phong illumination
        myPhong.setUpPhong( program, gl2 );

        
        // set up viewing and projection parameters
        myView.setUpFrustum( program, gl2 );

        // set up the camera
        myView.setUpCamera( program, gl2,
            2.2f, -2.0f, 2.5f,
            0.0f, 3.0f, -1.0f,
            0.0f, 1.0f, 0.0f
        );
//   earth image
     // set up transformations for earth
        myView.setUpTransforms( program, gl2,
            1.2f, 1.2f, 1.2f,
            0,
            60,
            0,
            -1.0f, 6.0f, -3.0f
        );

        //texture param for earth
        myTexture.textureLoader("earth.jpg");
        myTexture.setUpTextures(program, gl2);

        // draw earth
        selectBuffers( gl2, shapes.OBJ_Earth, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_Earth][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
// Cone
        myTexture.textureLoader("download.png");
        myTexture.setUpTextures(program, gl2);
        

        myView.setUpTransforms( program, gl2,
                0.7f, 0.7f, 0.7f,
                30,
                180,
                40,
                0.7f, 4.90f, -1.05f
            );
       
        selectBuffers( gl2, shapes.OBJ_Cylinder, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_Cylinder][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
       
// Umbrella Handle
        myTexture.textureLoader("black.png");
        myTexture.setUpTextures(program, gl2);
     
        myView.setUpTransforms( program, gl2,
            0.009f, 1.2f, 0.05f,
            30,
            180,
            40,
            -0.2f, 5.0f, -2.0f
        );
        
     
        selectBuffers( gl2, shapes.OBJ_UmbrellaHandle, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_UmbrellaHandle][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );

//        Drop   1
        
        myView.setUpTransforms( program, gl2,
                0.2f, 0.05f, 0.051f,
                0,
                0,
                0,
                1.0f, 7.0f, -2.5f
            );

            //texture param for Drops
            myTexture.textureLoader("drop.jpg");
            myTexture.setUpTextures(program, gl2);

           
            selectBuffers( gl2, shapes.OBJ_SUN, currentShader );
            gl2.glDrawElements( GL.GL_TRIANGLES,
                numVerts[shapes.OBJ_SUN][currentShader],
                GL.GL_UNSIGNED_SHORT, 0l
            );
//   Drop 2
            myView.setUpTransforms( program, gl2,
                    0.2f, 0.05f, 0.051f,
                    0,
                    0,
                    0,
                    1.5f, 8.0f, -2.5f
                );

                //texture param for drop
                myTexture.textureLoader("drop.jpg");
                myTexture.setUpTextures(program, gl2);

                // draw drop
                selectBuffers( gl2, shapes.OBJ_E, currentShader );
                gl2.glDrawElements( GL.GL_TRIANGLES,
                    numVerts[shapes.OBJ_E][currentShader],
                    GL.GL_UNSIGNED_SHORT, 0l
                );
//   drop 3
                myView.setUpTransforms( program, gl2,
                        0.2f, 0.05f, 0.051f,
                        0,
                        0,
                        0,
                        2f, 9.0f, -2.5f
                    );

                    //texture param for drop
                    myTexture.textureLoader("drop.jpg");
                    myTexture.setUpTextures(program, gl2);

                    // draw drop
                    selectBuffers( gl2, shapes.OBJ_E, currentShader );
                    gl2.glDrawElements( GL.GL_TRIANGLES,
                        numVerts[shapes.OBJ_E][currentShader],
                        GL.GL_UNSIGNED_SHORT, 0l
                    );
                    // drop
                    myView.setUpTransforms( program, gl2,
                            0.2f, 0.05f, 0.051f,
                            0,
                            0,
                            0,
                            2.5f, 10.0f, -2.5f
                        );

                        //texture param for drop
                        myTexture.textureLoader("drop.jpg");
                        myTexture.setUpTextures(program, gl2);

                        // draw drop
                        selectBuffers( gl2, shapes.OBJ_E, currentShader );
                        gl2.glDrawElements( GL.GL_TRIANGLES,
                            numVerts[shapes.OBJ_E][currentShader],
                            GL.GL_UNSIGNED_SHORT, 0l
                        );                   
//  Drop 4
                        myView.setUpTransforms( program, gl2,
                                0.2f, 0.05f, 0.051f,
                                0,
                                0,
                                0,
                                2f, 6.0f, -2.5f
                            );

                            //texture param for drop
                            myTexture.textureLoader("drop.jpg");
                            myTexture.setUpTextures(program, gl2);

                            // draw drop
                            selectBuffers( gl2, shapes.OBJ_E, currentShader );
                            gl2.glDrawElements( GL.GL_TRIANGLES,
                                numVerts[shapes.OBJ_E][currentShader],
                                GL.GL_UNSIGNED_SHORT, 0l
                            );
                            //drop
                            myView.setUpTransforms( program, gl2,
                                    0.2f, 0.05f, 0.051f,
                                    0,
                                    0,
                                    0,
                                    1.0f, 5.5f, -2.5f
                                );

                                //texture param for drop
                                myTexture.textureLoader("drop.jpg");
                                myTexture.setUpTextures(program, gl2);

                                // draw drop
                                selectBuffers( gl2, shapes.OBJ_E, currentShader );
                                gl2.glDrawElements( GL.GL_TRIANGLES,
                                    numVerts[shapes.OBJ_E][currentShader],
                                    GL.GL_UNSIGNED_SHORT, 0l
                                );
                            
                            
                            
                            
                            //drop
                                
                                myView.setUpTransforms( program, gl2,
                                        0.2f, 0.05f, 0.051f,
                                        0,
                                        0,
                                        0,
                                        1.5f, 6.5f, -2.5f
                                    );

                                    //texture param for drops
                                    myTexture.textureLoader("drop.jpg");
                                    myTexture.setUpTextures(program, gl2);

                                    // draw drop
                                    selectBuffers( gl2, shapes.OBJ_E, currentShader );
                                    gl2.glDrawElements( GL.GL_TRIANGLES,
                                        numVerts[shapes.OBJ_E][currentShader],
                                        GL.GL_UNSIGNED_SHORT, 0l
                                    );
                                
                                
                                
                                
                                
                            // drop
                            myView.setUpTransforms( program, gl2,
                                    0.2f, 0.05f, 0.051f,
                                    0,
                                    0,
                                    0,
                                    2.5f, 7.0f, -2.5f
                                );

                                //texture param for drops
                                myTexture.textureLoader("drop.jpg");
                                myTexture.setUpTextures(program, gl2);

                                // draw drops
                                selectBuffers( gl2, shapes.OBJ_E, currentShader );
                                gl2.glDrawElements( GL.GL_TRIANGLES,
                                    numVerts[shapes.OBJ_E][currentShader],
                                    GL.GL_UNSIGNED_SHORT, 0l
                                );                   
                    
                    
                    
            
            
       ////////////////// //texture param for earth
        myTexture.textureLoader("earth.jpg");
        myTexture.setUpTextures(program, gl2);
        
        // set up transformations for earth
        myView.setUpTransforms( program, gl2,
            1.1f, 1.1f, 1.1f,
            30,
            20,
            20,
            0.0f, 0.0f, 0.0f
        );
// Background
     
        
        myTexture.textureLoader("background.jpg");
        myTexture.setUpTextures(program, gl2);
        
        // set up transformations
        myView.setUpTransforms( program, gl2,
            20.0f, 20.0f, 20.0f,
            30,
            180,
            40,
            -6.0f, 18.0f, -12.0f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_BACKGROUND, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_BACKGROUND][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );

        
        if( animating ) {
            animate();
        }
    }

    /**
     * Notifies the listener to perform the release of all OpenGL
     * resources per GLContext, such as memory buffers and GLSL
     * programs.
     */
    public void dispose(GLAutoDrawable drawable)
    {
    }

    /**
     * Verify shader creation
     */
    private void checkShaderError( shaderSetup myShaders, int program,
        String which )
    {
        if( program == 0 ) {
            System.err.println( "Error setting " + which +
                " shader - " +
                myShaders.errorString(myShaders.shaderErrorCode)
            );
            System.exit( 1 );
        }
    }

    /**
     * Called by the drawable immediately after the OpenGL context is
     * initialized.
     */
    public void init(GLAutoDrawable drawable)
    {
        // get the gl object
        GL2 gl2 = drawable.getGL().getGL2();

        // create the Animator now that we have the drawable
        anime = new Animator( drawable );

        // Load shaders, verifying each
        shaderSetup myShaders = new shaderSetup();

        flat = myShaders.readAndCompile( gl2, "vshader.glsl",
            "fshader.glsl");
        checkShaderError( myShaders, flat, "flat" );

        gouraud = myShaders.readAndCompile( gl2, "vshader.glsl",
            "fshader.glsl");
        checkShaderError( myShaders, gouraud, "gouraud" );

        phong = myShaders.readAndCompile( gl2, "vshader.glsl",
            "fshader.glsl");
        checkShaderError( myShaders, phong, "phong" );

        // Default shader program
        program = phong;
        
        // Create shape method for all objects
        createShape( gl2, shapes.OBJ_E, 1 );
        createShape( gl2, shapes.OBJ_SUN, 1 );
        createShape( gl2, shapes.OBJ_SAT, 1 );
        createShape(gl2, shapes.OBJ_BACKGROUND, 1);
        createShape(gl2, shapes.OBJ_Earth, 1);
        createShape(gl2, shapes.OBJ_UmbrellaHandle, 1);
        createShape(gl2, shapes.OBJ_Cylinder, 1);
        
        // Other GL initialization
        gl2.glEnable( GL.GL_DEPTH_TEST );
        gl2.glEnable( GL.GL_CULL_FACE );
        gl2.glCullFace(  GL.GL_BACK );
        gl2.glFrontFace( GL.GL_CCW );
        gl2.glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
        gl2.glDepthFunc( GL.GL_LEQUAL );
        gl2.glClearDepth( 1.0f );
    }


    /**
     * Called by the drawable during the first repaint after the component
     * has been resized.
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                     int height)
    {
    }


    /**
     * Create vertex and element buffers for a shape
     */
    public void createShape(GL2 gl2, int obj, int flat )
    {
        // clear the old shape
        myShape = new shapes();

        // make the shape
        myShape.makeShape( obj, flat );

        // save the vertex count
        numVerts[obj][flat] = myShape.nVertices();

        // get the vertices
        Buffer points = myShape.getVertices();
        long dataSize = myShape.nVertices() * 4l * 4l;

        // get the normals
        Buffer normals = myShape.getNormals();
        long ndataSize = myShape.nVertices() * 3l * 4l;

        // get the element data
        Buffer elements = myShape.getElements();
        long edataSize = myShape.nVertices() * 2l;

        long tdataSize = myShape.nVertices() * 2l * 4l;
        Buffer texCoords = myShape.getUV();
        
        // generate the vertex buffer
        int bf[] = new int[1];

        gl2.glGenBuffers( 1, bf, 0 );
        vbuffer[obj][flat] = bf[0];
        gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
        gl2.glBufferData( GL.GL_ARRAY_BUFFER, dataSize + ndataSize+tdataSize, null,
        GL.GL_STATIC_DRAW );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, points );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, ndataSize,
        normals );
        
        //texture
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize+ndataSize, tdataSize,
                texCoords );
        
        // generate the element buffer
        gl2.glGenBuffers (1, bf, 0);
        ebuffer[obj][flat] = bf[0];
        gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );
        gl2.glBufferData( GL.GL_ELEMENT_ARRAY_BUFFER, edataSize, elements,
            GL.GL_STATIC_DRAW );

    }

    /**
     * Bind the correct vertex and element buffers
     *
     * Assumes the correct shader program has already been enabled
     */
    private void selectBuffers( GL2 gl2, int obj, int flat )
    {
        // bind the buffers
        gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
        gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );

        // calculate the number of bytes of vertex data
        long dataSize = numVerts[obj][flat] * 4l * 4l;
        
        //texture size
        
        long ndataSize = numVerts[obj][flat] * 3l * 4l;
        
        // set up the vertex attribute variables
        int vPosition = gl2.glGetAttribLocation( program, "vPosition" );
        gl2.glEnableVertexAttribArray( vPosition );
        gl2.glVertexAttribPointer( vPosition, 4, GL.GL_FLOAT, false,
                                       0, 0l );
        int vNormal = gl2.glGetAttribLocation( program, "vNormal" );
        gl2.glEnableVertexAttribArray( vNormal );
        gl2.glVertexAttribPointer( vNormal, 3, GL.GL_FLOAT, false,
                                   0, dataSize );
        
        int vTex = gl2.glGetAttribLocation(program, "vTexCoord");
        gl2.glEnableVertexAttribArray(vTex);
        gl2.glVertexAttribPointer(vTex, 2, GL.GL_FLOAT, false, 0, dataSize + ndataSize);

    }

    /**
     * Because I am a Key Listener...we'll only respond to key presses
     */
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();

        // Respond appropriately
        switch( key ) {

            case '1':    // flat shading
                program = phong;
                currentShader = shapes.SHADE_NOT_FLAT;
                break;

            case '2':    // Gouraud shading
                program = gouraud;
                currentShader = shapes.SHADE_NOT_FLAT;
                break;

            case '3':    // phong shading
                program = phong;
                currentShader = shapes.SHADE_NOT_FLAT;
                break;

            case 'a':    // animate
                animating = true;
                anime.start();
                break;

            case 's':    // stop animating
                animating = false;
                anime.stop();
                break;

            case 'q': case 'Q':
                System.exit( 0 );
                break;
        }

        // do a redraw
        myCanvas.display();
    }


    /**
     * main program
     */
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        // create your tessMain
        finalMain myMain = new finalMain(canvas);


        Frame frame = new Frame("CG - Final 3D Scene Project");
        frame.setSize(1000, 800);
        frame.add(canvas);
        frame.setVisible(true);

       
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
