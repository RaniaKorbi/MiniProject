package com.example.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.Entities.CinemaEvent;

import java.util.List;

public class CinemaEventListAdapter extends RecyclerView.Adapter<CinemaEventListAdapter.CinemaEventViewHolder> {
private final LayoutInflater mInflater;
private List<CinemaEvent> mEvents; // Cached copy of words

        CinemaEventListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

@Override
public CinemaEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cinema_event_item, parent, false);
        return new CinemaEventViewHolder(itemView);
        }

@Override
public void onBindViewHolder(CinemaEventViewHolder holder, int position) {
        if (mEvents != null) {
        CinemaEvent current = mEvents.get(position);
        holder.eventItemView.setText(current.getMCinemaEvent());
        } else {
        // Covers the case of data not being ready yet.
        holder.eventItemView.setText("No Word");
        }
        }

        void setEvents(List<CinemaEvent> events){
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

class CinemaEventViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventItemView;

    private CinemaEventViewHolder(View itemView) {
        super(itemView);
        eventItemView = itemView.findViewById(R.id.CinemaEventTextView);
    }
}
    public CinemaEvent getEventAtPosition (int position) {
        return mEvents.get(position);
    }
}
