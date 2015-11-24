package ad.uda.rocmoi.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.fragments.EnquestaInfoFragment;
import ad.uda.rocmoi.localDB.DBinterface;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Valoracio;


public class EnquestaActivity extends AppCompatActivity {

    private Dossier dossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int pos = 0;
        if (savedInstanceState == null) {
            pos = getIntent().getExtras().getInt("id");
        }

        DBinterface database = new DBinterface(this);
        dossier = database.getDossierById(pos, this);

        setContentView(R.layout.activity_enquesta);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enquesta, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_finish:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EnquestaActivity.this);

                // set title
                alertDialogBuilder.setTitle("Sortir!");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Acabar enquesta ?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

                break;
            case R.id.action_sortir:
                System.exit(0);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void setupViewPager(final ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle argsGuia = getArgsForGuia();
        EnquestaInfoFragment f1 = new EnquestaInfoFragment();
        f1.setArguments(argsGuia);
        adapter.addFragment(f1, dossier.getGuia().getDescripcio());

        Bundle argsHotel = getArgsForHotel();
        EnquestaInfoFragment f2 = new EnquestaInfoFragment();
        f2.setArguments(argsHotel);
        adapter.addFragment(f2, dossier.getHotel().getDescripcio());

        Bundle argsActivitat = getArgsForActivitat();
        EnquestaInfoFragment f3 = new EnquestaInfoFragment();
        f3.setArguments(argsActivitat);
        adapter.addFragment(f3, dossier.getActivitat().getDescripcio());

        FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rootView) {
                EnquestaInfoFragment e1 = (EnquestaInfoFragment) adapter.getItem(0);
                EnquestaInfoFragment e2 = (EnquestaInfoFragment) adapter.getItem(1);
                EnquestaInfoFragment e3 = (EnquestaInfoFragment) adapter.getItem(2);

                ArrayList<Valoracio> guia = e1.getValoracions();
                ArrayList<Valoracio> hotel = e2.getValoracions();
                ArrayList<Valoracio> activitat = e3.getValoracions();

                DBinterface database = new DBinterface(getBaseContext());

                for (int i = 0; i < guia.size(); i++) database.insert(guia.get(i));
                for (int i = 0; i < hotel.size(); i++) database.insert(hotel.get(i));
                for (int i = 0; i < activitat.size(); i++) database.insert(activitat.get(i));

                for (int i = 0; i < database.getValoracioList().size(); i++) {
                    Log.d("MMM", "TEST VALORACIONS FINAL !: " + database.getValoracioList().get(i).toString());
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EnquestaActivity.this);

                // set title
                alertDialogBuilder.setTitle("Enquesta realitzada!");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Passar següent usuari ?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        viewPager.setAdapter(adapter);
    }

    public Bundle getArgsForGuia() {
        Bundle argsGuia = new Bundle();

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Integer> paramsId = new ArrayList<>();
        for(int i = 0; i < dossier.getGuia().getParametres().size(); i++){
            params.add(dossier.getGuia().getParametres().get(i).getDescripcio());
        }
        for (int i = 0; i < dossier.getGuia().getParametres().size(); i++){
            paramsId.add(dossier.getGuia().getParametres().get(i).getId());
        }

        argsGuia.putInt("idDossier", dossier.getId());
        argsGuia.putInt("idServei", dossier.getGuia().getId());

        argsGuia.putStringArrayList("parametres", params);
        argsGuia.putIntegerArrayList("parametresId", paramsId);

        return argsGuia;
    }

    public Bundle getArgsForHotel() {
        Bundle argsHotel = new Bundle();

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Integer> paramsId = new ArrayList<>();
        for(int i = 0; i < dossier.getHotel().getParametres().size(); i++){
            params.add(dossier.getHotel().getParametres().get(i).getDescripcio());
        }
        for(int i = 0; i < dossier.getHotel().getParametres().size(); i++){
            paramsId.add(dossier.getHotel().getParametres().get(i).getId());
        }

        argsHotel.putInt("idDossier", dossier.getId());
        argsHotel.putInt("idServei", dossier.getHotel().getId());

        argsHotel.putStringArrayList("parametres", params);
        argsHotel.putIntegerArrayList("parametresId", paramsId);

        return argsHotel;
    }

    public Bundle getArgsForActivitat() {
        Bundle argsActivitat = new Bundle();

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Integer> paramsId = new ArrayList<>();
        for(int i = 0; i < dossier.getActivitat().getParametres().size(); i++){
            params.add(dossier.getActivitat().getParametres().get(i).getDescripcio());
        }
        for(int i = 0; i < dossier.getActivitat().getParametres().size(); i++){
            paramsId.add(dossier.getActivitat().getParametres().get(i).getId());
        }

        argsActivitat.putInt("idDossier", dossier.getId());
        argsActivitat.putInt("idServei", dossier.getActivitat().getId());

        argsActivitat.putStringArrayList("parametres", params);
        argsActivitat.putIntegerArrayList("parametresId", paramsId);

        return argsActivitat;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
