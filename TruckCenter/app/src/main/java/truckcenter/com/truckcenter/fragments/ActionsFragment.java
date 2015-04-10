package truckcenter.com.truckcenter.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import truckcenter.com.truckcenter.R;
import truckcenter.com.truckcenter.service.HelpTask;
import truckcenter.com.truckcenter.service.TrackerService;

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
        buttonStop.setText("Start");
        buttonHelp.setText("Help");

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrackerService.class);
                getActivity().stopService(intent);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrackerService.class);
                getActivity().startService(intent);
            }
        });

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HelpTask(getActivity()).execute("2c9191264ca03c36014ca03d51cc0001");
            }
        });

        return root;
    }


}
