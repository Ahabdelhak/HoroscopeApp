package com.example.webserv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
    private Sign_Model[] listdata;

    private Context context;


    SharedPreferences pref ;

    // RecyclerView recyclerView;
    public MyRecyclerViewAdapter(Sign_Model[] listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Sign_Model myListData = listdata[position];
        holder.txtSign.setText(listdata[position].getSign());
        holder.txt_date.setText(listdata[position].getDate());
        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getSign() + "Name " + listdata[position].getSign_eng_name(),Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("signName", listdata[position].getSign()); // Storing string
                editor.putString("signNameEng", listdata[position].getSign_eng_name()); // Storing string
                editor.commit(); // commit changes



                Intent intent =new Intent(context,Chooce_Daily_Mon.class);

                intent.putExtra("signName",listdata[position].getSign());
                intent.putExtra("signNameEng",listdata[position].getSign_eng_name());


                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), listdata[position].getImgId()); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("signImage", bs.toByteArray());

                //intent.putExtra("signImage",listdata[position].getImgId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtSign,txt_date;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img_sign);
            this.txtSign = (TextView) itemView.findViewById(R.id.txtsign);
            this.txt_date = (TextView) itemView.findViewById(R.id.txtdate);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ln);
        }
    }
}