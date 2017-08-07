package dfsolutions.com.tonnerdelivery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = ConfiguracaoFirebase.getFirebase();
        reference.child("funcionou").setValue(10);

    }
}
