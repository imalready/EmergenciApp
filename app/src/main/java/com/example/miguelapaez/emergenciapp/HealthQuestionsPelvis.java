package com.example.miguelapaez.emergenciapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HealthQuestionsPelvis extends AppCompatActivity {

    private String latUser;
    private String lonUser;
    private String email;
    //private String answer1;

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_questions_pelvis);
        getSupportActionBar().hide();

        latUser = getIntent().getStringExtra("latitud");
        lonUser = getIntent().getStringExtra("longitud");
        email = getIntent().getStringExtra("email");
        //answer1 =  getIntent().getStringExtra("answer1");

        mainGrid = (GridLayout) findViewById(R.id.gridLayoutQuestionPelvis);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            LinearLayout cardView = (LinearLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI == 0){
                        Intent intent = new Intent ( HealthQuestionsPelvis.this, MedicalCenters.class);
                        ArrayList<String> especialidades = new ArrayList<>();
                        especialidades.add("gastroenterología");
                        especialidades.add("nefrología");
                        intent.putStringArrayListExtra("especialidades",especialidades);
                        intent.putExtra("info","This is activity from card item index  "+finalI);
                        //intent.putExtra("answer1",answer1+"/AbdomenInferior");
                        intent.putExtra("latitud",latUser);
                        intent.putExtra("longitud",lonUser);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }
                    if(finalI == 1){
                        Intent intent = new Intent ( HealthQuestionsPelvis.this, MedicalCenters.class);
                        ArrayList<String> especialidades = new ArrayList<>();
                        especialidades.add("gastroenterología");
                        especialidades.add("urología");
                        especialidades.add("nefrología");
                        intent.putStringArrayListExtra("especialidades",especialidades);
                        intent.putExtra("info","This is activity from card item index  "+finalI);
                        //intent.putExtra("answer1",answer1+"/pichirilo");
                        intent.putExtra("latitud",latUser);
                        intent.putExtra("longitud",lonUser);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }

                }
            });
        }
    }
}
