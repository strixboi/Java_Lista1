package com.example.lista3finalfinalfinalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity_cheat extends AppCompatActivity {


    private Button btn_search;
    String query = "jaki jest sens zycia";
    TextView txt_view,txt_view2;
    int correct = 0, cheats = 0, question = 0, points = 0, ans = 0;
    int anss[] = {};
    String questions[] ={};
    Boolean isok = true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        init();
        Bundle b = getIntent().getExtras();
        query = b.getString("Question");
        txt_view.setText(query);
        points = b.getInt("points");
        correct = b.getInt("correct");
        question = b.getInt("question");
        cheats = b.getInt("cheats");
        ans = b.getInt("answer");
        anss = b.getIntArray("answers");
        questions = b.getStringArray("questions");
        isok = b.getBoolean("isok");

        if(ans==1)
        {txt_view2.setText("TAK");}
        else {
            txt_view2.setText("NIE");
        }

    }


    private void init(){
        btn_search = findViewById(R.id.btn_cheat_search);
        txt_view = findViewById(R.id.txt_cheat_view);
        txt_view2 = findViewById(R.id.txt_cheat_view2);
    }

    public void back(View view){
        Intent intent = new Intent(activity_cheat.this,MainActivity.class);
        intent.putExtra("points",points);
        intent.putExtra("correct",correct);
        intent.putExtra("question",question);
        intent.putExtra("cheats",cheats);
        intent.putExtra("Question",query);
        intent.putExtra("answers",anss);
        intent.putExtra("questions",questions);
        intent.putExtra("isok",isok);
        startActivity(intent);
    }


    public void search(View view){

        try{
        Intent search_in_web = new Intent(Intent.ACTION_WEB_SEARCH);
        search_in_web.putExtra(SearchManager.QUERY,query);
        startActivity(search_in_web);
        } catch (ActivityNotFoundException e){
            e.printStackTrace();
            searchNetCompat();
        }

    }
    private void searchNetCompat(){
        try{
            Uri uri = Uri.parse("http://www.google.com/#q="+query);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch(ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed(){
    }

}
