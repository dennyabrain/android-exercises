package dennymades.space.surfaceviewtext;

import android.view.SurfaceHolder;

/**
 * Created by abrain on 8/20/16.
 */
public class CompoundObjectForASync {
    public static SurfaceHolder mSurfaceHolder;
    public float mTouchX, mTouchY;
    CompoundObjectForASync(SurfaceHolder sh, float touchX, float touchY){
        if(mSurfaceHolder==null){
            mSurfaceHolder = sh;
        }
        mTouchX = touchX;
        mTouchY = touchY;
    }
}





