package com.example.fit_test_one.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fit_test_one.APIData
import com.example.fit_test_one.R

class RecaitalView : RecyclerView.Adapter<RecaitalView.UserViewHolder>() {
    var listOfData:ArrayList<APIData> = ArrayList()

    fun setList(arrayList: ArrayList<APIData>){
        this.listOfData=arrayList
        notifyDataSetChanged()


    }



    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_image:ImageView=itemView.findViewById(R.id.imageView)
            var tv_textId:TextView=itemView.findViewById(R.id.textView)
            var tv_textTital:TextView=itemView.findViewById(R.id.textView2)



        fun bind(apiData: APIData){

                    tv_textId.text=apiData.url
                    tv_textTital.text=apiData.title
                     Glide.with(itemView.context)
                  .load(apiData.thumbnailUrl)
                  .error(R.drawable.ic_launcher_background)
                     .into(tv_image)


                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.recicalview,parent,false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listOfData.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      var apidata:APIData= listOfData[position]
       holder.bind(apidata)

    }
}