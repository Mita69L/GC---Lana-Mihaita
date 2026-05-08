package com.example.cg.bouncycube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class CubeRenderer implements GLSurfaceView.Renderer {

    public static final int SS_SUNLIGHT = GL10.GL_LIGHT0;

    private Cube cube;

    private float angle=0;
    private float transY=0;

    public CubeRenderer(){
        cube=new Cube();
    }


    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(
                GL10.GL_COLOR_BUFFER_BIT |
                        GL10.GL_DEPTH_BUFFER_BIT
        );

        gl.glLoadIdentity();

        gl.glTranslatef(
                0f,
                (float)Math.sin(transY),
                -8f
        );


        gl.glRotatef(angle,1,0,0);
        gl.glRotatef(angle,0,1,0);

        cube.draw(gl);

        angle+=0.6f;
        transY+=0.07f;
    }



    @Override
    public void onSurfaceChanged(
            GL10 gl,
            int width,
            int height){

        gl.glViewport(0,0,width,height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float fov=30.0f/57.3f;

        float aspect=
                (float)width/(float)height;

        float zNear=0.1f;
        float zFar=1000;

        float size=
                zNear*
                        (float)Math.tan(fov/2);

        gl.glFrustumf(
                -size,
                size,
                -size/aspect,
                size/aspect,
                zNear,
                zFar
        );

        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }



    @Override
    public void onSurfaceCreated(
            GL10 gl,
            EGLConfig config) {

        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glClearColor(0,0,0,1);

        gl.glDepthMask(false);

        initLighting(gl);
    }



    private void initLighting(GL10 gl){

        float green[]  ={0,1,0,1};
        float red[]    ={1,0,0,1};
        float blue[]   ={0,0,1,1};
        float yellow[] ={1,1,0,1};



        /*
          LIGHT POSITION
         */
        float lightPos[]={
                10.0f,
                0.0f,
                3.0f,
                1.0f
        };

        gl.glLightfv(
                SS_SUNLIGHT,
                GL10.GL_POSITION,
                makeFloatBuffer(lightPos)
        );


        /*
          DIFFUSE
         */
        gl.glLightfv(
                SS_SUNLIGHT,
                GL10.GL_DIFFUSE,
                makeFloatBuffer(green)
        );


        /*
          SPECULAR
         */
        gl.glLightfv(
                SS_SUNLIGHT,
                GL10.GL_SPECULAR,
                makeFloatBuffer(red)
        );


        /*
          AMBIENT
         */
        gl.glLightfv(
                SS_SUNLIGHT,
                GL10.GL_AMBIENT,
                makeFloatBuffer(blue)
        );



        /*
         GLOBAL WORLD AMBIENT
         */
        float worldAmbient[]={
                0.2f,
                0.2f,
                0.2f,
                1.0f
        };

        gl.glLightModelfv(
                GL10.GL_LIGHT_MODEL_AMBIENT,
                makeFloatBuffer(worldAmbient)
        );



        /*
         ATTENUATION
         */
        gl.glLightf(
                SS_SUNLIGHT,
                GL10.GL_LINEAR_ATTENUATION,
                0.025f
        );



        /*
        SPOTLIGHT
         */

        float direction[]={
                -1.0f,
                0.0f,
                -1.0f
        };

        gl.glLightfv(
                SS_SUNLIGHT,
                GL10.GL_SPOT_DIRECTION,
                makeFloatBuffer(direction)
        );


        gl.glLightf(
                SS_SUNLIGHT,
                GL10.GL_SPOT_CUTOFF,
                35.0f
        );


        gl.glLightf(
                SS_SUNLIGHT,
                GL10.GL_SPOT_EXPONENT,
                10.0f
        );



        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(SS_SUNLIGHT);



        /*
           MATERIAL
         */

        gl.glMaterialfv(
                GL10.GL_FRONT_AND_BACK,
                GL10.GL_DIFFUSE,
                makeFloatBuffer(green)
        );


        gl.glMaterialfv(
                GL10.GL_FRONT_AND_BACK,
                GL10.GL_SPECULAR,
                makeFloatBuffer(red)
        );


        gl.glMaterialfv(
                GL10.GL_FRONT_AND_BACK,
                GL10.GL_AMBIENT,
                makeFloatBuffer(blue)
        );



        /*
         EMISSIVE MATERIAL
         cube glows by itself
         */
        gl.glMaterialfv(
                GL10.GL_FRONT_AND_BACK,
                GL10.GL_EMISSION,
                makeFloatBuffer(yellow)
        );



        /*
          SHININESS
         */
        gl.glMaterialf(
                GL10.GL_FRONT_AND_BACK,
                GL10.GL_SHININESS,
                25.0f
        );
    }



    private FloatBuffer makeFloatBuffer(
            float[] array){

        ByteBuffer bb=
                ByteBuffer.allocateDirect(
                        array.length*4
                );

        bb.order(
                ByteOrder.nativeOrder()
        );

        FloatBuffer fb=
                bb.asFloatBuffer();

        fb.put(array);
        fb.position(0);

        return fb;
    }

}