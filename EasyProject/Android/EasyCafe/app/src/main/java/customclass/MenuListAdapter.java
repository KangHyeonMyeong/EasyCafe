package customclass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.we25.jinju.BasketLayout;

import java.util.ArrayList;

/**
 * Created by we25 on 2017-07-04.
 */

public class MenuListAdapter extends BaseAdapter {
    Context context;

    public MenuListAdapter(Context context) {
        this.context = context;
    }

    ArrayList<ItemBasket> list = new ArrayList<ItemBasket>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(ItemBasket itemBasket){
        list.add(itemBasket);
    }

    public void resetAdapter(){
        list.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BasketLayout layout = new BasketLayout(context);
        ItemBasket item = list.get(position);

        layout.setMenuName(item.getMenuName());
        layout.setInfo(item.getInfo());
        layout.setNum(item.getNum()+"");
        layout.setPrice(item.getPrice()+"");

        return layout;
    }
}
