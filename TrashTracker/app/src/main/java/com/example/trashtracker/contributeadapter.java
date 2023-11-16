package com.example.trashtracker;

import android.app.Activity;
import android.content.Context;
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

public class contributeadapter extends RecyclerView.Adapter<contributeadapter.MyViewHolder> {

    ArrayList<Contributedatamodel> datamodelArrayList;
    Context context;
    LayoutInflater inflator;
    String status, error, url = Config.baseurl + "updatecontributestatus.php";

    public contributeadapter(truckcontributewaste c, ArrayList<Contributedatamodel> contributedatamodelArrayList) {


        this.datamodelArrayList = contributedatamodelArrayList;
        context = c;
        inflator = LayoutInflater.from(c);

    }


//    public contributeadapter( Context c, ArrayList<Contributedatamodel> datamodelArrayList) {
//
//        this.datamodelArrayList =datamodelArrayList;
//        context=c;
//        inflator=LayoutInflater.from(c);
//
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.contributeview, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.wastetype.setText(datamodelArrayList.get(position).getWastetype());
        holder.quantity.setText(datamodelArrayList.get(position).getQuantity());
        holder.number.setText(datamodelArrayList.get(position).getMobilenumber());
        holder.description.setText(datamodelArrayList.get(position).getDescription());

        holder.contributecomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(datamodelArrayList.get(position).getid());
            }
        });


    }

    @Override
    public int getItemCount() {
        return datamodelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        //declaring

        TextView wastetype, quantity, number, description;
        Button contributecomplete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //finding

            wastetype = itemView.findViewById(R.id.wastetypecontribute);
            quantity = itemView.findViewById(R.id.quantitycontribute);
            number = itemView.findViewById(R.id.numbercontribute);
            description = itemView.findViewById(R.id.descriptioncontribute);
            contributecomplete = itemView.findViewById(R.id.contributecomplete);


        }
    }

    private void updateStatus(String id) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
                    ((Activity) context).finish();

                } else {

                    Toast.makeText(context, "ERROR Occured", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map lg = new HashMap<String, String>();
                lg.put("id", id);
                return lg;

            }
        };

        RequestQueue q = Volley.newRequestQueue(context);
        q.add(request);
    }
}
