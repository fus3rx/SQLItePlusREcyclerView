package nazmul.problem2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nazmul on 4/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "employee_database";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_EMPLOYEE = "employee";

    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_COUNTRY_NAME = "country_name";

    public static String TAG = "tag";

    // Students Table Create Query
    /**
     * CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name
     * TEXT,phone_number TEXT);
     */

    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE "
            + TABLE_EMPLOYEE + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRST_NAME + " TEXT,"
            + KEY_LAST_NAME + " TEXT,"+KEY_PHONE_NUMBER + " TEXT," +KEY_COUNTRY_NAME + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_EMPLOYEE); // create students table

    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EMPLOYEE); // drop table if exists

        onCreate(db);
    }


    public long addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, employee.getFirst_name());
        values.put(KEY_LAST_NAME, employee.getLast_name());
        values.put(KEY_PHONE_NUMBER, employee.getPhone());
        values.put(KEY_COUNTRY_NAME, employee.getCountry_name());

        // insert row in students table

        long insert = db.insert(TABLE_EMPLOYEE, null, values);

        return insert;
    }


    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, employee.getFirst_name());
        values.put(KEY_LAST_NAME, employee.getLast_name());
        values.put(KEY_PHONE_NUMBER, employee.getPhone());
        values.put(KEY_COUNTRY_NAME, employee.getCountry_name());

        // update row in students table base on students.is value
        return db.update(TABLE_EMPLOYEE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
    }

    /**
     * Used to delete particular student entry
     *
     * @param id
     */
    public void deleteEntry(long id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Used to get particular student details
     *
     * @param id
     * @return
     */

    public Employee getEmployee(long id) {
        Employee employee = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            String first_name=c.getString(c.getColumnIndex(KEY_FIRST_NAME));
            String last_name=c.getString(c.getColumnIndex(KEY_LAST_NAME));
            String phone=c.getString(c.getColumnIndex(KEY_PHONE_NUMBER));
            String country_name=c.getString(c.getColumnIndex(KEY_COUNTRY_NAME));
            employee=new Employee(id,first_name,last_name,phone,country_name);
        }
        return employee;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * StudentsModel
     *
     * @return
     */
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<Employee>();

        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Employee employee;
                long id =c.getLong(c.getColumnIndex(KEY_ID));
                String first_name=c.getString(c.getColumnIndex(KEY_FIRST_NAME));
                String last_name=c.getString(c.getColumnIndex(KEY_LAST_NAME));
                String phone=c.getString(c.getColumnIndex(KEY_PHONE_NUMBER));
                String country_name=c.getString(c.getColumnIndex(KEY_COUNTRY_NAME));
                employee=new Employee(id,first_name,last_name,phone,country_name);

                // adding to Students list
                employeeList.add(employee);
            } while (c.moveToNext());
        }
        return employeeList;
    }
}
