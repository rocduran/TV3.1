package ad.uda.rocmoi.activities;


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

import java.util.ArrayList;
import java.util.List;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.fragments.EnquestaInfoFragment;
import ad.uda.rocmoi.localDB.DBinterface;
import ad.uda.rocmoi.localDB.DossierRepo;
import ad.uda.rocmoi.pojos.Dossier;



public class EnquestaActivity extends AppCompatActivity {

    private int pos;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dossier dossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                pos = -1;
            } else {
                pos = extras.getInt("id");
            }
        } else {
            pos = (int) savedInstanceState.getSerializable("id");
        }

        DBinterface database = new DBinterface(this);
        dossier = database.getDossierById(pos, this);

        setContentView(R.layout.activity_enquesta);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        pos = getIntent().getExtras().getInt("id");

        FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rootView) {
                Toast test = Toast.makeText(getApplicationContext(), "HOLA", Toast.LENGTH_SHORT);
                test.show();
            }
        });
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
                Toast test = Toast.makeText(getApplicationContext(), "Adeu", Toast.LENGTH_SHORT);
                test.show();
                finish();
                break;
            case R.id.action_sortir:
                System.exit(0);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

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

        viewPager.setAdapter(adapter);
    }

    public Bundle getArgsForGuia() {
        Bundle argsGuia = new Bundle();
        //Dossier dossier = DataLoader.dossiers.get(pos);

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
        //Dossier dossier = DataLoader.dossiers.get(pos);

        //Recuperem els parametres a valorar:
        ArrayList<String> params = new ArrayList<String>();
        for(int i = 0; i < dossier.getHotel().getParametres().size(); i++){
            params.add(dossier.getHotel().getParametres().get(i).getDescripcio());
        }

        argsHotel.putStringArrayList("parametres", params);

        return argsHotel;
    }

    public Bundle getArgsForActivitat() {
        Bundle argsActivitat = new Bundle();
        //Dossier dossier = DataLoader.dossiers.get(pos);
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
