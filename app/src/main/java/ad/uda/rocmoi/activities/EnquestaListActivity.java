package ad.uda.rocmoi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import ad.uda.rocmoi.adaptadors.EnquestaAdapter;
import ad.uda.rocmoi.fragments.EnquestaDetailFragment;
import ad.uda.rocmoi.fragments.EnquestaListFragment;
import ad.uda.rocmoi.R;
import ad.uda.rocmoi.pojos.Enquesta;
import ad.uda.rocmoi.tools.DataLoader;

public class EnquestaListActivity extends AppCompatActivity
        implements EnquestaListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquesta_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.enquesta_detail) != null) {
            mTwoPane = true;

            ((EnquestaListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.enquesta_list))
                    .setActivateOnItemClick(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(EnquestaDetailFragment.ARG_ITEM_ID, id);
            EnquestaDetailFragment fragment = new EnquestaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.enquesta_detail, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, EnquestaDetailActivity.class);
            detailIntent.putExtra(EnquestaDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_enviar:

                break;
            case R.id.action_sortir:
                System.exit(0);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
