package com.aa.a.reviewtalent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.aa.a.reviewtalent.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.aa.a.reviewtalent.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.aa.a.reviewtalent.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.aa.a.reviewtalent.EXTRA_PRIORITY";

    public @BindView(R.id.edit_title)
    EditText edit_title;
    public @BindView(R.id.edit_description)
    EditText edit_description;

    public @BindView(R.id.num_picker)
    NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            edit_title.setText(i.getStringExtra(EXTRA_TITLE));
            edit_description.setText(i.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(i.getIntExtra(EXTRA_PRIORITY,1));
        }
        else {
            setTitle("Add Note");
        }

    }

    private void saveNote(){
        String title = edit_title.getText().toString();
        String description = edit_description.getText().toString();
        int priority = numberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE,title);
            data.putExtra(EXTRA_DESCRIPTION,description);
            data.putExtra(EXTRA_PRIORITY,priority);

            int id = getIntent().getIntExtra(EXTRA_ID,-1);
            Log.d("show id",String.valueOf(id));
            if(id!=-1)
            {
                data.putExtra(EXTRA_ID,id);
            }


            setResult(RESULT_OK,data);
            finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_note:
                saveNote();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
