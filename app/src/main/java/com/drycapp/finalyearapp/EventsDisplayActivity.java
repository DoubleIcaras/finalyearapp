package com.drycapp.finalyearapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class EventsDisplayActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private DatabaseReference mDatabase;
    private EditText mSearch;
    private FirebaseRecyclerAdapter<Events, EventsDisplayActivity.EventsViewHolder> mEventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_display);

        setTitle("Events");

        //"Events" here will reflect what you have called your database in Firebase.
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        mDatabase.keepSynced(true);

        mRecylerView = findViewById(R.id.result_list);
        mSearch = findViewById(R.id.editTextSearch);




        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Events");
        Query query = dbRef.orderByKey();
        //Query search = dbRef.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        mRecylerView.hasFixedSize();
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Events>().setQuery(query, Events.class).build();

        mEventsAdapter = new FirebaseRecyclerAdapter<Events, EventsDisplayActivity.EventsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(EventsViewHolder holder, final int position, final Events model) {
                holder.setName(model.getName());
                holder.setDescription(model.getDescription());
                holder.setImage(getBaseContext(), model.getImage());
            }

            @Override
            public EventsDisplayActivity.EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.event_list, parent, false);

                return new EventsDisplayActivity.EventsViewHolder(view);
            }
        };

        mRecylerView.setAdapter(mEventsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mEventsAdapter.stopListening();


    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public EventsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }

        public void setName(String title){
            TextView post_title = mView.findViewById(R.id.event_title);
            post_title.setText(title);
        }
        public void setDescription(String desc){
            TextView post_desc = mView.findViewById(R.id.event_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context context, String image){
            ImageView post_image = mView.findViewById(R.id.event_image);
            Picasso.with(context).load(image).into(post_image);
        }
    }
}
