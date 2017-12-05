package edu.unc.jay.tj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jay on 12/3/2017.
 */

public class DatabaseActivity extends AppCompatActivity {
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_view);

        db = openOrCreateDatabase("CW4DB", Context.MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS History");
        db.execSQL("CREATE TABLE History (id INTEGER, opponent TEXT, result INTEGER, date_played TEXT)");

        db.execSQL("INSERT INTO History VALUES ('1', 'oscp2000', 'win', 'Nov 30, 2017')");
        db.execSQL("INSERT INTO History VALUES ('2', 'oscp2000', 'win', 'Nov 30, 2017')");
        db.execSQL("INSERT INTO History VALUES ('3', 'bdioni', 'loss', 'Dec 1, 2017')");
        db.execSQL("INSERT INTO History VALUES ('4', 'bdioni', 'win', 'Dec 1, 2017')");
        db.execSQL("INSERT INTO History VALUES ('5', 'oscp2000', 'loss', 'Dec 2, 2017')");
        db.execSQL("INSERT INTO History VALUES ('6', 'oscp2000', 'win', 'Dec 3, 2017')");
        db.execSQL("INSERT INTO History VALUES ('7', 'oscp2000', 'loss', 'Dec 3, 2017')");
        db.execSQL("INSERT INTO History VALUES ('8', 'bdioni', 'loss', 'Dec 3, 2017')");

        updateView();
    }

    public void updateView() {
        TextView tv = (TextView) findViewById(R.id.data);

        String q = "SELECT * FROM History";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        String str ="";


        for (int i = 0; i < c.getCount(); i++) {
            for (int j = 0; j < c.getColumnCount(); j++) {
                str += "     " + c.getString(j) + "              ";
            }
            str = str + '\n' + '\n' + '\n' + '\n';
            c.moveToNext();
        }

        tv.setText(str);

    }
}
