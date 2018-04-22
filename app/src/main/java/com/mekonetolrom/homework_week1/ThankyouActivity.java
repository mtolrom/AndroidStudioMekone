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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        textView = findViewById(R.id.tv_greeting);

        StringBuilder msg = new StringBuilder("Welcome: ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        assert b != null;
        if (b.get(getString(R.string.key_name)) != "") {
            String name = b.getString("name");
            msg.append(name).append(" ");
        }

        textView.setText(msg);
    }

    public void signUp(View View)
    {
        try {
            button_text = ((Button) View).getText().toString();
            if (button_text.equals("Sign Up")) {
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
            }
        }catch (Exception ex){
            //
        }
    }
}
