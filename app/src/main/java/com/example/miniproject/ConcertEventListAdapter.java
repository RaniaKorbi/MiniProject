package com.example.miniproject;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniproject.Entities.ConcertEvent;


import java.util.List;

public class ConcertEventListAdapter extends RecyclerView.Adapter<ConcertEventListAdapter.ConcertEventViewHolder>{
    private final LayoutInflater mInflater;
    private List<ConcertEvent> mEvents; // Cached copy of words
    Context context;
    ConcertEventListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ConcertEventListAdapter.ConcertEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cinema_event_item, parent, false);
        return new ConcertEventListAdapter.ConcertEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConcertEventListAdapter.ConcertEventViewHolder holder, int position) {


        if (mEvents != null) {
            ConcertEvent current = mEvents.get(position);
            holder.eventItemView.setText(current.getConcertEvent());
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("No Word");
        }

    }

    void setEvents(List<ConcertEvent> events){
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

    class ConcertEventViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        private final TextView eventItemView;
        public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
        private ConcertEventViewHolder(View itemView) {
            super(itemView);
            eventItemView = itemView.findViewById(R.id.CinemaEventTextView);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ReservationActivity.class);
            v.getContext().startActivity(intent);
        }

    }
    public ConcertEvent getEventAtPosition (int position) {
        return mEvents.get(position);
    }
}
