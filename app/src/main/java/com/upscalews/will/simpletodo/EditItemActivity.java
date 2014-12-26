package com.upscalews.will.simpletodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditItemActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Button submit = (Button) findViewById(R.id.editSavebutton);

        String editText = getIntent().getStringExtra("itemText");
        final int itemPosition = getIntent().getIntExtra("itemPosition", 1);

        final EditText etEditItem = (EditText) findViewById(R.id.editText);

        etEditItem.setText(editText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemText = etEditItem.getText().toString();

                Intent dataIntent = new Intent();

                dataIntent.putExtra("editedText", itemText);
                dataIntent.putExtra("itemPosition", itemPosition);

                setResult(RESULT_OK, dataIntent);

                EditItemActivity.this.finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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



}
