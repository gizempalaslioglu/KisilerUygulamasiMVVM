package com.example.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisilerdaoRepository

class AnasayfaFragmentViewModel:ViewModel() {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    val kdaor=KisilerdaoRepository()


  //viewmodel'dan nesne oluşturduğumuz anda bilgiyi nesneye aktaralım
    init {
        kisileriYukle()
        kisilerListesi=kdaor.kisileriGetir()
    }

    fun kisileriYukle() {
      //val liste=ArrayList<Kisiler>()
        //liste.add(Kisiler(1,"Ahmet","999"))
        //liste.add(Kisiler(2,"Mehmet","888"))

        //kisilerListesi.value=liste //livedataya veri aktarmak

      kdaor.tumKisileriAl()

    }
  fun ara(aramaKelimesi:String) {
    kdaor.kisiAra(aramaKelimesi)
  }

    fun sil(kisi_id: Int) { //silme
    //Log.e("Kişi sil","$kisi_id")
      kdaor.kisiSil(kisi_id)
    }
}