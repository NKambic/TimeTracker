package koki_kambic.emp_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.Task;

public class AddTask extends AppCompatActivity {
    DatabaseConnector myDb;
    String UserId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        myDb = new DatabaseConnector(this);
        // get user ID
        Intent intent = getIntent();
        if (intent != null) {
            final Bundle bundle = intent.getExtras();
            if (bundle != null) {
                UserId = bundle.getString("UserID");
            }
        }
        Button btn =(Button) findViewById(R.id.btn_addTask);
        final String finalUserId = UserId;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(finalUserId);
            }
        });
    }
    private void addTask(String userId){
        TextView textView =  (TextView) findViewById(R.id.TaskError_TxtView);
        EditText editText =(EditText) findViewById(R.id.input_TaskName);
        String taskName = editText.getText().toString();
        if(taskName.length()>0) {
            myDb.open();
            myDb.insertTask(taskName,userId);
            myDb.close();
            finish();

        }
        else {
            textView.setText("enter Task name");
        }


    }

}
