package com.example.week3task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3task.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


//week 3 task 2
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make buttons
        Button buttonmake = (Button)findViewById(R.id.button1);

        Button buttonappend = (Button) findViewById(R.id.button2);


        //first button function
        buttonmake.setOnClickListener(view -> {
            //make the edit text the file name
            EditText textfile = findViewById((R.id.text1));
            String filename = textfile.getText().toString();

            //make directory/file
            File dir = new File(MainActivity.this.getFilesDir(),filename);
            if(!dir.exists()){
                dir.mkdir();
            }
            Log.d("successt","File made");
            Toast.makeText(MainActivity.this, "New file created", Toast.LENGTH_LONG).show();
        });

        //second button function
        buttonappend.setOnClickListener(new View.OnClickListener(){
            public FileWriter writer;
            @Override
            public void onClick (View view) {
                EditText textfile = findViewById((R.id.text1));
                String filename = textfile.getText().toString();
                File dir = new File(MainActivity.this.getFilesDir(),filename);

                TextView textappend = (TextView)findViewById(R.id.text2);
                try {
                    File file = new File(dir,filename);
                    writer = new FileWriter(file,true);
                    writer.append(textappend.getText().toString());
                    writer.append("\n");
                    Log.d("successt","appended text");
                    writer.close();

                    //alert notification
                    Toast.makeText(MainActivity.this, "Saved text", Toast.LENGTH_LONG).show();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

}