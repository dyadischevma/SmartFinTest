package ru.dyadischevma.smartfintest;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.dyadischevma.smartfintest.data.Country;
import ru.dyadischevma.smartfintest.data.DataRepository;
import ru.dyadischevma.smartfintest.data.entity.Good;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DataRepository dataRepository;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();

        dataRepository = DataRepository.getDataRepository(getApplication());

        compositeDisposable.add(dataRepository.deleteAll().subscribe());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();

        compositeDisposable.add(dataRepository.insertItem(new Good("Морковь", Country.RUSSIA, 2500L)).subscribe(System.out::println));
        compositeDisposable.add(dataRepository.insertItem(new Good("Картофель", Country.BELORUS, 5300L)).subscribe(System.out::println));
        compositeDisposable.add(dataRepository.insertItem(new Good("Чеснок", Country.BELORUS, 25000L)).subscribe(System.out::println));
        compositeDisposable.add(dataRepository.insertItem(new Good("Свекла", Country.RUSSIA, 4000L)).subscribe(System.out::println));
        compositeDisposable.add(dataRepository.insertItem(new Good("Капуста", Country.RUSSIA, 3000L)).subscribe(System.out::println));
        compositeDisposable.add(dataRepository.insertItem(new Good("Огурцы", Country.RUSSIA, 14000L)).subscribe(System.out::println));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}