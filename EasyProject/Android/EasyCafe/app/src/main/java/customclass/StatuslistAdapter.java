package customclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by we25 on 2017-07-11.
 */

public class StatuslistAdapter extends BaseAdapter {
    ArrayList<String> listTitle = new ArrayList<String>();
    HashMap<String, String> listStatus = new HashMap<String, String>();
    Context context;
    public StatuslistAdapter(Context context,ArrayList<String> listTitle ,HashMap<String, String> listStatus) {
            this.context = context;
        this.listTitle = listTitle;
        this.listStatus = listStatus;
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return listTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addList(String title, String status){
        listTitle.add(title);
        listStatus.put(title , status);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatusLayout layout = new StatusLayout(context);
        layout.setTitle(listTitle.get(position));
        layout.setStatus(listStatus.get(listTitle.get(position)));
        return layout;
    }
}
