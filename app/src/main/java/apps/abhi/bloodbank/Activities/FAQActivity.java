package apps.abhi.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import apps.abhi.bloodbank.Models.FAQModel;
import apps.abhi.bloodbank.R;

public class FAQActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        recyclerView = findViewById(R.id.recyclerViewfaq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Query query = FirebaseDatabase.getInstance()
                .getReference("Data").child("FAQ");
        FirebaseRecyclerOptions<FAQModel> options =
                new FirebaseRecyclerOptions.Builder<FAQModel>()
                        .setQuery(query, new SnapshotParser<FAQModel>() {
                            @NonNull
                            @Override
                            public FAQModel parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new FAQModel(snapshot.getKey().toString(),
                                        snapshot.getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<FAQModel,ViewHolder>(options) {
            @Override
            public FAQActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.faqrecycler_items, parent, false);
                return new ViewHolder(view);
            }

            @NonNull
            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, FAQModel model) {
                holder.setTxtTitle(model.getTitle());
                holder.setTxtDesc(model.getDesc());
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
        private TextView txtTitle,txtDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root1);
            txtTitle =(TextView) itemView.findViewById(R.id.titletxt);
            txtDesc =(TextView) itemView.findViewById(R.id.desctxt);

        }
        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }
        public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }


    }
}

