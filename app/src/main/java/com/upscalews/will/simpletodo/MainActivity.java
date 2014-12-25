package com.upscalews.will.simpletodo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = (ListView) findViewById(R.id.listView);

        items = readItems();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        listViewItems.setAdapter(itemsAdapter);

        listViewItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    items.remove(position);
                    itemsAdapter.notifyDataSetChanged();
                    writeItems(items);
                    return true;
                }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem) ;
        String itemText = etNewItem.getText().toString();

        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems(items);
    }

    ArrayList<String> readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");

        try {
            return (ArrayList<String>) FileUtils.readLines(todoFile) ;
        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    private void writeItems(ArrayList<String> itms){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");

        try {
            FileUtils.writeLines(todoFile, itms);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
