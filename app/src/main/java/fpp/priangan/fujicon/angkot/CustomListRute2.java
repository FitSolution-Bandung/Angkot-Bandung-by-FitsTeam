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
public class CustomListRute2 extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private String[] jrk;;
    private Activity context;

    public CustomListRute2(Activity context, String[] names, String[] desc, Integer[] imageid, String[] jrk) {
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
            textViewName.setText(Html.fromHtml("Mulai dari <i>" + names[0].trim().replace("null", "") + "</i><><br/>\n"));
            textViewDesc.setText(Html.fromHtml("Jalan kaki sampai <i>"+desc[0].trim().replace("null", "")+"\n"+"sejauh "+jrk[0].trim().replace("null","")+" m<br/><br/>\n"));
            //textViewDesc.setText(jrk[position].trim().replace("null",""));
        }else if(position==1) {
            textViewName.setText(Html.fromHtml("Naik angkot "+names[3].trim().replace("null","")));
        }else if(position==2){
            textViewName.setText(Html.fromHtml(desc[2].trim().replace("null","")+"\n"));

           /* double jrkm= Double.parseDouble(jrk[2].trim());
            double mtokm=jrkm/1000;
            double t=mtokm/5;
            double s=t*3600;
            int m= (int) (s/60);*/
          //  textViewName.setText("Turun dan berjalan dari "+names[0].trim().replace("null","")+"\n");
           // textViewDesc.setText("Menuju "+desc[0].trim().replace("null", "")+"\n"+"Sejauh "+jrk[2].trim().replace("null","")+" meter estimasi "+m+" menit");
        }else if(position==3){
            double jrkm= Double.parseDouble(jrk[3].trim());
            double mtokm=jrkm/1000;
            double t=mtokm/5;
            double s=t*3600;
            int m= (int) (s/60);
           textViewName.setText(Html.fromHtml("Jalan kaki sampai <i>"+desc[1].trim().replace("null", "")+"</i>\n"+"<br/>sejauh "+jrk[3].trim().replace("null","")+" m\n"));
            textViewDesc.setText(Html.fromHtml("Tiba di tujuan"+names[2].trim().replace("null", "")));

        }

//        textViewName.setText("Turun di "+names[position].trim().replace("null","")+"\n");
//        textViewDesc.setText("menuju "+names[position].trim().replace("null", "")+"\n"+"sejauh "+jrk[position].trim().replace("null","")+" m,estimasi "+" menit.");
//
//        if(position==0){
//            double jrkm= Double.parseDouble(jrk[0].trim());
//            double mtokm=jrkm/1000;
//            double t=mtokm/5;
//            double s=t*3600;
//            int m= (int) (s/60);
//            textViewName.setText(Html.fromHtml("Jalan dari " + names[0].trim().replace("null", "") + "\n"));
//            textViewDesc.setText(Html.fromHtml("menuju "+desc[0].trim().replace("null", "")+"\n"+"sejauh "+jrk[0].trim().replace("null","")+" m, estimasi "+m+" menit."+"\n"));
//            //textViewDesc.setText(jrk[position].trim().replace("null",""));
//        }else if(position==1) {
//            textViewName.setText(Html.fromHtml("Naik angkot "+names[3].trim().replace("null","")+"\n"+ "dari "+desc[0].trim().replace("null","")+"\n"));
//            textViewDesc.setText(Html.fromHtml("menuju "+names[1].trim().replace("null", "")+"\n"+"sejauh "+jrk[1].trim().replace("null","")+" km."+"\n"));
//        }else if(position==2){
//            textViewName.setText(Html.fromHtml("Turun di "+names[1].trim().replace("null","")+"\n"+desc[2].trim().replace("null","")+"\n"));
//            textViewDesc.setText(Html.fromHtml("menuju "+desc[1].trim().replace("null","")+".\n"+"sejauh "+jrk[2].trim().replace("null","")+" m."+"\n"));
//
//           /* double jrkm= Double.parseDouble(jrk[2].trim());
//            double mtokm=jrkm/1000;
//            double t=mtokm/5;
//            double s=t*3600;
//            int m= (int) (s/60);*/
//            //  textViewName.setText("Turun dan berjalan dari "+names[0].trim().replace("null","")+"\n");
//            // textViewDesc.setText("Menuju "+desc[0].trim().replace("null", "")+"\n"+"Sejauh "+jrk[2].trim().replace("null","")+" meter estimasi "+m+" menit");
//        }else if(position==3){
//            double jrkm= Double.parseDouble(jrk[3].trim());
//            double mtokm=jrkm/1000;
//            double t=mtokm/5;
//            double s=t*3600;
//            int m= (int) (s/60);
//            textViewName.setText(Html.fromHtml("Turun di "+desc[1].trim().replace("null","")+"\n"));
//            textViewDesc.setText(Html.fromHtml("menuju "+names[2].trim().replace("null", "")+"\n"+"sejauh "+jrk[3].trim().replace("null","")+" m,estimasi "+m+" menit."));
//
//        }
        image.setImageResource(imageid[position]);
        return  listViewItem;
    }
}
