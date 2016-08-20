package dennymades.space.deprecatedcamoverlayedsurface;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    public static String LOG_TAG = "Denny Old Camera";
    public static final int CAMERA_PERMISSION_CODE=1;
    private static oldCamera mOldCamera;
    private static SurfaceHolder svHolder;

    private SurfaceView sv;
    private TextSurfaceView tsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv = (SurfaceView) findViewById(R.id.surfaceView);
        svHolder = sv.getHolder();
        svHolder.addCallback(this);

        tsv = (TextSurfaceView) findViewById(R.id.textSurfaceView);
        tsv.setZOrderOnTop(true);
        SurfaceHolder temp = tsv.getHolder();
        temp.setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults){
        switch(requestCode){
            case CAMERA_PERMISSION_CODE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.d(LOG_TAG, "camera permission granted");
                    Toast.makeText(this, "camera permitted", Toast.LENGTH_SHORT).show();
                    mOldCamera.startCamera();
                }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mOldCamera = new oldCamera(this, surfaceHolder);
        mOldCamera.requestPermission();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mOldCamera.closeCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    );}
    }
}