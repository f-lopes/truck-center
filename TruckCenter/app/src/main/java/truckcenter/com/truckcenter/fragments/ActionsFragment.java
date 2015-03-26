package truckcenter.com.truckcenter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import truckcenter.com.truckcenter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionsFragment extends Fragment {

    private Button buttonPause;
    private Button buttonStop;
    private Button buttonHelp;

    public ActionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actions, container, false);
        // Inflate the layout for this fragment
        buttonPause = (Button) root.findViewById(R.id.button_pause);
        buttonStop = (Button) root.findViewById(R.id.button_stop);
        buttonHelp = (Button) root.findViewById(R.id.button_help);

        buttonPause.setText("Pause");
        buttonStop.setText("Stop");
        buttonHelp.setText("Help");

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Pause !", Toast.LENGTH_SHORT);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Stop !", Toast.LENGTH_SHORT);
            }
        });

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Help !", Toast.LENGTH_SHORT);
            }
        });

        return root;
    }


}
