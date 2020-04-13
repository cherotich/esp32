package com.example.esp32;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
//    ImageView ledon,ledoff;
    DatabaseReference ledref;
    SwitchCompat toggleswitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ledon=findViewById(R.id.on);
//        ledoff=findViewById(R.id.off);
        ledref= FirebaseDatabase.getInstance().getReference().child("LED_STATUS");
        toggleswitch = findViewById(R.id.toggle);
        ledref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    int state= Integer.parseInt(dataSnapshot.getValue().toString());

                    if (state==1)
                    {
                       toggleswitch.setChecked(true);
                    }
                    else if(state==0)
                    {
                        toggleswitch.setChecked(false);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        toggleswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleswitch.isChecked())
                {
                    ledref.setValue(1);

                }
                else {
                    ledref.setValue(0);
                }


            }
        });




//        ledon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ledref.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                      String status=  dataSnapshot.getValue().toString();
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                    }
////                });
//ledref.setValue(1);
//            }
//        });
//
//        ledoff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ledref.setValue(0);
//            }
//        });
    }
}
