package com.example.kisileruygulamasi.repo

import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.CRUDCevap
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.entity.KisilerCevap
import com.example.kisileruygulamasi.retrofit.ApiUtils
import com.example.kisileruygulamasi.retrofit.KisilerDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//retrofitle alakalı metotlar,veri tabanından veri getirip viewmodel'e sunacak
class KisilerdaoRepository {
    private val kisilerListesi: MutableLiveData<List<Kisiler>>
    private val kdaoi: KisilerDaoInterface

    init {
        kdaoi = ApiUtils.getKisilerDaoInterface()
        kisilerListesi = MutableLiveData()
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> { //verileri veritabanından alıp kisilerListesi'ne aktarıyoruz
        return kisilerListesi
    }

    fun tumKisileriAl() {
        kdaoi.tumKisiler().enqueue(object : Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body().kisiler
                kisilerListesi.value = liste
            }
            override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
        })
    }

    fun kisiAra(aramaKelimesi:String) {
        kdaoi.kisiAra(aramaKelimesi).enqueue(object : Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body().kisiler
                kisilerListesi.value = liste
            }
            override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
        })
    }

    fun kisiKayit(kisi_ad: String, kisi_tel: String) {
        kdaoi.kisiEkle(kisi_ad, kisi_tel).enqueue(object : Callback<CRUDCevap?> {
            override fun onResponse(call: Call<CRUDCevap?>, response: Response<CRUDCevap?>) {}
            override fun onFailure(call: Call<CRUDCevap?>, t: Throwable) {}
        })
    }

    fun kisiGuncelle(kisi_id:Int,kisi_ad: String, kisi_tel: String) {
        kdaoi.kisiGuncelle(kisi_id,kisi_ad,kisi_tel).enqueue(object :
            Callback<CRUDCevap?> {
            override fun onResponse(call: Call<CRUDCevap?>, response: Response<CRUDCevap?>) {}
            override fun onFailure(call: Call<CRUDCevap?>, t: Throwable) {}
        })
    }

    fun kisiSil(kisi_id: Int) {
        kdaoi.kisiSil(kisi_id).enqueue(object : Callback<CRUDCevap?> {
            override fun onResponse(call: Call<CRUDCevap?>, response: Response<CRUDCevap?>) {
                tumKisileriAl() //silme işleminden sonra güncelleme
            }
            override fun onFailure(call: Call<CRUDCevap?>, t: Throwable) {}
        })
    }
}