package com.mekonetolrom.homework_week1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import java.lang.*;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.hello_id);
        button = findViewById(R.id.go_id);
    }

    public void translateText(View View)
    {
        String button_text = button.getText().toString();
        if(button_text.equals(getString(R.string.translate_in)))
        {
            textView.setText(R.string.french);
            textView.setTextColor(getResources().getColor(R.color.colorAccent));
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, R.dimen.font_size);
        }
    }
}
