package com.hllbr.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hllbr.retrofitkotlin.R
import com.hllbr.retrofitkotlin.adapter.RecyclerViewAdapter
import com.hllbr.retrofitkotlin.model.CryptoModel
import com.hllbr.retrofitkotlin.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener{
    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // RecyclerView data gelemden önce oluşturmak mantıklı bir tercih olarka görünüyor bu projede

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }
    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object :Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        //bir üst satırdaki yapı eğer kod bloğum boş gelmediyse alt satırdakileri devreye sok
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            //it yerine cryptoModels!! da yazılabilirdi alternatif olarak
                            recyclerView.adapter = recyclerViewAdapter
                        }

                    /*
                        for (cryptoModel :CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)

                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Cliked:${cryptoModel.currency}",Toast.LENGTH_LONG).show()
        }
    /*https://api.nomics.com/v1/prices?key=a82399be10cc5c1dadff681c8df1eeefc123916b
      a82399be10cc5c1dadff681c8df1eeefc123916b
      API = application programing interface = uygulama programlama arayüzü
      API genellikle iki sistem arasında iletişimi sağlar
      Örneğin sunucu ve uygulama yada pc ve sunucu arasında iletişimi sağlar
      sunucudan bir transfer yapmak yada sunucuya bir transfer yapmak için kullanılıyor.API
      Örneğin hava durumu uygulaması yapacağız weather API kullanmamaız gerekiyor.
      currency API döviz çevirici buradaki veriler bir sunucuda depolanıyor sonuç olarak
      Biz verilerin depolandığı sunucuya bir istek yollayarak verilerin çekilmesini sağlayabiliyoruz.
      O sunucu bizim veri yazmamıza izin veriyorsa o veriyide yazabiliyoruz.
      API ler veriyi bize çoğunlukla json olarak veriyorlar.Böyle bir zorunluluk yok illa json olarak verecek diye bir durum söz konusu değil
      Genellikle endüstride standart budur.
      Verileri anlaşılır hale getirmek anlamlı hale getirmek için JSON Beautifer internetytte aratılarak herhangi bir sitedcen yararlanılabilir.
      JSON ile ilgili bir çok kütüphane mevcut anlamlandırabilmek modelleyebilmek için
      JSON =JAVA SCRİPT OBJECT NOTATİON = aslında bir gösterim şekli

      Asynctask = çekilen verielrin asenkron bir şekilde gelmesini sağlıyor.
      Biz internetten bir veri çektiğimizde  verinin inmesi uzun sürüyor.
      internetten indirilen verinin süresi farklılıklar göstermekte 1 saniyede sürebilir 1 saatte
      uzun süren işlemlerde gittik geldik durumlarında arkaplanda yapılması gerekiyor .
      Kullanıcının arayüzünü bloklamadan işlemlerin gerçekleştirilmesi gerekiyor.
      Bu RUNNABLE içerisinde gördüğüm ve kullandığım mantık
      kullanıcının akışında bir problem olmadan arkaplanda işlem devam eder .
      Bu işlevselliği sağlamak için asynctask kullanılıyordu eskiden .
      Firebase internetten veri alır yada yazarken asyncron şekilde çalıştığı için bu yapıya ihtiyaç duymadık
      Şimdi yapmak istediğim işlemi internetten alacağım veriyi arkaplanda almalıyım ve asyncron olarak almalıyım.
      Bu yapı 2020 ortasından itibaren tedavülden kalma durumuna geldi .Hala çalışır fakat tavsiye edilen bir durum değil .
      Bu yapının yerine retrofit oalrak tanımlanan ayrı bir kütüphane kullanılıyor.

      İş dünyasında Retrofit kütüphanesini ve RXJAVA oalrak bilinen bir kütüphane daha var bunuda bilmek gerekiyor.
      Retrofit internetten veri çekmek için özelllikle API ye bir istek yollayıp cevaplarını alıp uygulama üzerinde göstermek için kullandığımız bir kütüphane .
      RXJAVA ise bu kütüphaneye yardımcı bir kütüphane olarak karşımıza çıkıyor.
      Asynctask ve basşka tradelerde yapılamsı gereken işlemlerde rxjava bize yardımcı oluyor.
      Bu yapı sadece java da değil kotlinde de kullanılabiliyor.

      Burada bir model oluşturacağız.Bu model ile indirilen verileri işlenebilir hale getireceğiz.
      Json vb... kullanarak bundan soran veriler bize işlenmiş şekilde sunulmuş olacak
       */
}