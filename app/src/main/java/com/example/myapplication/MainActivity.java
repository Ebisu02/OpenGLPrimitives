package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.example.myapplication.sphere.MyGLSurfaceView;
import com.example.myapplication.sphere.MyRendererSphere;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView mglsfv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawSphere();
    }

    private void drawSquare() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        GLSurfaceView g = new GLSurfaceView(this);
        g.setRenderer(new MyRendererSquare());
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(g);
    }

    private void drawCube() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        GLSurfaceView g = new GLSurfaceView(this);
        g.setRenderer(new MyRendererCube());
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(g);
    }

    private void drawSphere() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mglsfv = new MyGLSurfaceView(this, size.x, size.y);
        setContentView(mglsfv);
    }

    @Override
    protected void onResume() {
        mglsfv.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mglsfv.onPause();
        super.onPause();
    }
}