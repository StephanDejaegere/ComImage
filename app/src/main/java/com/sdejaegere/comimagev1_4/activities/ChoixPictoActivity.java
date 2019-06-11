package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.adapters.CategorieAdapter;
import com.sdejaegere.comimagev1_4.adapters.PictoAdapter;

public class ChoixPictoActivity extends AppCompatActivity {

    Spinner spinnerPicto;
    String[] nomPicto;
    int[] imagePicto;

    Spinner spinnerCategorie;
    String[] categorieSpinnerString;

    //l'entier pour récupérer l'image choisie
    private int spinnerSelected;

    //le string pour récupérer le nom de l'image choisie
    String nomPictoIntent;

    //l'entier pour se souvenir de quel bouton l'activity est appelé
    int leBouton;

    //les entiers pour garder en mémoire les choix précédents
    private int choixUn;
    private int choixDeux;
    private int choixTrois;
    private int choixQuatre;
    private int choixCinq;

    //les strings pour garder en mémoire les noms des choix précédents
    private String choixPictoUnFromIntent;
    private String choixPictoDeuxFromIntent;
    private String choixPictoTroisFromIntent;
    private String choixPictoQuatreFromIntent;
    private String choixPictoCinqFromIntent;

    //les strings pour garder l'utilisateur en mémoire
    private String idFromIntent;
    private String emailFromIntent;

