package net.mnementh.trajectory;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.navigation.Navigation;


public class GameView extends androidx.fragment.app.Fragment {
    private DisplayThread displayThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        // Get the placeholder where the correct layout will be inflated
        RelativeLayout gridSection = v.findViewById(R.id.canvasSection);
        inflater.inflate(R.layout.ttt_surface_view, gridSection);
        SurfaceView canvas = v.findViewById(R.id.surfaceView);
        canvas.setFocusable(true);

        //Starts the display thread
        if (displayThread == null || !displayThread.isRunning()) {
            displayThread = new DisplayThread(canvas.getHolder());
            displayThread.start();
        } else {
            displayThread.start();
        }

        v.findViewById(R.id.settingsBtn).setOnClickListener(View -> {
            Navigation.findNavController(v).navigate(R.id.action_game_to_settings);
            Activity act = getActivity();
            if (act instanceof MainActivity) ((MainActivity) act).vibrate();
        });

        v.findViewById(R.id.newGameBtn).setOnClickListener(View -> {
            Activity act = getActivity();
            if (act instanceof MainActivity) ((MainActivity) act).vibrate();
//            newGame(board);
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        //Stop the display thread
        displayThread.setIsRunning(false);
        super.onDestroyView();
    }

}
