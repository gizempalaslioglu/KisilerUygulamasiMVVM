package com.example.kisileruygulamasi.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.adapter.KisilerAdapter
import com.example.kisileruygulamasi.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel


class AnasayfaFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var tasarim:FragmentAnasayfaBinding
    //adapter ve viewmodel tanımlama
    private lateinit var adapter: KisilerAdapter
    private lateinit var viewModel:AnasayfaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment=this
        tasarim.anasayfaToolbarBaslik="Kişiler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)//toolbara menü eklemek için

        //viewmodel içindeki kodlamayı çalıştırma kısmı
        viewModel.kisilerListesi.observe(viewLifecycleOwner,{kisilerListesi->
           adapter= KisilerAdapter(requireContext(),kisilerListesi,viewModel)
            tasarim.adapter=adapter
        })

        return tasarim.root
    }

    fun fabTikla(view:View){ //fab(+) butona tıklandığında sayfa geçişi olacak
         //Log.e("Deneme","fab")

        Navigation.findNavController(view).navigate(R.id.kayitGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//menuyu çalıştırmak için
        val temp:AnasayfaFragmentViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { //toolbar menu bağlamak
        inflater.inflate(R.menu.toolbar_arama,menu)
        val item=menu.findItem(R.id.action_ara)//item'a erişmek
        //search view özelliği vermek
        val searchView=item.actionView as SearchView
        //search view 'ı sayfaya bağlamak
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Arama tuşuna basılınca",query)
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf girdikçe",newText)
        viewModel.ara(newText)
        return true
    }

    override fun onResume() { //her geri geldiğimizde arayüz güncellenecek
        super.onResume()
        viewModel.kisileriYukle()
    }
}