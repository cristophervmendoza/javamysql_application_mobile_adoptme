package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountTestsFragment extends Fragment {

    private ProgressBar progressBar;
    private int currentQuestion = 0;
    private int[] answers = new int[10];

    private LinearLayout startScreen;
    private View questionLayout;
    private View resultsLayout;

    private String[][] questions = {
            {"Question 1 of 10", "How much time can you dedicate to a pet daily?", "Less than 1 hour", "1-2 hours", "3-4 hours", "More than 4 hours"},
            {"Question 2 of 10", "What is your living space like?", "Small apartment", "Medium-sized house", "Large house with yard", "Farm or rural property"},
            {"Question 3 of 10", "What is your activity level?", "Sedentary/Low activity", "Moderate activity", "Active/Athletic", "Very active/Outdoorsy"},
            {"Question 4 of 10", "Do you have experience with pets?", "First-time pet owner", "Some experience", "Experienced owner", "Expert/Professional"},
            {"Question 5 of 10", "Do you have children at home?", "No children", "Young children (0-5 years)", "School-age children (6-12 years)", "Teenagers (13+ years)"},
            {"Question 6 of 10", "What's your noise tolerance level?", "Low (need quiet environment)", "Moderate (some noise is okay)", "High (noise doesn't bother me)", "Very high (I love lively pets!)"},
            {"Question 7 of 10", "How much grooming are you willing to do?", "Minimal/No grooming", "Occasional grooming", "Regular grooming (weekly)", "Daily grooming (I enjoy it!)"},
            {"Question 8 of 10", "Do you have allergies to pet fur or dander?", "No allergies", "Mild allergies", "Moderate allergies", "Severe allergies"},
            {"Question 9 of 10", "What's your budget for pet care?", "Low budget (under $50/month)", "Moderate ($50-$150/month)", "High ($150-$300/month)", "No budget limit"},
            {"Question 10 of 10", "What personality do you prefer in a pet?", "Independent/Low maintenance", "Affectionate/Cuddly", "Playful/Energetic", "Calm/Gentle"}
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_tests, container, false);

        progressBar = root.findViewById(R.id.progress_bar);
        startScreen = root.findViewById(R.id.start_screen);
        questionLayout = root.findViewById(R.id.question_layout);
        resultsLayout = root.findViewById(R.id.results_layout);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            if (currentQuestion == 0) {
                requireActivity().getSupportFragmentManager().popBackStack();
            } else {
                previousQuestion();
            }
        });

        // Pantalla de inicio
        AppCompatButton btnStart = root.findViewById(R.id.btn_start);
        TextView btnCancelStart = root.findViewById(R.id.btn_cancel_start);

        btnStart.setOnClickListener(v -> {
            startScreen.setVisibility(View.GONE);
            questionLayout.setVisibility(View.VISIBLE);
            currentQuestion = 1;
            updateProgress();
            loadQuestion(0);
        });

        btnCancelStart.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return root;
    }

    private void loadQuestion(int index) {
        TextView questionNumber = questionLayout.findViewById(R.id.question_number);
        TextView questionText = questionLayout.findViewById(R.id.question_text);
        RadioButton option1 = questionLayout.findViewById(R.id.radio_option_1);
        RadioButton option2 = questionLayout.findViewById(R.id.radio_option_2);
        RadioButton option3 = questionLayout.findViewById(R.id.radio_option_3);
        RadioButton option4 = questionLayout.findViewById(R.id.radio_option_4);
        RadioGroup radioGroup = questionLayout.findViewById(R.id.radio_group);
        AppCompatButton btnNext = questionLayout.findViewById(R.id.btn_next);
        TextView btnPrevious = questionLayout.findViewById(R.id.btn_previous);

        // Cargar datos
        questionNumber.setText(questions[index][0]);
        questionText.setText(questions[index][1]);
        option1.setText(questions[index][2]);
        option2.setText(questions[index][3]);
        option3.setText(questions[index][4]);
        option4.setText(questions[index][5]);

        // Limpiar selección
        radioGroup.clearCheck();
        btnNext.setEnabled(false);
        btnNext.setAlpha(0.5f);

        // Mostrar/ocultar botón Previous
        btnPrevious.setVisibility(currentQuestion > 1 ? View.VISIBLE : View.GONE);

        // Cambiar texto del botón en la última pregunta
        btnNext.setText(currentQuestion == 10 ? "Finish" : "Next");

        // Listener para habilitar Next
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            btnNext.setEnabled(true);
            btnNext.setAlpha(1.0f);

            if (checkedId == R.id.radio_option_1) answers[index] = 1;
            else if (checkedId == R.id.radio_option_2) answers[index] = 2;
            else if (checkedId == R.id.radio_option_3) answers[index] = 3;
            else if (checkedId == R.id.radio_option_4) answers[index] = 4;
        });

        btnNext.setOnClickListener(v -> nextQuestion());
        btnPrevious.setOnClickListener(v -> previousQuestion());
    }

    private void nextQuestion() {
        if (currentQuestion < 10) {
            currentQuestion++;
            updateProgress();
            loadQuestion(currentQuestion - 1);
        } else {
            // Mostrar resultados
            questionLayout.setVisibility(View.GONE);
            resultsLayout.setVisibility(View.VISIBLE);
        }
    }

    private void previousQuestion() {
        if (currentQuestion > 1) {
            currentQuestion--;
            updateProgress();
            loadQuestion(currentQuestion - 1);
        }
    }

    private void updateProgress() {
        progressBar.setProgress(currentQuestion);
    }
}
