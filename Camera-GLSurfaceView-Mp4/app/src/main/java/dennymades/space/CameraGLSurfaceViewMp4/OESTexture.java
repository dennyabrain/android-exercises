package dennymades.space.CameraGLSurfaceViewMp4;

/**
 * Created by abrain on 9/8/16.
 */

import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import static android.opengl.GLES20.*;

public class OESTexture {
    private int mTextureHandle;

    public OESTexture() {
        // TODO Auto-generated constructor stub

    }

    public int getTextureId(){
        return mTextureHandle;
    }

    public void init(){
        int[] mTextureHanles = new int[1];
        glGenTextures(1, mTextureHanles, 0);
        mTextureHandle = mTextureHanles[0];
        glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureHandle);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
    }
}
