package sw.iot.droid.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Administrator  on 2019/4/19.
 */
public class FragmentIn extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in, container, false);

        view.setBackgroundColor(requireContext().getResources().getColor(R.color.colorPrimary));
        return view;
    }

    public static Fragment newInstance() {
        return new FragmentIn();
    }
}
