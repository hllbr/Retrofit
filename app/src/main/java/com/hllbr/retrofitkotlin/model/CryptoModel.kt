package com.hllbr.retrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency")
    val currency:String,
    //@SerializedName("price")
    val price:String)
    /*
    data class = Eğer data sınıfı yapıyorsak bir constructor parametresi olması lazım.
    Data sınıfının tek amacı tamamen veri çekeceğimiz ve bu verileri işleyeceiğimiz bir yapı oluşturmak.
    Genelde data sınıflarının içersinde çok fazla method vb olmaz
    sadece constructor da hangi verileri çekeceğimizi belirten parametreler olur.
    Bu uygulama için paramtrelerin currency ve price olacak her bir modelin içerisinde her bir objenin içersinde
    2 attribute olacak
    yukarıdaki yapı oluşturulduktan sonra süslü parantezler bırakıladabilir silinedebilir önemi kalmamış oluyor.
    **Kotlinde bu data sınıfının yardımıyla ben eğer currency ve price değerlerini aynı isimle kaydedersem SerializedName ifadelerini koymama gerek kalmıyor.

     */
