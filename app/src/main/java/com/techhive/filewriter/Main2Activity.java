package com.techhive.filewriter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Main2Activity extends AppCompatActivity {

    TextView tv;
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b=getIntent().getExtras();
        p=b.getString("path");
        tv=(TextView)findViewById(R.id.red);
       // tv.setText(read_file(getApplicationContext(),p));
        tv.setText(Jsonred());
    }
    public String Jsonred()
    {
        try {
            String s = read_file(getApplicationContext(), p);
            JSONObject jb = new JSONObject(s);
            return jb.getString("data");
        }catch (Exception e){return e.toString();}
    }
    public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename+".txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return e.toString();
        } catch (UnsupportedEncodingException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }
 public String filenames()
    {
        String s="nunu\n";
        String[] SavedFiles = getApplicationContext().fileList();
        String path = getApplicationContext().getFilesDir().toString();
        File directory = new File(path);
        File fileList[] = directory.listFiles();
        Log.e("test"," "+fileList.length);
        if(fileList.length==0){return "no files";}
        for (int i=0; i < fileList.length; i++)
        {
            s = s+fileList[i].getName().toString()+"\n";
        }
        return s;
    }
}
