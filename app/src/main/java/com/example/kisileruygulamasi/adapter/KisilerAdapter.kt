package com.example.kisileruygulamasi.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.databinding.CardTasarimBinding
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.fragment.AnasayfaFragmentDirections
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

//
class KisilerAdapter(var mContext:Context,var kisilerListesi:List<Kisiler>,var viewModel: AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>() { //sınıfı kişileradapter'a bağladık

    inner class CardTasarimTutucu(cardTasarimBinding: CardTasarimBinding) :
        RecyclerView.ViewHolder(cardTasarimBinding.root) {
        //tasarim değişkeni oluşturuyoruz.Bununla görsel nesnelere erişmiş olacağız.
        var cardTasarim:CardTasarimBinding

        init {
            this.cardTasarim=cardTasarimBinding
        }
    }
    //tasarimla bağlama işlemi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater=LayoutInflater.from(mContext)
        val tasarim=CardTasarimBinding.inflate(layoutInflater,parent,false)
        //CardTasarimTutucu sınıfından bir nesne istiyor
        return CardTasarimTutucu(tasarim)

    }
    //card a özgü işlemler burada gerçekleşir ve kaç eleman varsa o kadar çalışır(position)
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kisi=kisilerListesi.get(position) //position:0 ilk kişiyi,1 gelirse ikinciyi..
        //holder nesnesi cardTasarima erişmemizi sağlıyor

        holder.cardTasarim.kisiNesnesi=kisi //databinding variable oluşturulduktan sonraki kod yazımı

        //card'a tıklanma kodu
        holder.cardTasarim.satirCard.setOnClickListener{
            //geçiş kısmı
            val gecis= AnasayfaFragmentDirections.detayGecis(kisi)
            Navigation.findNavController(it).navigate(gecis)
        }

        //menüdeki tıklanmalar
        holder.cardTasarim.imageViewSilResim.setOnClickListener{
            Snackbar.make(it,"${kisi.kisi_ad} silmek istiyor musunuz?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.sil(kisi.kisi_id)
                    Snackbar.make(it,"${kisi.kisi_ad} silindi", Snackbar.LENGTH_LONG).show()

                }
                .show()
        }
    }

    override fun getItemCount(): Int { //veri kümesinin içinde kaç eleman var,kaç satır oluşturacağım vs.
        return kisilerListesi.size //kisilerlistesinin büyüklüğü kadar döndürürürüm
    }
}