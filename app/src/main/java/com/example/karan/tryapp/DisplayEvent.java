package com.example.karan.tryapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayEvent extends AppCompatActivity {
    private String eventId;
    private JSONObject event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        Intent intent = getIntent();
        eventId = intent.getStringExtra(MyListActivity.EXTRA_MESSAGE);
        System.out.print(eventId);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(eventId);
        new GetEvent().execute();

    }
    private void  updateEvent(){
        try{
            String eventName = event.get("name").toString();
            String eventLocation = event.get("venue").toString();
            String eventDate = event.get("date").toString() + " at " +event.get("time").toString();
            String eventDescription = event.get("description").toString();
            TextView location = (TextView) findViewById(R.id.location);
            location.setText(eventLocation);
            TextView date = (TextView) findViewById(R.id.date);
            date.setText(eventDate);
            TextView description = (TextView) findViewById(R.id.description);
            description.setText(eventDescription);

            System.out.println(eventName);

        }catch (JSONException e){

        }
    }


    public class GetEvent extends AsyncTask<Void, Void, Boolean> {
        private String toast;

        public GetEvent() {
            toast = null;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            System.out.println("do in background started");
            HttpHelper httpHelper = new HttpHelper("http://192.168.43.23:3000");
            event = httpHelper.getJsonObject("/events/"+eventId +".json", "", "");
            if(event == null) {
                toast = "Connection problem...";
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if(success) {
                updateEvent();
            } else {
                Toast.makeText(getBaseContext(), "Connection problem...", Toast.LENGTH_LONG).show();
            }
        }
    }
}
