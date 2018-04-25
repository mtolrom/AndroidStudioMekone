package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankyouActivity extends AppCompatActivity {

    private String button_text;
    private TextView textView;
    private TextView textUsername;
    private TextView textEmail;
    private TextView textAge;
    private TextView textOccupation;
    private TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        textView = findViewById(R.id.tv_greeting);
        textUsername = findViewById(R.id.tv_username);
        textEmail = findViewById(R.id.tv_email);
        textAge = findViewById(R.id.tv_age);
        textOccupation = findViewById(R.id.tv_occupation);
        textDescription = findViewById(R.id.tv_description);

        //StringBuilder msg = new StringBuilder("Welcome: ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        assert b != null;
        String name = b.getString("name");
        String username = b.getString("username");
        String email = b.getString("email");
        String age = b.getString("age");
        String occupation = b.getString("occupation");
        String description = b.getString("description");

        textView.setText(name);
        textUsername.setText(username);
        textEmail.setText(email);
        textAge.setText(age);
        textOccupation.setText(occupation);
        textDescription.setText(description);
    }

    public void signUp(View View)
    {
        button_text = ((Button) View).getText().toString();
        if (button_text.equals("Sign Up")) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }
}
