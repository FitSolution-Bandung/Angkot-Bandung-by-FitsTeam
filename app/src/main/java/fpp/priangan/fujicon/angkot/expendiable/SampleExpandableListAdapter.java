package fpp.priangan.fujicon.angkot.expendiable;
 
import java.util.HashMap;  
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;

import fpp.priangan.fujicon.angkot.R;

public class SampleExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter  {
	public Context context;
	CheckBox checkBox;
    private LayoutInflater vi;
    private String[][] data;
    int _objInt;
    public static Boolean checked[] = new Boolean[1];
  
    HashMap<Long,Boolean> checkboxMap = new HashMap<Long,Boolean>();
    private static final int GROUP_ITEM_RESOURCE = R.layout.group_item;
    private static final int CHILD_ITEM_RESOURCE = R.layout.child_item;
    public String []check_string_array;
    
    public SampleExpandableListAdapter(Context context, Activity activity, String[][] data) {
        this.data = data;
        this.context = context;
        vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        _objInt = data.length; 
        check_string_array = new String[_objInt];
        popolaCheckMap();
    }
    public void popolaCheckMap(){

    	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);  
    	String buffer = null;
        
        for(int i=0; i<_objInt; i++){
        	buffer = settings.getString(String.valueOf((int)i),"false");
        	if(buffer.equals("false"))
        		checkboxMap.put((long)i, false);
        	else checkboxMap.put((long)i, true);
        }
    }
    
    public class CheckListener implements OnCheckedChangeListener{

        long pos;

        public void setPosition(long p){
            pos = p;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked) {
        	Log.i("checkListenerChanged", String.valueOf(pos)+":"+String.valueOf(isChecked));
            checkboxMap.put(pos, isChecked); 
            if(isChecked == true) check_string_array[(int)pos] = "true";
            else				  check_string_array[(int)pos] = "false";
           // save checkbox state of each group
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);                 
            SharedPreferences.Editor preferencesEditor = settings.edit(); 
            preferencesEditor.putString(String.valueOf((int)pos), check_string_array[(int)pos]);
            preferencesEditor.commit(); 
        }
    }
    public String getChild(int groupPosition, int childPosition) {
        return data[groupPosition][childPosition];
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return data[groupPosition].length;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        String child = getChild(groupPosition, childPosition);
        int id_res = 0; 
        if(groupPosition == 0){ 
        	if(childPosition == 0) id_res = R.drawable.a12;
        }
        else if(groupPosition == 1){ 
        	if(childPosition == 0) id_res = R.drawable.b01;
        	if(childPosition == 1) id_res = R.drawable.a02;
            if(childPosition == 2) id_res = R.drawable.a03;
            if(childPosition == 3) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 4) id_res = R.drawable.a12;
            if(childPosition == 5) id_res = R.drawable.a10;
        }
        else if(groupPosition == 2){ 
        	if(childPosition == 0) id_res = R.drawable.a04;
        	if(childPosition == 1) id_res = R.drawable.antapani_ciroyom;
        	if(childPosition == 2) id_res = R.drawable.a08;
            if(childPosition == 3) id_res = R.drawable.cibiru_cicadas;
            if(childPosition == 4) id_res = R.drawable.cicadas_elang;
            if(childPosition == 5) id_res = R.drawable.a07;
            if(childPosition == 6) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 7) id_res = R.drawable.a16;
            if(childPosition == 8) id_res = R.drawable.a12;
            if(childPosition == 9) id_res = R.drawable.a15;
        }
        else if(groupPosition == 3){
            if(childPosition == 0) id_res = R.drawable.a18;
        }
        else if(groupPosition == 4){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
        }
        else if(groupPosition == 5){
            if(childPosition == 0) id_res = R.drawable.a04;
            if(childPosition == 1) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 2) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 3) id_res = R.drawable.cisitu_tegalega;
        }
        else if(groupPosition == 6){
            if(childPosition == 0) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 1) id_res = R.drawable.cikudapateuh_ciroyom;
        }
        else if(groupPosition == 7){
            if(childPosition == 0) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 1) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 2) id_res = R.drawable.cikudapateuh_ciroyom;
        }
        else if(groupPosition == 8){
            if(childPosition == 0) id_res = R.drawable.a03;
            if(childPosition == 1) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 2) id_res = R.drawable.cicadas_elang;
            if(childPosition == 3) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 4) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 5) id_res = R.drawable.a12;
        }
        else if(groupPosition == 9){
            if(childPosition == 0) id_res = R.drawable.a17;
            if(childPosition == 1) id_res = R.drawable.a10;
            if(childPosition == 2) id_res = R.drawable.cijerah_ciwastra;
        }
        else if(groupPosition == 10){
            if(childPosition == 0) id_res = R.drawable.a08;
            if(childPosition == 1) id_res = R.drawable.cibaduyut_karang_setra;
        }
        else if(groupPosition == 11){
            if(childPosition == 0) id_res = R.drawable.cibiru_cicadas;
        }
        else if(groupPosition == 12){
            if(childPosition == 0) id_res = R.drawable.a03;
            if(childPosition == 1) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 2) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 3) id_res = R.drawable.a17;
            if(childPosition == 4) id_res = R.drawable.a06;
            if(childPosition == 5) id_res = R.drawable.a05;
            if(childPosition == 6) id_res = R.drawable.cisitu_tegalega;
            if(childPosition == 7) id_res = R.drawable.a11;
            if(childPosition == 8) id_res = R.drawable.a14;
            if(childPosition == 9) id_res = R.drawable.a15;
        }
        else if(groupPosition == 13){
            if(childPosition == 0) id_res = R.drawable.a03;
            if(childPosition == 1) id_res = R.drawable.a06;
            if(childPosition == 2) id_res = R.drawable.a05;
            if(childPosition == 3) id_res = R.drawable.a11;
            if(childPosition == 4) id_res = R.drawable.a15;
        }
        else if(groupPosition == 14){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.a20;
            if(childPosition == 2) id_res = R.drawable.a17;
            if(childPosition == 3) id_res = R.drawable.a06;
            if(childPosition == 4) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 5) id_res = R.drawable.a19;
        }
        else if(groupPosition == 15){
            if(childPosition == 0) id_res = R.drawable.a11;
        }
        else if(groupPosition == 16){
            if(childPosition == 0) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 1) id_res = R.drawable.cicadas_elang;
            if(childPosition == 2) id_res = R.drawable.a12;
        }
        else if(groupPosition == 17){
            if(childPosition == 0) id_res = R.drawable.a06;
            if(childPosition == 1) id_res = R.drawable.a16;
            if(childPosition == 2) id_res = R.drawable.a18;
        }
        else if(groupPosition == 18){
            if(childPosition == 0) id_res = R.drawable.a04;
            if(childPosition == 1) id_res = R.drawable.cibogo_elang;
            if(childPosition == 2) id_res = R.drawable.cicadas_elang;
        }
        else if(groupPosition == 19){
            if(childPosition == 0) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 1) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 2) id_res = R.drawable.cisitu_tegalega;
        }
        else if(groupPosition == 20){
            if(childPosition == 0) id_res = R.drawable.cicadas_elang;
            if(childPosition == 1) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 2) id_res = R.drawable.a12;
        }
        else if(groupPosition == 21){
            if(childPosition == 0) id_res = R.drawable.cibiru_cicadas;
            if(childPosition == 1) id_res = R.drawable.a12;
        }
        else if(groupPosition == 22){
            if(childPosition == 0) id_res = R.drawable.a02;
            if(childPosition == 1) id_res = R.drawable.a17;
            if(childPosition == 2) id_res = R.drawable.a09;
            if(childPosition == 3) id_res = R.drawable.a16;
        }
        else if(groupPosition == 23){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.a08;
            if(childPosition == 2) id_res = R.drawable.cibiru_cicadas;
            if(childPosition == 3) id_res = R.drawable.cicadas_elang;
            if(childPosition == 4) id_res = R.drawable.a07;
            if(childPosition == 5) id_res = R.drawable.a16;
            if(childPosition == 6) id_res = R.drawable.a18;
            if(childPosition == 7) id_res = R.drawable.a15;
        }
        else if(groupPosition == 24){
            if(childPosition == 0) id_res = R.drawable.a17;
        }
        else if(groupPosition == 25){
            if(childPosition == 0) id_res = R.drawable.a03;
            if(childPosition == 1) id_res = R.drawable.a20;
            if(childPosition == 2) id_res = R.drawable.a17;
            if(childPosition == 3) id_res = R.drawable.caringin_sadang_serang;
            if(childPosition == 4) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 5) id_res = R.drawable.cibogo_elang;
            if(childPosition == 6) id_res = R.drawable.cijerah_sederhana;
        }
        else if(groupPosition == 26){
            if(childPosition == 0) id_res = R.drawable.b01;
            if(childPosition == 1) id_res = R.drawable.a02;
            if(childPosition == 2) id_res = R.drawable.a03;
            if(childPosition == 3) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 4) id_res = R.drawable.cicadas_elang;
        }
        else if(groupPosition == 27){
            if(childPosition == 0) id_res = R.drawable.a20;
            if(childPosition == 1) id_res = R.drawable.a17;
            if(childPosition == 2) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 3) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 4) id_res = R.drawable.cisitu_tegalega;
            if(childPosition == 5) id_res = R.drawable.a11;
            if(childPosition == 6) id_res = R.drawable.a12;
            if(childPosition == 7) id_res = R.drawable.a14;
            if(childPosition == 8) id_res = R.drawable.a13;
        }
        else if(groupPosition == 28){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 2) id_res = R.drawable.caringin_sadang_serang;
            if(childPosition == 3) id_res = R.drawable.cicadas_elang;
            if(childPosition == 4) id_res = R.drawable.a11;
            if(childPosition == 5) id_res = R.drawable.a12;
            if(childPosition == 6) id_res = R.drawable.a14;
            if(childPosition == 7) id_res = R.drawable.a10;
            if(childPosition == 8) id_res = R.drawable.a13;
        }
        else if(groupPosition == 29){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.a08;
            if(childPosition == 2) id_res = R.drawable.cibiru_cicadas;
            if(childPosition == 3) id_res = R.drawable.cicadas_elang;
            if(childPosition == 4) id_res = R.drawable.a07;
            if(childPosition == 5) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 6) id_res = R.drawable.a16;
            if(childPosition == 7) id_res = R.drawable.a18;
            if(childPosition == 8) id_res = R.drawable.a15;
        }
        else if(groupPosition == 30){
            if(childPosition == 0) id_res = R.drawable.cijerah_ciwastra;
        }
        else if(groupPosition == 31){
            if(childPosition == 0) id_res = R.drawable.cicadas_elang;
            if(childPosition == 1) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 2) id_res = R.drawable.a12;
        }
        else if(groupPosition == 32){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 2) id_res = R.drawable.a20;
            if(childPosition == 3) id_res = R.drawable.a17;
            if(childPosition == 4) id_res = R.drawable.caringin_sadang_serang;
            if(childPosition == 5) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 6) id_res = R.drawable.cibogo_elang;
            if(childPosition == 7) id_res = R.drawable.cicadas_elang;
            if(childPosition == 8) id_res = R.drawable.a06;
            if(childPosition == 9) id_res = R.drawable.cijerah_sederhana;
            if(childPosition == 10) id_res = R.drawable.a19;
            if(childPosition == 11) id_res = R.drawable.cisitu_tegalega;
        }
        else if(groupPosition == 33){
            if(childPosition == 0) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 1) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 2) id_res = R.drawable.a20;
            if(childPosition == 3) id_res = R.drawable.a17;
            if(childPosition == 4) id_res = R.drawable.caringin_sadang_serang;
            if(childPosition == 5) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 6) id_res = R.drawable.cicadas_elang;
            if(childPosition == 7) id_res = R.drawable.a06;
            if(childPosition == 8) id_res = R.drawable.cijerah_sederhana;
            if(childPosition == 9) id_res = R.drawable.a19;
            if(childPosition == 10) id_res = R.drawable.cisitu_tegalega;
            if(childPosition == 11) id_res = R.drawable.a13;
            if(childPosition == 12) id_res = R.drawable.a11;
            if(childPosition == 13) id_res = R.drawable.a12;
            if(childPosition == 14) id_res = R.drawable.a14;
            if(childPosition == 15) id_res = R.drawable.a10;
        }
        else if(groupPosition == 34){
            if(childPosition == 0) id_res = R.drawable.a04;
            if(childPosition == 1) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 2) id_res = R.drawable.cikudapateuh_ciroyom;
            if(childPosition == 3) id_res = R.drawable.cisitu_tegalega;
        }
        else if(groupPosition == 35){
            if(childPosition == 0) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 1) id_res = R.drawable.a12;
        }
        else if(groupPosition == 36){
            if(childPosition == 0) id_res = R.drawable.cibaduyut_karang_setra;
            if(childPosition == 1) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 2) id_res = R.drawable.cikudapateuh_ciroyom;
        }else if(groupPosition == 37){
            if(childPosition == 0) id_res = R.drawable.a02;
        }
        else if(groupPosition == 38){
            if(childPosition == 0) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 1) id_res = R.drawable.cijerah_sederhana;
            if(childPosition == 2) id_res = R.drawable.a19;
            if(childPosition == 3) id_res = R.drawable.a11;
            if(childPosition == 4) id_res = R.drawable.a15;
        }
        else if(groupPosition == 39){
            if(childPosition == 0) id_res = R.drawable.a04;
            if(childPosition == 1) id_res = R.drawable.a20;
            if(childPosition == 2) id_res = R.drawable.a17;
            if(childPosition == 3) id_res = R.drawable.a08;
            if(childPosition == 4) id_res = R.drawable.cibiru_cicadas;
            if(childPosition == 5) id_res = R.drawable.cijerah_ciwastra;
            if(childPosition == 6) id_res = R.drawable.a16;
            if(childPosition == 7) id_res = R.drawable.a12;
            if(childPosition == 8) id_res = R.drawable.a15;
        }
        else if(groupPosition == 40){
            if(childPosition == 0) id_res = R.drawable.b01;
            if(childPosition == 1) id_res = R.drawable.a02;
            if(childPosition == 2) id_res = R.drawable.a03;
            if(childPosition == 3) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 4) id_res = R.drawable.a18;
            if(childPosition == 5) id_res = R.drawable.a12;
            if(childPosition == 6) id_res = R.drawable.a10;
        }
        else if(groupPosition == 41){
            if(childPosition == 0) id_res = R.drawable.a18;
        }
        else if(groupPosition == 42){
            if(childPosition == 0) id_res = R.drawable.a03;
            if(childPosition == 1) id_res = R.drawable.antapani_ciroyom;
            if(childPosition == 2) id_res = R.drawable.buah_batu_sederhana;
            if(childPosition == 3) id_res = R.drawable.a20;
            if(childPosition == 4) id_res = R.drawable.a17;
            if(childPosition == 5) id_res = R.drawable.caringin_sadang_serang;
            if(childPosition == 6) id_res = R.drawable.cisitu_tegalega;
            if(childPosition == 7) id_res = R.drawable.a11;
            if(childPosition == 8) id_res = R.drawable.a09;
            if(childPosition == 9) id_res = R.drawable.a18;
            if(childPosition == 10) id_res = R.drawable.a14;
            if(childPosition == 11) id_res = R.drawable.a15;
        }
        
        if (child != null) {
            v = vi.inflate(CHILD_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);
            holder.text.setText(Html.fromHtml(child));
            
            holder.imageview.setImageResource(id_res);
        }
        return v;
    }

    public String getGroup(int groupPosition) {
        return "group-" + groupPosition;
    }

    public int getGroupCount() {
        return data.length;
    }


    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        String group = null;
        int id_res = 0;
        long group_id = getGroupId(groupPosition);
        if(group_id == 0){
        	group = "Jl_ABC";
        }else if(group_id == 1){
        	group = "Jl. Aceh";
        }else if(group_id == 2){
        	group = "Jl. Ahmad Yani";
        }else if(group_id == 3){
            group = "Jl. Ambon";
        }else if(group_id == 4){
            group = "Jl. Antapani";
        }else if(group_id == 5){
            group = "Jl. Astana Anyar";
        }else if(group_id == 6){
            group = "Jl. Banteng";
        }else if(group_id == 7){
            group = "Jl. BKR";
        }else if(group_id == 8){
            group = "Jl. Buah Batu";
        }else if(group_id == 9){
            group = "Jl. Caringin";
        }else if(group_id == 10){
            group = "Jl. Cibaduyut";
        }else if(group_id == 11){
            group = "Jl. Cibiru";
        }else if(group_id == 12){
            group = "Jl. Cihampelas";
        }else if(group_id == 13){
            group = "Jl. Cipaganti";
        }else if(group_id == 14){
            group = "Jl. Ciroyom";
        }else if(group_id == 15){
            group = "Jl. Cimbuleuit";
        }else if(group_id == 16){
            group = "Jl. Dalem Kaum";
        }else if(group_id == 17){
            group = "Jl. Dipatiukur";
        }else if(group_id == 18){
            group = "Jl. Elang";
        }else if(group_id == 19){
            group = "Jl. Gardujati";
        }else if(group_id == 20){
            group = "Jl. Jendral Gatot Subroto";
        }else if(group_id == 21){
            group = "Jl. Gedebage";
        }else if(group_id == 22){
            group = "Jl. Ir. H Djuanda (Dago)";
        }else if(group_id == 23){
            group = "Jl. Jakarta";
        }else if(group_id == 24){
            group = "Jl. Jamika";
        }else if(group_id == 25){
            group = "Jl. Jendral Sudirman";
        }else if(group_id == 26){
            group = "Jl. Karapitan";
        }else if(group_id == 27){
            group = "Jl. Kebon Jati";
        }else if(group_id == 28){
            group = "Jl. Kebon Kawung";
        }else if(group_id == 29){
            group = "Jl. Kiaracondong";
        }else if(group_id == 30){
            group = "Jl. Lodaya";
        }else if(group_id == 31){
            group = "Jl. Otto Iskandar Dinata (Otista)";
        }else if(group_id == 32){
            group = "Jl. Pajajaran";
        }else if(group_id == 33){
            group = "Jl. Pasir Kaliki";
        }else if(group_id == 34){
            group = "Jl. Pasir Koja";
        }else if(group_id == 35){
            group = "Jl. Pelajar Pejuang";
        }else if(group_id == 36){
            group = "Jl. Peta";
        }else if(group_id == 37){
            group = "Jl. RE. Martadinata (Riau)";
        }else if(group_id == 38){
            group = "Jl. Sederhana";
        }else if(group_id == 39){
            group = "Jl. Soekarno Hatta";
        }else if(group_id == 40){
            group = "Jl. Sumatra";
        }else if(group_id == 41){
            group = "Jl. Ujung Berung";
        }else if(group_id == 42){
            group = "Jl. Wastukencana";
        }
//        id_res = R.drawable.ferrari;



        if (group != null) {
            v = vi.inflate(GROUP_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);

            holder.text.setText(Html.fromHtml(group));
            holder.imageview.setImageResource(id_res);
            holder.checkbox.setFocusable(false);
            CheckListener checkL = new CheckListener();
            checkL.setPosition(group_id);
            holder.checkbox.setOnCheckedChangeListener(checkL);
            holder.checkbox.setChecked(checkboxMap.get(group_id));  
        }
        return v;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public boolean hasStableIds() {
        return true;
    }
} 