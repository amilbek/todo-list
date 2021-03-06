package com.example.myapplication.views;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragments.DatePickerFragment;
import com.example.myapplication.fragments.TimePickerFragment;
import com.example.myapplication.objects.Todo;
import com.example.myapplication.objects.TodoList;

import java.util.Objects;

public class CreateTodoView extends Fragment {

    @SuppressLint("StaticFieldLeak")
    public static TimePicker timePicker;
    @SuppressLint("StaticFieldLeak")
    public static DatePicker datePicker;

    private EditText editTitle;
    private EditText editDescription;
    private TextView timeTextView;
    private TextView dateTextView;

    private final TodoList todoList;

    public CreateTodoView() {
        // Required empty public constructor
        todoList = new TodoList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_create, container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.editTitle = Objects.requireNonNull(getView()).findViewById(R.id.editTextTitle);
        this.editDescription = getView().findViewById(R.id.editTextDescription);
        this.timeTextView = getView().findViewById(R.id.timeTextView);
        this.dateTextView = getView().findViewById(R.id.dateTextView);

        Button btnTime = getView().findViewById(R.id.btnTime);
        Button btnDate = getView().findViewById(R.id.btnDate);
        Button btnSubmit = getView().findViewById(R.id.btnAdd);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            private CreateTodoView createTodoView;

            @Override
            public void onClick(View view) {
                createTodoView.createTask();
                Toast.makeText(getContext(),
                        "Todo '" + editTitle.getText().toString() + "' added to list !",
                        Toast.LENGTH_LONG).show();
            }

            public View.OnClickListener init(CreateTodoView createTodoView) {
                this.createTodoView = createTodoView;

                return this;
            }
        }.init(this));

        btnTime.setOnClickListener(view1 -> showTimePickerDialog());

        btnDate.setOnClickListener(view1 -> showDatePickerDialog());
    }

    public void createTask() {
        String title = this.editTitle.getText().toString();
        String description = this.editDescription.getText().toString();

        if (title.equals("")) {
            Toast.makeText(getContext(),
                    "Title cannot be empty!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String date = null;
        String time = null;

        if (datePicker != null) {
            date = getMonth(datePicker.getMonth()) + " " + datePicker.getDayOfMonth() + ", " +
                    datePicker.getYear();
            dateTextView.setText(date);
            datePicker = null;
        }

        if (timePicker != null) {
            if (timePicker.getMinute() < 10) {
                time = timePicker.getHour() + ":0" + timePicker.getMinute();
            } else {
                time = timePicker.getHour() + ":" + timePicker.getMinute();
            }
            timeTextView.setText(time);
            timePicker = null;
        }

        this.todoList.addTodo(new Todo(title, description, date, time));
        editTitle.setText("");
        editDescription.setText("");
        timeTextView.setText("");
        dateTextView.setText("");
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                "timePicker");
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                "datePicker");
    }

    private String getMonth(int i) {
        switch (i) {
            case 0: return "January";
            case 1: return "February";
            case 2: return "March";
            case 3: return "April";
            case 4: return "May";
            case 5: return "June";
            case 6: return "July";
            case 7: return "August";
            case 8: return "September";
            case 9: return "October";
            case 10: return "November";
            default: return "December";
        }
    }
}
