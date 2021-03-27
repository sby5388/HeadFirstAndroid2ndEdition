package sw.iot.droid.coordinatorlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Administrator  on 2019/4/19.
 */
public class FragmentOut extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new View(getContext());
        view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorAccent));
        return view;
    }

    public static Fragment newInstance(){
        return  new FragmentOut();
    }
}
