package fpp.priangan.fujicon.angkot;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomListhome extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private String[] jrk;;
    private Activity context;

    public CustomListhome(Activity context, String[] names, String[] desc, Integer[] imageid, String[] jrk) {
        super(context, R.layout.list_layout_home, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.jrk = jrk;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout_home, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);
        TextView txtjrk = (TextView) listViewItem.findViewById(R.id.txtjrk);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);

//        textViewName.setText(Html.fromHtml("<b>Rute 1 :</b> <br/>Mulai dari : "+names[position].trim().replace("null","")+"<br>"+"menuju : "+desc[position].trim().replace("null", "")+"<br/>"+"sejauh "+jrk[position].trim().replace("null","")+" km.<br/><br/>"));
//        textViewDesc.setText(Html.fromHtml("<b>Rute 2 :</b> <br/>Mulai dari : "+desc[position].trim().replace("null","")+"<br>"+"menuju : "+names[position].trim().replace("null", "")+"<br/>"+"sejauh "+jrk[position].trim().replace("null","")+" km.<br/><br/>"));

        textViewName.setText(Html.fromHtml("<b>Rute 1 :</b> <br/>Mulai dari : "+names[position].trim().replace("null","")+"<br>"+"menuju : "+desc[position].trim().replace("null", "")+" <br/><br/>"));
        textViewDesc.setText(Html.fromHtml("<b>Rute 2 :</b> <br/>Mulai dari : "+desc[position].trim().replace("null","")+"<br>"+"menuju : "+names[position].trim().replace("null", "")+" <br/><br/>"));
        //textViewDesc.setText(jrk[position].trim().replace("null",""));
        image.setImageResource(imageid[position]);
        return  listViewItem;
    }
}
