package fpp.priangan.fujicon.angkot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapter;
=======
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapter;
import fpp.priangan.fujicon.angkot.sheet.MainActivitySheet;
>>>>>>> origin/master

public class Info_detail_angkot extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
// Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] no;
    String[] warna;
    String[] nama_trayek;
    String[] lintasan;
    String[] lintasan2;
    String[] harga;
    String[] operasi;
    int[] gambar;
    TextView nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detail_angkot);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowHomeEnabled(true);

        String[] no= new String[] {
                "01A","01B","02","03","04","05","06","07","08","09",
                "10","11A","11B","12","13","14","15","16","17","18",
                "19A","19B","20","21","22","23","24","25","26","27",
                "28","41","30","31","32","33","34","35","36"
        };

        String[] warna= new String[] {
                "Hijau-Merah Tua","Hijau-Orange","Hijau-Biru Muda","Orange-Putih-Hijau","Hijau-Hitam","Hijau-Orange-Hijau","Coklat-Krem-Coklat","Merah-Putih-Merah","Hijau-Orange-Hijau","Hijau-Kuning-Hijau",
                "Hijau-Biru-Hijau","Hijau Muda-kuning-Hijau","Biru Tua-Orange-Hijau","Biru Muda-Orange-Hijau Lintas","Kuning-Biru-Kuning-Biru","Putih-Kuning-Hijau","Orange-Putih-Hijau","Putih-Kuning-Merah-Hijau","Hijau-Krem-Hijau","Hijau-Strip Krem-Orange","Putih-Kuning-Putih-Hijau",/*20*/
                "Biru-Putih-Hijau","Hijau-Merah-Hijau","Ungu-Putih-Hijau","Abu Abu-Putih-Hijau","Biru-Krem-Hijau","Coklat-Merah-Hijau","Hijau-Biru-Hijau","Biru Muda-Kuning-Hijau",/*28*/
                "Kuning-Putih-Hijau","Biru Muda-Biru Tua-Hijau","Hijau-Kuning","Hijau-Kuning","Hijau-Kuning","Hijau-Kuning","Hijau-Kuning","Hijau-Kuning","Hijau-Kuning"/*37*/
        };

        String[] nama_trayek = new String[] {
                "Abdulmuis - Cicaheum via Binong",
                "Abdulmuis - Cicaheum via Aceh",
                "Abdulmuis - Dago",
                "Abdulmuis - Ledeng",
                "Abdulmuis - Elang",
                "Cicaheum - Ledeng",
                "Cicaheum - Ciroyom",
                "Cicaheum - Ciwastra",
                "Cicaheum - Cibaduyut",
                "Dago - ST Hall",
                "ST Hall - Sadang Serang",
                "Cimbuleuit - ST Hall via Eykman",
                "Cimbuleuit - ST Hall via Cihampelas",
                "ST Hall - Gedebage",
                "ST Hall - Sarijadi",
                "ST Hall - Gunung Batu",
                "Margahayu - Ledeng",
                "Riung Bandung - Dago",
                "Caringin - Dago",
                "Panghegar - Dipatiukur",
                "Ciroyom - Sarijadi via Sukajadi",
                "Ciroyom - Sarijadi via Setrasari",
                "Bumi Asri - Ciroyom",
                "Cikudapateh - Ciroyom",
                "Buah Batu - Sederhana",
                "Cijerah - Sederhana",
                "Sederhana - Cimindi",
                "Ciwastra - Ujungberung",
                "Cisitu - Tegalega",
                "Cijerah - Ciwastra",
                "Elang - Gedebage",/*31*/
                "Abdulmuis -Mengger",
                "Cicadas - Elang",
                "Antapani - Ciroyom",
                "Cibiru - Cicadas",
                "Sekemirung - Panyileukan",
                "Caringin - Sadang Serang",
                "Cibaduyut - Karang Setra",
                "Cibogo - Elang"

        };


        String[] lintasan= new String[] {///blm
                "<b>RUTE 1 (DARI ABDUL MUIS KE CICAHEUM-BINONG)</b><br/>\n"+
                        "Abdul Muis (Kebon Kalapa) – Cicaheum : Terminal Kebon Kalapa – Jl.Dewi Sartika – Jl.Balong Gede – Jl.Buah Batu - Jl.Palasari – Jl.Talaga Bodas – Jl.Pelajar Pejuang 45 – Jl.RAA.Martanegara – Jl.Turangga – Jl.Gatot Subroto – Jl.Ibrahim Adjie (Jl.Kiaracondong) – Jl.Jakarta – Jl.Supratman – Jl.Brigjen Katamso – Jl.Cikutra – Jl.KH.Hasan Mustofa – Jl.Ahmad Yani – Terminal Cicaheum .\n" +
                        " \n",
               "<b>RUTE 1 (DARI ABDUL MUIS KE CICAHEUM-ACEH)</b><br/>\n"+
                                "Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Lombok – Jl. Aceh – Jl. Taman Pramuka – Jl. Cendana – Taman WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum\n" +
                                " \n",
                "<b>RUTE 1 (DARI ABDUL MUIS KE DAGO)</b><br/> \n" +
                                "Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Sumbawa – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Sulawesi – Jl. Seram – Jl. RE Martadinata (Riau) – Jl. Ir. H. Juanda (Dago) – RS. Boromeus (Dago) – ITB (Jl. Ganesha, Dago) – Simpang Dago – Terminal Dago\n" +
                                " \n",
                        "<b>RUTE 1 (DARI ABDUL MUIS KE LEDENG)</b><br/>\n" +
                                "Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Sumbawa – Jl. Lombok – Jl. Banda – Jl. RE Martadinata (Riau) – BIP (Dago) – Jl. Merdeka – Jl. Aceh – Jl. Wastu Kencana – Jl. Rivai – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
                                "\n",
                        "<b>RUTE 1 (DARI ABDUL MUIS KE ELANG)</b><br/>\n" +
                                "Terminal Kebon Kelapa – Jl. Pungkur – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Astana Anyar – Jl. Panjunan – Jl. Kopo – Jl. Pasir Koja – Jl. Astana Anyar – Jl. Pagarsih – Jl. Nawawi – Jl. Aksan – Jl. Suryani – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Elang – Terminal Elang\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CICAHEUM KE LEDENG)</b><br/>\n" +
                                "Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Katamso – Jl. WR. Supratman – Jl. Diponegoro – Jl. Sulanjana – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Lamping – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CICAHEUM KE CIROYOM)</b><br/>\n" +
                                "Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Surapati (Suci) – Lapangan Gasibu (Surapati) – Jl. Panatayuda – Jl. Dipati Ukur – Simpang Dago – Jl. Sumur Bandung – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Eyckman – RS. Hasan Sadikin – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Ciroyom – Terminal Ciroyom\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CICAHEUM KE CIWASTRA)</b><br/>\n" +
                                "*Terminal Cicaheum – *Jl. PHH. Mustofa (Suci) – Jl. Surapati (Suci) – Jl. Sentot Alibasyah – Jl. Diponegoro – Jl. WR. Supratman – Jl. Ahmad Yani – Jl. Jakarta – Jl. Kiara Condong – Jl. Terusan Kiara Condong – Jl. Margacinta – Jl. Ciwastra – Terminal Ciwastra\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CICAHEUM KE CIBADUYUT)</b><br/>\n" +
                                "*Terminal Cicaheum – *Jl. PHH. Mustofa (Suci) – *Jl. Katamso – *Jl. WR. Supratman – *Jl. Ahmad Yani – Jl. Jakarta – Jl. Kiara Condong – Jl. Sukarno-Hatta – Terminal Leuwi Panjang (Sukarno-Hatta) – Jl. Kopo – Jl. Cibaduyut – Terminal Cibaduyut\n" +
                                "\n",
                        "<b>RUTE 1 (DARI DAGO KE ST.HALL)</b><br/>\n" +
                                "Terminal Dago – Jl. Ir. H. Juanda – Simpang Dago – ITB (Jl. Ganesha, Dago) – RS Boromeus (Dago) – BIP (Merdeka) – Jl. Merdeka – Jl. Perintis Kemerdekaan – Jl. Braga – Jl. Suniaraja – Jl. Stasiun Barat (Stasiun Bandung)\n",
                        "<b>RUTE 1 (DARI ST.HALL KE SADANG SERANG)</b><br/>\n" +
                                "Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Braga – Jl. Lembong – Jl. Veteran – Jl. Sunda – Jl. Sumbawa – Jl. Lombok – Jl. Citarum – Jl. WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra Barat – Terminal Sadang Serang\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIUMBULEUIT KE ST.HALL)</b><br/>\n" +
                                "Terminal Ciumbuleuit – Jl. Ciumbuleuit – UNPAR (Ciumbuleuit) – Jl. Cihampelas – Jl. Bapa Husen – Jl. Sederhana -  Jl. Pasir Kaliki – RS. Hasan Sadikin – Jl. Pasteur – Jl. Cihampelas – Jl. Rivai – Jl. Cipto – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Terminal Stasiun\n" +
                                "\n",
                "<b>RUTE 1 (DARI ST.HALL KE CIUMBULEUIT via CIHAMPELAS)</b><br/>\n"+
                        "Terminal St.Hall – Jl.Suniaraja – Jl. Otista - Jl.Kebon Kawung – Jl.HOS.Cokroaminoto (Jl.Pasir Kaliki) – Jl.Padjajaran – Jl.Cihampelas – Jl.Dr.Rivai – Jl.Capaganti – Jl.Setia Budi – Jl.Ciumbeuleuit – Pangkalan Ciumbeuleuit .\n",
                "<b>RUTE 1 (DARI ST.HALL KE GEDEBAGE)</b><br/>\n" +
                                "St.Hall – Jl. Dulatip – Pasar Baru – Jl. Otto Iskandardinata (Otista) – Jl. Kepatihan – Jl. Dewi Sartika – Jl. Dalem Kaum – Alun-Alun (Asia Afrika) – Jl. Banceuy – Jl. ABC – Jl. Naripan – Jl. Sunda – Jl. Veteran – Jl. Ahmad Yani – Jl. Gatot Subroto – Jl. Burangrang – Jl. Halimun – Jl. Malabar – Jl. Talaga Bodas – Jl. Pelajar Pejuang – Jl. Martanegara – Jl. Reog – Jl. Karawitan – Jl. Kliningan – Jl. Buah Batu – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Pasar Induk Gede Bage\n" +
                                "\n",
                        "<b>RUTE 1 (DARI ST.HALL KE SARIJADI)</b><br/>\n" +
                                "St.Hall – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawun) – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. DR. Junjunan (Terusan Pasteur) – BTC (Pasteur) – Jl. Surya Sumantri – Universitas Maranatha (Surya Sumantri) – Jl. Lemah Nendeut (Sarijadi) – Jl. Sari Rasa (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Asih (Sarijadi) – Terminal Sarijadi\n" +
                                "\n",
                        "<b>RUTE 1 (DARI ST.HALL KE GUNUNG BATU)</b><br/>\n" +
                                "Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Rum – Jl. Gunawan – Jl. Otten – Jl. Pasteur – BEC (Pasteur) – Jl. Westhoff – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Gunung Batu – Terminal Gunung Batu\n" +
                                "\n",
                        "<b>RUTE 1 (DARI MARGAHAYU KE LEDENG)</b><br/>\n" +
                                "Terminal Margahayu – Jl. Ranca Bolang (Margahayu Raya) – Jl. Sukarno-Hatta – Jl. Kiara Condong – Jl. Jakarta – Jl. WR. Supratman – Jl. Cendana – Jl. Taman Pramuka – Jl. RE. Martadinata – ??? – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Rivai – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
                                "\n",
                        "<b>RUTE 1 (DARI RIUNG BANDUNG KE DAGO)</b><br/>\n" +
                                "Terminal Riung Bandung – Jl. Riung Bandung – Jl. Cipamolokan (Riung Bandung) – Jl. Sukarno-Hatta – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Kiara Condong – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Jl. RE. Martadinata – Jl. Anggrek – Jl. Gudang Utara – Jl. Patra Komala – Jl. Belitung – Jl. Banda – Jl. Diponegoro – Jl. Aria Jipang – Jl. Surapati (Suci) – Jl. Panatayuda – Jl. Dipati Ukur – Simpang Dago – Jl. Ir. H. Juanda – Terminal Dago\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CARINGIN KE DAGO)</b><br/>\n" +
                                "Pasar Induk Caringin – Jl. Babakan Ciparay – Jl. Sukarno-Hatta – Jl. Sukamulya – Jl. Terusan Jamika – Jl. Jamika – Jl. Sudirman – Jl. Guanan – Jl. Kebon Jati – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – Jl. Cikapayang – Jl. Surapati (Suci) – Jl. Pahlawan – Jl. Cikutra Barat – Jl. Cigadung Raya – Terminal Dago\n" +
                                "\n",
                        "<b>RUTE 1 (DARI PANGHEGAR KE DIPATIUKUR)</b><br/>\n" +
                                "Terminal Panghegar – Jl. Cisaranten – Jl. Cicukang – Jl. AH. Nasution (Raya Ujung Berung) – Sindanglaya (Nasution) – Terminal Cicaheum – Jl. Ahmad Yani – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Jl. RE. Martadinata – Jl. Ambon – Masjid Istiqamah – Jl. Cisanggarung – Jl. Cimanuk – Jl. Cimandiri – Jl. Cimalaya – Jl. Diponegoro – Jl. Sulanjana – Jl. Tamansari – Jl. Ganesha – ITB – RS. Boromeus – Jl. Hasanudin – Jl. Dipati Ukur – Terminal Dipati Ukur\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIROYOM KE SARIJADI via SUKAJADI)</b><br/>\n" +
                                "Terminal Ciroyom – Jl. Arjuna – Jl. Pajajaran – Jl. Baladewa – Jl. Dursasana – Jl. Pasir Kaliki – Jl. Sukajadi – Jl. Sindang Sirna – Jl. Sindang Sirna – Jl. Geger Kalong Hilir – Jl. Sari Endah – Jl. Sari Jadi – Jl. Sari Manah (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Terminal Sarijadi\n" +
                                "\n",
                "<b>RUTE 1 (DARI CIROYOM KE SARIJADI via SETRASARI)</b><br/>\n" +
                        "Terminal Ciroyom – Jl.Arjuna – Jl.Pajajaran – Jl.Dr.Cipto – Jl.Gunawan – Jl.Rajiman – Jl.Rontgen – Jl.Ehrlich – Jl.Rumah Sakit – Jl.Prof.Eyckman – Jl.Sederhana – Jl.Makmur – Jl.Sejahtera – Jl.Sampurna – Jl.Boscha – Jl.Lamping – Jl.Jurang – Jl.Sukamaju – Jl.Sukajadi – Jl.Sindangsirna – Jl.Ir.Sutami – Jl.Surya Sumatri – Jl.Lemahneundeut – Jl.Sariwangi – Jl.Sarimadu Barat\n" +
                        "\n",
                "<b>RUTE 1 (DARI BUMI ASRI KE CIROYOM)</b><br/>\n" +
                                "Bumi Asri – Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Sukarno-Hatta – Jl. Sudirman – Jl. Rajawali Barat – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Stasiun Timur – Viaduct – Jl. Wastu Kencana – Jl. Rivai – Jl. Cipto – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. Pajajaran – Jl. Arjuna – Jl. Supadio – Jl. Ciroyom – Terminal Ciroyom\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIKUDAPATEUH KE CIROYOM)</b><br/>\n" +
                                "Jl. Cikudapateuh – Jl. Kembang Sepatu – Jl. Tarate – Jl. Samboja – Jl. Laswi – Jl. Gatot Subroto – Jl. Malabar – Jl. Buah Batu – Jl. Gurame – Jl. Moh. Ramdan – Jl. BKR – Jl. Peta – Jl. Kopo – Jl. Pasir Koja – Jl. Astana Anyar – Jl. Cibadak – Jl. Sudirman – Jl. Guanan – Jl. Kebon Jati – Jl. Arjuna – Terminal Ciroyom\n" +
                                "\n",
                        "<b>RUTE 1 (DARI BUAH BATU KE KEBON KELAPA)</b><br/>\n" +
                                "Terminal Buah Batu – Jl. Buah Batu – Jl. Gurame – Jl. Karapitan – Jl. Lengkong Kecil – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa– Jl. Dewi Sartika – Jl. Banceuy – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Jl. Kebon Jukut – Jl. Kebon Kawung (Stasiun Bandung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Rum – Jl. Gunawan – Jl. Otten – Jl. Pasteur – Jl. Pasir Kaliki (RS. Hasan Sadikin) – Jl. Sederhana – Terminal Sederhana\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIJERAH KE SEDERHANA)</b><br/>\n" +
                                "Jl. Melong Asih – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Garuda – Jl. Abdul Rahman Saleh – Jl. Pajajaran – Jl. Pandu – Jl. Dursasana – Jl. Pasir Kaliki – Jl. Sederhana\n" +
                                "\n",
                "<b>RUTE 1 (DARI SEDERHANA KE CIMINDI)</b><br/>\n"+
                        "Sederhana – Cimindi : Term/Pasar Sederhana – Jl.Prof.Eykman – Jl.Sukajadi – Jl.Sukagalih – Jl.Junjunan – Jl.Komp.Cibogo – Jl.Sukaraja – Jl.Gunung Batu – Stasiun Cimindi\n" +
                        " \n",
                "<b>RUTE 1 (DARI CIWASTRA KE UJUNGBERUNG)</b><br/>\n"+
                        "Ciwastra – Ujung Berung : Pangkalan Ciwastra – Jl.Ciwastra – Jl.Derwati – Jl.Ranca Balong – Jl.Rancacili – Jl.Cipamokolan – Jl.Soekarno Hatta – Jl.Guruminda – Jl.Cisaranten Kulon – Jl.Cicukang – Jl.AH.Nasution (Jl.Raya Ujung Berung) – Term.Ujung Berung\n" +
                        " \n",
                "<b>RUTE 1 (DARI CISITU KE TEGALEGA)</b><br/>\n" +
                                "Terminal Cisitu – Jl. Cisitu Lama VIII – Jl. Cisitu – Jl. Sangkuriang – Jl. Siliwangi – Jl. Sumur Bandung – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – Terminal Stasiun – Jl. Dulatip – Pasar Baru – Jl. Sudirman – Jl. Astana Anyar – Jl. Kalipah Apo – Jl. Otto Iskandardinata (Otista) – Terminal Tegalega\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIJERAH KE CIWASTRA)</b><br/>\n" +
                                "Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Caringin – Jl. Kopo – Jl. Peta – Jl. BKR – Jl. Moh. Ramdan – Jl. Sadakeling – Jl. Talaga Bodas – Jl. Pelajar Pejuang – Jl. Martanegara – Jl. Reog – Jl. Karawitan -  Jl. Kliningan – Jl. Buah Batu – Jl. Sukarno-Hatta – Jl. Terusan Kiara Condong – Jl. Margacinta – Jl. Ciwastra – Terminal Ciwastra\n" +
                                "\n",
                "<b>RUTE 1 (DARI ELANG KE GEDEBAGE)</b><br/>\n"+
                        "Elang – Ujung Berung : Pangkalan Elang – Jl.Elang – Jl.Jend.Sudirman – Jl.Jamika – Jl.Pasir Koja – Jl.Otista – Jl.BKR – Jl.Sriwijaya – Jl.Kembar Mas – Jl.Pasir Salam – Jl.Pasir Luyu – Jl.Soekarno Hatta – Jl.Rumah Sakit – Jl.AH.Nasution (Jl.Ujung Berung) – Jl.Cigending – Jl.Cikoang – Jl.Pasir Jati – Cijambe – Terminal Ujung Berung\n" +
                        " \n",
                "<b>RUTE 1 (DARI ABDULMUIS KE MENGGER)</b><br/>\n"+
                        "Abdul Muis (Kebon Kalapa) – Mengger : Jl.Dewi Sartika – Jl.Kautamaan Istri – Jl.Balong Gede – Jl.Pungkur Jl.Ciateul – Jl.Sawah Kurung – Jl.Kota Baru – Jl.Moch.Ramdan – Jl.Peta – Jl.Sriwijaya – Jl.Soekarno Hatta – Jl.Pasir Luyu\n" +
                        " \n",
                "<b>RUTE 1 (DARI CICADAS KE ELANG)</b><br/>\n" +
                                "Cicadas – Jl. Kiara Condong – Jl. Gatot Subroto – Jl. Burangrang – Jl. Sadakeling – Jl. Buah Batu – Jl. Gurame – Jl. Karapitan – Jl. Lengkong Kecil – Jl. Lengkong Besar – Jl. Pungkur – Terminal Kebon Kelapa – Jl. Dewi Sartika – Alun-Alun – Jl. Banceuy – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Dadali -  Jl. Kasuari – Jl. Rajawali Barat – Elang\n" +
                                "\n",
                        "<b>RUTE 1 (DARI ANTAPANI KE CIROYOM)</b><br/>\n" +
                                "Terminal Antapani – Jl. Cibatu (Antapani) – Jl. Purwakarta (Antapani) – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Stadion Persib (Ahmad Yani) – Jl. Ahmad Yani – Jl. Gudang Utara – Jl. Bangka – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Istana Plaza (Pajajaran) – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Ciroyom – Terminal Ciroyom\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIBIRU KE CICADAS)</b><br/>\n" +
                                "Terminal Cibiru – Jl. Sukarno-Hatta – Pasar Induk Gede Bage (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Kiara Condong – Jl. Jakarta – Jl. Ahmad Yani – Cicadas (Ahmad Yani)\n" +
                                "\n",
                "<b>RUTE 1 (DARI SEKEMIRUNG KE PANYILEUKAN)</b><br/>\n"+
                        "Gedebage – Dago : Perum.Bumi Panyileukan – Jl.Soekarno Hatta – Jl.Pasar Induk Gede Bage – Jl.Soekarno Hatta – Jl.Rumah Sakit – Jl.AH.Nasution (Jl.Ujung Berung) – Jl.Ahmad Yani – Jl.KH.Mustofa – Jl.Cikutra Timur – Jl.Pahlawan – Jl.Surapati – Jl.Diponegoro – Jl.Surapati – Jl.Panata Yuda – Jl.Dipatiukur – Jl.Hasanudin – Jl.Ir.H.Juanda – Jl.Tubagus Ismail – Jl.Cibeunying Kolot – Jl.Cikondang – Pangkalan Sekemirung\n" +
                        " \n",
                "<b>RUTE 1 (DARI CARINGIN KE SADANG SERANG)</b><br/>\n" +
                                "Terminal Caringin – Jl. Caringin – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Garuda – Jl Abdul Rahman Saleh – Jl. Pajajaran – Jl. Pandu – Jl. Rajiman – Jl. Rivai – Jl. Wastu Kencana – Jl. Tamansari – Jl. Ganesha – Jl. Ir. H. Juanda (Dago) – Jl. TB. Ismail – Jl. Sadang Serang – Terminal Sadang Serang\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIBADUYUT KE KARANG SETRA)</b><br/>\n" +
                                "Jl. Cibaduyut – Jl. Bojongloa – Jl. Peta – Jl. BKR – Jl. Moh. Toha – Jl. Pungkur – Terminal Kebon Kalapa – Jl. Pasir Koja – Jl. Pajagalan – Jl. Gardu Jati – Jl. Pasir Kaliki – Jl. Sukajadi – Karang Setra\n" +
                                "\n",
                        "<b>RUTE 1 (DARI CIBOGO KE ELANG)</b><br/>\n" +
                                "Cibogo – Jl. Hercules – Jl. Pinggir Tol – Jl. Sukawarna – Jl. Suparmin – Jl. Sebelah IPTN – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Elang\n" +
                                "\n"};

        String[] lintasan2= new String[] {///blm
                "<b>RUTE 2 (DARI CICAHEUM KE ABDUL MUIS-BINONG)</b><br/>\n" +
                        "Terminal Cicaheum – Jl.Ahmad Yani – Jl.KH.Hasan Mustofa – Jl.Pahlawan – Jl.Brigjen Katamso – Jl.Supratman – Jl.Ahmad Yani – Jl.Bogor – Jl.Jakarta – jl.Ibrahim Adjie (Jl.kiaracondong) – Jl.Gatot Subroto – jl.Turangga – Jl.RAA.Martanegara – Jl.Pelajar pejuang 45 – Jl.Telaga Bodas – Jl.Palasari – jl.Gajah – Jl.Buah Batu – Jl.Gurame – Jl.Moh.Ramdan – Jl.BKR – Jl.Moh.Toha – Jl.Ibu Inggit – Jl.Dewi Sartika – Terminal Kebon Kalapa",
                "<b>RUTE 2 (DARI CICAHEUM KE ABDUL MUIS-ACEH)</b><br/>\n" +
                        "Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Cikutra – Jl. Katamso – Taman WR. Supratman – Jl. Cendana – Jl. Taman Pramuka – Jl. Aceh – Jl. Lombok – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
                "<b>RUTE 2 (DARI DAGO KE ABDUL MUIS)</b><br/>\n" +
                "Terminal Dago – Jl. H. Juanda (Dago) – Simpang Dago – ITB (Jl. Ganesha, Dago) – RS. Boromeus (Dago) – Jl. Sultan Agung – Jl. Trunojoyo – Jl. RE. Martadinata – BIP (Jl. Merdeka Dago) – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
                "<b>RUTE 2 (DARI LEDENG KE ABDUL MUIS)</b><br/>\n" +
                        "Terminal Ledeng – Jl. Setiabudi – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – BEC (Purnawarman) – Jl. Wastu Kencana – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
                "<b>RUTE 2 (DARI ELANG KE ABDUL MUIS)</b><br/>\n" +
                        "Terminal Elang – Jl. Sukarno-Hatta – Jl. Holis – Jl. Nana Rohana – Jl. Suryani – Jl. Situ Aksan – Jl. Pagarsih – Jl. Kalipah Apo – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
                "<b>RUTE 2 (DARI LEDENG KE CICAHEUM)</b><br/>\n" +
                        "Terminal Ledeng – Jl. Setiabudi – Jl. Cihampelas – Jl. Siliwangi – Jl. Sumur Bandung – Jl. Tamansari – Jl. Sulanjana – Jl. Diponegoro – Jl. WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum",
                "<b>RUTE 2 (DARI CIROYOM KE CICAHEUM)</b><br/>\n" +
                        "Terminal Ciroyom – Jl. Ciroyom – Jl. Arjuna – Jl. Pajajaran – Jl. Pasir Kaliki – RS Hasan Sadikin – Jl. Eyckman – Jl. Cipaganti – Jl. Setiabudi – Jl. Cihampelas – Jl. Siliwangi – Simpang Dago – Jl. Dipati Ukur – Jl. Singa Perbangsa – Monumen Perjuangan – Jl. Japati – Lapangan Gasibu (Surapati) – Jl. Surapati (Suci) – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum",
                "<b>RUTE 2 (DARI CIWASTRA KE CICAHEUM)</b><br/>\n" +
                        "Terminal Ciwastra – Jl. Ciwastra – Jl. Margacinta – Jl. Terusan Kiara Condong – Jl. Kiara Condong – Jl. Jakarta – Jl. WR. Supratman – Jl. Diponegoro – Jl. Sentot Alibasyah Lapangan Gasibu – Jl. Surapati (Suci) – *Jl. PHH. Mustofa (Suci) – *Terminal Cicaheum\n" +
                        " \n" +
                        "*hampir tidak pernah dilewati",
                "<b>RUTE 2 (DARI CIBADUYUT KE CICAHEUM)</b><br/>\n" +
                        "Terminal Cibaduyut – Jl. Cibaduyut – Jl. Kopo – Terminal Leuwi Panjang (Sukarno-Hatta) – Jl. Sukarno-Hatta – Jl. Kiara Condong – Jl. Jakarta – *Jl. WR. Supratman – *Jl. Katamso – *Jl. Pahlawan – *Jl. Cikutra – *Jl. PHH. Mutofa (Suci) – *Terminal Cicaheum",
                "<b>RUTE 2 (DARI ST.HALL KE DAGO)</b><br/>\n" +
                        "Jl. Stasiun Barat (Stasiun Bandung) – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Ir. H. Juanda – RS Boromeus (Dago) – ITB (Jl. Ganesha, Dago) – Simpang Dago – Terminal Dago",
                "<b>RUTE 2 (DARI SADANG SERANG KE ST.HALL)</b><br/>\n" +
                        "Terminal Sadang Serang – Jl. Cikutra Barat – Jl. Pahlawan – Jl. Katamso – Jl. WR. Supratman – Jl. Citarum – Jl. Lombok – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Jl. Braga – Jl. Suniaraja – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Suniaraja – Terminal Stasiun",
                "<b>RUTE 2 (DARI ST.HALL KE CIUMBULEUIT)</b><br/>\n" +
                        "Terminal Stasiun – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Cipaganti – Jl. Eyckman – Jl. Sederhana – Jl. Sempuna – Jl. Cipaganti – Jl. Setiabudi – Jl. Ciumbuleuit – UNPAR (Ciumbuleuit) – Terminal Ciumbuleuit",
                "<b>RUTE 2 (DARI CIUMBULEUIT KE ST.HALL via CIHAMPELAS)</b><br/>\n" +
                        "Pangkalan Ciumbeuleuit – Jl.Ciumbeuleuit – Jl.Cihampelas – Jl.Dr.Rivai – Jl.Dr.Cipto – Jl.Padjajaran – Jl.Cicendo – Jl.Kebon Kawung – Jl.HOS.Cokroaminoto (Jl.Pasir Kaliki) – Jl.Kebon Jati -Terminal St.Hall",
                "<b>RUTE 2 (DARI GEDEBAGE KE ST.HALL)</b><br/>\n" +
                        "Pasar Induk Gede Bage – Jl. Sukarno-Hatta – Riung Bandung (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Buah Batu – Jl. Kliningan – Jl. Karawitan – Jl. Mas Kumambang – Jl. Martanegara – Jl. Pelajar Pejuang – Jl. Talaga Bodas – Jl. Malabar – Jl. Ahmad Yani – Pasar Kosambi (Ahmad Yani) – Jl. Sunda – Jl. Sumbawa – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – St.Hall",
                "<b>RUTE 2 (DARI SARIJADI KE ST.HALL)</b><br/>\n" +
                        "Terminal Sarijadi – Jl. Sari Asih (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Jl. Sari Rasa (Sarijadi) – Jl. Surya Sumantri – Universitas Maranatha (Surya Sumantri) – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – St.Hall",
                "<b>RUTE 2 (DARI GUNUNG BATU KE ST.HALL)</b><br/>\n" +
                        "Terminal Gunung Batu – Jl. Gunung Batu – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Westhoff – Jl. Pasteur – BEC (Pasteur) – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – Terminal Stasiun",
                "<b>RUTE 2 (DARI LEDENG KE MARGAHAYU)</b><br/>\n" +
                        "Terminal Ledeng – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Cemara – Jl. Jurang – Jl. Sederhana – Jl. Eyckman – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Ahmad Yani – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Sukarno-Hatta – Jl. Ranca Bolang (Margahayu Raya) – Terminal Margahayu",
                "<b>RUTE 2 (DARI DAGO KE RIUNG BANDUNG)</b><br/>\n" +
                        "Terminal Dago – Jl. Ir. H. Juanda (Dago) – Simpang Dago – Jl. Dipati Ukur – Jl. Panatayuda – Jl. Surapati (Suci) – Jl. Sentot Alibasyah – Jl. Diponegoro – Jl. Citarum – Jl. RE. Martadinata – Jl. Laswi – Jl. Sukabumi – Jl. Ahmad Yani – Jl. Kiara Condong – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Jl. Cipamolokan (Riung Bandung) – Jl. Riung Bandung – Terminal Riung Bandung",
                "<b>RUTE 2 (DARI DAGO KE CARINGIN)</b><br/>\n" +
                        "Terminal Dago – Jl. Cigadung Raya – Jl. Cikutra Barat – Jl. Pahlawan – Jl. Surapati (Suci) – Jl. Cikapayang – Jl. Tamansari – Jl. Sawunggaling – Jl. Rangga Gading – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – Jl. Wastu Kencana – Jl. Purnawarman – Jl. Pajajaran – Jl. Cicendo – Jl. Rivai – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Arjuna – Jl. Supadio – Jl. Ciroyom – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Waringin – Jl. Sudirman – Jl. Jamika – Jl. Terusan Jamika – Jl. Sukamulya – Jl. Sukarno-Hatta – Jl. Babakan Ciparay – Pasar Induk Caringin (Sukarno-Hatta)",
                "<b>RUTE 2 (DARI DIPATIUKUR KE PANGHEGAR)</b><br/>\n" +
                        "Terminal Dipati Ukur – Jl. Hasanudin – Jl. Ir. H. Juanda – RS. Boromeus – Jl. Ganesha – ITB – Jl. Tamansari – Jl. Sawunggaling – UNISBA & UNPAS (Tamansari) – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Banda – Jl. Belitung – Jl. Sumatera – Jl. Veteran – Jl. Ahmad Yani – Pasar Kosambi (Ahmad Yani) – Cicadas (Ahmad Yani) – Terminal Cicaheum – Jl. AH. Nasution – Sindanglaya (Nasution) – Ujung Berung (Nasution) – Jl. Cicukang – Jl. Cisaranten – Panghegar",
                "<b>RUTE 2 (DARI SARIJADI KE CIROYOM via SUKAJADI)</b><br/>\n" +
                        "Terminal Sarijadi – Jl. Sari Wangi (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Asih (Sarijadi) – Jl. Sari Jadi – Jl. Geger Kalong Hilir – Jl. Cipedes – Jl. Sindang Sirna – Jl. Sirnagalih – Jl. Sukajadi – Jl. Sukamaju – Jl. Sederhana – RS. Hasan Sadikin – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Ciroyom – Terminal Ciroyom",
                "<b>RUTE 2 (DARI SARIJADI KE CIROYOM via SETRASARI)</b><br/>\n" +
                        "Jl.Peristis – Jl.Ters.Ir.Sutami – Jl.Ir.Sutami – Jl.Sindangsirna – Jl.Bungur – Jl.Karang Tinggal – Jl.Sukajadi – Jl.Pasteur – Jl.Otten – Jl.Dr.Rajiman – Jl.HOS.Cokroaminoto (Jl.Pasir Kaliki) – Jl.Pajajaran Jl.Abd.Saleh – Jl.Ciroyom – Term.Ciroyom",
                "<b>RUTE 2 (DARI CIROYOM KE BUMI ASRI)</b><br/>\n" +
                        "Terminal Ciroyom – Jl. Ciroyom – Jl. Garuda – Jl. Sudirman – Jl. Sukarno-Hatta – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Bumi Asri",
                "<b>RUTE 2 (DARI CIROYOM KE CIKUDAPATEUH)</b><br/>\n" +
                        "Terminal Ciroyom – Jl. Arjuna – Jl. Kebon Jati – Jl. Gardu Jati – Jl. Astana Anyar – Jl. Kopo – Jl. Peta – Jl. BKR – Jl. Moh. Ramdan – Jl. Banteng – Jl. Ahmad Yani – Jl. Cikudapateuh",
                "<b>RUTE 2 (DARI KEBON KELAPA KE BUAH BATU)</b><br/>\n" +
                        "Terminal Sederhana – Jl. Jurang – Jl. Cemara – Jl. Sukajadi – Jl. Pasir Kaliki (RS. Hasan Sadikin) – Jl. Pasteur – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Jukut – Viaduct – Jl. Braga – Jl. Lembong – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa– Jl. Dalem Kaum – Jl. Lengkong Besar – Jl. Cikawao – Jl. Buah Batu – Jl. Banteng – Jl. Gajah – Jl. Buah Batu – Terminal Buah Batu",
                "<b>RUTE 2 (DARI SEDERHANA KE CIJERAH)</b><br/>\n" +
                        "Jl. Sederhana – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Jl. Cijerah – Jl. Melong Asih",
                "<b>RUTE 2 (DARI CIMINDI KE SEDERHANA)</b><br/>\n" +
                        "Stasiun Cimindi – Jl.Gunung Batu – Jl.Sukaraja I – Jl.Komp.Cibogo – Jl.Dr.Junjunan – Jl.Sukagalih – Jl.Sukajadi – Jl.Cemara – Jl.Jurang -Term/Pasar Sederhana ",
                "<b>RUTE 2 (DARI UJUNGBERUNG KE CIWASTRA)</b><br/>\n" +
                        " Term.Ujung Berung – Jl.AH.Nasution (Jl.Raya Ujung Berung) – Jl.Cicukang – Jl.Cisaranten Kulon – Jl.Guruminda – Jl.Soekarno Hatta – Jl.Cipamokolan – Jl.Rancacili – Jl.Rancabolang – Jl.Derwati – Jl.Ciwastra – Pangkalan Ciwastra",
                "<b>RUTE 2 (DARI TEGALEGA KE CISITU)</b><br/>\n" +
                "Terminal Tegalega – Jl. Astana Anyar – Jl. Panjunan – Jl. Kopo – Jl. Pasir Koja – Jl. Pajagalan?? – Jl. Gardu Jati – Jl. Suniaraja – Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Wastu Kencana – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – ITB – Jl. Siliwangi – Jl. Sangkuriang – Jl. Cisitu – Jl. Cisitu Lama – Jl. Cisitu Lama VIII – Terminal Cisitu",
                "<b>RUTE 2 (DARI CIWASTRA KE CIJERAH)</b><br/>\n" +
                        "Terminal Ciwastra – Jl. Ciwastra – Jl. Margacinta – Jl. Terusan Kiara Condong – Jl. Sukarno-Hatta – Jl. Buah Batu – Jl. Kliningan – Jl. Karawitan – Jl. Reog – Jl. Martanegara – Jl. Lodaya – Jl. Sadakeling – Jl. Buah Batu – Jl. Moh. Ramdan – Jl. BKR – Jl. Peta – Jl. Bojongloa – Jl. Sukarno-Hatta – Jl. Caringin – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah",
                "<b>RUTE 2 (DARI GEDEBAGE KE ELANG)</b><br/>\n" +
                        "Terminal Ujung Berung – Jl.AH.Nasution (Jl.Ujung Berung) – Jl.Rumah Sakit – Jl.Soekarno Hatta – Jl.Pasir Luyu – Jl.Pasir Salam – Jl.Kembar Mas – Jl.Sriwijaya – Jl.BKR – Jl.Otista – Jl.Astana Anyar Jl.Panjunan – KH.Wahid Hasyim (Jl.Kopo) – Jl.Pasir Koja – Jl.Jamika – Jl.Jend.Sudirman – Jl.Rajawali – Jl.Elang – Pangkalan Elang.",
                "<b>RUTE 2 (DARI MENGGER KE ABDULMUIS)</b><br/>\n" +
                        " Jl.Mengger Hilir – Jl.Mengger Tengah – Jl.Sukaati – Jl.Soekarno Hatta – Jl.Sriwijaya – Jl.Lingkar Selatan – Jl. Sawah Kurung – Jl.ciateul – Jl.Dewi sartika.",
                "<b>RUTE 2 (DARI ELANG KE CICADAS)</b><br/>\n" +
                        "Elang – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Moh. Toha – Jl. Pungkur – Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Dalem Kaum – Jl. Lengkong Besar – Jl. Cikawao – Jl. Sadakeling – Jl. Burangrang – Jl. Gatot Subroto – Jl. Kiara Condong – Jl. Jakarta – Jl. Ahmad Yani – Cicadas",
                "<b>RUTE 2 (DARI CIROYOM KE ANTAPANI)</b><br/>\n" +
                        "Terminal Ciroyom – Jl. Ciroyom – Jl. Arjuna – Jl. Pajajaran – Istana Plaza (Pajajaran) – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – BEC (Purnawarman) – Jl. Wastu Kencana – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Bangka – Jl. Gudang Utara – Jl. Ahmad Yani – Stadion Persib (Ahmad Yani) – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Jakarta (Antapani) – Jl. Subang (Antapani) – Jl. Cibatu (Antapani) – Terminal Antapani",
                "<b>RUTE 2 (DARI CICADAS KE CIBIRU)</b><br/>\n" +
                        "Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Pasar Induk Gede Bage (Sukarno-Hatta) – Terminal Cibiru",
                "<b>RUTE 2 (DARI PANYILEUKAN KE SEKEMIRUNG)</b><br/>\n" +
                        "Perum.Bumi Panyileukan – Jl.Soekarno Hatta – Jl.Pasar Induk Gede Bage – Jl.Soekarno Hatta – Jl.Rumah Sakit – Jl.AH.Nasution (Jl.Ujung Berung) – Jl.Cikutra Timur – Jl.Pahlawan – Jl.Surapati – Jl.Sentot Alibasyah – Jl.Diponegoro – Jl.Surapati – Jl.Panata Yuda – Jl.Dipatiukur – Jl.Pager Gunung – Jl.Hasanudin – Jl.Ir.H.Juanda – Jl.Tubagus Ismail – Jl.Terunsan Cigadung – Jl.Cigadung Selatan – Jl.Cibeunying Selatan – Jl.Cibeunying Kolot – Jl.Cikondang – Pangkalan Sekemirung ",
                "<b>RUTE 2 (DARI SADANG SERANG KE CARINGIN)</b><br/>\n" +
                "Terminal Sadang Serang – Jl. Serang Serang – Jl. Tubagus Ismail – Simpang Dago – Jl. Tamansari – Jl. Sawunggaling – Jl. Tamansari – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kresna – Jl. Bima – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Caringin – Terminal Caringin",
                "<b>RUTE 2 (DARI KARANG SETRA KE CIBADUYUT)</b><br/>\n" +
                        "\n" +
                        "Karang Setra – Jl. Sukajadi – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cicendo – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Dulatip – Jl. Sudirman – Jl. Astana Anyar – Jl. Pasir Koja – Terminal Kebon Kalapa – Jl. Otto Iskandardinata – Jl. Peta – Jl. Bojongloa – Jl. Cibaduyut",
                "<b>RUTE 2 (DARI ELANG KE CIBOGO)</b><br/>\n" +
                        "Elang – Jl. Rajawali Timur – Jl. Garuda – Jl. Abdul Rahman Saleh – Jl. Pajajaran – Jl. Sebelah IPTN – Jl. Suparmin – Jl. Sukawarna – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Pinggir Tol – Jl. Hercules – Cibogo"
        };

        String[] harga= new String[] {
                "Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",/*10*/
                "Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",
                "Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",
                "Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000"

        };
        /*,"Rp.000","Rp.000","Rp.000"*/
        String[] operasi= new String[] {
                "04.00 wib - 21.00 wib","00.00 wib - 24.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "00.00 wib - 24.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","00.00 wib - 24.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib","04.00 wib - 21.00 wib",
                "04.00 wib - 21.00 wib","04.00 wib - 21.00 wib"
        };

        int[] gambar=new int[]{
<<<<<<< HEAD
                R.drawable.a01,
                R.drawable.b01,
                R.drawable.a02,
                R.drawable.a03,
                R.drawable.a04,
                R.drawable.a05,
                R.drawable.a06,
                R.drawable.a07,
                R.drawable.a08,
                R.drawable.a09,
                R.drawable.a10,
                R.drawable.a11,
                R.drawable.b11,
                R.drawable.a12,
                R.drawable.a13,
                R.drawable.a14,
                R.drawable.a15,
                R.drawable.a16,
                R.drawable.a17,
                R.drawable.a18,
                R.drawable.a19,
                R.drawable.b19,
                R.drawable.a20,
=======
                R.drawable.abdul_muis_cicaheum_via_binong,
                R.drawable.abdul_muis_cicaheum_via_aceh,
                R.drawable.abdul_muis_dago,
                R.drawable.abdul_muis_ledeng,
                R.drawable.abdul_muis_elang,
                R.drawable.cicaheum_ledeng,
                R.drawable.cicaheum_ciroyom,
                R.drawable.cicaheum_ciwastra,
                R.drawable.cicaheum_cibaduyut,
                R.drawable.dago_sthall,
                R.drawable.sthall_sadang_serang,
                R.drawable.ciumbuleuit_sthall,
                R.drawable.ciumbuleuit_sthall2,
                R.drawable.sthall_gedebage,
                R.drawable.sthall_sarijadi,
                R.drawable.sthall_gunung_batu,
                R.drawable.margahayu_ledeng,
                R.drawable.riungbandung_dago,
                R.drawable.caringin_dago,
                R.drawable.panghegar_dipatiukur,
                R.drawable.ciroyom_sarijadi,
                R.drawable.ciroyom_sarijadi2,
                R.drawable.bumi_asri_ciroyom,
>>>>>>> origin/master
                R.drawable.cikudapateuh_ciroyom,
                R.drawable.buah_batu_sederhana,
                R.drawable.cijerah_sederhana,
                R.drawable.cimindi_sederhana,
                R.drawable.ciwastra_uber,
                R.drawable.cisitu_tegalega,
                R.drawable.cijerah_ciwastra,
                R.drawable.elang_uber,
                R.drawable.abdulmuis_mengger,
                R.drawable.cicadas_elang,
                R.drawable.antapani_ciroyom,
                R.drawable.cibiru_cicadas,
                R.drawable.sekemirung_panyileukan_dago_gedebage,
                R.drawable.caringin_sadang_serang,
                R.drawable.cibaduyut_karang_setra,
                R.drawable.cibogo_elang
//                R.drawable.abdul_muis_cicaheum_via_aceh,
//                R.drawable.abdul_muis_dago,
//                R.drawable.abdul_muis_ledeng,
//                R.drawable.abdul_muis_elang,
//                R.drawable.cicaheum_ledeng,
//                R.drawable.cicaheum_ciroyom,
//                R.drawable.cicaheum_ciwastra,
//                R.drawable.cicaheum_cibaduyut,
//                R.drawable.dago_sthall,
//                R.drawable.sthall_sadang_serang,
//                R.drawable.ciumbuleuit_sthall,
//                R.drawable.sthall_gedebage,
//                R.drawable.sthall_sarijadi,
//                R.drawable.sthall_gunung_batu,
//                R.drawable.margahayu_ledeng,
//                R.drawable.riungbandung_dago,
//                R.drawable.caringin_dago,
//                R.drawable.panghegar_dipatiukur,
//                R.drawable.ciroyom_sarijadi,
//                R.drawable.bumi_asri_ciroyom,
//                R.drawable.cikudapateuh_ciroyom,
//                R.drawable.buah_batu_sederhana,
//                R.drawable.cijerah_sederhana,
//                R.drawable.cisitu_tegalega,
//                R.drawable.cijerah_ciwastra,
//                R.drawable.cicadas_elang,
//                R.drawable.antapani_ciroyom,
//                R.drawable.cibiru_cicadas,
//                R.drawable.caringin_sadang_serang,
//                R.drawable.cibaduyut_karang_setra,
//                R.drawable.cibogo_elang,
//                R.drawable.abdul_muis_cicaheum_via_binong,
//                R.drawable.ciumbuleuit_sthall2,
//                R.drawable.cimindi_sederhana,
//                R.drawable.ciwastra_uber,
//                R.drawable.elang_uber,
//                R.drawable.sekemirung_panyileukan_dago_gedebage,
//                R.drawable.abdulmuis_mengger
        };
        /*,
                    R.drawable.ciburial_ciroyom,
                    R.drawable.cicaheum_cileunyi,
                    R.drawable.lembang_sthall*/
        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
