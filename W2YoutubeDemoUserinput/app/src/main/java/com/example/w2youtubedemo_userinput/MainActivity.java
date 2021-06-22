package com.example.w2youtubedemo_userinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleName(View par1){
        //method will be called when button is clicked bc changed onclick attribute to handleName
        //next 2 line gets text from the input
        EditText t = findViewById(R.id.input1);
        String inp = t.getText().toString();
        //
        ((TextView)findViewById(R.id.tv1)).setText(inp);

        //makes alert notification
        Toast.makeText(this, "Alert",Toast.LENGTH_LONG).show();

        Log.d("info",inp);
    }
}