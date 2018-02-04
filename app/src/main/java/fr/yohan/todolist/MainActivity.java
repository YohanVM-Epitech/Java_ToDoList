package fr.yohan.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout myLayout;
    private MainActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ctx = this;
        myLayout = (LinearLayout) findViewById(R.id.myListLayout);

        Button button = new Button(this);
        button.setText("Add a task");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CustomTaskPopup customTaskPopup = new CustomTaskPopup(ctx);
                customTaskPopup.setTitle("Add a task");
                customTaskPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customTaskPopup.dismiss();
                    }
                });
                customTaskPopup.getAddButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final TextView text = new TextView(ctx);
                        text.setText(Html.fromHtml("<b>" + customTaskPopup.getEditTitle().getText() + "</b>" + "<br />" +
                                "<small>" + customTaskPopup.getEditContent().getText() + "</small>" + "<br />" + "<br />" +
                                "<small>" + customTaskPopup.getEditDate().getText() + "</small>"));
                        text.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                        text.setTextColor(getResources().getColor(R.color.colorText));
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lparams.setMargins(15, 5, 15, 5);
                        text.setLayoutParams(lparams);
                        text.setPadding(20, 20, 20, 20);
                        text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final CustomTaskEditPopup customTaskEditPopup = new CustomTaskEditPopup(ctx,
                                        customTaskPopup.getEditTitle().getText().toString(), customTaskPopup.getEditContent().getText().toString(),
                                        customTaskPopup.getEditDate().getText().toString());
                                customTaskEditPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        customTaskEditPopup.dismiss();
                                    }
                                });
                                customTaskEditPopup.getEditButton().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        text.setText(Html.fromHtml("<b>" + customTaskEditPopup.getEditTitle().getText() + "</b>" + "<br />" +
                                                "<small>" + customTaskEditPopup.getEditContent().getText() + "</small>" + "<br />" + "<br />" +
                                                "<small>" + customTaskEditPopup.getEditDate().getText() + "</small>"));
                                        customTaskEditPopup.dismiss();
                                    }
                                });
                                customTaskEditPopup.getDoneButton().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myLayout.removeView(text);
                                        customTaskEditPopup.dismiss();
                                    }
                                });
                                customTaskEditPopup.build();
                            }
                        });
                        try {
                            addTask(text);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        customTaskPopup.dismiss();
                    }
                });
                customTaskPopup.build();
            }
        });
        myLayout.addView(button);
    }

    private Boolean isAfter(String oldDateString, String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date oldDate = format.parse(oldDateString);
        Date date = format.parse(dateString);
        if (date.before(oldDate))
            return true;
        else if (date.after(oldDate))
            return false;
        else
            return false;
    }

    public void addTask(View view) throws ParseException {
        // get number of children
        int childcount = myLayout.getChildCount();
        // create array
        ArrayList<TextView> children = new ArrayList<TextView>();
        //TextView[] children = new TextView[childcount + 1];

        // get children of linearlayout
        for (int i = 0; i < childcount; i++) {
            children.add((TextView)myLayout.getChildAt(i));
        }
        children.add((TextView) view);

        if (childcount > 1)
            myLayout.removeViews(1, childcount - 1);
        children.remove(0);
        CharSequence allText;
        String date;
        String oldDate;
        int max;
        while (children.size() != 0) {
            oldDate = "99/99/9999";
            max = 0;
            for (int i = 0; i < children.size(); i++) {
                allText = children.get(i).getText();
                date = allText.toString().substring(allText.length() - 10);
                if (isAfter(oldDate, date)) {
                    oldDate = date;
                    max = i;
                }
            }
            myLayout.addView(children.get(max));
            children.remove(max);
        }
    }
}
