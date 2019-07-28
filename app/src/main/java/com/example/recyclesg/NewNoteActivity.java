package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNoteActivity extends AppCompatActivity{
    public static EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private Button buttonDone;
    private Button buttonCam;
    String[] minuteValues = new String[140];
    private int volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
//        setTitle("Add Note");

        editTextTitle = findViewById(R.id.barcode_txt);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        buttonDone = (Button) findViewById(R.id.btn_done);
        buttonCam = (Button) findViewById(R.id.buttonCam);

        numberPickerPriority.setMinValue(0);
        numberPickerPriority.setMaxValue(140);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setOnItemClickListener(this);
//
//        numberPickerPriority.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                picker.setValue((newVal < oldVal)?oldVal-10:oldVal+10);
//            }
//
//        });


        String[] minuteValues = new String[141];

        for (int i = 0; i < minuteValues.length; i++) {
            String number = Integer.toString(100+i*10);
            minuteValues[i] = number.length() < 2 ? "0" + number : number;
        }

        numberPickerPriority.setDisplayedValues(minuteValues);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        buttonCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanning();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = editTextDescription.getText().toString();
        String description = "Metal"; //editTextTitle.getText().toString();
        int priority = 320;

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference notebookRef = FirebaseFirestore.getInstance()
                .collection("Notebook");
        notebookRef.add(new Note(title, description, priority));
        Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void openScanning(){
        Intent intent = new Intent(this, activityScanningforNew.class);
        startActivity(intent);
    }
}
