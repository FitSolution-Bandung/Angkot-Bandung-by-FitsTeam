package fpp.priangan.fujicon.angkot.djikstra2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class dijkstra {
	
	SQLHelper dbHelper;
	Cursor cursor;
    String[] explode1;
    List<String> gabungSimpulPilihan = new ArrayList<String>();
	String[][] graph = new String[900][900];
	public String jalur_terpendek1 = "";
	public String status = "none";
    Context context;
	public void jalurTerpendek(String[][] arg_graph, int simpulAwal, int simpulTujuan){

		System.out.println("sa : " + simpulAwal + " & st : " + simpulTujuan);

//        Toast toast = Toast.makeText(context,"sa : " + simpulAwal + " & st : " + simpulTujuan, Toast.LENGTH_LONG);
//        toast.show();
        if(simpulAwal == simpulTujuan){
			status = "die";
			return;
			//System.exit(0);
		}

		
		graph = arg_graph;
        int simpul_awal = simpulAwal;
        int simpul_maju = simpulAwal;
        int simpul_tujuan = simpulTujuan;  		
        
        if(simpul_maju != simpul_tujuan){
            //HITUNG JUMLAH SIMPUL
            int jml_simpul = 0;
            for(String[] array : graph){
                if(array[0] != null){
                    jml_simpul += 1;
                }
            }          
            //System.out.println("Jumlah Simpul : "+jml_simpul);
            //System.out.println("==============================================");
            //System.out.println();
            
            //TANDAI SIMPUL YANG AKAN DIKERJAKAN
            List<Integer> simpulYangDikerjakan = new ArrayList<Integer>(); 
            
            //UNTUK MENYIMPAN NILAI-NILAI * YANG DITANDAI
            List<Integer> simpulYangSudahDikerjakan_bawah = new ArrayList<Integer>();

            double nilaiSimpulYgDitandai = 0;
            double nilaiSimpulFixYgDitandai = 0;
            
            //PERULANGAN HANDLE
            for(int perulangan = 0; perulangan < 1; perulangan++){
                //UNTUK MNDAPATKAN 1 BOBOT PALING MINIMUM DARI SETIAP SIMPUL
                List<Double> perbandinganSemuaBobot = new ArrayList<Double>();
                
                //DAFTARKAN SIMPUL pertama YANG AKAN DIKERJAKAN KE DALAM ARRAY
                if(!simpulYangDikerjakan.contains(simpul_maju)){
                    simpulYangDikerjakan.add(simpul_maju);
                }
                
                //PERULANGAN SIMPUL SIMPUL YANG DITANDAI
                for(int perulanganSimpul = 0; perulanganSimpul < simpulYangDikerjakan.size(); perulanganSimpul++){
                    //HITUNG JUMLAH BARIS PER KOLOM SIMPUL
                    int jml_baris_fix = 0;
                        for(int min_batas_bris = 0; min_batas_bris < 900; min_batas_bris++){
                            if(graph[simpulYangDikerjakan.get(perulanganSimpul)][min_batas_bris] != null){
                                jml_baris_fix += 1;
                            } 
                        }

                        //CARI BOBOT MINIMUM di 1 simpul berdasarkan baris scr urut[0][0],[0][1] dst
                        List<Double> bobot = new ArrayList<Double>();                        
                        int status_baris = 0;

                        //perulangan CARI BOBOT2 DI 1 SIMPUL
                        for(int min_batas_bris_fix = 0; min_batas_bris_fix < jml_baris_fix; min_batas_bris_fix++){
                            String bobot_dan_ruas = graph[simpulYangDikerjakan.get(perulanganSimpul)][min_batas_bris_fix];//pasti berurutan [0][0],[0][1] dst
                            //print isi dr baris[0][0],[0][1],[0][2] dst
                            //System.out.println("bobot_dan_ruas : "+bobot_dan_ruas);
                            String[] explode;
                            explode = bobot_dan_ruas.split("->");
                            //System.out.println("bobot_ : "+explode[0]);
                            //cari bobot yg belum dikerjakan (yg tidak ada tanda ->y)
                            if(explode.length == 2){
                                status_baris += 1; // masih ada yg belum ->y
                
                                //Cek simpul apakah sudah ditandai apa blom, klo udh berarti nilai * tidak ditambah lagi / 0
                                //kalo blm ditandai, berarti nilai * bernilai nilaiSimpulYgditandai
                                if(!simpulYangSudahDikerjakan_bawah.isEmpty()){                                    
                                    if(simpulYangSudahDikerjakan_bawah.contains(simpulYangDikerjakan.get(perulanganSimpul))){
                                       nilaiSimpulYgDitandai = 0;                                            
                                    }else{
                                      nilaiSimpulYgDitandai = nilaiSimpulFixYgDitandai;
                                    }
                                }
                                                                
                                bobot.add((Double.parseDouble(explode[1])+nilaiSimpulYgDitandai));//bs acak bobot[0],bobot[2]// 0 dan 2 berdasarkan baris yg akan dikerjakan
                               graph[simpulYangDikerjakan.get(perulanganSimpul)][min_batas_bris_fix] = 
                               String.valueOf(explode[0]+"->"+(Double.parseDouble(explode[1])+nilaiSimpulYgDitandai));                                
                            }
                        }

                        //jika baris di kolom belum ->y semua, maka lakukan if di bawah ini :
                        if(status_baris > 0){

                            //DAPATKAN BOBOT MINIMUM
                            for(int index_bobot = 0; index_bobot < bobot.size(); index_bobot++){
                               if(bobot.get(index_bobot) <= bobot.get(0)){
                                   bobot.set(0, bobot.get(index_bobot));
                               }
                            } 

                        perbandinganSemuaBobot.add(bobot.get(0));                            
                        }//end if jika ->y atau ->t belum semua dikerjakan
                        else{//Jika baris di kolom sudah ->y semua, maka lakukan else di bawah ini
                            //System.out.println("=======||Baris sudah ->y semua||=======");
                        }   
                        
                        //DAFTARKAN SIMPUL SIMPUL YANG baru selesai DIKERJAKAN
                        if(!simpulYangSudahDikerjakan_bawah.contains(simpulYangDikerjakan.get(perulanganSimpul))){
                            simpulYangSudahDikerjakan_bawah.add(simpulYangDikerjakan.get(perulanganSimpul));
                        }
                }//end for perulanganSimpul                
                
                //DAPATKAN 1 BOBOT PALING MINIMUM DARI SIMPUL YG DITANDAI
                for(int min_indexAntarBobotYgDitandai = 0; min_indexAntarBobotYgDitandai < perbandinganSemuaBobot.size(); min_indexAntarBobotYgDitandai++){
                    if(perbandinganSemuaBobot.get(min_indexAntarBobotYgDitandai) <= perbandinganSemuaBobot.get(0)){
                        perbandinganSemuaBobot.set(0, perbandinganSemuaBobot.get(min_indexAntarBobotYgDitandai));
                    }
                }

                //DAPATKAN INDEX SIMPUL+BOBOTNYA YG ASLI DARI SIMPUL YG DITANDAI
                int indexAwalAsli = 0; //index simpulnya
                int status_baris1 = 0;                
                int dapat_indexAsliBobot = 0;
                int simpul_lama = 0;
                for(Integer indexAsli_bobot : simpulYangDikerjakan){
                    for(int baris1 = 0; baris1 < 900; baris1++){
                        if(graph[simpulYangDikerjakan.get(indexAwalAsli)][baris1] != null){
                            String bobot_dan_ruas1 = graph[simpulYangDikerjakan.get(indexAwalAsli)][baris1];
                            //System.out.println(bobot_dan_ruas1);
                            //String[] explode1;
                            explode1 = bobot_dan_ruas1.split("->");
                            if(explode1.length == 2){
                               // System.out.println("----------;"+explode1[1]);
                                if(perbandinganSemuaBobot.get(0) == Double.parseDouble(explode1[1])){
                                    dapat_indexAsliBobot = baris1;
                                    simpul_lama = simpulYangDikerjakan.get(indexAwalAsli);
                                    simpul_maju = Integer.parseInt(explode1[0]);
                                    status_baris1 += 1;                   
                                }                                         
                            }//end if cek ->y atau ->t
                        }//end if cek baris != null         
                    }//end for limit baris = 900
                    indexAwalAsli++; //index simpul di tambah 1
                }//end for simpul yang dikerjakan

                //BULETIN BOBOT MINIMUM YANG UDH DIDAPAT  dan HAPUS RUAS YANG BERHUBUNGAN              
<<<<<<< HEAD
                if(status_baris1 > 0){
=======
                if(status_baris1 > 0){                    
>>>>>>> origin/master
                    graph[simpul_lama][dapat_indexAsliBobot] = graph[simpul_lama][dapat_indexAsliBobot]+"->y";

                    //HAPUS RUAS LAIN
                    for(int min_kolom = 0; min_kolom < jml_simpul; min_kolom++){
                        for(int min_baris = 0; min_baris < 900; min_baris++){
                                    
                            if(graph[min_kolom][min_baris] != null){
                                String ruasYgAkanDihapus = graph[min_kolom][min_baris];
                                String[] explode3 = ruasYgAkanDihapus.split("->");                                  
                                if(explode3.length == 2){
                                    if(explode3[0].equals(String.valueOf(simpul_maju))){
                                        graph[min_kolom][min_baris] = graph[min_kolom][min_baris]+"->t";                                        
                                    }
                                }//end if cek ->y atau ->t
                            }//end if cek baris != null
                        }//end for baris
                    }//end for kolom  
                }//end if cek status_baris sudah ->y atau ->t semua apa belum
<<<<<<< HEAD

=======
       
>>>>>>> origin/master
                //Nilai * yg ditandai
                nilaiSimpulFixYgDitandai = perbandinganSemuaBobot.get(0);                
                //System.out.println("nilaiSimpulFixYgDitandai : "+nilaiSimpulFixYgDitandai);
                //System.out.println("perbandingan simpul? : "+simpul_maju+" = "+simpul_tujuan+" ..?");
                if(simpul_maju != simpul_tujuan){
                  --perulangan; 
                }
                else{
                    break; //akhiri perulangan
                }
            }//end for handle perulangan   
            //System.out.println("--SELESAI--");

            //taruh simpul gabungan ke array; misal : simpul 6-10
//            List<String> gabungSimpulPilihan = new ArrayList<String>();
            for(int h = 0; h < jml_simpul; h++){
                for(int n = 0; n < 900; n++){
                    if(graph[h][n] != null){
                        String str_graph = graph[h][n];
                        if(str_graph.substring(str_graph.length()-1, str_graph.length()).equals("y")){
                            String[] explode4 = graph[h][n].split("->");
                            String simpulGabung = h+"-"+explode4[0];
                            
                            gabungSimpulPilihan.add(simpulGabung);
                        }
                    }//end if cek isi graph != null
                }//end for looping baris
            }//end looping kolom (simpul)
            
            //masukkan simpul yg sudah diurutkan (dari simpul tujuan ke simpul awal). (nanti direverse arraynya)
            List<Integer> simpulFix_finish = new ArrayList<Integer>();
            //masukkan pertama kali simpul tujuan (simpul akhir) ke array dgn index 0. (nanti dibalik(reverse) arraynya)
            simpulFix_finish.add(simpul_tujuan);
            
            int simpul_explode = simpul_tujuan;  
            
            for(int v = 0; v < 1; v++){
                for(int w = 0; w < gabungSimpulPilihan.size(); w++){
                    String explode_simpul = gabungSimpulPilihan.get(w);
                    String[] explode5 = explode_simpul.split("-");
                    if(simpul_explode == Integer.parseInt(explode5[1])){
                        simpulFix_finish.add(Integer.parseInt(explode5[0]));
                        simpul_explode = Integer.parseInt(explode5[0]);
                    }
                    if(simpul_explode == simpul_awal){
                        break;
                    }
                }
                
                if(simpul_awal != simpul_explode){
                    --v;
                }else{
                    break;
                }
            }//end for cari simpul yang dibuletin lalu dibandingkan dgn simpul_tujuan
            
            //array di balik indexnya; jadi simpul tujuan di pindah posisi ke akhir index array
            Collections.reverse(simpulFix_finish);
            String jalur_terpendek = "";
            for(int x = 0; x < simpulFix_finish.size(); x++){
                if(x == simpulFix_finish.size()-1){
                    jalur_terpendek += simpulFix_finish.get(x);
                }else{
                    jalur_terpendek += simpulFix_finish.get(x)+"->";
                }
            }
            
            //System.out.println("... "+jalur_terpendek);

//            Toast.makeText(getApplicationContext(), "... "+jalur_terpendek, Toast.LENGTH_LONG).show();
            jalur_terpendek1 = jalur_terpendek;
//            jalur_terpendek1 = String.valueOf(simpulYangSudahDikerjakan_bawah);
        }//end if start != finish
        
	}


}