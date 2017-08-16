package craftosaka.syukupili.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import craftosaka.syukupili.R;
import model.KadListItem;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListRecyclerAdapter extends RecyclerView.Adapter<KadListRecyclerAdapter.RecyclerViewHolder> {

    List<KadListItem> list;
    LayoutInflater layoutInflater;

    public KadListRecyclerAdapter(Context context, List<KadListItem> itemList){
        layoutInflater = LayoutInflater.from(context);
        list = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.kadlist_item,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
