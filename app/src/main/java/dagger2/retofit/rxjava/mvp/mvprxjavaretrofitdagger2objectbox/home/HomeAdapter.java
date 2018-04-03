package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.R;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.Datum;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.Images;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Datum> data;
    private Context context;

    public HomeAdapter(Context context, List<Datum> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);

        Images images = data.get(position).getImages();

        Glide.with(context)
                .asBitmap()
                .load(images.get480wStill().getUrl())
                .into(holder.background);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(Datum Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.image);
        }


        public void click(final Datum cityListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onClick(cityListData));
        }
    }


}
