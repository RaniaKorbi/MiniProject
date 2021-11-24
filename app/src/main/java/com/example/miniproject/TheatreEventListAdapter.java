package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniproject.Entities.TheatreEvent;
import java.util.List;

public class TheatreEventListAdapter extends RecyclerView.Adapter<TheatreEventListAdapter.TheatreEventViewHolder>{
    private final LayoutInflater mInflater;
    private List<TheatreEvent> mEvents; // Cached copy of words

    TheatreEventListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TheatreEventListAdapter.TheatreEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cinema_event_item, parent, false);
        return new TheatreEventListAdapter.TheatreEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TheatreEventListAdapter.TheatreEventViewHolder holder, int position) {

        if (mEvents != null) {
            TheatreEvent current = mEvents.get(position);
            holder.eventItemView.setText(current.getTheatreEvent());
            holder.eventItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReservationActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("No Word");

        }
    }

    void setEvents(List<TheatreEvent> events){
        mEvents = events;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
// mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEvents != null)
            return mEvents.size();
        else return 0;
    }

    class TheatreEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
        private final TextView eventItemView;

        private TheatreEventViewHolder(View itemView) {
            super(itemView);
            eventItemView = itemView.findViewById(R.id.CinemaEventTextView);
        }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservationActivity.class);
                v.getContext().startActivity(intent);
            }

    }
    public TheatreEvent getEventAtPosition (int position) {
        return mEvents.get(position);
    }
}
