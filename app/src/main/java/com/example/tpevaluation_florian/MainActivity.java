package com.example.tpevaluation_florian;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    String[] items = {"bleu", "rouge", "vert", "jaune", "orange", "rose","bleu", "rouge", "vert", "jaune", "orange", "rose","bleu", "rouge", "vert", "jaune", "orange", "rose","bleu", "rouge", "vert", "jaune", "orange", "rose","bleu", "rouge", "vert", "jaune", "orange", "rose","bleu", "rouge", "vert", "jaune", "orange", "rose"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on attribue la liste, chaque item est placer dans label, chaque ligne suit le schema de layout row
        setListAdapter(new CustomAdapter(this,
                R.layout.row,
                R.id.label,
                items));
    }

    class CustomAdapter extends ArrayAdapter {

        public CustomAdapter(@NonNull Context context,
                             int resource,
                             int textViewResourceId,
                             @NonNull Object[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            //on modifie convertview pour garder les etoiles
            View cv = convertView;
            if (convertView == null){
                LayoutInflater layoutinflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cv = layoutinflater.inflate(R.layout.row,parent,false);
            }

            //on fait un super de notre view avec le convertview modifier et on le retourne
            View view = super.getView(position, cv, parent);
            return view;
        }
    }

    //methode onClick associer a chaque etoile
    public void rate(View view) {

        //recupere le nom de l'id de l'etoile
        String id = getResources().getResourceEntryName(view.getId());

        //recupere le chiffre dans l'id
        int selectedStar = Integer.parseInt(id.substring(id.length()-1));

        //recupere le parent = le container avec toutes les images et zone de texte de la ligne actuel
        View parent = (View) view.getParent();

        //boucle 5 fois
        for (int i = 1; i <= 5; i++) {
            //recupere l'etoile numerotÃ© i
            int star = getResources().getIdentifier("star" + i, "id", getPackageName());
            ImageView img = parent.findViewById(star);

            //verifie si i est plus petit ou grand que l'etoile selectione, et change l'image en fonction du resultat
            if (i <= selectedStar) {
                img.setImageResource(android.R.drawable.btn_star_big_on);
            }else {
                img.setImageResource(android.R.drawable.btn_star_big_off);
            }
        }

        //recupere le tag de l'etoile selectionner, envoie le text dans le Textview
        TextView tagstar =  parent.findViewById(getResources().getIdentifier("tagstar", "id", getPackageName()));
        tagstar.setText(view.getTag().toString());
    }


}