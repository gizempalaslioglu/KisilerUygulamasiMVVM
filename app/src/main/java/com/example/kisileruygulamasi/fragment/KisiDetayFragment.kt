package com.example.kisileruygulamasi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.databinding.FragmentKisiDetayBinding
import com.example.kisileruygulamasi.viewmodel.KisiDetayFragmentViewModel


class KisiDetayFragment : Fragment() {

    private lateinit var tasarim:FragmentKisiDetayBinding
    private lateinit var viewModel: KisiDetayFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_kisi_detay, container, false)
        tasarim.kisiDetayFragment=this
        tasarim.kisiDetayToolbarBaslik="Kişi Detay"

        val b:KisiDetayFragmentArgs by navArgs()
        tasarim.kisiNesne=b.nesne

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Fragment içinde viewModel tanımlaması
        val tempViewModel: KisiDetayFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun buttonGuncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String){ //buton bastığımda güncelleme yapacak
         viewModel.guncelle(kisi_id,kisi_ad,kisi_tel)
    }


}