package com.ritikaneema.example.quizzeteria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public int questions[] = {R.string.ques1, R.string.ques2, R.string.ques3, R.string.ques4,
            R.string.ques5, R.string.ques6, R.string.ques7, R.string.ques8, R.string.ques9, R.string.ques10};
    public int options[] = {R.string.optn_1a, R.string.optn_1b, R.string.optn_1c, R.string.optn_1d,
            R.string.optn_2a, R.string.optn_2b, R.string.optn_2c, R.string.optn_2d,
            R.string.optn_3a, R.string.optn_3b, R.string.optn_3c, R.string.optn_3d,
            R.string.optn_4a, R.string.optn_4b, R.string.optn_4c, R.string.optn_4d,
            R.string.optn_5a, R.string.optn_5b, R.string.optn_5c, R.string.optn_5d,
            R.string.optn_6a, R.string.optn_6b, R.string.optn_6c, R.string.optn_6d,
            R.string.optn_7a, R.string.optn_7b, R.string.optn_7c, R.string.optn_7d,
            R.string.optn_8a, R.string.optn_8b, R.string.optn_8c, R.string.optn_8d,
            R.string.optn_9a, R.string.optn_9b, R.string.optn_9c, R.string.optn_9d};
    public RadioGroup radioGroupOptions;
    public Button submitButton;
    public RadioButton radioButtonOptnA, radioButtonOptnB, radioButtonOptnC, radioButtonOptnD;
    public CheckBox checkBoxA, checkBoxB, checkBoxC, checkBoxD;
    EditText editTextAnswer;
    TextView tvQuestion, textViewScore, textViewQuesNo;
    LinearLayout editTextLayout, checkBoxLayout;
    private int quesTracker = 0, optionTracker = -1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.textViewQuestion);

        radioGroupOptions = findViewById(R.id.radioGroup);
        radioButtonOptnA = findViewById(R.id.options_a);
        radioButtonOptnB = findViewById(R.id.options_b);
        radioButtonOptnC = findViewById(R.id.options_c);
        radioButtonOptnD = findViewById(R.id.options_d);

        checkBoxLayout = findViewById(R.id.checkboxLayout);
        checkBoxA = findViewById(R.id.checkboxA);
        checkBoxB = findViewById(R.id.checkboxB);
        checkBoxC = findViewById(R.id.checkboxC);
        checkBoxD = findViewById(R.id.checkboxD);

        editTextLayout = findViewById(R.id.editTextLayout);
        editTextAnswer = findViewById(R.id.editTextAnswer);

        submitButton = findViewById(R.id.buttonSubmit);

        textViewScore = findViewById(R.id.textViewScore);
        textViewQuesNo = findViewById(R.id.textViewQuesNo);

        setQuestion(quesTracker);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (quesTracker) {
                    case 0:
                        if (radioButtonOptnC.isChecked())
                            score += 1;
                        break;
                    case 1:
                        if (radioButtonOptnD.isChecked())
                            score += 1;
                        break;
                    case 2:
                        if (radioButtonOptnB.isChecked())
                            score += 1;
                        break;
                    case 3:
                        if (radioButtonOptnC.isChecked())
                            score += 1;
                        break;
                    case 4:
                        if (radioButtonOptnC.isChecked())
                            score += 1;
                        break;
                    case 5:
                        if (radioButtonOptnA.isChecked())
                            score += 1;
                        break;
                    case 6:
                        if (radioButtonOptnD.isChecked())
                            score += 1;
                        break;
                    case 7: {
                        if (radioButtonOptnB.isChecked()) {
                            score += 1;
                        }
                        radioGroupOptions.setVisibility(View.INVISIBLE);
                        checkBoxLayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 8: {
                        if (checkBoxA.isChecked() && checkBoxB.isChecked() && checkBoxC.isChecked()) {
                            score += 1;
                        }
                        checkBoxLayout.setVisibility(View.INVISIBLE);
                        editTextLayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 9:
                        if (editTextAnswer.getText().toString() == "2007")
                            score += 1;
                        break;
                }
                radioGroupOptions.clearCheck();
                quesTracker += 1;
                setQuestion(quesTracker);
            }
        });
    }

    private void setQuestion(int ques) {


        //setting radiobutton options if Question No < 9 (ques <8) else setting checkbox options for Question No 9(ques==9)
        if (ques == 10) {
            editTextLayout.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
            tvQuestion.setText(getString(R.string.finalMessage, score));
            tvQuestion.setTextSize(24);
            tvQuestion.setTextColor(getResources().getColor(R.color.white));
            textViewScore.setText("Score: " + String.valueOf(score));
        } else {
            if (ques < 8) {
                tvQuestion.setText(questions[ques]);
                radioButtonOptnA.setText(options[++optionTracker]);
                radioButtonOptnB.setText(options[++optionTracker]);
                radioButtonOptnC.setText(options[++optionTracker]);
                radioButtonOptnD.setText(options[++optionTracker]);
            } else if (ques == 8) {
                tvQuestion.setText(questions[ques]);
                checkBoxA.setText(options[++optionTracker]);
                checkBoxB.setText(options[++optionTracker]);
                checkBoxC.setText(options[++optionTracker]);
                checkBoxD.setText(options[++optionTracker]);
            } else if (ques == 9) {
                tvQuestion.setText(questions[ques]);
            }

            ques++;
            textViewQuesNo.setText("Question " + String.valueOf(ques) + "/10");

            textViewScore.setText("Score: " + String.valueOf(score));
        }
    }
}