    //le string pour garder la question en mémoire
    private String textQuestionFromIntent;
    private String idQuestionFromIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choix_picto);
        Bundle bundle = getIntent().getExtras();

        //on récupère les intents de CreateQuestionActivity
        idFromIntent = bundle.getString("IDUSER");
        emailFromIntent = bundle.getString("EMAIL");
        leBouton = bundle.getInt("BOUTON");
        choixUn = bundle.getInt("CHOIXUN");
        choixDeux = bundle.getInt("CHOIXDEUX");
        choixTrois = bundle.getInt("CHOIXTROIS");
        choixQuatre = bundle.getInt("CHOIXQUATRE");
        choixCinq = bundle.getInt("CHOIXCINQ");
        choixPictoUnFromIntent = bundle.getString("NOMCHOIXUN");
        choixPictoDeuxFromIntent = bundle.getString("NOMCHOIXDEUX");
        choixPictoTroisFromIntent = bundle.getString("NOMCHOIXTROIS");
        choixPictoQuatreFromIntent = bundle.getString("NOMCHOIXQUATRE");
        choixPictoCinqFromIntent = bundle.getString("NOMCHOIXCINQ");
        textQuestionFromIntent = bundle.getString("QUESTION");
        if(getIntent().hasExtra("IDQUESTION")){
            idQuestionFromIntent = bundle.getString("IDQUESTION");
            Log.d("******INTENTIDQUESTION", idQuestionFromIntent);
        }

        //on lie les spinners à leur pendant visuel
        spinnerPicto = findViewById(R.id.spinnerPicto);
        spinnerCategorie = findViewById(R.id.spinnerCategorie);

        //on inflate le catégorieSpinner grâce à son adapter et un tableau de String
        categorieSpinnerString = new String[]{"Adjectifs", "Chiffres", "Couleurs", "Demandes", "Habits", "Jouets", "Lieux", "Douleurs", "Propreté"};
        CategorieAdapter categorieAdapter = new CategorieAdapter(ChoixPictoActivity.this, categorieSpinnerString);
        spinnerCategorie.setAdapter(categorieAdapter);

        //on inflate spinnerPicto en fonction du choix sur categorieSpinner
        spinnerCategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(categorieSpinnerString[position] == "Adjectifs"){
                    spinnerPicto.setAdapter(null);

                    nomPicto = new String[]{"Beaucoup", "Bon", "Chaud", "Faible", "Fort", "Froid", "Grand", "Gros", "Léger", "Lourd", "Mince", "Pas bon", "Petit", "Peu"};
                    imagePicto = new int[]{R.drawable.adjectif_beaucoup
                            , R.drawable.adjectif_bon
                            , R.drawable.adjectif_chaud
                            , R.drawable.adjectif_faible
                            , R.drawable.adjectif_fort
                            , R.drawable.adjectif_froid
                            , R.drawable.adjectif_grand
                            , R.drawable.adjectif_gros
                            , R.drawable.adjectif_leger
                            , R.drawable.adjectif_lourd
                            , R.drawable.adjectif_mince
                            , R.drawable.adjectif_pas_bon
                            , R.drawable.adjectif_petit
                            , R.drawable.adjectif_peu};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);

                }

                if(categorieSpinnerString[position] == "Chiffres"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Chiffre 1", "Chiffre 2", "Chiffre 3", "Chiffre 4", "Chiffre 5", "Chiffre 6", "Chiffre 7", "Chiffre 8", "Chiffre 9",  "Chiffre 10"};
                    imagePicto = new int[]{R.drawable.chiffre_1
                            , R.drawable.chiffre_2
                            , R.drawable.chiffre_3
                            , R.drawable.chiffre_4
                            , R.drawable.chiffre_5
                            , R.drawable.chiffre_6
                            , R.drawable.chiffre_7
                            , R.drawable.chiffre_8
                            , R.drawable.chiffre_9
                            , R.drawable.chiffre_10};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Couleurs"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Blanc", "Bleu", "Gris", "Jaune", "Marron", "Mauve", "Noir", "Orange", "Rose",  "Rouge", "Vert"};
                    imagePicto = new int[]{R.drawable.couleur_blanc
                            , R.drawable.couleur_bleu
                            , R.drawable.couleur_gris
                            , R.drawable.couleur_jaune
                            , R.drawable.couleur_marron
                            , R.drawable.couleur_mauve
                            , R.drawable.couleur_noir
                            , R.drawable.couleur_orange
                            , R.drawable.couleur_rose
                            , R.drawable.couleur_rouge
                            , R.drawable.couleur_vert};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Demandes"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Avoir", "Etre", "Vouloir"};
                    imagePicto = new int[]{R.drawable.demande_avoir
                            , R.drawable.demande_etre
                            , R.drawable.demande_vouloir};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Habits"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Bonnet", "Chaussettes", "Chemise", "Débardeur", "Gants", "Imperméable", "Jupe", "Manteau", "Pantalon", "Polo", "Pull", "Pullzip", "Robe", "Short", "Slip", "T-Shirt", "Veste"};
                    imagePicto = new int[]{R.drawable.habit_bonnet
                            , R.drawable.habit_chaussette
                            , R.drawable.habit_chemise
                            , R.drawable.habit_debardeur
                            , R.drawable.habit_gant
                            , R.drawable.habit_impermeable
                            , R.drawable.habit_jupe
                            , R.drawable.habit_manteau
                            , R.drawable.habit_pantalon
                            , R.drawable.habit_polo
                            , R.drawable.habit_pull
                            , R.drawable.habit_pullzip
                            , R.drawable.habit_robe
                            , R.drawable.habit_short
                            , R.drawable.habit_slip
                            , R.drawable.habit_teeshirt
                            , R.drawable.habit_veste};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Jouets"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Ballon", "Cartes", "Château", "Console", "Dés", "Dominos", "Fusée", "Poupée", "Puzzle"};
                    imagePicto = new int[]{R.drawable.jouet_ballon
                            , R.drawable.jouet_cartes
                            , R.drawable.jouet_chateau
                            , R.drawable.jouet_console
                            , R.drawable.jouet_de
                            , R.drawable.jouet_dominos
                            , R.drawable.jouet_fusee
                            , R.drawable.jouet_poupee
                            , R.drawable.jouet_puzzle};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Lieux"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Balançoire", "École", "Hôpital", "IME", "Jardin", "Maison", "Mer", "Montagne", "Parc", "Piscine", "Plage"};
                    imagePicto = new int[]{R.drawable.lieu_balancoire
                            , R.drawable.lieu_ecole
                            , R.drawable.lieu_hopital
                            , R.drawable.lieu_ime
                            , R.drawable.lieu_jardin
                            , R.drawable.lieu_maison
                            , R.drawable.lieu_mer
                            , R.drawable.lieu_montagne
                            , R.drawable.lieu_parc
                            , R.drawable.lieu_piscine
                            , R.drawable.lieu_plage};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Douleurs"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Bouche", "Bras", "Coude", "Dents", "Dos", "Épaule", "Fesses", "Genoux", "Jambes", "Mains", "Mollet", "Oeil", "Oreilles", "Pied", "Tête", "Ventre"};
                    imagePicto = new int[]{R.drawable.mal_bouche
                            , R.drawable.mal_bras
                            , R.drawable.mal_coudes
                            , R.drawable.mal_dents
                            , R.drawable.mal_dos
                            , R.drawable.mal_epaules
                            , R.drawable.mal_fesses
                            , R.drawable.mal_genoux
                            , R.drawable.mal_jambes
                            , R.drawable.mal_mains
                            , R.drawable.mal_mollet
                            , R.drawable.mal_oeil
                            , R.drawable.mal_oreilles
                            , R.drawable.mal_pieds
                            , R.drawable.mal_ventre};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }

                if(categorieSpinnerString[position] == "Propreté"){
                    spinnerPicto.setAdapter(null);
                    nomPicto = new String[]{"Bain", "Laver les mains", "Toilettes"};
                    imagePicto = new int[]{R.drawable.proprete_bain
                            , R.drawable.proprete_laver_mains
                            , R.drawable.proprete_toilettes};

                    PictoAdapter pictoAdapter = new PictoAdapter(ChoixPictoActivity.this, nomPicto, imagePicto);
                    spinnerPicto.setAdapter(pictoAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //on intègre le choix de spinnerPicto dans deux variables, à chaque changement, afin de pouvoir les récupérer
        spinnerPicto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelected = imagePicto[position];
                nomPictoIntent = nomPicto[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * cette méthode sera lié au bouton valider dans la vue XML correspondante à l'activité
     * @param v
     */
    public void onButtonClick(View v){
        Intent myIntent;

        if(idQuestionFromIntent != null){
            myIntent = new Intent(getBaseContext(), ModifiySingleQuestionActivity.class);
            Log.d("*****MODIFY", idQuestionFromIntent);
            myIntent.putExtra("IDQUESTION", idQuestionFromIntent);
        }
        else{
            myIntent = new Intent(getBaseContext(), CreateQuestionActivity.class);
        }


        myIntent.putExtra("CHOIX", spinnerSelected);
        myIntent.putExtra("BOUTON", leBouton);
        myIntent.putExtra("NOMPICTO", nomPictoIntent);
        myIntent.putExtra("CHOIXUN", choixUn);
        myIntent.putExtra("CHOIXDEUX", choixDeux);
        myIntent.putExtra("CHOIXTROIS", choixTrois);
        myIntent.putExtra("CHOIXQUATRE", choixQuatre);
        myIntent.putExtra("CHOIXCINQ", choixCinq);
        myIntent.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
        myIntent.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
        myIntent.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
        myIntent.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
        myIntent.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
        myIntent.putExtra("EMAIL", emailFromIntent);
        myIntent.putExtra("IDUSER", idFromIntent);
        myIntent.putExtra("QUESTION", textQuestionFromIntent);


        startActivity(myIntent);
    }

    /**
     * cette méthode sera lié au bouton annuler dans la vue XML correspondante à l'activité
     * La fonction onBackPressed() retourne à l'activité precédente, en gardant toutes les données en mémoire, inutile donc d'utiliser des intents
     * @param v
     */
    public void quit(View v){
        onBackPressed();
    }
}
