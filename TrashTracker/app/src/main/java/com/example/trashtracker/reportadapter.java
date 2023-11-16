package com.example.trashtracker;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import javax.net.ssl.SSLEngineResult;

public class reportadapter extends RecyclerView.Adapter<reportadapter.MyViewHolder> {

    ArrayList<Reportdatamodel> reportdatamodelArrayList;
    Context context;
    LayoutInflater inflator;
    String status,url=Config.baseurl+"updatereportstatus.php",error;
    String id,mobilenumber;



    public reportadapter(truckreportwaste truckreportwaste, ArrayList<Reportdatamodel> reportdatamodelArrayList) {

        this.reportdatamodelArrayList=reportdatamodelArrayList;
        this.context=truckreportwaste;
        inflator=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public reportadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =inflator.inflate(R.layout.reportview, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull reportadapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.report_name.setText("Name:"+reportdatamodelArrayList.get(position).getName());
        holder.report_location.setText("Location:"+reportdatamodelArrayList.get(position).getLocation());
        holder.report_description.setText("Description:"+reportdatamodelArrayList.get(position).getDescription());
        holder.report_ward.setText("Ward:"+reportdatamodelArrayList.get(position).getWard());
        holder.report_date.setText("Date:"+reportdatamodelArrayList.get(position).getDate());
        holder.report_time.setText("Time:"+reportdatamodelArrayList.get(position).getTime());

        holder.reportcomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(reportdatamodelArrayList.get(position).getId(),holder,position);
              //  Toast.makeText( context, reportdatamodelArrayList.get( position ).getId(), Toast.LENGTH_SHORT ).show();
            }
        });





    }

    @Override
    public int getItemCount() {
        return reportdatamodelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        //declaring

        TextView report_name,report_location,report_description,report_ward,report_date,report_time;
        Button reportcomplete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //finding

            report_name=itemView.findViewById(R.id.report_name);
            report_location=itemView.findViewById(R.id.report_location);
            report_description=itemView.findViewById(R.id.report_description);
            report_ward=itemView.findViewById(R.id.report_ward);
            reportcomplete=itemView.findViewById(R.id.reportcomplete);
            report_date=itemView.findViewById(R.id.report_date);
            report_time=itemView.findViewById(R.id.report_time);



        }
    }

    private void updateStatus(String id, MyViewHolder holder, int position) {
        StringRequest request =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("1")) {
                    Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    fetch(reportdatamodelArrayList.get( position ).getMid());

                }else{

                    Toast.makeText(context, "ERROR Occured", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams()  {

                Map lg=new HashMap<String,String>();
                lg.put("id",id);
                return lg;

            }
        };

        RequestQueue q= Volley.newRequestQueue(context);
        q.add(request);

    }

    private void fetch(String mid) {
        String url1 =Config.baseurl+"Member.php";
        StringRequest request =new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("message");
                    id = jsonObject.getString("id");
                    mobilenumber = jsonObject.getString("mobilenumber");
                  //  Toast.makeText( context, mobilenumber, Toast.LENGTH_SHORT ).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("1")) {
                  //  Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,Sendsms.class);
                    intent.putExtra( "id",id );
                    intent.putExtra( "mobilenumber",mobilenumber );
                    context.startActivity( intent );


                }else{

                   // Toast.makeText(context, "ERROR Occured", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams()  {

                Map lg=new HashMap<String,String>();
                lg.put("mid",mid);
                return lg;

            }
        };

        RequestQueue q= Volley.newRequestQueue(context);
        q.add(request);



    }
}
