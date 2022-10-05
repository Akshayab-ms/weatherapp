package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText etCity,etCountry;
    TextView tvResult;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCity=findViewById(R.id.et_loc);
        tvResult=findViewById(R.id.tv_scroll);
    }
    public void getWeatherDetails(View view){
        String city=etCity.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=0d6c51cd2b5959387bf840e166bc5776";
         final String appid="0d6c51cd2b5959387bf840e166bc5776";
        RequestQueue queue =Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    JSONObject object1 = response.getJSONObject("coord");

                    String lone=object1.getString("lon");
                    String lane=object1.getString("lat");
                    String temperature = object.getString("temp");
                    tvResult.append("country:"+city+","+"Temperature:"+temperature+","+"longitude:"+ lone+","+"latitude"+lane+"\n\n");


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"erro",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"eehh",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
            }

        }

