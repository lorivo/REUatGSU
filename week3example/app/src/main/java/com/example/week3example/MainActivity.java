package com.example.week3example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3example.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createfile = (Button)findViewById(R.id.filebutton);

        TextView tv1 = (TextView) findViewById(R.id.t1);
        final String[] filename = new String[1];

        //creating the file using the 1st button
        createfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = new File(MainActivity.this.getFilesDir(), "week3");
                String filename = "text.txt";
                //Checking if directory exists
                if(!dir.exists())
                {
                    dir.mkdir();
                }

                try {

                    File file = new File(dir, filename);
                    //appending data to the same file multiple times on multiple button clicks
                    FileWriter writer = new FileWriter(file, true);
                    writer.append(tv1.getText().toString());
                    writer.append("\n");
                    writer.close();
                    Toast.makeText(MainActivity.this, "Saved your text", Toast.LENGTH_LONG).show();



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}