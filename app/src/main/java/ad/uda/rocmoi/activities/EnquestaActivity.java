package ad.uda.rocmoi.activities;


import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.dummy.DummyContent;
import ad.uda.rocmoi.fragments.EnquestaDetailFragment;
import ad.uda.rocmoi.fragments.EnquestaInfoFragment;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.tools.DataLoader;


public class EnquestaActivity extends AppCompatActivity {

    public int pos;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquesta);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                pos = 0;
            } else {
                pos = extras.getInt("id");
            }
        } else {
            pos = (int) savedInstanceState.getSerializable("id");
        }


    }

    /**
     *Per a recuperar els parametres de Guia, Hotel i Activitat a valorar
     * i presentarlos en el seu corresponent fragment, el que fem es
     * crear un Bundle per cada fragment i accedir a la taula a memoria DataLoader.dossiers
     * a partir d'aqui carregar els parametres es facil
     * Es la unica manera que he trobat, a mes quan tinguem la BD en local,
     * podrem cambiar el DataLoader.dossiers.get(pos) per la query, en principi
     * serie l'unic canvi a fer (espero vamos xD)
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle argsGuia = getArgsForGuia();
        EnquestaInfoFragment f1 = new EnquestaInfoFragment();
        f1.setArguments(argsGuia);
        adapter.addFragment(f1, "Guia");

        Bundle argsHotel = getArgsForHotel();
        EnquestaInfoFragment f2 = new EnquestaInfoFragment();
        f2.setArguments(argsHotel);
        adapter.addFragment(f2, "Hotel");

        Bundle argsActivitat = getArgsGorActivitat();
        EnquestaInfoFragment f3 = new EnquestaInfoFragment();
        f3.setArguments(argsActivitat);
        adapter.addFragment(f3, "Activitat");

        viewPager.setAdapter(adapter);
    }

    public Bundle getArgsForGuia() {
        Bundle argsGuia = new Bundle();
        Dossier dossier = DataLoader.dossiers.get(pos);

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<String>();
        for(int i = 0; i < dossier.getGuia().getParametres().size(); i++){
            params.add(dossier.getGuia().getParametres().get(i).getDescripcio());
        }

        argsGuia.putStringArrayList("parametres", params);

        return argsGuia;
    }

    public Bundle getArgsForHotel() {
        Bundle argsHotel = new Bundle();
        Dossier dossier = DataLoader.dossiers.get(pos);

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<String>();
        for(int i = 0; i < dossier.getHotel().getParametres().size(); i++){
            params.add(dossier.getHotel().getParametres().get(i).getDescripcio());
        }

        argsHotel.putStringArrayList("parametres", params);

        return argsHotel;
    }

    public Bundle getArgsGorActivitat() {
        Bundle argsActivitat = new Bundle();
        Dossier dossier = DataLoader.dossiers.get(pos);
        argsActivitat.putString("tipus", "g");

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<String>();
        for(int i = 0; i < dossier.getActivitat().getParametres().size(); i++){
            params.add(dossier.getActivitat().getParametres().get(i).getDescripcio());
        }

        argsActivitat.putStringArrayList("parametres", params);

        return argsActivitat;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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
