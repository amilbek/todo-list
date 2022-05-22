package com.example.myapplication.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterView;
import com.example.myapplication.objects.TodoList;

public class TodoListView extends Fragment {
    private final TodoList viewModel;

    public TodoListView() {
        viewModel = new TodoList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.todolist_layout, container , false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView todoList = view.findViewById(R.id.todoList);

        AdapterView adapter = new AdapterView(viewModel.getTodoList());
        viewModel.setAdapter(adapter);
        todoList.setAdapter(adapter);
        todoList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
