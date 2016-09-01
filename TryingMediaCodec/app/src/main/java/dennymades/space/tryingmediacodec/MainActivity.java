package dennymades.space.tryingmediacodec;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaExtractor extractor;
    String inputFilePath;
    String LOG_TAG="Denny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extractor = new MediaExtractor();

        inputFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath().toString()+"/snap-test.mp4";

        //Check if file exists
        /*File f = new File(inputFilePath);
        if(f.exists()){
            Log.d(LOG_TAG, "file exists");
        }else{
            Log.d(LOG_TAG, "file does not exist");
        }*/

        try {
            extractor.setDataSource(inputFilePath);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IO Exception", e);
        }

        for(int i=0;i<extractor.getTrackCount();i++){
            MediaFormat format = extractor.getTrackFormat(i);
            Log.d(LOG_TAG, "track "+i+"'s format is "+format.KEY_MIME);
        }
    }
}
