package craftosaka.syukupili.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.model.User;
import craftosaka.syukupili.util.App;
import craftosaka.syukupili.util.PrefUtil;
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
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.childName.setText(list.get(position).getChildName());
        holder.pointText.setText(String.valueOf(list.get(position).getPoint()));
        holder.endDateText.setText(Util.getInstance().correctDate(String.valueOf(list.get(position).getEndDate())));
        holder.kadName.setText(list.get(position).getKadName());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = Util.searchUser(list.get(position).getChildId());

                if (user == null) {
                    Toast.makeText(App.getAppContext(), "error", Toast.LENGTH_SHORT).show();
                } else {
                    user.pointAdd(list.get(position).getPoint());
                    PrefUtil.updateUserItem(user);
                    list.remove(position);
                    notifyItemChanged(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView childName, pointText, endDateText, kadName;
        ImageButton imageButton;

        public RecyclerViewHolder(View v) {
            super(v);
            childName = v.findViewById(R.id.child_name_text);
            pointText = v.findViewById(R.id.point_text);
            endDateText = v.findViewById(R.id.end_date_text);
            kadName = v.findViewById(R.id.kad_name_text);
            imageButton = v.findViewById(R.id.kad_check_image_button);
        }
    }
}
