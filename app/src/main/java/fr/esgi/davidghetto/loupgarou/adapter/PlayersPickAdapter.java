package fr.esgi.davidghetto.loupgarou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;

public class PlayersPickAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.item_player_pick;

    public PlayersPickAdapter(Context context) {
        super(context, layoutResource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        final Player player = getItem(position);
        TextView playerName = (TextView) convertView.findViewById(R.id.item_player_pick_name_text_view);
        playerName.setText(player.getName());

        return convertView;
    }
}
