package com.example.helloworld;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new HelloWorldRenderer());
        setContentView(view);
    }
}