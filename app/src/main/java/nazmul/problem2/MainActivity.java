package nazmul.problem2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner coutrynameSpinner;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText phoneET;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(getApplicationContext());
        coutrynameSpinner= (Spinner) findViewById(R.id.countryNameSpinner);
        firstNameET= (EditText) findViewById(R.id.firstNameET);
        lastNameET= (EditText) findViewById(R.id.lastNameET);
        phoneET= (EditText) findViewById(R.id.phoneET);
        String[]min_hour_day = getResources().getStringArray(R.array.country);
        ArrayAdapter<CharSequence> countryAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_layout,R.id.spinnerItemText,min_hour_day);
        coutrynameSpinner.setAdapter(countryAdapter);
    }

    public void saveEmployee(View view) {
        String firstName=firstNameET.getText().toString();
        String lastName=lastNameET.getText().toString();
        String phone=phoneET.getText().toString();
        String country=coutrynameSpinner.getSelectedItem().toString();
        Employee employee=new Employee(firstName,lastName,phone,country);
        db.addEmployee(employee);
        Toast.makeText(this, ""+db.getAllEmployee(), Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(MainActivity.this,EmployeeListActivity.class);
        startActivity(intent);
    }
}
