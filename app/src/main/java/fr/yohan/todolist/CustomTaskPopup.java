package fr.yohan.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTaskPopup extends Dialog {

    // fields
    private String title;
    private Button cancelButton, addButton;
    private EditText editTitle, editContent, editDate;
    private TextView textView;

    // constructor
    public CustomTaskPopup(Activity activity)
    {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        setContentView(R.layout.popup_template);
        title = "Add a task";
        cancelButton = findViewById(R.id.cancelButton);
        addButton = findViewById(R.id.addButton);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        editDate = findViewById(R.id.editDate);
        textView = findViewById(R.id.title);
    }

    public void setTitle(String title) { this.title = title; }

    public Button getCancelButton() { return cancelButton; }

    public Button getAddButton() { return addButton; }

    public EditText getEditTitle() { return editTitle; }

    public EditText getEditContent() { return editContent; }

    public EditText getEditDate() { return editDate; }

    public void build()
    {
        show();
        textView.setText(title);
    }
}
