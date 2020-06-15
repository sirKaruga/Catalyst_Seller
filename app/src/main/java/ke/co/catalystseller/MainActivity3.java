package ke.co.catalystseller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends Activity{
    DatabaseHelper myDb;
    Button submitbtn;
    EditText nameTxt, emailTxt, contactTxt, pwdTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDb = new DatabaseHelper(this);
        submitbtn = (Button)findViewById(R.id.submit_button);
        nameTxt = (EditText)findViewById(R.id.user_nametxt);
        emailTxt = (EditText)findViewById(R.id.user_emailtxt);
        contactTxt = (EditText)findViewById(R.id.user_contacttxt);
        pwdTxt = (EditText)findViewById(R.id.user_passttxt);
        //viewAllData();
        addData();
    }

    public void addData(){
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(nameTxt.getText().toString(), emailTxt.getText().toString(), contactTxt.getText().toString(), pwdTxt.getText().toString());

                if (isInserted==true){
                    Toast.makeText(MainActivity3.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    //            To Product Selling Activity
                    Intent myIntent = new Intent(MainActivity3.this, SignupToggle.class);
                    startActivity(myIntent);

                }else {
                    Toast.makeText(MainActivity3.this, "Not Registered", Toast.LENGTH_LONG).show();
                    display("Failed!!", "There was a Problem Registering Your Details");

                }
            }
        });
    }

//    public void viewAllData(){
//        showAllBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor all = myDb.getAllData();
//                int count = all.getCount();
//                if (count==0){
//                    display("Error","The database has nothing to show");
//                    return;
//                }else{
//                    StringBuffer buffer = new StringBuffer();
//                    while (all.moveToNext()){
//                        buffer.append("id: "+ all.getString(0)+"\n");
//                        buffer.append("Name: "+ all.getString(1)+ "\n");
//                        buffer.append("Surname: "+ all.getString(2)+ "\n");
//                        buffer.append("Surname: "+ all.getString(3)+ "\n");
//                        buffer.append("marks: "+ all.getString(4)+ "\n\n");
//                    }
//                    display("DAta",buffer.toString());
//                }
//            }
//        });
//    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

