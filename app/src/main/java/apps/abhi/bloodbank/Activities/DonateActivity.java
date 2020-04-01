package apps.abhi.bloodbank.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import apps.abhi.bloodbank.R;

public class DonateActivity extends AppCompatActivity {
    TextInputEditText n,l,p;
    NiceSpinner b;
    FirebaseUser user;
    DatabaseReference mDataBase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.donate_actvity);
        b = (NiceSpinner) findViewById(R.id.bg_inp);
        List<String> dataset = new LinkedList<>(Arrays.asList("Blood Group","A+ve","B+ve","O+ve",
                                                "A-ve","B-ve","O-ve","AB+ve","AB-ve"));
        b.attachDataSource(dataset);
        user= FirebaseAuth.getInstance().getCurrentUser();
        n = findViewById(R.id.name_inp);
        if(user.getDisplayName()!=null){
            n.setText(user.getDisplayName());
        }
        l = findViewById(R.id.loc_inp);
        p = findViewById(R.id.phone_inp);
        try {
            mDataBase = FirebaseDatabase.getInstance().getReference("Data").child("Donors").push();
            findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDataBase.child("Name").setValue(n.getText().toString());
                    mDataBase.child("Blood_Group").setValue(b.getSelectedItem().toString());
                    mDataBase.child("Location").setValue(l.getText().toString());
                    mDataBase.child("Phone").setValue(p.getText().toString());
                    mDataBase.child("Status").setValue("P");
                    Toast.makeText(DonateActivity.this,"Data Recorded Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DonateActivity.this,MainActivity.class));
                }
            });
        }
        catch (Exception e){
            Toast.makeText(DonateActivity.this,"Error processing information",Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
    }
}
