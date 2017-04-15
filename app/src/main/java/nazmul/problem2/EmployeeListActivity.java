package nazmul.problem2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

public class EmployeeListActivity extends AppCompatActivity {
    private RecyclerView employeeList;
    EMployeeAdapter eMployeeAdapter;
    ArrayList<Employee>employeeArrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        employeeList = (RecyclerView)findViewById(R.id.emplLIst);
        employeeArrayList= (ArrayList<Employee>) databaseHelper.getAllEmployee();
        eMployeeAdapter = new EMployeeAdapter(getApplicationContext(),employeeArrayList);
        setLayoutManager();
        employeeList.setAdapter(eMployeeAdapter);
    }

    private void setLayoutManager() {
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        employeeList.setLayoutManager(mStaggeredLayoutManager);
    }

    public void newEMployee(View view) {
        Intent intent=new Intent(EmployeeListActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
