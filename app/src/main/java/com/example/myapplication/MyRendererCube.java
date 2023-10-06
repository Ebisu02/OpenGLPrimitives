package com.example.myapplication;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyRendererCube implements GLSurfaceView.Renderer {
    private final float[] vertices = new float[] {
            // Front face
            -1, 1, 1,
            1, 1, 1,
            1, -1, 1,
            -1, -1, 1,

            // Back face
            -1, 1, -1,
            1, 1, -1,
            1, -1, -1,
            -1, -1, -1,

            // Left face
            -1, 1, -1,
            -1, 1, 1,
            -1, -1, 1,
            -1, -1, -1,

            // Right face
            1, 1, -1,
            1, 1, 1,
            1, -1, 1,
            1, -1, -1,

            // Top face
            -1, 1, -1,
            1, 1, -1,
            1, 1, 1,
            -1, 1, 1,

            // Bottom face
            -1, -1, -1,
            1, -1, -1,
            1, -1, 1,
            -1, -1, 1
    };

    private final int[] indices = new int[] {
            0, 1, 2, 0, 2, 3, // Front face
            4, 6, 5, 4, 7, 6, // Back face
            8, 9, 10, 8, 10, 11, // Left face
            12, 14, 13, 12, 15, 14, // Right face
            16, 17, 18, 16, 18, 19, // Top face
            20, 22, 21, 20, 23, 22 // Bottom face
    };

    private final float[] colors = new float[] {
            // Front face (red)
            1, 0, 0, 1,
            1, 0, 0, 1,
            1, 0, 0, 1,
            1, 0, 0, 1,

            // Back face (green)
            0, 1, 0, 1,
            0, 1, 0, 1,
            0, 1, 0, 1,
            0, 1, 0, 1,

            // Left face (blue)
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,

            // Right face (orange)
            1, 0.5f, 0, 1,
            1, 0.5f, 0, 1,
            1, 0.5f, 0, 1,
            1, 0.5f, 0, 1,

            // Top face (yellow)
            1, 1, 0, 1,
            1, 1, 0, 1,
            1, 1, 0, 1,
            1, 1, 0, 1,

            // Bottom face (purple)
            1, 0, 1, 1,
            1, 0, 1, 1,
            1, 0, 1, 1,
            1, 0, 1, 1
    };

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private FloatBuffer colorBuffer;

    public MyRendererCube() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(new short[]{0, 1, 2, 0, 2, 3, 4, 6, 5, 4, 7, 6, 8, 9, 10, 8, 10, 11, 12, 14, 13, 12, 15, 14, 16, 17, 18, 16, 18, 19, 20, 22, 21, 20, 23, 22}, 0, 36);
        indexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) { gl.glEnable(GL10.GL_DEPTH_TEST); }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 1.0f, 20.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear the screen
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Reset the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        // Set the camera position
        GLU.gluLookAt(gl, 4, 5, 10, 0, 0, 0, 0, 1, 0);

        // Translate the cube
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        // Enable vertex arrays
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Set the vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        // Set the index buffer and draw the cube
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable vertex arrays
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
