package ad.uda.rocmoi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


import ad.uda.rocmoi.adaptadors.EnquestaAdapter;
import ad.uda.rocmoi.dummy.DummyContent;
import ad.uda.rocmoi.pojos.Enquesta;
import ad.uda.rocmoi.tools.DataLoader;

/**
 * A list fragment representing a list of Enquestes. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link EnquestaDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class EnquestaListFragment extends ListFragment {

    ListView llista;
    EnquestaAdapter adaptador;
    ArrayList<Enquesta> enquestes;


    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EnquestaListFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Definició dels elements necessaris
        enquestes =new ArrayList<Enquesta>();

        //Adapter (inicialment buid)
        adaptador =new EnquestaAdapter(getActivity(), enquestes);
        setListAdapter(adaptador);
        //Gestor asyncron per recuperar dades i presentar la informació
        //Quan es rebi
        DataLoader dl = new DataLoader(getActivity(),  adaptador);
        dl.execute();
        Log.d("AAAH","aH");

        /*Enquesta e1 = new Enquesta(1, "enquesta 1");
        Enquesta e2 = new Enquesta(2, "enquesta 2");
        Enquesta e3 = new Enquesta(3, "enquesta 3");

        enquestes = new ArrayList<Enquesta>();
        enquestes.add(e1);
        enquestes.add(e2);
        enquestes.add(e3);

        setListAdapter(new EnquestaAdapter(getActivity(), R.layout.item_enquesta, enquestes) {

            @Override
            public void onEntrada(Enquesta enquesta, View view) {
                if (enquesta != null) {
                    TextView tvId = (TextView) view.findViewById(R.id.tvId);
                    if (tvId != null)
                        tvId.setText(String.valueOf(enquesta.getId()));

                    TextView tvNom = (TextView) view.findViewById(R.id.tvNom);
                    if (tvNom != null)
                        tvNom.setText(enquesta.getNom());
                }
            }
        });*/

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
