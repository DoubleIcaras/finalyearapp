
package com.drycapp.finalyearapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class EventDetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    private DatabaseReference mDatabase;
    private DatabaseReference mEventReference;
    private ValueEventListener mEventListener;
    private String mEventKey;

    private TextView mDetailName;
    private TextView mDetailDesc;
    private ImageView mDetailImage;
    private static final String TAG = "EventDetailActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        //Getting the selected event from intent
        mEventKey = getIntent().getStringExtra(EXTRA_POSITION);
        if(mEventKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POSITION");
        }
        //Init the DB
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events").child(mEventKey);
        mDatabase.keepSynced(true);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        //Init Views
        mDetailName = findViewById(R.id.event_title_detail);
        mDetailDesc = findViewById(R.id.event_desc_detail);
        mDetailImage = findViewById(R.id.event_image_detail);
    }

    public void onStart() {
        super.onStart();

        // Event Listener to the view
        // [Start event_value_event_listener]
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Event Object and use the values to update said UI
                Events events = dataSnapshot.getValue(Events.class);
                // [START_EXCLUDE]
                mDetailName.setText(events.name);
                mDetailDesc.setText(events.description);
                // [END_EXCLUDE]]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(EventDetailActivity.this, "Failed to load events.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]

            }
        };
        mEventReference.addValueEventListener(eventListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mEventListener = eventListener;
    }

    @Override
    public void onStop() {
        super.onStop();
        // Remove post value event listener
        if (mEventListener != null) {
            mEventReference.removeEventListener(mEventListener);
        }
    }
}
