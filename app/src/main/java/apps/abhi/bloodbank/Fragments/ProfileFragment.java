package apps.abhi.bloodbank.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.List;

import apps.abhi.bloodbank.R;

public class ProfileFragment extends Fragment {
    FirebaseUser user;
    FirebaseAuth mAuth;
    TextView n,e,p;
    String name,email,phone;
    List<? extends UserInfo> dat = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);
        mAuth = FirebaseAuth.getInstance();
        n = root.findViewById(R.id.textName);
        e = root.findViewById(R.id.textEmail);
        p = root.findViewById(R.id.textPhone);
        user = mAuth.getCurrentUser();
        try {
            name = user.getDisplayName();
            email = user.getEmail();
            phone = user.getPhoneNumber();
            dat = user.getProviderData();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            n.setText(name);
            e.setText(email);
            if (phone != null) {
                p.setText(phone);
            }
            else{
                p.setText("Phone Number Not Available");
            }
        }
        return root;
    }
}
