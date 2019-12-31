package com.byandev.warnaspvdev.MainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class DetailOrderActivity extends AppCompatActivity {

    private ApiEndPoint mApiService;
    private Integer idContact,idOrder;
    TextView namaCfa, tglPooling, jamPooling, sumberOrder, stsKonsumen, namaOutlet, cabangFif, posFif, tglSurvei, jamSurvei,
            namaPemohon, noHp, noHp2, tmpLhr, tglLhr, pNama, pTmpLhr, pTglLhr, namaIbu, jmlTanggungan,
            pekerjaan, stsKaryawan, jabatan, lamaKerja, perusahaan, penghasilan, pengeluaran,
            pPekerjaan, pStatusKaryawan, pJabatan, pLamaKerja, pPerusahaan, pPenghasilan, pPengeluaran,
            otr, plafond, dp, angsuran, tenor, keperluan,
            merk, type, vehicle, tahunKendaraan, nopol, sPajak, owner,
            alamat, rtrw, kel, kec, kab, prov, sRumah, sAlamat;
    FloatingActionButton btnScrrenShot;
    String model, tahun;
    NumberFormat formatter = new DecimalFormat("#,###");
    SharedPrefManager sharedPrefManager;
    private Context context;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_detail_order);
        context = this;

        sharedPrefManager = new SharedPrefManager(context);

        namaCfa = findViewById(R.id.tv_hsl_nama_cfa);
        tglPooling = findViewById(R.id.tv_hsl_tgl_pooling);
        jamPooling = findViewById(R.id.tv_hsl_jam_pooling);
        sumberOrder = findViewById(R.id.tv_hsl_sumber_order);
        stsKonsumen = findViewById(R.id.tv_hsl_status_konsumen);
        namaOutlet = findViewById(R.id.tv_hsl_nama_outlet);
        cabangFif = findViewById(R.id.tv_hsl_cabang_fif);
        posFif = findViewById(R.id.tv_hsl_pos_fif);
        tglSurvei = findViewById(R.id.tv_hsl_tgl_survey);
        jamSurvei = findViewById(R.id.tv_hsl_jam_survey);

        namaPemohon = findViewById(R.id.tv_hsl_nama_pemohon);
        noHp = findViewById(R.id.tv_hsl_no_hp);
        noHp2 = findViewById(R.id.tv_hsl_no_hp_2);
        tmpLhr = findViewById(R.id.tv_hsl_tempat_lahir);
        tglLhr = findViewById(R.id.tv_hsl_tgl_lahir);
        pNama = findViewById(R.id.tv_hsl_nama_penjamin);
        pTmpLhr = findViewById(R.id.tv_hsl_ptempat_lahir);
        pTglLhr = findViewById(R.id.tv_hsl_ptgl_lahir);
        namaIbu = findViewById(R.id.tv_hsl_ibu_kandung);
        jmlTanggungan = findViewById(R.id.tv_hsl_jml_tanggungan);

        pekerjaan = findViewById(R.id.tv_hsl_pekerjaan);
        stsKaryawan = findViewById(R.id.tv_hsl_status_karyawan);
        jabatan = findViewById(R.id.tv_hsl_jabatan);
        lamaKerja = findViewById(R.id.tv_hsl_lama_kerja);
        perusahaan = findViewById(R.id.tv_hsl_perusahaan);
        penghasilan = findViewById(R.id.tv_hsl_penghasilan);
        pengeluaran = findViewById(R.id.tv_hsl_pengeluaran);

        pPekerjaan = findViewById(R.id.tv_hsl_pekerjaan_pasangan);
        pStatusKaryawan = findViewById(R.id.tv_hsl_status_pasangan);
        pJabatan = findViewById(R.id.tv_hsl_jabatan_pasangan);
        pLamaKerja = findViewById(R.id.tv_hsl_lama_kerja_pasangan);
        pPerusahaan = findViewById(R.id.tv_hsl_perusahaan_pasangan);
        pPenghasilan = findViewById(R.id.tv_hsl_penghasilan_pasangan);
        pPengeluaran = findViewById(R.id.tv_hsl_pengeluaran_pasangan);

        otr = findViewById(R.id.tv_hsl_otr);
        plafond = findViewById(R.id.tv_hsl_plafond);
        dp = findViewById(R.id.tv_hsl_dp);
        angsuran = findViewById(R.id.tv_hsl_angsuran);
        tenor = findViewById(R.id.tv_hsl_tenor);
        keperluan = findViewById(R.id.tv_hsl_keperluan);

        merk = findViewById(R.id.tv_hsl_merk_kendaraan);
        type = findViewById(R.id.tv_hsl_type_kendaraan);
        vehicle = findViewById(R.id.tv_hsl_vehicle);
        tahunKendaraan = findViewById(R.id.tv_hsl_thn_kendaraan);
        nopol = findViewById(R.id.tv_hsl_nopol);
        sPajak = findViewById(R.id.tv_hsl_pajak);
        owner = findViewById(R.id.tv_hsl_owner);

        alamat = findViewById(R.id.tv_hsl_alamat);
        rtrw = findViewById(R.id.tv_hsl_rtrw);
        kel = findViewById(R.id.tv_hsl_kelurahan);
        kec = findViewById(R.id.tv_hsl_kecamatan);
        kab = findViewById(R.id.tv_hsl_kabupaten);
        prov = findViewById(R.id.tv_hsl_provinsi);
        sRumah = findViewById(R.id.tv_hsl_sts_rumah);
        sAlamat = findViewById(R.id.tv_hsl_sts_alamat);

        btnScrrenShot = findViewById(R.id.btnScreenShot);
//        btnScrrenShot.setVisibility(View.GONE);

        mApiService = UtilsApi.getAPIService();
        idContact = getIntent().getIntExtra("idContact", 0);
        idOrder = getIntent().getIntExtra("idOrder", 0);

//        getDataPrint();
    }

    private void convertTime(String time) {
        @SuppressLint("SimpleDateFormat")SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")SimpleDateFormat format1 = new SimpleDateFormat("dd-MMMM-yyyy");
    }
}
