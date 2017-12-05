package customclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by we25 on 2017-07-03.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    Context mContext;
    public DBOpenHelper(Context context, int version) {
        super(context, Environment.getExternalStorageDirectory()+ File.separator+"EasyDB"+File.separator+DBName.DB , null,1);
        mContext = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText( mContext, "파일을 찾지 못해 생성을 시도했습니다." , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
