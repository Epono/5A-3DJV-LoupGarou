package fr.esgi.davidghetto.loupgarou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.esgi.davidghetto.loupgarou.R;
import fr.esgi.davidghetto.loupgarou.models.Player;

public class PlayersGameInfoAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.item_player_game_info;

    private boolean inGameMasterMode = false;

    public PlayersGameInfoAdapter(Context context) {
        super(context, layoutResource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        final Player player = getItem(position);

        TextView playerName = (TextView) convertView.findViewById(R.id.item_player_game_info_player_name);
        playerName.setText(player.getName());

        ImageView captainImageView = (ImageView) convertView.findViewById(R.id.item_player_game_info_captain_image);
        captainImageView.setVisibility(player.isCaptain() ? View.VISIBLE : View.INVISIBLE);

        ImageView loversImageView = (ImageView) convertView.findViewById(R.id.item_player_game_info_lovers_image);
        loversImageView.setVisibility(inGameMasterMode && player.isLover() ? View.VISIBLE : View.INVISIBLE);

        ImageView roleImageView = (ImageView) convertView.findViewById(R.id.item_player_game_info_role_image);
        roleImageView.setImageDrawable(inGameMasterMode || !player.isAlive() ? convertView.getResources().getDrawable(player.getRole().getDrawableRes()) : convertView.getResources().getDrawable(R.drawable.unknown));
        roleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getContext().getResources().getString(player.getRole().getNameRes()), Toast.LENGTH_SHORT).show();
            }
        });

        ImageView deadImageView = (ImageView) convertView.findViewById(R.id.item_player_game_info_dead_image);
        deadImageView.setVisibility(player.isAlive() ? View.INVISIBLE : View.VISIBLE);

        return convertView;
    }

    public void setGameMasterMode(boolean inGameMasterMode) {
        this.inGameMasterMode = inGameMasterMode;
        notifyDataSetChanged();
    }
}
