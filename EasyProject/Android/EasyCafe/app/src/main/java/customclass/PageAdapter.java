package customclass;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.we25.easycafe.R;

/**
 * Created by we25 on 2017-06-23.
 */

public class PageAdapter extends PagerAdapter {
    LayoutInflater inflater;

    public PageAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = inflater.inflate(R.layout.viewpager_child , null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.coffe_1);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView( (View)object);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==object);
    }
}
