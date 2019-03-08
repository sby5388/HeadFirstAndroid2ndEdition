package sw.iot.droid.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author admin  on 2019/3/7.
 */
public class TempFragment extends Fragment {
    public static final String INDEX = "INDEX";

    public static Fragment newInstance(int index) {
        Fragment fragment = new TempFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public TempFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(INDEX, -1);
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        TextView textView = view.findViewById(R.id.textView_temp);
        textView.setText(String.valueOf(index));
        return view;
    }


}
