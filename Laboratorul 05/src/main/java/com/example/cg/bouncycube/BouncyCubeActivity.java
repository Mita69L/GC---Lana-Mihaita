package com.example.cg.bouncycube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class BouncyCubeActivity extends Activity {

    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glView = new GLSurfaceView(this);
        glView.setRenderer(new CubeRenderer());

        setContentView(glView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }
}