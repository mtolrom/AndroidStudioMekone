package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String button_text;
    private EditText usr_name;
    private EditText age;
    private EditText email;
    private EditText username;
    private EditText dob;
    private TextView err_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr_name = findViewById(R.id.edit_name);
        age = findViewById(R.id.edit_age);
        email = findViewById(R.id.edit_email);
        username = findViewById(R.id.edit_username);
        dob = findViewById(R.id.edit_dob);
        err_message = findViewById(R.id.error_status);
    }

    public void createAccount(View View)
    {
        try {
            button_text = ((Button) View).getText().toString();
            if (button_text.equals("Submit")) {
                if (age.getText().toString() != null) {
                    if (username != null) {
                        if (Integer.parseInt(age.getText().toString()) > 18) {
                            Intent it = new Intent(this, ThankyouActivity.class);
                            it.putExtra("name", usr_name.getText().toString());
                            it.putExtra("email", email.getText().toString());
                            it.putExtra("username", username.getText().toString());
                            it.putExtra("age", age.getText().toString());
                            it.putExtra("dob", dob.getText().toString());
                            startActivity(it);
                        } else {
                            err_message.setText(R.string.fix_errors);
                            err_message.setTextColor(Color.RED);
                        }
                    }
                }
            }
        }catch (Exception ex){
            err_message.setText(R.string.oops_errors);
            err_message.setTextColor(Color.RED);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", usr_name.getText().toString());
    }
}
