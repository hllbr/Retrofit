package com.hllbr.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hllbr.retrofitkotlin.R
import com.hllbr.retrofitkotlin.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>,private val listener: Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors : Array<String> = arrayOf("#009569","#FE20B2","#c0cfb2","#a0bed1"
    ,"#045150","#f09e2e","#c01025","#00f6f0","#005bf6","#306100","#b600f6","#5b9398","#08c7c9","#f6d0e1"
    ,"#c88777","#e2b7bf")


    class RowHolder(view: View):RecyclerView.ViewHolder(view){
        /*
      Bu alanda hangi item ne verisi gösterecek onu belirlediğimiz alan
       */
        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position:Int,listener:Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }

            itemView.setBackgroundColor(Color.parseColor(colors[position % 16]))
            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        //Kaç row oluşturulacağını söylemem gerekiyor.Burada uygulamamdan kaynasklı sayıyı bilemem çünkü milyon adet coin bulunuyor.Burada işlemi MainActivity içerisinde
        return cryptoList.count()
       // return  cryptoList.size

    }
}