package util;

import android.hardware.Camera;
/**
 * Created by abrain on 9/1/16.
 */
public class MyCamera {
    public static Camera mNativeCamera;
    private static int mID;

    public MyCamera(int id){
        mID = id;
    }

    public void setup(){
        //open camera
        open();

        //creating a preview class


        //placing preview in layout

        //start preview
    }

    public void open(){
        mNativeCamera = Camera.open(mID);
    }

    public Camera getNativeCamera(){
        return mNativeCamera;
    }
}
