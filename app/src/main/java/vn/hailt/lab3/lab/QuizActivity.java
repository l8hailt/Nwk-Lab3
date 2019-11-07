package vn.hailt.lab3.lab;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import vn.hailt.lab3.R;
import vn.hailt.lab3.lt.GetTask;
import vn.hailt.lab3.lt.ITaskFinishedListener;

public class QuizActivity extends AppCompatActivity {

    private List<String> mQuestions;
    private List<String> mOptions1;
    private List<String> mOptions2;
    private List<String> mOptions3;
    private List<String> mOptions4;
    private List<String> mAnswers;

    private int index = 0;

    private TextView tvTopic;
    private TextView tvQuestion;
    private LinearLayout containerOptions;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnNext;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvTopic = findViewById(R.id.tvTopic);
        tvQuestion = findViewById(R.id.tvQuestion);
        containerOptions = findViewById(R.id.containerOptions);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        mQuestions = new ArrayList<>();
        mOptions1 = new ArrayList<>();
        mOptions2 = new ArrayList<>();
        mOptions3 = new ArrayList<>();
        mOptions4 = new ArrayList<>();
        mAnswers = new ArrayList<>();

        GetTask getTask = new GetTask();
        getTask.execute("http://dotplays.com/android/lab3.json");
        getTask.setListener(new ITaskFinishedListener() {
            @Override
            public void onFinished(String result) {

                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(result);

                JsonObject root = jsonElement.getAsJsonObject();

                JsonObject quiz = root.get("quiz").getAsJsonObject();

                String topic = getIntent().getStringExtra("topic");

                JsonArray quizTopic = quiz.get(topic).getAsJsonArray();

                for (int i = 0; i < quizTopic.size(); i++) {
                    JsonObject question = quizTopic.get(i).getAsJsonObject();

                    String content = question.get("question").getAsString();
                    String answer = question.get("answer").getAsString();
                    JsonArray options = question.get("options").getAsJsonArray();

                    mQuestions.add(content);
                    mAnswers.add(answer);

                    String option1 = options.get(0).getAsString();
                    mOptions1.add(option1);

                    String option2 = options.get(1).getAsString();
                    mOptions2.add(option2);

                    String option3 = options.get(2).getAsString();
                    mOptions3.add(option3);

                    String option4 = options.get(3).getAsString();
                    mOptions4.add(option4);

                }

                initQuiz();

            }
        });


    }

    private void initQuiz() {
        tvTopic.setText(getIntent().getStringExtra("topic"));
        tvQuestion.setText(mQuestions.get(index));

        btnOption1.setText(mOptions1.get(index));
        btnOption2.setText(mOptions2.get(index));
        btnOption3.setText(mOptions3.get(index));
        btnOption4.setText(mOptions4.get(index));

        for (int button = 0; button < containerOptions.getChildCount(); button++) {

            Button btnGuess = (Button) containerOptions.getChildAt(button);

            btnGuess.setAllCaps(false);
            btnGuess.setOnClickListener(btnOptionListener);
        }
    }

    private View.OnClickListener btnOptionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btnOptions = (Button) view;
            Log.e("TAG", "onClick: " + btnOptions.getText());
            if (btnOptions.getText().equals(mAnswers.get(index))) {
                score++;
                Toast.makeText(QuizActivity.this, "Dung", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuizActivity.this, "Sai", Toast.LENGTH_SHORT).show();
            }

            index++;
            if (index == mQuestions.size()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setTitle("Ket qua");
                builder.setMessage("Ban da lam dung: " + score + "/" + mQuestions.size() + " cau");
                builder.show();
            } else {
                initQuiz();
            }
        }
    };
}
