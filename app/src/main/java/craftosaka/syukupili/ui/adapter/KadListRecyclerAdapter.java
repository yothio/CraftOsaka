package craftosaka.syukupili.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.Util;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListRecyclerAdapter extends RecyclerView.Adapter<KadListRecyclerAdapter.RecyclerViewHolder> {

    List<KadListItem> list;
    LayoutInflater layoutInflater;

    public KadListRecyclerAdapter(Context context, List<KadListItem> itemList) {
        layoutInflater = LayoutInflater.from(context);
        list = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.kadlist_item, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.childName.setText(list.get(position).getChildName());
        holder.pointText.setText(String.valueOf(list.get(position).getPoint()));
        holder.endDateText.setText(Util.getInstance().correctDate(String.valueOf(list.get(position).getEndDate())));
        holder.kadName.setText(list.get(position).getKadName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView childName, pointText, endDateText, kadName;

        public RecyclerViewHolder(View v) {
            super(v);
            childName = v.findViewById(R.id.child_name_text);
            pointText = v.findViewById(R.id.point_text);
            endDateText = v.findViewById(R.id.end_date_text);
            kadName = v.findViewById(R.id.child_name_text);
        }
    }
}
