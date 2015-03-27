package truckcenter.com.truckcenter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import truckcenter.com.truckcenter.R;
import truckcenter.com.truckcenter.adapters.MessageAdapter;
import truckcenter.com.truckcenter.model.TruckMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    private ListView messageView;

    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_messages, container, false);
        // Inflate the layout for this fragment
        messageView = (ListView) root.findViewById(R.id.message_list);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<TruckMessage> truckMessages = new ArrayList<>();
        truckMessages.add(new TruckMessage("Gérard", "T'es au putes ?", new Date()));
        truckMessages.add(new TruckMessage("Pascal", "OUI", new Date()));
        truckMessages.add(new TruckMessage("Gérard", "Génial !", new Date()));
        messageView.setAdapter(new MessageAdapter(getActivity(), R.layout.message_adapter, truckMessages));
    }


}
