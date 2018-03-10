package com.example.usuario.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    LinearLayout ll;
    String[] StringBTN = new String[]{
	"Abrir Camara","Abrir Telefono", "Abrir Mapa", "AbrirVideo","Tomar Foto", "Asignar Alarma","Cambiar Estilo"
};

    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(R.layout.activity_main);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createButtons();

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }




    private void createButtons(){
        setContentView(R.layout.activity_main);
        ll = (LinearLayout)findViewById(R.id.linearlayout1);
        LinearLayout.LayoutParams lp=
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i = 0;i < StringBTN.length;i++){
            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setText(StringBTN[i]);
            button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            button.setTypeface(Typeface.SERIF,Typeface.BOLD);
            button.setBackgroundResource(R.drawable.botoneditable1);
            button.setId(i);
            button.setOnClickListener(this);
            registerForContextMenu(button);////Aqui va a escuchar el menu contextual////
            ll.addView(button);
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Toast.makeText(getApplicationContext(),
                "Has echo click en el boton " + id + " " + StringBTN[id],Toast.LENGTH_SHORT).show();

        if (id ==1){
            Intent i = new Intent(Intent.ACTION_DIAL);
            startActivity(i);
        }
        if(id == 6){
            for( int i = 0; i < StringBTN.length; i++){
                Button button = (Button) findViewById(i);
                button.setBackgroundResource(R.drawable.botoneditable2);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Button button = (Button) findViewById(v.getId());
        menu.setHeaderTitle(button.getText().toString());
        menu.setHeaderIcon(getResources().getDrawable(android.R.drawable.ic_dialog_email));
        menu.add(0,v.getId(),0,"Upload");
        menu.add(0,v.getId(),0,"Search");
        menu.add(0,v.getId(),0,"Share");
        menu.add(0,v.getId(),0,"Bookmark");

    }

    ///Arriba se crearon los elementos y ahora abajo veremos que van a hacer cuando seleccionemos uno de los elementos////


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),"Item Seleccionado:", Toast.LENGTH_SHORT).show();
        if(item.getTitle() == "Share"){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.unam.mx"));
            startActivity(i);
        }

        return super.onContextItemSelected(item);
        ////hasta aqui no hace nada por que no hay un onClick Listener///
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
