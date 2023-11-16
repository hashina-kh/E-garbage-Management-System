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

public class CompleteAdapter  extends RecyclerView.Adapter<CompleteAdapter.MyViewHolder> {
    ArrayList<Reportdatamodel> reportdatamodelArrayList;
    Context context;
    LayoutInflater inflator;
    String status,error;
    String id,mobilenumber;
//    String url=Config.baseurl+"updatestatus.php";


    public CompleteAdapter(CompletedList completedList, ArrayList<Reportdatamodel> reportdatamodelArrayList) {

        this.reportdatamodelArrayList=reportdatamodelArrayList;
        this.context=completedList;
        inflator=LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public CompleteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =inflator.inflate(R.layout.comp, parent, false);
        CompleteAdapter.MyViewHolder holder = new CompleteAdapter.MyViewHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull CompleteAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.report_name.setText("Name:"+reportdatamodelArrayList.get(position).getName());
        holder.report_location.setText("Location:"+reportdatamodelArrayList.get(position).getLocation());
        holder.report_description.setText("Description:"+reportdatamodelArrayList.get(position).getDescription());
        holder.report_ward.setText("Ward:"+reportdatamodelArrayList.get(position).getWard());
        holder.report_status.setText("Status:"+reportdatamodelArrayList.get(position).getStatus());

//        holder.reportcomplete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateStatus(reportdatamodelArrayList.get(position).getId(),holder,position);
//                //  Toast.makeText( context, reportdatamodelArrayList.get( position ).getId(), Toast.LENGTH_SHORT ).show();
//            }
//        });

//        holder.report.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fetch(reportdatamodelArrayList.get( position ).getId(),reportdatamodelArrayList.get( position ).getDrivername(),
//                        reportdatamodelArrayList.get( position ).getWard());
//            }
//        } );




    }

    private void fetch(String id, String name,String ward) {
        String url1 =Config.baseurl+"Add_complaint.php";
        StringRequest request =new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("message");
//                    id = jsonObject.getString("id");
//                    mobilenumber = jsonObject.getString("mobilenumber");
                    //  Toast.makeText( context, mobilenumber, Toast.LENGTH_SHORT ).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("1")) {
                    Toast.makeText(context, "Complaint Registerd", Toast.LENGTH_SHORT).show();
                    context.startActivity( new Intent(context,WardHome.class) );
//                    Intent intent=new Intent(context,Sendsms.class);
//                    intent.putExtra( "id",id );
//                    intent.putExtra( "mobilenumber",mobilenumber );
//                    context.startActivity( intent );


                }else{

                    Toast.makeText(context, "Failled", Toast.LENGTH_SHORT).show();

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
                lg.put("name",name);
                lg.put("ward",ward);
                return lg;

            }
        };

        RequestQueue q= Volley.newRequestQueue(context);
        q.add(request);




    }

    @Override
    public int getItemCount() {
        return reportdatamodelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        //declaring

        TextView report_name, report_location, report_description, report_ward, report_status;
        Button reportcomplete, report;


        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            //finding

            report_name = itemView.findViewById( R.id.report_name );
            report_location = itemView.findViewById( R.id.report_location );
            report_description = itemView.findViewById( R.id.report_description );
            report_ward = itemView.findViewById( R.id.report_ward );
            report_status = itemView.findViewById( R.id.report_status );
//            reportcomplete = itemView.findViewById( R.id.reportcomplete );
//            report = itemView.findViewById( R.id.report );


        }

    }

    }
