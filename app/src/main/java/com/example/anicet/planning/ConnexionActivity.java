package com.example.anicet.planning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends AppCompatActivity {
    private EditText nom_utilisateur_connexion,mot_de_passe_connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        nom_utilisateur_connexion = (EditText) findViewById(R.id.nom_utilisateur_connexion);
        mot_de_passe_connexion = (EditText) findViewById(R.id.mot_de_passe_connexion);
        Button connexion = (Button) findViewById(R.id.connexion);

        //CLIQUE SUR CONNECTER
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificationConnexion() == true){
                    SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(ConnexionActivity.this);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putBoolean("connexion_etabli",true);
                    editor.apply();
                    startActivity(new Intent(ConnexionActivity.this, AccueilActivity.class));
                }else{
                    verificationConnexion();
                }
            }
        });

    }


    //METHODE DE VERIFICATION DES INFOS DE CONNEXION
    public boolean verificationConnexion(){
        boolean verifier = true;
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        String control_nom_utilisateur_connexion = preference.getString("nom_utilisateur","faux");
        String control_mot_de_passe_connexion = preference.getString("mot_de_passe","faux");

        if(nom_utilisateur_connexion.getText().toString().equals(control_nom_utilisateur_connexion) && mot_de_passe_connexion.getText().toString().equals(control_mot_de_passe_connexion)){

            verifier = true;

        }else{
            Toast affiche;
            affiche = Toast.makeText(this,"NOM D'UTILISATEUR OU MOT DE PASSE INCORRECT",Toast.LENGTH_LONG);
            affiche.setGravity(Gravity.TOP,20,0);
            affiche.show();
            verifier = false;
        }
        return verifier;
    }
}
