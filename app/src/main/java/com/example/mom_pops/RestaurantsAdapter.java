package com.example.mom_pops;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
    private List<Restaurant> restaurant_list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, food_type, price_range, phone_number, address, miles_away;
        public ImageView profile;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.text_restaurant_name);
            food_type = (TextView) view.findViewById(R.id.text_food_type);
            price_range = (TextView) view.findViewById(R.id.text_price_range);
            phone_number = (TextView) view.findViewById(R.id.text_restaurant_phone_number);
            address = (TextView) view.findViewById(R.id.text_restaurant_address);
            miles_away = (TextView) view.findViewById(R.id.text_miles_away);
            profile = (ImageView) view.findViewById(R.id.image_restaurant_profile);
        }
    }

    public RestaurantsAdapter(List<Restaurant> restaurant_list) {
        this.restaurant_list = restaurant_list;
    }

    @Override @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int view_ype) {
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_row, parent, false);
        return new MyViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Restaurant restaurant = restaurant_list.get(position);
        holder.name.setText(restaurant.getName());
        holder.food_type.setText(restaurant.getFoodType());
        holder.price_range.setText(restaurant.getPriceRange());
        holder.phone_number.setText(restaurant.getPhoneNumber());
        holder.address.setText(restaurant.getAddress());
        holder.miles_away.setText(restaurant.getMilesAway());
        holder.profile.setImageResource(restaurant.getImage());
    }

    @Override
    public int getItemCount() {
        return restaurant_list.size();
    }
}
