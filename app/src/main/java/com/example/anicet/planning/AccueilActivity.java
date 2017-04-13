package com.example.anicet.planning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean utilisateur_inscrit = preference.getBoolean("inscription_fait",false);
        Boolean utilisateur_connecter = preference.getBoolean("connexion_etabli",false);

        if(utilisateur_inscrit == false){
            startActivity(new Intent(AccueilActivity.this,InscriptionActivity.class));
        }else if(utilisateur_connecter == false){
            startActivity(new Intent(AccueilActivity.this,ConnexionActivity.class));
        }




        Button deconnexion = (Button) findViewById(R.id.deconnexion);

        //CLIQUE SUR DECONNEXION
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(AccueilActivity.this);
                SharedPreferences.Editor editor = preference.edit();
                editor.putBoolean("connexion_etabli",false);
                editor.apply();
                startActivity(new Intent(AccueilActivity.this,ConnexionActivity.class));
            }
        });
    }
}
