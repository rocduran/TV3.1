package ad.uda.rocmoi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ad.uda.rocmoi.fragments.EnquestaDetailFragment;
import ad.uda.rocmoi.fragments.EnquestaListFragment;
import ad.uda.rocmoi.R;

public class EnquestaListActivity extends AppCompatActivity implements EnquestaListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enquesta_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.enquesta_detail_container) != null) {
            mTwoPane = true;

            ((EnquestaListFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.enquesta_list))
                                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            Log.d("MMM", "id ENQUESTALISTACTV: " + id);
            arguments.putString(EnquestaDetailFragment.ARG_ITEM_ID, id);
            EnquestaDetailFragment fragment = new EnquestaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.enquesta_detail_container, fragment).commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, EnquestaDetailActivity.class);
            detailIntent.putExtra(EnquestaDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
