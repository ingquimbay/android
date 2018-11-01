package android.concam;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by carlitos on 9/13/17.
 */

public class ContactCursor extends CursorAdapter {

    private static final int CONTACT_ID_INDEX = 0;
    private static final int DISPLAY_NAME_INDEX = 1;

    public ContactCursor(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.contact_list_layout,
                parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvIdContacto = (TextView) view.findViewById(R.id.id_contacto);
        TextView tvNomContacto = (TextView) view.findViewById(R.id.name_contacto);
        int idnum = cursor.getInt(CONTACT_ID_INDEX);
        String nombre = cursor.getString(DISPLAY_NAME_INDEX);
        tvIdContacto.setText(String.valueOf(idnum));
        tvNomContacto.setText(nombre);
    }
}
