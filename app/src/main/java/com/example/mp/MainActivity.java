package com.example.mp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
     EditText ed1,ed2,ed3;
    private Button bt1,bt2;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.nameId);
        ed2=findViewById(R.id.mailId);
        ed3=findViewById(R.id.desId);
        bt1=findViewById(R.id.saveId);
        bt2=findViewById(R.id.showId);
        db=FirebaseFirestore.getInstance();
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam=ed1.getText().toString();
                String mail=ed2.getText().toString();
                String des=ed3.getText().toString();
                String id= UUID.randomUUID().toString();
                saVetoStore(id,nam,mail,des);

            }
        });
    }
    private void saVetoStore(String id,String nam,String mail,String des)
    {
        if(!nam.isEmpty()&& !mail.isEmpty())
        {
            HashMap<String,Object>map=new HashMap<>();
            map.put("id",id);
            map.put("name",nam);
            map.put("Email",mail);
            map.put("description",des);
            db.collection("Documents").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Data saving successful",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,"data saving is  not successful",Toast.LENGTH_SHORT).show();

                }
            });

        }else
        {
            Toast.makeText(this,"something wrong",Toast.LENGTH_SHORT).show();
        }
    }
}