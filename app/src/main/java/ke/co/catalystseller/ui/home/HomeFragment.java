package ke.co.catalystseller.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ke.co.catalystseller.MainActivity3;
import ke.co.catalystseller.R;
import ke.co.catalystseller.RecyclerViewMain;
import ke.co.catalystseller.SignupToggle;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    CardView jobs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         jobs= root.findViewById(R.id.jobcard);
        jobs();
        return root;

    }
    public void jobs(){
        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), RecyclerViewMain.class);
                startActivity(i);
//                Intent i = new Intent(getActivity(), ImagesActivity.class);
//                startActivity(i);
            }
        });
    }

}