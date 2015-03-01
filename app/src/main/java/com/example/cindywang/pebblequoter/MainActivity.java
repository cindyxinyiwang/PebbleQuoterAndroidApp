package com.example.cindywang.pebblequoter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    String quote="";
    TextView quoteTextView;
    List<ParseObject> quoteObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "a6fH6KKCSCeUqHLFqUlwspOOs8OUgploceRxIHps", "CvtYHVchwrBBEpOGnugwluwdbdD80N3EtLB1vYk1");
        quoteObjects = new ArrayList<ParseObject>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuoteObject");

        /*
        ParseObject test = new ParseObject("QuoteObject");
        test.put("quotes", "this is test");
        test.saveInBackground(); */
/*
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {
                if (parseObject == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {
                    Log.d("score", "Retrieved the object.");
                    quote = parseObject.getString("quotes");
                    quoteTextView = (TextView) findViewById(R.id.quoteTextView);
                    quoteTextView.setText(quote);
                }
            }
        }); */

        try {
            quoteObjects = query.find();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }

        if (quoteObjects.size() == 0){
            quote = "No quote at this time :(";
        } else {
            quote = (quoteObjects.get(0)).getString("quotes");
        }

        //quote = "test";
        quoteTextView = (TextView) findViewById(R.id.quoteTextView);
        quoteTextView.setText(quote);





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

    public void createNew(View view){
        Intent i = new Intent(MainActivity.this, addQuoteActivity.class);
        startActivity(i);

    }
}
