package tk.crucial.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText etTitle = findViewById(R.id.etTitle);
        final EditText etAuthor = findViewById(R.id.etAuthor);
        final EditText etPublisher = findViewById(R.id.etPublisher);
        final EditText etIsbn = findViewById(R.id.etIsbn);

        final Button button = findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String publisher = etPublisher.getText().toString().trim();
                String isbn = etIsbn.getText().toString().trim();
                if(title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter valid search term",Toast.LENGTH_LONG).show();
                }else {
                    URL queryUrl = ApiUtil.buildUrl(title,author,publisher,isbn);
                    //Shared Preferences
                    Context context = getApplicationContext();
                    int position = SpUtils.getPreferenceInt(context,SpUtils.POSITION);
                    if(position == 0 || position == 5){
                        position = 1;
                    }else{
                        position++;
                    }

                    String key = SpUtils.QUERY + String.valueOf(position);
                    String value = title + "," + author + "," + publisher + "," + isbn;
                    SpUtils.setPreferenceString(context,key,value);
                    SpUtils.setPreferenceInt(context,key,position);

                    Intent intent = new Intent(getApplicationContext(),BookListActivity.class);
                    intent.putExtra("Query",queryUrl.toString());
                    startActivity(intent);
                }
            }
        });
    }
}
