package com.example.cindywang.pebblequoter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class addQuoteActivity extends ActionBarActivity {
    String quote="";
    EditText newQuoteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_quote, menu);
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

    public void submit(View view){
        newQuoteString = (EditText) findViewById(R.id.inputQuoteEditText);
        quote = newQuoteString.getText().toString();




        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addQuoteActivity.this);
        if (quote.length() == 0 || quote.length() > 52){
            //answer complete
            //ask user if save answer
            alertDialogBuilder.setMessage("Unqualified Message...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {


                }
            });

        } else {
            //answer not complete
            //warning shown
            alertDialogBuilder.setMessage("You just pushed a quote :)");
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {
                    //add this answer info in "answerInProgress"
                    List<ParseObject> quoteObjects = new ArrayList<ParseObject>();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("QuoteObject");

                    try {
                        quoteObjects = query.find();
                    } catch (com.parse.ParseException e) {
                        e.printStackTrace();
                    }
                    ParseObject object;

                    if (quoteObjects.size() == 0){
                        object = new ParseObject("QuoteObject");
                    } else {
                        object = quoteObjects.get(0);
                    }
                    object.put("quotes", quote);
                    object.saveInBackground();
                    //intent to Question list
                    Intent i = new Intent(addQuoteActivity.this, MainActivity.class);
                    startActivity(i);
                }
            });

        }


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