//        adapter = new ViewPagerAdapter(Info_detail_angkot.this, no, warna, nama_trayek, lintasan, lintasan2,harga,operasi,gambar);
        adapter = new ViewPagerAdapter(Info_detail_angkot.this, no, nama_trayek, lintasan, lintasan2,gambar);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        int posi = bundle.getInt("setk");
        viewPager.setCurrentItem(posi);




//       rute1.setOnClickListener(this);
//        rute2.setOnClickListener(this);


    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
////        if(backButtonCount >= 1)
////        {
////            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            startActivity(intent);
////        }
////        else
////        {
////            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
////            backButtonCount++;
////        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_place) {
            Intent openStep = new Intent(this, MainActivity.class);
            startActivity(openStep);
        }else if (id == R.id.nav_camera) {
            Intent openStep = new Intent(this, Tes2.class);
            startActivity(openStep);
        } else if (id == R.id.nav_gallery) {
            Intent openStep = new Intent(this, Info_jalan.class);
            startActivity(openStep);
        } else if (id == R.id.nav_slideshow) {
            Intent openStep = new Intent(this, Info_angkot.class);
            startActivity(openStep);
        } else if (id == R.id.nav_manage) {
            Intent openStep = new Intent(this, about.class);
            startActivity(openStep);
        }/*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
