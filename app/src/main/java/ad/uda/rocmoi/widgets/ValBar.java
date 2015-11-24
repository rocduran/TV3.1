package ad.uda.rocmoi.widgets;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import ad.uda.rocmoi.pojos.Valoracio;

/**
 * Created by sesiom on 23/11/15.
 */
public class ValBar {

    private Context context;
    private Valoracio valoracio;
    private RatingBar ratingBar;

    public ValBar(Context context, final Valoracio valoracio){
        this.context = context;
        ViewGroup.LayoutParams ratingBarLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        this.valoracio = valoracio;
        this.ratingBar = new RatingBar(context);
        this.ratingBar.setLayoutParams(ratingBarLayoutParams);
        this.ratingBar.setStepSize(1);
        this.ratingBar.setMax(5);
        this.ratingBar.setNumStars(5);
        this.ratingBar.setMinimumHeight(100);
        this.ratingBar.setScaleX((float) 1.5);
        this.ratingBar.setScaleY((float) 1.5);
        this.ratingBar.setPadding(0, 10, 0, 0);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                valoracio.setValor((int) ratingBar.getRating());
            }
        });
    }

    public RatingBar getRatingBar(){
        return this.ratingBar;
    }

}
