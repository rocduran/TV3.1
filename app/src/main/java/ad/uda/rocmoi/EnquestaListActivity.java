package ad.uda.rocmoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;


/**
 * An activity representing a list of Enquestes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link EnquestaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link EnquestaListFragment} and the item details
 * (if present) is a {@link EnquestaDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link EnquestaListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class EnquestaListActivity extends AppCompatActivity
        implements EnquestaListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquesta_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.enquesta_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((EnquestaListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.enquesta_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link EnquestaListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(EnquestaDetailFragment.ARG_ITEM_ID, id);
            EnquestaDetailFragment fragment = new EnquestaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.enquesta_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, EnquestaDetailActivity.class);
            detailIntent.putExtra(EnquestaDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //Envia a Activitat per realitzar enquesta
        if (keyCode == KeyEvent.KEYCODE_0 || keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            startActivity(new Intent("ad.uda.rocmoi.Enquesta"));
        }

        return false;

    }
}
