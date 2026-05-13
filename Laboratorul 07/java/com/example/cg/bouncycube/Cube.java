package com.example.cg.bouncycube;

import java.nio.*;
import javax.microedition.khronos.opengles.GL10;

public class Cube {

    private FloatBuffer vb;
    private FloatBuffer cb;
    private ByteBuffer ib;

    public Cube() {

        float v[] = {
                -1,1,1,  1,1,1,  1,-1,1,  -1,-1,1,
                -1,1,-1, 1,1,-1, 1,-1,-1, -1,-1,-1
        };

        float c[] = {
                0,0,1,0.5f,
                0,0,1,0.5f,
                0,0,1,0.5f,
                0,0,1,0.5f,

                1,0,0,0.5f,
                1,0,0,0.5f,
                1,0,0,0.5f,
                1,0,0,0.5f
        };

        byte i[] = {
                0,1,2, 0,2,3,
                4,5,6, 4,6,7,
                0,4,7, 0,7,3,
                1,5,6, 1,6,2,
                0,1,5, 0,5,4,
                3,2,6, 3,6,7
        };

        vb = ByteBuffer.allocateDirect(v.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vb.put(v).position(0);

        cb = ByteBuffer.allocateDirect(c.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        cb.put(c).position(0);

        ib = ByteBuffer.allocateDirect(i.length);
        ib.put(i).position(0);
    }

    public void draw(GL10 gl) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vb);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, cb);

        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, ib);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}