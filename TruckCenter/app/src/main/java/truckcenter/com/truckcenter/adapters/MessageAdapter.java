package truckcenter.com.truckcenter.adapters;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import truckcenter.com.truckcenter.R;
import truckcenter.com.truckcenter.model.TruckMessage;

/**
 * Created by martin on 26/03/15.
 */
public class MessageAdapter extends ArrayAdapter<TruckMessage> {

    int resource;
    String response;
    Context context;
    List<TruckMessage> truckMessages;

    public MessageAdapter(Context context, int resource, List<TruckMessage> objects) {
        super(context, resource, objects);
        this.resource=resource;
        this.truckMessages = objects;
    }

    public void addObject(TruckMessage truckMessage) {
        truckMessages.add(truckMessage);
    }

    public void addObjects(List<TruckMessage> truckMessages) {
        this.truckMessages.addAll(truckMessages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout messageView;

        TruckMessage message = getItem(position);

        if(convertView==null)
        {
            messageView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, messageView, true);
        }
        else
        {
            messageView = (LinearLayout) convertView;
        }

        TextView date = (TextView)messageView.findViewById(R.id.message_adapter_date);

        date.setText(android.text.format.DateFormat.format("yyyy-MM-dd", message.getDate()));

        TextView sender = (TextView)messageView.findViewById(R.id.message_adapter_sender);

        sender.setText(message.getSender());

        TextView messagev = (TextView)messageView.findViewById(R.id.message_adapter_message);

        messagev.setText(message.getMessage());

        return messageView;
    }

}