package activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import br.com.aplicacoessql.R;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_minhas_anotacoes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enviarEmail();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_servicos,
                R.id.nav_info, R.id.nav_share, R.id.nav_send , R.id.nav_contatos)
                .setDrawerLayout(drawer)
                .build();

        //configura area de carregamento das fragments
        //CARREGA OS BOTOES DE NAVEGAÇÕES, E A AREA DE NAVEGAÇÃO
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //configura menu superior de navegação
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //configura a navegação entre os valores de itens no navigation
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void enviarEmail(){
        //teste de enviar ligação
        //  Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:31988833740"));
        //  String imagemVisualizada = "https://bhmodels.com.br/uploads/images/timeline/s-15791859447TPSL6Hwwf.jpeg";
        //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(imagemVisualizada));
        String email = "admredinfun@gmail.com";
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email,"junior.raveline@gmail.com","italosanches@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT,"Contato pelo app.");
        i.putExtra(Intent.EXTRA_TEXT,"Fale conosco");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"Compartilhar"));
        startActivity(i);

    }
}
