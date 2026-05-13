package com.example.cg.bouncycube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;

public class CubeRenderer implements GLSurfaceView.Renderer {

    Cube cube = new Cube();

    float angle = 0;
    float t = 0;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glClearColor(0,0,0,1);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {

        gl.glViewport(0,0,w,h);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float r = (float)w/h;
        gl.glFrustumf(-r,r,-1,1,1,100);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0,(float)Math.sin(t),-8);

        gl.glRotatef(angle,1,1,0);

        cube.draw(gl);

        angle += 0.5f;
        t += 0.05f;
    }
}