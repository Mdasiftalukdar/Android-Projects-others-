package com.example.EmergencyHealthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.EmergencyHealthcare.FindHospital.FindNearestHospital;
import com.example.EmergencyHealthcare.VolunteerDocSignup.VolunteerDocSignupActivity;
import com.example.EmergencyHealthcare.auth.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class startHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    RelativeLayout btn_NearHospital , btn_emAmbulance, btn_pandamicInfo, btn_support, btn_who, btn_faq, btn_set_appointment;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private static final int OK_MENU_ITEM = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_home_page);

        //--------------------------- Hooks ---------------------------

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar3);
//        logout = findViewById(R.id.nav_logout);

        //---------------------------- ToolBar -------------------------
        setSupportActionBar(toolbar);
        // Navigation drawer menu

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);




        btn_NearHospital = findViewById(R.id.btn_near_hospital);
        btn_emAmbulance = findViewById(R.id.btn_ambulance);
        btn_pandamicInfo = findViewById(R.id.btn_pandemic_info);
        btn_support = findViewById(R.id.btn_support_volunteer);
        btn_who = findViewById(R.id.btn_who_update);
        btn_faq = findViewById(R.id.btn_faq);
        btn_set_appointment = findViewById(R.id.btn_set_appointment);



        btn_pandamicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, homepage.class);
                startActivity(intent);

            }
        });

        btn_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, VolunteerDocSignupActivity.class);
                startActivity(intent);

            }
        });

        btn_NearHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, FindNearestHospital.class);
                startActivity(intent);
            }
        });
        btn_who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.who.int/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_logout){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(startHomePage.this, LoginActivity.class);
            startActivity(intent);
            finish();


        }

        return true;

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(startHomePage.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}