package customclass;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.we25.easycafe.R;

/**
 * Created by we25 on 2017-07-11.
 */

public class StatusLayout extends LinearLayout {
    TextView title , status;
    public StatusLayout(Context context) {
        super(context);
        init(context);
    }

    public StatusLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.statuslist, this, true);

        title = (TextView) findViewById(R.id.listTitle);
        status = (TextView) findViewById(R.id.statusValue);

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }
}
