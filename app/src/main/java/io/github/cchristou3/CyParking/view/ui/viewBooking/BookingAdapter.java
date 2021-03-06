package io.github.cchristou3.CyParking.view.ui.viewBooking;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import io.github.cchristou3.CyParking.R;
import io.github.cchristou3.CyParking.view.data.pojo.parking.booking.PrivateParkingBooking;

/**
 * Purpose: <p> Handles how each item of the RecyclerView will look like. </p>
 *
 * @author Charalambos Christou
 * @version 1.0 05/11/20
 */
public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    private static final long DURATION = 500;
    private static View.OnClickListener mOnItemClickListener;
    private final List<PrivateParkingBooking> mDataset;
    private boolean onAttach = true;

    public BookingAdapter(List<PrivateParkingBooking> mDataset) {
        this.mDataset = mDataset;
    }

    public void setOnItemClickListener(View.OnClickListener mOnItemClickListener) {
        BookingAdapter.mOnItemClickListener = mOnItemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent,
                                                          int viewType) {
        // create and return a new view
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_item, parent, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        setAnimation(holder.itemView, position);

        final String price = Double.toString(mDataset.get(position).getPrice());
        final CharSequence date = "Date: " + DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
                .format(mDataset.get(position).getDateOfBooking());
        final String time = "Time: " + mDataset.get(position).getStartingTime()
                .concat(" - ").concat(mDataset.get(position).getEndingTime());
        final String status = "Status: " + (mDataset.get(position).isCompleted() ? "Completed" : "Pending");
        final String parkingName = "Parking: " + mDataset.get(position).getParkingName();

        holder.status.setText(status);
        holder.price.setText(price);
        holder.parking_name.setText(parkingName);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                onAttach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View itemView, int i) {
        if (!onAttach) {
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView status;
        public TextView price;
        public TextView parking_name;
        public TextView date;
        public TextView time;
        public ImageButton cancelButton;

        public MyViewHolder(View view) {
            super(view);
            status = view.findViewById(R.id.booking_placeholder_item_txt_status);
            price = view.findViewById(R.id.booking_placeholder_item_txt_price);
            parking_name = view.findViewById(R.id.booking_placeholder_item_txt_parking_name);
            date = view.findViewById(R.id.booking_placeholder_item_txt_date);
            time = view.findViewById(R.id.booking_placeholder_item_txt_time);
            cancelButton = view.findViewById(R.id.booking_placeholder_item_btn_cancel);
            cancelButton.setTag(this);
            cancelButton.setOnClickListener(mOnItemClickListener);
        }

    }
}
