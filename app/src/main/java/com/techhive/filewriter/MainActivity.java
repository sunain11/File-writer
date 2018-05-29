package com.techhive.filewriter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    EditText ed, hd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        ed = (EditText) findViewById(R.id.edt);
        hd = (EditText) findViewById(R.id.txt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //writeToFile(ed.getText().toString());
                Jsonwrit(ed.getText().toString());
            }
        });
    }
    public void Jsonred()
    {
        try {
            String s = read_file(getApplicationContext(),"datanew1.txt");
            JSONObject jb = new JSONObject(s);
            //Jsonwrit(jb);
        }catch (Exception e){Toast.makeText(this,"error:"+e.toString(),Toast.LENGTH_LONG).show();}
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
    public void Jsonwrit(String d)
    {
        try
        {
            JSONObject jb=new JSONObject(d);
            jb.put("data", ed.getText().toString());
            writeToFile(jb.toString());
        }catch (Exception e)
        {
            Toast.makeText(this,"error: "+e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    public void writeToFile(String data) {
        try {
            //FileOutputStream fou = openFileOutput("datanew1.txt", MODE_APPEND);
            FileOutputStream fou = openFileOutput("datanew3.txt",MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            String p="datanew3";
            Intent i= new Intent(this,Main2Activity.class);
            i.putExtra("path",p);
            startActivity(i);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            Toast.makeText(this,"error: "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            String p=gpxfile.getName().toString();
            Intent i= new Intent(context,Main2Activity.class);
            i.putExtra("path",p);
            startActivity(i);
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
