package fr.yohan.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTaskEditPopup extends Dialog {

    // fields
    private Button cancelButton, editButton, doneButton;
    private EditText editTitle, editContent, editDate;
    private TextView textView;
    private String title, content, date;

    // constructor
    public CustomTaskEditPopup(Activity activity, String title, String content, String date)
    {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        setContentView(R.layout.popup_taskedit_template);
        cancelButton = findViewById(R.id.cancelButton);
        editButton = findViewById(R.id.editButton);
        doneButton = findViewById(R.id.doneButton);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        editDate = findViewById(R.id.editDate);
        textView = findViewById(R.id.title);
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Button getCancelButton() { return cancelButton; }

    public Button getEditButton() { return editButton; }

    public Button getDoneButton() { return doneButton; }

    public EditText getEditTitle() { return editTitle; }

    public EditText getEditContent() { return editContent; }

    public EditText getEditDate() { return editDate; }

    public void build()
    {
        show();
        editTitle.setText(title);
        editContent.setText(content);
        editDate.setText(date);
    }
}
