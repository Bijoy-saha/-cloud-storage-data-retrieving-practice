package com.example.mp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView rcv;
    private FirebaseFirestore db;
    private myAdapter adapter;
    private List<model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rcv=findViewById(R.id.recycleView);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapter=new myAdapter(this,list);
        rcv.setAdapter(adapter);
        showData();
    }
    private  void  showData()
    {
       db.collection("Documents").get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       list.clear();
                       for(DocumentSnapshot snapshot :task.getResult())
                       {
                           model mdl=new model(snapshot.getString("id"),snapshot.getString("name"),snapshot.getString("Email"),snapshot.getString("description"));
                           list.add(mdl);
                       }
                       adapter.notifyDataSetChanged();

                   }
               }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(MainActivity2.this, "opps  sorry", Toast.LENGTH_SHORT).show();

           }
       });



    }
}