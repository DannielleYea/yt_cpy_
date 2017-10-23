package com.example.zvjerka.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ScrollView s;
    LinearLayout l;
    RecyclerView videi, komentari;
    RelativeLayout video;
    Button rew, start, forw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onStart(){
        super.onStart();

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rew.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                forw.setVisibility(View.VISIBLE);

                new visi().start();
            }
        });

        videi.setAdapter(new RecyclerView.Adapter() {

            String[] tekst = {"Priv","Drugi", "Treci", "Cetvrti", "Peti", "Sesti", "Sedemi", "Osmi", "Deveti", "Deseti"};
            @Override
            public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videi, parent, false);
                return new Holder(v);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder h = (Holder) holder;

                h.txt.setText(tekst[position]);
            }

            @Override
            public int getItemCount() {
                return 10;
            }

            class Holder extends RecyclerView.ViewHolder{
                TextView txt;

                Holder(View v){
                    super(v);
                    txt = (TextView) v.findViewById(R.id.txt);
                }
            }
        });

        komentari.setAdapter(new RecyclerView.Adapter() {
            String[] tekst = {"Priv_kom","Drugi_kom", "Treci_kom", "Cetvrti_kom", "Peti_kom", "Sesti_kom", "Sedemi_kom", "Osmi_kom", "Deveti_kom", "Deseti_kom"};

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komentari, parent, false);
                return new Holder(v);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder h = (Holder) holder;

                h.txt.setText(tekst[position]);
            }

            @Override
            public int getItemCount() {
                return 10;
            }

            class Holder extends RecyclerView.ViewHolder{
                TextView txt;

                Holder(View v){
                    super(v);
                    txt = (TextView) v.findViewById(R.id.txt_kom);
                }
            }
        });

    }


    private void init(){
        s = (ScrollView) findViewById(R.id.scroll);
        l =  s.findViewById(R.id.sve);
        videi = l.findViewById(R.id.videi);
        komentari = l.findViewById(R.id.komentari);
        video = (RelativeLayout) findViewById(R.id.video);
        rew = video.findViewById(R.id.rew);
        start = video.findViewById(R.id.start);
        forw = video.findViewById(R.id.forw);

        videi.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        komentari.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        rew.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        forw.setVisibility(View.INVISIBLE);

    }

    private class visi extends Thread{

        public synchronized void run(){
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rew.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.INVISIBLE);
                    forw.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
