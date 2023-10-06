package com.example.myapplication.sphere;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {
    boolean zooming = false;
    private int zoom, width, height;
    private MyRendererSphere renderer;
    private long timeOfLastZoom;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        setEGLConfigChooser(true);
        zoom = 4;

        renderer = new MyRendererSphere(zoom);
        setRenderer(renderer);
        timeOfLastZoom = System.currentTimeMillis();
    }

    public MyGLSurfaceView(Context context, int width, int height) {
        super(context);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(true);
        zoom = 6;

        Point size = new Point();
        this.width = width;
        this.height = height;

        renderer = new MyRendererSphere(zoom);
        // Set the renderer to our demo renderer, defined below.
        setRenderer(renderer);
        timeOfLastZoom = System.currentTimeMillis();
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (System.currentTimeMillis() - timeOfLastZoom > 250 && !zooming) {
            if (event.getY() > height / 2 && zoom <= 1 || event.getY() <= height / 2 && zoom >= 9)
                return true;
            queueEvent(new Runnable() {
                @Override
                public void run() {
                    zooming = true;
                    if (event.getY() > height / 2)
                        zoom--;
                    else
                        zoom++;
                    renderer.zoom(zoom);
                    timeOfLastZoom = System.currentTimeMillis();
                    zooming = false;
                }
            });
        }
        return true;
    }
}