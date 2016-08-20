package dennymades.space.deprecatedcamoverlayedsurface;

/**
 * Created by abrain on 8/20/16.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.io.IOException;

public class oldCamera {
    private Camera mCamera;
    private String LOG_TAG = "Denny Old Camera";
    private Context sharedContext;
    private Activity mActivity;
    private SurfaceHolder mSurfaceHolder;
    private int CAMERA_FRONT_FACING_ID=0;
    private int CAMERA_FACE_FACING_ID=1;

    public oldCamera(Activity activity, SurfaceHolder sv){
        mActivity = activity;
        mSurfaceHolder = sv;
        sharedContext = activity.getApplicationContext();
    }

    public void openCamera(){
        try{
            mCamera = Camera.open(CAMERA_FACE_FACING_ID);
        }catch(RuntimeException e){
            Log.d(MainActivity.LOG_TAG, "exception", e);
        }
    }

    public void setSurfaceAndStartPreview(){
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            Log.d(MainActivity.LOG_TAG, "exception", e);
        }
        mCamera.startPreview();
    }

    public void test(){
        Camera.Parameters param = mCamera.getParameters();
        String paramString = param.flatten();
        Log.d(MainActivity.LOG_TAG, paramString);
    }

    public void requestPermission(){
        if(ContextCompat.checkSelfPermission(sharedContext, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CAMERA},
                    MainActivity.CAMERA_PERMISSION_CODE);
        }else{
            startCamera();
        }
    }

    public void setCameraDisplayOrientation() {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();

        android.hardware.Camera.getCameraInfo(CAMERA_FACE_FACING_ID, info);
        int rotation = mActivity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }

    public void closeCamera(){
        mCamera.stopPreview();
        mCamera.release();
    }

    public void startCamera(){
        openCamera();
        test();
        setCameraDisplayOrientation();
        setSurfaceAndStartPreview();
    }
}