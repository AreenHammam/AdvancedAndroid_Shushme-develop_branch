package com.example.android.NavSite;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.NavSite.Crawler.CrawlerActivity;
import com.google.android.gms.location.places.PlaceBuffer;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {

    private Context mContext;
    // this will act as our data source for the adapter
    private PlaceBuffer mPlaces;

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
    public PlaceListAdapter(Context context, PlaceBuffer places) {
        this.mContext = context;
        this.mPlaces = places;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item
     *
     * @param parent   The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View
     * @return A new PlaceViewHolder that holds a View with the item_place_card layout
     */
    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_place_card, parent, false);
        return new PlaceViewHolder(view);
    }

    /**
     * Binds the data from a particular position in the cursor to the corresponding view holder
     *
     * @param holder   The PlaceViewHolder instance corresponding to the required position
     * @param position The current position that needs to be loaded with data
     */
    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        String placeName = mPlaces.get(position).getName().toString();
        String placeAddress = mPlaces.get(position).getAddress().toString();
        holder.nameTextView.setText(placeName);
        holder.addressTextView.setText(placeAddress);



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onclick(View v, int position, boolean isLongClick) {

                if(isLongClick)
                    Toast.makeText(mContext, "Long Click: " + mPlaces.get(position), Toast.LENGTH_SHORT);


                    else {
                    Toast.makeText(mContext, "Short Click: " + mPlaces.get(position), Toast.LENGTH_SHORT);
                    Intent intent = new Intent(mContext,itemRecycleViewActivity.class);
                    intent.putExtra("address",mPlaces.get(position).getAddress());
                    intent.putExtra("name",mPlaces.get(position).getName());
                    intent.putExtra("id",mPlaces.get(position).getId());

                    // https://www.youtube.com/watch?v=ZXoGG2XTjzU
                    // for images

                    mContext.startActivity(intent);


                    }
            }
        });
    }

    public void swapPlaces(PlaceBuffer newPlaces){
        mPlaces = newPlaces;
        if (mPlaces != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    /**
     * Returns the number of items in the cursor
     *
     * @return Number of items in the cursor, or 0 if null
     */
    @Override
    public int getItemCount() {
        if(mPlaces==null) return 0;
        return mPlaces.getCount();
    }

    /**
     * PlaceViewHolder class for the recycler view item
     */
    class PlaceViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener ,View.OnLongClickListener {

        TextView nameTextView;
        TextView addressTextView;
        private ItemClickListener itemClickListener;
        public LinearLayout linearLayout;


        public PlaceViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            addressTextView = (TextView) itemView.findViewById(R.id.address_text_view);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        public void setItemClickListener (ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onclick(v,getAdapterPosition(),true);
            return true;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onclick(v,getAdapterPosition(),false);


        }
    }
}
