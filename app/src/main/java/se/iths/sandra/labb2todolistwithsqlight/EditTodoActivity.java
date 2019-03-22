package se.iths.sandra.labb2todolistwithsqlight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class EditTodoActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String REPLY_FOR_FAVOURITE = "favourite";
    private EditText editTodoView;
    private Switch favouriteSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        editTodoView = findViewById(R.id.edit_todo);
        favouriteSwitch =findViewById(R.id.switch1);
        final Button button = findViewById(R.id.button_save);

        Intent intent =getIntent();

        String oldTodo = intent.getStringExtra("todoname");
        int todoId = intent.getIntExtra("position",-1);
        int importance = intent.getIntExtra("important", 0);

        if(oldTodo!=null){
            editTodoView.setText(oldTodo);
            button.setText("Update");
            if(importance == 1){
                favouriteSwitch.setChecked(true);
            }
        }


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                int favourite;

                if (TextUtils.isEmpty(editTodoView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String newTodo = editTodoView.getText().toString();
                    if(favouriteSwitch.isChecked()){
                       favourite = 1;
                    }else{
                        favourite = 0;
                    }
                    replyIntent.putExtra(EXTRA_REPLY, newTodo);
                    replyIntent.putExtra(REPLY_FOR_FAVOURITE, favourite);
                    if(todoId!=-1){
                        replyIntent.putExtra("todoId", todoId);
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
