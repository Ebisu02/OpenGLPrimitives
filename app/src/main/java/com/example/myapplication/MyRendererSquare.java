package com.example.myapplication;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRendererSquare implements GLSurfaceView.Renderer {
    private final float[] a = new float[] {
            -1, 1, 0,
            -1, -1, 0,
            1, -1, 0,
            1, 1, 0
    };
    FloatBuffer f;
    ByteBuffer b;

    public MyRendererSquare() {
        b = ByteBuffer.allocateDirect(4 * 3 * 4);
        b.order(ByteOrder.nativeOrder());
        f = b.asFloatBuffer();
        f.put(a);
        f.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClearColor(1, 1, 0, 1);
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -1);
        gl10.glScalef(0.5f, 0.25f, 0.5f);

        gl10.glColor4f(0, 1,1,1);
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
