package craftosaka.syukupili.ui.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.PointListItem;
import craftosaka.syukupili.ui.fragment.PointExchangeConfirmDialog;
import craftosaka.syukupili.util.App;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.NotifyUtil;
import craftosaka.syukupili.util.PrefUtil;
import craftosaka.syukupili.util.Util;

/**
 * Created by yocchi on 2017/08/16.
 */

public class PointListRecyclerAdapter extends RecyclerView.Adapter<PointListRecyclerAdapter.RecyclerViewHolder> {

    List<PointListItem> list;
    LayoutInflater layoutInflater;
    Context context;
    FragmentManager fm;
    CoordinatorLayout root;
    public PointListRecyclerAdapter(Context c, List<PointListItem> list, FragmentManager fm, CoordinatorLayout root) {
        layoutInflater = LayoutInflater.from(c);
        this.list = list;
        context = c;
        this.fm = fm;
        this.root = root;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.point_list_item,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Log.d("PointListRecyclerAdapte", String.valueOf(position));
        holder.pointName.setText(list.get(position).getPointItemName());
        holder.consumePoint.setText(list.get(position).getPoint());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Data.getInstance().parentFrag){
                    PointExchangeConfirmDialog dialog = new PointExchangeConfirmDialog();
                    dialog.setNeedInfo(list.get(position).getPointItemName(),Integer.parseInt(list.get(position).getPoint()),Data.getInstance().getNowUser().getPoint());
                    dialog.setCallback(new PointExchangeConfirmDialog.MyCallback() {
                        @Override
                        public void positive(Boolean bool) {
                            if(bool){
                                Data.getInstance().getNowUser().pointMinus(Integer.parseInt(list.get(position).getPoint()));
                                NotifyUtil.Notify(root);
                            }else{
                                NotifyUtil.notifyFailed(root);
                            }
                        }

                        @Override
                        public void negative() {

                        }
                    });
                    dialog.show(fm,"");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView pointName;
        TextView consumePoint;
        public RecyclerViewHolder(View v) {
            super(v);
            pointName = v.findViewById(R.id.point_item_name);

            consumePoint = v.findViewById(R.id.consume_point_text);
        }
    }
}
