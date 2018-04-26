package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String button_text;
    private EditText usr_name;
    private EditText age;
    private EditText email;
    private EditText username;
    //private EditText dob;
    private EditText occupation;
    private EditText description;
    private TextView err_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr_name = findViewById(R.id.edit_name);
        age = findViewById(R.id.edit_age);
        email = findViewById(R.id.edit_email);
        username = findViewById(R.id.edit_username);
        err_message = findViewById(R.id.error_status);
        occupation = findViewById(R.id.edit_occupation);
        description = findViewById(R.id.edit_description);
    }

    public void createAccount(View View)
    {
        try {
            button_text = ((Button) View).getText().toString();

            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(age.getText().toString());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1); // don't forget this if date is arbitrary
            int year2 = cal1.get(Calendar.YEAR);

            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today); // don't forget this if date is arbitrary
            int year = cal.get(Calendar.YEAR);

            int int_age = year - year2;

            if (button_text.equals("Submit")) {
                if (age.getText().toString() != null) {
                    if (username != null) {
                        if (int_age >= 18) {
                            Intent it = new Intent(this, ThankyouActivity.class);
                            it.putExtra("name", "Name : " + usr_name.getText().toString());
                            it.putExtra("email", "Email : " + email.getText().toString());
                            it.putExtra("username", "Username : " + username.getText().toString());
                            it.putExtra("age", "Age : " + Integer.toString(int_age));
                            it.putExtra("occupation", "Occupation : " + occupation.getText().toString());
                            it.putExtra("description", "Description : " + description.getText().toString());
                            startActivity(it);
                        } else {
                            err_message.setText(R.string.fix_errors);
                            err_message.setTextColor(getColor(R.color.colorAccent));
                        }
                    }
                }
            }
        }catch (Exception ex){
            err_message.setText(R.string.oops_errors);
            //err_message.setText(ex.getMessage());
            err_message.setTextColor(getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", usr_name.getText().toString());
    }
}
