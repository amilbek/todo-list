package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.objects.Todo;
import com.example.myapplication.objects.TodoList;
import com.example.myapplication.views.UpdateView;

import java.util.List;

public class AdapterView extends RecyclerView.Adapter<AdapterView.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final TextView time;
        private final CheckBox checkBox;
        private Todo todo = null;

        public ViewHolder(View view, FragmentActivity c, TodoList model) {
            super(view);

            title = view.findViewById(R.id.todoTitle);
            description = view.findViewById(R.id.todoDescription);
            date = view.findViewById(R.id.todoDate);
            time = view.findViewById(R.id.todoTime);
            checkBox = view.findViewById(R.id.todoCheckBox);

            Button btnEdit = view.findViewById(R.id.btnEdit);
            Button btnDelete = view.findViewById(R.id.btnDelete);

            checkBox.setOnClickListener(new View.OnClickListener() {
                private TodoList todoList;

                @Override
                public void onClick(View view) {
                    boolean checked = ((CheckBox) view).isChecked();

                    if (todo != null)
                        this.todoList.setTodoDone(todo, checked);
                }

                public View.OnClickListener init(TodoList model) {
                    this.todoList = model;

                    return this;
                }
            }.init(model));

            btnDelete.setOnClickListener(new View.OnClickListener() {
                private TodoList model;

                @Override
                public void onClick(View view) {
                    if (todo != null)
                        this.model.removeTodo(todo);
                }

                public View.OnClickListener init(TodoList model) {
                    this.model = model;

                    return this;
                }
            }.init(model));


            btnEdit.setOnClickListener(view1 -> {
                if (todo != null) {
                    UpdateView fragment = new UpdateView(todo);
                    FragmentTransaction fragmentTransaction =
                            c.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }


        public TextView getTitleTextView() { return title; }
        public TextView getDescriptionTextView() {
            return description;
        }
        public TextView getDateTextView() {
            return date;
        }
        public TextView getTimeTextView() {
            return time;
        }
        public CheckBox getCheckBox() {
            return checkBox;
        }
        public void setTask(Todo todo) {
            this.todo = todo;
        }
    }

    private final List<Todo> todoList;

    public AdapterView(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list, viewGroup, false);

        return new ViewHolder(view, new FragmentActivity(), new TodoList().setAdapter(this));
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(todoList.get(position).getTitle());
        viewHolder.getDescriptionTextView().setText(todoList.get(position).getDescription());
        viewHolder.getDateTextView().setText(todoList.get(position).getDate());
        viewHolder.getTimeTextView().setText(todoList.get(position).getTime());
        viewHolder.getCheckBox().setChecked(todoList.get(position).isDone());
        viewHolder.setTask(todoList.get(position));
    }
}
