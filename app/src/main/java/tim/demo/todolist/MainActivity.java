package tim.demo.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {

    private EditText itemET;
    private Button btn;
    private ListView itemList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET = findViewById(R.id.add_item_txt);
        btn = findViewById(R.id.add_btn);
        itemList = findViewById(R.id.list_item);

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        itemList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemList.setOnItemClickListener(this);
        itemList.setOnItemLongClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:

                String itemEntered = itemET.getText().toString();
                if(!itemEntered.equals(""))
                    //Toast.makeText(this, itemEntered + " Added", Toast.LENGTH_SHORT).show();
                    adapter.add(itemEntered);
                else
                    Toast.makeText(this, "入力は空です", Toast.LENGTH_SHORT).show();

                itemET.setText("");

                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "長押しで削除します", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "削除されました", Toast.LENGTH_SHORT).show();
        items.remove(position);
        adapter.notifyDataSetChanged();
        return true;
    }
}