package com.eugene.listviewexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button btnSave;
    EditText etItem;
    ListView listView;

    private void findViewsById() {
        btnSave = (Button) findViewById(R.id.btnSave);
        etItem = (EditText) findViewById(R.id.etItem);
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        adapterImplementation();
        addItemToList();
        listClick();
    }

    LogArrayAdapter logArrayAdapter;
    ArrayList<Log> itemList = new ArrayList<>();

    private void adapterImplementation() {
        logArrayAdapter = new LogArrayAdapter(this, 0, itemList);
        listView.setAdapter(logArrayAdapter);
        registerForContextMenu(listView); // on long click, show menu
    }

    String strItem = "";

    private void addItemToList() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etItem.getText().toString().equals("")) {
                    strItem = "No Title";
                } else {
                    strItem = etItem.getText().toString();
                }
                Log log = new Log();
                log.setItem(strItem);
                logArrayAdapter.add(log);
                logArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void listClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log log = logArrayAdapter.getItem(position);
                String listItemName = log.getItem();
                Toast.makeText(MainActivity.this, listItemName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
   NOTE: Below handles the context menu for when the list item is long pressed
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // Add Items to menu
        if (v.getId() == R.id.listView) {
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId(); // context menu list position
        int itemListPosition = info.position; // listView item position
        Log log = logArrayAdapter.getItem(itemListPosition);
        if (menuItemIndex == 0) {
            logArrayAdapter.remove(log); // Delete from adapter
            logArrayAdapter.notifyDataSetChanged(); // Update Adapter
        }
        if (menuItemIndex == 1) {
            // Do nothing
        }
        return true;
    }
}
