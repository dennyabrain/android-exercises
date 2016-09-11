package dennymades.space.CameraGLSurfaceViewMp4GrafikaBased;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;

import java.io.File;
import java.io.IOException;

/**
 * Created by abrain on 9/9/16.
 */

public class Mp4Encoder extends MediaCodec.Callback {
    private String TAG=this.getClass().getName();

    private MediaCodec mMediaCodec;
    private MediaFormat mMediaFormat;
    private String MIME_TYPE="video/avc";
    private int FRAME_RATE=30;
    private int IFRAME_INTERVAL=5;
    private int DURATION_SEC=10;
    private int bitRate=6000000;
    private Surface inputSurface;

    private MediaRecorder mMediaRecorder;

    private MediaMuxer mMuxer;
    int mTrackIndex;
    boolean mMuxerStarted;

    private static final File OUTPUT_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);


    public Mp4Encoder(SurfaceTexture st){
        try {
            mMediaCodec = MediaCodec.createEncoderByType(MIME_TYPE);
        } catch (IOException e) {
            Log.d(TAG, "IO Exception in creating encoder", e);
        }
        mMediaFormat = MediaFormat.createVideoFormat(MIME_TYPE, 480,640);
        mMediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT,
                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        mMediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
        mMediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_RATE);
        mMediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);

        //inputFromGLSurface = new Surface(st)
        mMediaCodec.setCallback(this);
        mMediaCodec.configure(mMediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);

        inputSurface=mMediaCodec.createInputSurface();

        mMediaCodec.start();

        //output file name
        /*String outputPath = new File(OUTPUT_DIR,"test.mp4").toString();

        try {
            mMuxer = new MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        } catch (IOException ioe) {
            throw new RuntimeException("MediaMuxer creation failed", ioe);
        }

        mTrackIndex = -1;
        mMuxerStarted = false;*/
    }

    public void init(){

    }

    @Override
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        Log.d(TAG,"input buffer available");
    }

    @Override
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        Log.d(TAG,"output buffer available");
    }

    @Override
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException e) {
        Log.d(TAG,"onError ");
    }

    @Override
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        Log.d(TAG,"onOutputFormatChanged");
    }

    public Surface getInputSurface() {
        return inputSurface;
    }
}
