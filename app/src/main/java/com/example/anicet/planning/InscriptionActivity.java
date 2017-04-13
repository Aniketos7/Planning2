package com.example.anicet.planning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InscriptionActivity extends AppCompatActivity {
    private EditText nom_utilisateur, mot_de_passe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        Button inscription = (Button) findViewById(R.id.inscription);
         nom_utilisateur =(EditText) findViewById(R.id.nom_utilisateur);
         mot_de_passe =(EditText) findViewById(R.id.mot_de_passe);



        // CLIQUE SUR INSCRIPTION
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificationInscription() == true){
                    SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(InscriptionActivity.this);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString("nom_utilisateur",nom_utilisateur.getText().toString());
                    editor.putString("mot_de_passe",mot_de_passe.getText().toString());
                    editor.putBoolean("inscription_fait",true);
                    editor.putBoolean("connexion_etabli",true);
                    editor.apply();
                    startActivity(new Intent(InscriptionActivity.this,AccueilActivity.class));
                }else{
                    verificationInscription();
                }

            }
        });
    }



    //METHODE DE VERIFICATION DES INFOS D INSCRIPTION
    public boolean verificationInscription(){
        boolean valider = true;
        if(nom_utilisateur.getText().toString().isEmpty()){
            nom_utilisateur.setError("VEUILLEZ RENSEIGNER NOM D'UTILISATEUR");
            valider = false;
        }else if(mot_de_passe.getText().toString().isEmpty()){
            mot_de_passe.setError("VEUILLEZ RENSEIGNER VOTRE MOT DE PASSE");
            valider = false;
        }else{
            mot_de_passe.setError(null);
            nom_utilisateur.setError(null);
        }
        return valider;
    }
}
