package com.example.myapplicationhab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    public static String EXTRA_STR = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button calBtn = (Button) findViewById(R.id.calBtn);
        Button graphBtn = (Button) findViewById(R.id.graphBtn);
        Button idBtn = (Button) findViewById(R.id.idBtn);
        Button graph2Btn = (Button) findViewById(R.id.graph2Btn);
        EditText editText = (EditText) findViewById(R.id.editText);

        idBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                editText.setText("");
                Intent intent = new Intent(HomeActivity.this, CalenderActivity.class);
                ((User) getApplication()).setId(str);
                startActivity(intent);
            }
        });

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });

        graph2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Graph2Activity.class);
                startActivity(intent);
            }
        });

    }
}