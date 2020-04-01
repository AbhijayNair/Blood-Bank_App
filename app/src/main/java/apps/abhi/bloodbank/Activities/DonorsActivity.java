package apps.abhi.bloodbank.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import apps.abhi.bloodbank.Models.DonorDetails;
import apps.abhi.bloodbank.R;

public class DonorsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    HashMap<String,String> map = new HashMap<>();
    List<HashMap<String,String>> dons = new ArrayList<>();
    private FirebaseUser user;
    String n,l,b,p,s;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donors_activity);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Query query = FirebaseDatabase.getInstance()
                .getReference("Data")
                .child("Donors").orderByChild("Status").equalTo("A");
        FirebaseRecyclerOptions<DonorDetails> options =
                new FirebaseRecyclerOptions.Builder<DonorDetails>()
                        .setQuery(query, new SnapshotParser<DonorDetails>() {
                            @NonNull
                            @Override
                            public DonorDetails parseSnapshot(@NonNull DataSnapshot snapshot) {
                                   return new DonorDetails(snapshot.child("Name").getValue().toString(),
                                           snapshot.child("Location").getValue().toString(),
                                           snapshot.child("Phone").getValue().toString(),
                                           snapshot.child("Blood_Group").getValue().toString(),
                                           snapshot.child("Status").getValue().toString());

                                }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<DonorDetails, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recyclerview_items, parent, false);
                return new ViewHolder(view);
            }

            @NonNull
            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, DonorDetails model) {
                holder.setTxtBlood(model.getBlood());
                holder.setTxtLoc(model.getLocation());
                holder.setTxtName(model.getName());
            }
        };
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout root;
        private TextView txtName,txtBlood,txtLoc;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtBlood = itemView.findViewById(R.id.donorBlood);
            txtLoc = itemView.findViewById(R.id.donorLoc);
            txtName = itemView.findViewById(R.id.donorName);
        }

        public void setTxtName(String string) {
            txtName.setText(string);
        }
        public void setTxtBlood(String string) {
            txtBlood.setText(string);
        }
        public void setTxtLoc(String string){
            txtLoc.setText(string);
        }

    }

}