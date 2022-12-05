package com.example.lista3finalfinalfinalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
//                KLUCZ ODPOWIEDZI
//        1. Czy elektron ma ładunek ujemny?                                       Prawda
//        2. Czy światło jest falą elektromagnetyczną?                              Prawda
//        3. Czy energia cieplna jest jednym rodzajem energii?                       Falsz
//        4. Czy cząsteczki wody kropelkowej są mniejsze niż cząsteczki gazowe?       Falsz
//        5. Czy siła odbicia jest przeciwieństwem siły nacisku?                    Falsz
//        6. Czy ruch obrotowy jest zawsze zgodny z kierunkiem ruchu?                 Falsz
//        7. Czy wszystkie ciała mają ten sam moment bezwładności?                      Falsz
//        8. Czy ciągłe zmiany w prędkości są wymagane dla ruchu prostoliniowego?   Falsz
//        9. Czy czas trwania jednego cyklu falowego jest stały?                        Prawda
//        10. Czy impulsy elektromagnetyczne są zgodne z zasadą zachowania energii? Prawda
//        11. Czy dźwięk jest falą mechaniczną?                                     Prawda
//        12. Czy energia kinetyczna jest konserwatywna?                            Falsz
//        13. Czy energia potencjalna jest przenoszona w fali?                       Falsz
//        14. Czy energia jest konserwowana w układzie mechanicznym?                Prawda
//        15. Czy oddziaływanie grawitacyjne jest zależne od odległości?             Prawda
//        16. Czy praca jest jednym rodzajem energii?                                Prawda
//        17. Czy ruch drgający ciągły jest szybszy niż ruch falowy?                 Falsz



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView txtQuestion,txtSprawdzam;
    private Button btnTrue, btnFalse, btnCheat,btnRestart;
    int[] answers = {0,0,0,0,0,0,0,0,0,0};
    int[] base_answers = {1,1,0,0,0,0,0,0,1,1,1,0,0,1,1,1,0};
    String[] questions = {"Pytanie1","Pytanie2","Pytanie3","Pytanie4","Pytanie5","Pytanie6","Pytanie7","Pytanie8","Pytanie9","Pytanie10"};
    String[] base_questions = {"Czy elektron ma ładunek ujemny?","Czy światło jest falą elektromagnetyczną?","Czy energia cieplna jest jedynym rodzajem energii?", "Czy cząsteczki wody kropelkowej są mniejsze niż cząsteczki gazowe?"
            ,"Czy siła odbicia jest przeciwieństwem siły nacisku?","Czy ruch obrotowy jest zawsze zgodny z kierunkiem ruchu?","Czy wszystkie ciała mają ten sam moment bezwładności?",
            "Czy ciągłe zmiany w prędkości są wymagane dla ruchu prostoliniowego?","Czy czas trwania jednego cyklu falowego jest stały?","Czy impulsy elektromagnetyczne są zgodne z zasadą zachowania energii?",
            "Czy dźwięk jest falą mechaniczną?","Czy energia kinetyczna jest konserwatywna?","Czy energia potencjalna jest przenoszona w fali?","Czy energia jest konserwowana w układzie mechanicznym?",
            "Czy oddziaływanie grawitacyjne jest zależne od odległości?","Czy praca jest jednym rodzajem energii?","Czy ruch drgający ciągły jest szybszy niż ruch falowy?"};
    int correct = 0, cheats = 0, question = 0, points = 0;
    boolean end = false ,is_quiz_created=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            points = savedInstanceState.getInt("points", 0);
            correct = savedInstanceState.getInt("correct", 0);
            question = savedInstanceState.getInt("question", 0);
            cheats = savedInstanceState.getInt("cheats", 0);
            answers = savedInstanceState.getIntArray("answers");
            questions = savedInstanceState.getStringArray("questions");
            is_quiz_created = savedInstanceState.getBoolean("isok");

        }
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            points = extras.getInt("points");
            correct = extras.getInt("correct");
            question = extras.getInt("question");
            cheats = extras.getInt("cheats");
            answers = extras.getIntArray("answers");
            questions = extras.getStringArray("questions");
            is_quiz_created = extras.getBoolean("isok");

        }
        init();
        if(!is_quiz_created){
        for (int i=0;i<10;i++){
            int randomIndex = (int)(Math.random()*17);
            questions[i] = base_questions[randomIndex];
            answers[i] = base_answers[randomIndex];
            for(int j=0;j<i;j++){
                if(questions[j]==questions[i]){
                    i--;
                    break;
                }
            }
        }
        is_quiz_created=true;}

        txtSprawdzam.setText("Poprawne: " + correct +  "  Cheats: " + cheats + "Numer pytania: " + question);
        txtQuestion.setText(questions[question]);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstaceStace){
        super.onSaveInstanceState(savedInstaceStace);
        savedInstaceStace.putInt("points",points);
        savedInstaceStace.putInt("correct",correct);
        savedInstaceStace.putInt("question",question);
        savedInstaceStace.putInt("cheats",cheats);
        savedInstaceStace.putStringArray("questions",questions);
        savedInstaceStace.putIntArray("answers",answers);
        savedInstaceStace.putBoolean("isok",is_quiz_created);

    }
    @Override
    public void onClick(View view) {
        if(!end){switch (view.getId()) {
            case R.id.btn_True:
                if (answers[question] == 1) {
                    correct++;
                    points += 10;
                } else {

                }
                question++;
                break;
            case R.id.btn_False:
                if (answers[question] == 0) {
                    correct++;
                    points += 10;
                } else {

                }
                question++;
                break;
            case R.id.btn_Cheat:
                cheats++;
                points -= 15;
                Intent intent = new Intent(MainActivity.this, activity_cheat.class);
                Bundle b = new Bundle();
                b.putInt("answer",answers[question]);
                b.putInt("points",points);
                b.putInt("correct",correct);
                b.putInt("question",question);
                b.putInt("cheats",cheats);
                b.putString("Question",questions[question]);
                b.putIntArray("answers",answers);
                b.putStringArray("questions",questions);
                b.putBoolean("isok",is_quiz_created);
                intent.putExtras(b);
                startActivity(intent);

                break;
        }
    }

                if(question >= 10) {
                    txtQuestion.setText("Koniec pytan");
                    txtSprawdzam.setText("Poprawne: " + correct + "  Cheats: " + cheats+ "Numer pytania: " + question);
                    end = true;
                    end_quiz(view);
                }else{
                    txtQuestion.setText(questions[question]);
                    txtSprawdzam.setText("Poprawne: " + correct + "  Cheats: " + cheats+ "Numer pytania: " + question);
                }



    }

    private void init(){
        Log.d(TAG,"init: Started");
        txtQuestion = findViewById(R.id.txt_Question);
        txtSprawdzam = findViewById(R.id.txt_Sprawdzam);

        btnTrue = findViewById(R.id.btn_True);
        btnFalse = findViewById(R.id.btn_False);
        btnCheat = findViewById(R.id.btn_Cheat);
        btnRestart = findViewById(R.id.btn_Restart);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        btnCheat.setOnClickListener(this);

    }
    public void end_quiz(View view){

    btnCheat.setVisibility(View.GONE);
    btnFalse.setVisibility(View.GONE);
    btnTrue.setVisibility(View.GONE);
    btnRestart.setVisibility(View.VISIBLE);


    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.popup,null);
        TextView result = popupView.findViewById(R.id.txt_popup);
        result.setText("\nWynik: " + points + "\nPoprawne odpowiedzi: " + correct + "\nOszustwa: " + cheats);

    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true;
    final PopupWindow popupWindow = new PopupWindow(popupView,width,height,focusable);
    popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        popupView.setOnTouchListener(new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event){
            popupWindow.dismiss();
            return true;
        }

    });
    }
    public void restart(View view){
        correct = 0; cheats = 0; question = 0; points = 0;
        end = false;
        init();
       txtSprawdzam.setText("Poprawne: " + correct + "  Cheats: " + cheats+ "Numer pytania: " + question);
        txtQuestion.setText(questions[question]);

        btnCheat.setVisibility(View.VISIBLE);
        btnFalse.setVisibility(View.VISIBLE);
        btnTrue.setVisibility(View.VISIBLE);
        btnRestart.setVisibility(View.GONE);

    }

    //Powstrzymuje przed przejsciem do cheat_activity bez zarejestrowania przez system punktów
    @Override
    public void onBackPressed(){}


}