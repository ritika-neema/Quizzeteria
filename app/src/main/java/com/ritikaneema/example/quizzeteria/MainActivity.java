package com.ritikaneema.example.quizzeteria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static butterknife.ButterKnife.*;


public class MainActivity extends AppCompatActivity {

    private int questions[] = {R.string.ques1, R.string.ques2, R.string.ques3, R.string.ques4,
            R.string.ques5, R.string.ques6, R.string.ques7, R.string.ques8, R.string.ques9, R.string.ques10};
    private int options[] = {R.string.optn_1a, R.string.optn_1b, R.string.optn_1c, R.string.optn_1d,
            R.string.optn_2a, R.string.optn_2b, R.string.optn_2c, R.string.optn_2d,
            R.string.optn_3a, R.string.optn_3b, R.string.optn_3c, R.string.optn_3d,
            R.string.optn_4a, R.string.optn_4b, R.string.optn_4c, R.string.optn_4d,
            R.string.optn_5a, R.string.optn_5b, R.string.optn_5c, R.string.optn_5d,
            R.string.optn_6a, R.string.optn_6b, R.string.optn_6c, R.string.optn_6d,
            R.string.optn_7a, R.string.optn_7b, R.string.optn_7c, R.string.optn_7d,
            R.string.optn_8a, R.string.optn_8b, R.string.optn_8c, R.string.optn_8d,
            R.string.optn_9a, R.string.optn_9b, R.string.optn_9c, R.string.optn_9d};
    @BindView(R.id.radioGroup) RadioGroup radioGroupOptions;
    @BindView(R.id.options_a) RadioButton radioButtonOptnA;
    @BindView(R.id.options_b) RadioButton radioButtonOptnB;
    @BindView(R.id.options_c) RadioButton radioButtonOptnC;
    @BindView(R.id.options_d) RadioButton radioButtonOptnD;

    @BindView(R.id.buttonSubmit) Button submitButton;

    @BindView(R.id.checkboxLayout) LinearLayout checkBoxLayout;
    @BindView(R.id.checkboxA)     CheckBox checkBoxA;
    @BindView(R.id.checkboxB)     CheckBox checkBoxB;
    @BindView(R.id.checkboxC)     CheckBox checkBoxC;
    @BindView(R.id.checkboxD)     CheckBox checkBoxD;

    @BindView(R.id.editTextLayout) LinearLayout editTextLayout;
    @BindView(R.id.editTextAnswer) EditText editTextAnswer;

    @BindView(R.id.textViewQuestion) TextView tvQuestion;
    @BindView(R.id.textViewScore) TextView textViewScore;
    @BindView(R.id.textViewQuesNo) TextView textViewQuesNo;

    @BindView( R.id.card_viewQuestions) CardView cardViewQues;

    private int quesTracker = 0, optionTracker = -1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(this);

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
                        //Removing Radiogroup options from UI and adding checkboxes for next question
                        radioGroupOptions.setVisibility(View.GONE);
                        checkBoxLayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 8: {
                        if (checkBoxA.isChecked() && checkBoxB.isChecked() && checkBoxC.isChecked() && !checkBoxD.isChecked()) {
                            score += 1;
                        }
                        //Removing CheckBoxes from UI and adding EditText for next question
                        checkBoxLayout.setVisibility(View.GONE);
                        editTextLayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 9:
                        if (editTextAnswer.getText().toString().equals("2007"))
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

            cardViewQues.setVisibility(View.GONE);
            editTextLayout.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            textViewScore.setVisibility(View.GONE);

            Toast.makeText(getApplicationContext(), getString(R.string.scoreMessage,score), Toast.LENGTH_LONG).show();

            textViewQuesNo.setText(R.string.thankyouMessage);
            textViewQuesNo.setTextSize(24);
            textViewQuesNo.setTextColor(getResources().getColor(R.color.white));
            textViewQuesNo.setGravity(Gravity.CENTER);
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
            textViewQuesNo.setText("Ques. " + String.valueOf(ques) + "/10");

            textViewScore.setText("Score: " + String.valueOf(score));
        }
    }
}
