package com.example.myapplication.objects;

import com.example.myapplication.adapters.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private static final List<Todo> TASK_LIST = new ArrayList<Todo>();
    private static AdapterView adapter;

    public TodoList() {
        super();
    }

    public void addTodo(Todo todo) {
        TASK_LIST.add(todo);

        if (adapter != null)
            adapter.notifyItemInserted(TASK_LIST.size()-1);
    }

    public void removeTodo(Todo todo) {
        int index = TASK_LIST.indexOf(todo);
        TASK_LIST.remove(todo);

        if (adapter != null)
            adapter.notifyItemRemoved(index);
    }

    public void editTodo(Todo oldTodo, Todo newTodo) {
        int index = TASK_LIST.indexOf(oldTodo);
        TASK_LIST.get(index).setTitle(newTodo.getTitle());
        TASK_LIST.get(index).setDescription(newTodo.getDescription());
        TASK_LIST.get(index).setTime(newTodo.getTime());
        TASK_LIST.get(index).setDate(newTodo.getDate());

        if (adapter != null) {
            adapter.notifyItemChanged(index);
        }
    }

    public void setTodoDone(Todo todo, boolean isDone) {
        int index = TASK_LIST.indexOf(todo);

        if (isDone) {
            TASK_LIST.get(index).setDone();
        } else {
            TASK_LIST.get(index).setUndone();
        }

        if (adapter != null)
            adapter.notifyItemChanged(index);
    }

    public List<Todo> getTodoList() {
        return TASK_LIST;
    }

    public TodoList setAdapter(AdapterView adapter) {
        TodoList.adapter = adapter;

        return this;
    }
}
