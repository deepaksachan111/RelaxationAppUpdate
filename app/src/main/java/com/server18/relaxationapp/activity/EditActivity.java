package com.server18.relaxationapp.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.server18.relaxationapp.R;
import com.server18.relaxationapp.datamodel.Memo;
import com.server18.relaxationapp.db.DatabaseAccess;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditActivity extends Activity {
    private EditText etText;
    private Button btnSave;
    private Button btnCancel;
    private Memo memo;
   String ss;
 Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.etText = (EditText) findViewById(R.id.etText);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);

        Bundle bundle = getIntent().getExtras();
       ss = getIntent().getStringExtra("DATA");


       // date= Long.parseLong(ss);
       DateFormat formatter ;

        formatter = new SimpleDateFormat("dd-MMM-yy");
        try {
            date = formatter.parse(ss);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(bundle != null) {
            memo = (Memo) bundle.get("MEMO");

            if(memo != null) {
                this.etText.setText(memo.getText());
            }
        }

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });
    }

    public void onSaveClicked() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        if(memo == null) {
            // Add new memo
            Memo temp = new Memo(date);
            temp.setText(etText.getText().toString());
            databaseAccess.save(temp);
        } else {
            // Update the memo
            memo.setText(etText.getText().toString());
            databaseAccess.update(memo);
        }
        databaseAccess.close();
        this.finish();
    }

    public void onCancelClicked() {
        this.finish();
    }
}
