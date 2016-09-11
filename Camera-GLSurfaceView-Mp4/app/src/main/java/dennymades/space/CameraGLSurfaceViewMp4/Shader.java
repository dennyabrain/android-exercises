package dennymades.space.CameraGLSurfaceViewMp4;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import static android.opengl.GLES20.*;
/**
 * Created by abrain on 9/8/16.
 */

public class Shader {
    private String TAG = "SHADER : ";

    private int mProgram=0;
    private int mVertexShader=0;
    private int mFragmentShader=0;

    private String vertexSource;
    private String fragmentSource;

    private final HashMap<String, Integer> mShaderHandleMap = new HashMap<String, Integer>();

    public Shader() {
        // TODO Auto-generated constructor stub
    }

    public void setProgram(Context context, int vertexShader, int fragmentShader) throws Exception{
        try {
            vertexSource = loadRawString(context, vertexShader);
            fragmentSource = loadRawString(context, fragmentShader);
        } catch (Exception e) {
            Log.d(TAG, "IO Exception while loading shaders from string", e);
        }

        try {
            mVertexShader = loadShader(GL_VERTEX_SHADER, vertexSource);
            mFragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentSource);
        } catch (Exception e) {
            Log.d(TAG, "Exception Loading shaders ", e);
        }

        int program = glCreateProgram();
        if(program!=0){
            glAttachShader(program, mVertexShader);
            glAttachShader(program, mFragmentShader);
            glLinkProgram(program);
            int[] linkstatus = new int[1];
            glGetProgramiv(program, GL_LINK_STATUS, linkstatus,0);
            if(linkstatus[0]!=GL_TRUE){
                String error = glGetProgramInfoLog(program);
                glDeleteProgram(program);
                throw new Exception(error);
            }

        }
        mProgram = program;
        mShaderHandleMap.clear();
    }

    public void useProgram(){
        glUseProgram(mProgram);
    }

    public void deleteProgram(){
        glDeleteShader(mVertexShader);
        glDeleteShader(mFragmentShader);
        glDeleteProgram(mProgram);
        mProgram = mVertexShader = mFragmentShader = 0;
    }

    public int programHandle(){
        return mProgram;
    }

    public int getHandle(String name){
        if(mShaderHandleMap.containsKey(name)){
            return mShaderHandleMap.get(name);
        }

        int handle = glGetAttribLocation(mProgram, name);
        if(handle == -1){
            handle = glGetUniformLocation(mProgram, name);
        }
        if(handle == -1){
            Log.d("GLSL shader", "Could not get attrib location for " + name);
        }else{
            mShaderHandleMap.put(name, handle);
        }

        return handle;
    }

    public int[] getHandles(String... names){
        int[] res = new int[names.length];
        for(int i = 0; i < names.length; ++i){
            res[i] = getHandle(names[i]);
        }

        return res;
    }

    private int loadShader(int type, String shaderSource) throws Exception{
        int shader = glCreateShader(type);
        if(shader!=0){
            glShaderSource(shader, shaderSource);
            glCompileShader(shader);
            int[] compiled = new int[1];
            glGetShaderiv(shader, GL_COMPILE_STATUS, compiled, 0);
            if(compiled[0]==0){
                String error = glGetShaderInfoLog(shader);
                glDeleteShader(shader);
                throw new Exception(error);
            }
        }
        return shader;
    }

    private String loadRawString(Context context,int rawID) throws Exception{
        StringBuilder shader = new StringBuilder();
        InputStream is = context.getResources().openRawResource(rawID);
        InputStreamReader isReader = new InputStreamReader(is);
        BufferedReader bfrdReader = new BufferedReader(isReader);
        String nextLine;
        while((nextLine = bfrdReader.readLine())!=null){
            shader.append(nextLine);
            shader.append('\n');
        }
        return shader.toString();
    }
}
