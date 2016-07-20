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
public class CustomListRute extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private String[] jrk;;
    private Activity context;

    public CustomListRute(Activity context, String[] names, String[] desc, Integer[] imageid, String[] jrk) {
        super(context, R.layout.list_layout_rute, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.jrk = jrk;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout_rute, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName1);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc1);
        TextView txtjrk = (TextView) listViewItem.findViewById(R.id.txtjrk1);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);


        if(position==0){
            double jrkm= Double.parseDouble(jrk[0].trim());
            double mtokm=jrkm/1000;
            double t=mtokm/5;
            double s=t*3600;
            int m= (int) (s/60);
            textViewName.setText(Html.fromHtml("Mulai dari <i>"+names[0].trim().replace("null","")+"</i>\n"));
            textViewDesc.setText(Html.fromHtml("Jalan kaki sampai <i>"+desc[0].trim().replace("null", "")+"</i>\n"+"sejauh "+jrk[0].trim().replace("null","")+" m"+"<br/><br/>\n\n"));

        }else if(position==1) {
            textViewName.setText(Html.fromHtml("Naik Angkot ["+names[1].trim().replace("null","")+"]<br/> sejauh "+jrk[1].trim().replace("null","")+" km"+"\n"));
            textViewDesc.setText(Html.fromHtml("Ongkos biasanya sih "+desc[2].trim().replace("null", "")+" (?)"+"<br/><br/>\n\n"));
        }else if(position==2){
            double jrkm= Double.parseDouble(jrk[2].trim());
            double mtokm=jrkm/1000;
            double t=mtokm/5;
            double s=t*3600;
            int m= (int) (s/60);
            textViewName.setText(Html.fromHtml("Jalan kaki sampai <i>"+desc[1].trim().replace("null", "")+"</i>\n"+"sejauh "+jrk[2].trim().replace("null","")+" m\n"));
            textViewDesc.setText("Tiba di tujuan "+desc[1].trim().replace("null", ""));
        }


//        if(position==0){
//            double jrkm= Double.parseDouble(jrk[0].trim());
//            double mtokm=jrkm/1000;
//            double t=mtokm/5;
//            double s=t*3600;
//            int m= (int) (s/60);
//            textViewName.setText("Jalan kaki dari "+names[0].trim().replace("null","")+"\n");
//            textViewDesc.setText("menuju "+desc[0].trim().replace("null", "")+"\n"+"sejauh "+jrk[0].trim().replace("null","")+" m, estimasi "+m+" menit."+"\n");
//            //textViewDesc.setText(jrk[position].trim().replace("null",""));
//        }else if(position==1) {
//            textViewName.setText("Naik angkot dari "+desc[0].trim().replace("null","")+"\n");
//            textViewDesc.setText("menuju "+names[1].trim().replace("null", "")+"\n"+"sejauh "+jrk[1].trim().replace("null","")+" km."+"\n");
//        }else if(position==2){
//            double jrkm= Double.parseDouble(jrk[2].trim());
//            double mtokm=jrkm/1000;
//            double t=mtokm/5;
//            double s=t*3600;
//            int m= (int) (s/60);
//            textViewName.setText("Turun di "+names[1].trim().replace("null","")+",\n");
//            textViewDesc.setText("dan jalan kaki menuju "+desc[1].trim().replace("null", "")+"\n"+"sejauh "+jrk[2].trim().replace("null","")+" m, estimasi "+m+" menit.");
//        }
        image.setImageResource(imageid[position]);
        return  listViewItem;
    }
}
