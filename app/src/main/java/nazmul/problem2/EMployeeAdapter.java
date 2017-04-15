package nazmul.problem2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 1/22/2017.
 */

public class EMployeeAdapter extends RecyclerView.Adapter<EMployeeAdapter.MyViewHolder> {
    private ArrayList<Employee> employeeArrayList;
    private Context context;
    private int previousPosition = 0;

    public EMployeeAdapter(Context context, ArrayList<Employee> employeeArrayList) {
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Employee employee = employeeArrayList.get(position);
        holder.firstNameText.setText(employee.getFirst_name());
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstNameText;

        MyViewHolder(final View itemView) {
            super(itemView);
            firstNameText= (TextView) itemView.findViewById(R.id.emp_first_name);
        }
    }
}
