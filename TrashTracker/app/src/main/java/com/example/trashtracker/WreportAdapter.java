package com.example.trashtracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WreportAdapter extends RecyclerView.Adapter<WreportAdapter.MyViewHolder> {
    ArrayList<Reportdatamodel> reportdatamodelArrayList;
    Context context;
    LayoutInflater inflator;
    String status,error;
    String id,mobilenumber;



    public WreportAdapter(View_reports view_reports, ArrayList<Reportdatamodel> reportdatamodelArrayList) {

        this.reportdatamodelArrayList=reportdatamodelArrayList;
        this.context=view_reports;
        inflator=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public WreportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =inflator.inflate(R.layout.report, parent, false);
        WreportAdapter.MyViewHolder holder = new WreportAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WreportAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.report_name.setText("Name:"+reportdatamodelArrayList.get(position).getName());
        holder.report_location.setText("Location:"+reportdatamodelArrayList.get(position).getLocation());
        holder.report_description.setText("Description:"+reportdatamodelArrayList.get(position).getDescription());
        holder.report_ward.setText("Ward:"+reportdatamodelArrayList.get(position).getWard());

//        holder.reportcomplete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateStatus(reportdatamodelArrayList.get(position).getId(),holder,position);
//                //  Toast.makeText( context, reportdatamodelArrayList.get( position ).getId(), Toast.LENGTH_SHORT ).show();
//            }
//        });





    }

    @Override
    public int getItemCount() {
        return reportdatamodelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        //declaring

        TextView report_name,report_location,report_description,report_ward;
        Button reportcomplete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //finding

            report_name=itemView.findViewById(R.id.report_name);
            report_location=itemView.findViewById(R.id.report_location);
            report_description=itemView.findViewById(R.id.report_description);
            report_ward=itemView.findViewById(R.id.report_ward);



        }


    }

}
