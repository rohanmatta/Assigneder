package com.example.assigneder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ASSIGNMENT = 1;

    private List<Object> items;

    public AssignmentAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Assignment.DateHeader) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ASSIGNMENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.assignment_list_item, parent, false);
            return new AssignmentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_HEADER) {
            Assignment.DateHeader header = (Assignment.DateHeader) items.get(position);
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerText.setText(header.getHeaderText());
        } else {
            Assignment assignment = (Assignment) items.get(position);
            AssignmentViewHolder assignmentHolder = (AssignmentViewHolder) holder;
            assignmentHolder.titleText.setText(assignment.getTitle());
            assignmentHolder.dueDateText.setText("Due: " + assignment.getDueDate().toString());
            assignmentHolder.completedCheckBox.setChecked(assignment.isCompleted());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerText;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerText = itemView.findViewById(android.R.id.text1);
        }
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        CheckBox completedCheckBox;
        TextView titleText;
        TextView dueDateText;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            completedCheckBox = itemView.findViewById(R.id.completed_check_box);
            titleText = itemView.findViewById(R.id.assignment_title);
            dueDateText = itemView.findViewById(R.id.due_text);
        }
    }
}
