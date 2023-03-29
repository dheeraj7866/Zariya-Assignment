package com.example.zyra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signOutBtn;
    TextView name,email;
    ArrayList<Detail> detailArrayList;
    RecyclerView recyclerViewHome;
    Adapter adapter;
    CircleImageView profileImage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name=findViewById(R.id.person_name);
        email=findViewById(R.id.person_email);

        signOutBtn=findViewById(R.id.sign_out);
        profileImage=findViewById(R.id.profile_image);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            name.setText(personName);
            email.setText(acct.getEmail());
            String url = String.valueOf(acct.getPhotoUrl());
            //Log.e("h",url);
            Picasso.get().load(url).into(profileImage);
        }
        recyclerViewHome=findViewById(R.id.recycler_home);
        detailArrayList=new ArrayList<>();
        detailArrayList.add(new Detail(R.drawable.zyra1,"Proudly announcing that one of our dearest mates is here with his next project -\n" +
                "\n" +
                "Indian Predator: The Butcher of Delhi"));
        detailArrayList.add(new Detail(R.drawable.zyra2,"Proudly announcing that one of our dearest mates is here with her next project -\n" +
                "\n" +
                "Raksha Bandhan"));
        detailArrayList.add(new Detail(R.drawable.zyra3,"Proudly announcing that one of our dearest mates is here with his next project -\n" +
                "\n" +
                "Babli Bouncer\n" +
                "\n" +
                "Directed by Madhur Bhandarkar (@imbhandarkar)\n" +
                "\n" +
                "Released on 23rd September on Disney Hotstar"));
        detailArrayList.add(new Detail(R.drawable.zyra4,"Happy World Theatre Day❤️\n" +
                "\n" +
                "We were like two performers in a play, but we were divided, we were not acting with one another. We had to endure it alone,\n" +
                "\n" +
                "we had to put up this show, this miserable, sham performance for the sake of all these people I did not know and did not want to see again."));
        detailArrayList.add(new Detail(R.drawable.zyra5,"Happy World Theatre Day❤️\n" +
                "\n" +
                "Humans had built a world inside the world, which reflected it in pretty much the same way as a drop of water reflected the landscape. And yet and yet....\n" +
                "\n" +
                "Inside this little world they had taken pains to put all the things you might think they would want to escape from -hatred, fear, tyranny, and so forth. Death was intrigued. They thought they wanted to be taken out of themselves, and every art humans dreamt up took them further in. He was fascinated."));
        detailArrayList.add(new Detail(R.drawable.zyra6,"Michelle Yeoh becomes first Asian actress to win an Oscar."));
        detailArrayList.add(new Detail(R.drawable.zyra7,"Realization of the patterns of their brain and that of the character's brain is the first thing which makes an actor aware about the reality of the situation/scene.\n" +
                "Neglecting these patterns is the first thing to abandon the reality of your character.\n" +
                "\n" +
                "ZARIYA THEATRE GROUP\n" +
                "presents"));
        detailArrayList.add(new Detail(R.drawable.zyra8,"I know \"death is the ultimate truth of this world!\" But I never thought in my dreams that I would write this thing about my best friend #SatishKaushik while alive. Such a sudden full stop on a friendship of 45 years!! Life will NEVER be the same without you SATISH! Om Shanti!\"\n"));
        detailArrayList.add(new Detail(R.drawable.zyra9,"First look\uD83C\uDF3C\n" +
                "\n" +
                "Character - Mrs. Bandita Chatterjee\n" +
                "\n" +
                "Actor - @okay_dj7"));
        detailArrayList.add(new Detail(R.drawable.zyra10,"Ana De Armas in Golden Globes'2023."));

        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,detailArrayList);
        recyclerViewHome.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        // Call the super class method
        super.onBackPressed();
        // Close the app and remove it from the recent apps list
        finishAffinity();
        //System.exit(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.sign_out){
            signOut();
            startActivity(new Intent(Home.this,SignIn.class));

        }
        return super.onOptionsItemSelected(item);
    }
    private void signOut() {
        gsc.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(Home.this, "Signed Out successfully...", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}