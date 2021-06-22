package com.example.week3task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


//week 3 task 1
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make the edit text the file name
                EditText namefile = findViewById((R.id.textfile));
                String filename = namefile.getText().toString();

                //make directory/file
                File dir = new File(MainActivity.this.getFilesDir(),filename);
                if(!dir.exists()){
                    dir.mkdir();
                }

                TextView textappend = (TextView)findViewById(R.id.textin);
                FileWriter writer;
                try {
                    File file = new File(dir,filename);
                    writer = new FileWriter(file,false);
                    writer.write(textappend.getText().toString());
                    writer.write("\n");
                    Log.d("successt","wrote text");
                    writer.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Log.d("successt","File made");
                Toast.makeText(MainActivity.this, "File made", Toast.LENGTH_LONG).show();
            }
        });

    }
}