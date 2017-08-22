package craftosaka.syukupili.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.PointListItem;

/**
 * Created by yocchi on 2017/08/16.
 */

public class PointListRecyclerAdapter extends RecyclerView.Adapter<PointListRecyclerAdapter.RecyclerViewHolder> {

    List<PointListItem> list;
    LayoutInflater layoutInflater;

    public PointListRecyclerAdapter(Context c, List<PointListItem> list) {
        layoutInflater = LayoutInflater.from(c);
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.point_list_item,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Log.d("PointListRecyclerAdapte", String.valueOf(position));
        holder.pointName.setText(list.get(position).getPointItemText());
        holder.usePointText.setText(list.get(position).getPointText());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView pointName;
        Button btn;
        TextView usePointText;
        public RecyclerViewHolder(View v) {
            super(v);
            pointName = v.findViewById(R.id.point_item_name);

            usePointText = v.findViewById(R.id.consume_point_text);
        }
    }
}
