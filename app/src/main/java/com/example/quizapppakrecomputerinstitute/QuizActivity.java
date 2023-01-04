package com.example.quizapppakrecomputerinstitute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.media.RingtoneManager.*;

public class QuizActivity extends AppCompatActivity {
    TextView timerText;
    Button stopStartButton;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    boolean timerStarted = false;
    MediaPlayer mySong;

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private  static final String KEY_SCORE = "keyScore";
    private  static final String KEY_QUESTION_COUNT = "KeyQuestionCount";
    private  static final String KEY_MILLIS_LEFT = "KeyMillisLeft";
    private  static final String KEY_ANSWERED = "KeyAnswered";
    private  static final String KEY_QUESTION_LIST = "KeyQuestionList";



private TextView textViewQuestion;
private  TextView textViewScore;
private TextView textViewQuestionCount;
private TextView textViewCategory;
private TextView textViewDifficulty;



private TextView textViewCountDown;
private RadioGroup rbGroup;
private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private  long timeleftInMillis;



    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private  Question currentQuestion;
    private int score;
    private boolean answered;
private long backPressedTime;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        timerText =(TextView ) findViewById(R.id.timerText);
        stopStartButton = (Button)findViewById(R.id.startStopButton);
        timer = new Timer();




        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_questions_count);
        textViewDifficulty = findViewById(R.id.text_view_difficulty);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        textViewCategory = findViewById(R.id.text_view_category);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);

        String difficulty = intent.getStringExtra(StartingScreenActivity.EXTRA_DIFFICULTY);
        textViewCategory.setText("Category: " + categoryName);
        textViewDifficulty.setText("Difficulty: " + difficulty);
        if(savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestion(categoryID,difficulty);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        } else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal  = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter -1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeleftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            if(!answered) {

                startCountDown();

            }else{

                updateCountDownText();
                showSolution();


            }
        }

buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(!answered){

            if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){

                checkAnswer();
            }else{
                Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
            }
        }else{

            showNextQuestion();

        }
    }
});
    }
    private void showNextQuestion() {
        final Ringtone r=RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();





        if(questionCounter < questionCountTotal) {

            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
            timeleftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        }else{

            finishQuiz();
        }

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeleftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftInMillis = millisUntilFinished;
                updateCountDownText();



            }

            @Override
            public void onFinish() {
                timeleftInMillis = 0;
                updateCountDownText();
                checkAnswer();



            }
        }.start();
    }


    private  void updateCountDownText() {
        final Ringtone r=RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        int minutes = (int) (timeleftInMillis / 1000) /60;
        int seconds = (int) (timeleftInMillis / 1000) %60;


        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if(timeleftInMillis < 10000) {


            r.play();

            textViewCountDown.setTextColor(Color.RED);

        }


        else{

            textViewCountDown.setTextColor(textColorDefaultCd);

        }

    }
    private  void checkAnswer(){

        answered = true;


        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) +1;
        if(answerNr == currentQuestion.getAnswerNr()) {

            score++;
            textViewScore.setText("Score: " + score);

        }
        showSolution();
    }
    private  void showSolution() {

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNr()) {

            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");

                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;

        }
        if(questionCounter < questionCountTotal ) {

            buttonConfirmNext.setText("Next");
        } else{

            buttonConfirmNext.setText("Finish");
        }

    }
    private  void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();

        }else{
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();


        }
        backPressedTime = System.currentTimeMillis();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {

            countDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeleftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);


    }


    public void startStopTapped(View view) {
        if(timerStarted == false){

            timerStarted =true;
            setButtonUI("STOP", R.color.red);
            startTimer();

        }else{

            timerStarted = false;
            setButtonUI("START", R.color.green);
            timerTask.cancel();


        }

    }

    private void setButtonUI(String start, int color) {
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer() {

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());

                    }
                });



            }


        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText() {

int rounded = (int) Math.round(time);
int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) /60;
        int hours = ((rounded % 86400) / 3600) ;
        return formatTime(seconds,minutes,hours);


    }

    private String formatTime(int seconds, int minutes, int hours) {

        return String.format("%02d",hours) +" : " + String.format("%02d",minutes) +" : " + String.format("%02d",seconds);
    }
}