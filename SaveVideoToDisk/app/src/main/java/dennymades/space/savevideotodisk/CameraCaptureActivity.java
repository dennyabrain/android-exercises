package dennymades.space.savevideotodisk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaActionSound;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CameraCaptureActivity extends AppCompatActivity{
    MediaCodecList mMedCodList;
    MediaCodecInfo[] mMedCodInfo;
    MediaCodec mEncoder;
    String LOG_TAG = "denny";
    byte[] img;
    MediaCodec.BufferInfo mBufferInfo;
    MediaMuxer mMediaMuxer;
    CodecInputSurface
    final int MY_WRITE_FILE_PERMISSION = 0;
    int mVideoTrackIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);

        //initializing bytebuffer image
        img = new byte[160000];
        for(int i=0;i<400;i++){
            for(int j=0;j<400;j++){
                img[400*i+j]= 0x6;
            }
        }

        mBufferInfo = new MediaCodec.BufferInfo();

        //DO PERMISSION STUFF
        permissionStuff();

        createEncoder();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults){
        switch(requestCode){
            case MY_WRITE_FILE_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(LOG_TAG, "permission granted");

                }
        }
    }

    public void permissionStuff(){
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            //seek permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_WRITE_FILE_PERMISSION);
        }
    }

    public void createEncoder(){
        try {
            mEncoder = MediaCodec.createEncoderByType("video/avc");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

