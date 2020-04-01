package apps.abhi.bloodbank.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import apps.abhi.bloodbank.Activities.DonateActivity;
import apps.abhi.bloodbank.Activities.DonorsActivity;
import apps.abhi.bloodbank.Activities.FAQActivity;
import apps.abhi.bloodbank.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    CardView don,req,faq,lorem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        don = root.findViewById(R.id.carddonor);
        req = root.findViewById(R.id.cardrequest);
        faq = root.findViewById(R.id.cardfaq);
        lorem = root.findViewById(R.id.cardlorem);
        don.setOnClickListener(this);
        req.setOnClickListener(this);
        faq.setOnClickListener(this);
        lorem.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.carddonor:
                startActivity(new Intent(getActivity(), DonateActivity.class));
                break;
            case R.id.cardfaq:
                startActivity(new Intent(getActivity(), FAQActivity.class));
                break;
            case R.id.cardrequest:
                startActivity(new Intent(getActivity(), DonorsActivity.class));
                break;
            case R.id.cardlorem:
                Toast.makeText(getActivity(),"Lorem Ipsum",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
