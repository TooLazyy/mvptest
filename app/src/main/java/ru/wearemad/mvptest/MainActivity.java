package ru.wearemad.mvptest;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.wearemad.mvptest.Core.ResponseDataModel.WallItemsResponse;
import ru.wearemad.mvptest.Core.VkApiInterface;
import ru.wearemad.mvptest.Core.mvp.presenter.MainPresenter;
import ru.wearemad.mvptest.Core.mvp.presenter.MainPresenterImpl;
import ru.wearemad.mvptest.Core.mvp.view.MainListViewAdapter;
import ru.wearemad.mvptest.Core.mvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView, ListView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    @Inject
    Retrofit retrofit;
    @Inject
    VkApiInterface vkApiInterface;
    private MainPresenter presenter;
    private ListView list;
    private RelativeLayout loading;
    private MainListViewAdapter adapter;
    private Toolbar toolbar;
    private SwipeRefreshLayout refresh;
    private ArrayList<HashMap<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this);
        setupView();
    }

    private void setupView () {
        list = (ListView) findViewById(R.id.lvList);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        loading = (RelativeLayout) findViewById(R.id.rlLoadingPanel);
        toolbar = (Toolbar) findViewById(R.id.tbToolbar);
        setSupportActionBar(toolbar);
        String[] from = {"from"};
        int[] to = {R.id.ivImage};
        adapter = new MainListViewAdapter(this,data ,R.layout.main_list_item,from,to);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void stopRefresh() {
        refresh.setRefreshing(false);
        //Toast.makeText(this, "asd",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.onItemClick(data.get(i), this);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String m) {
        Toast.makeText(this,m,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupAdapterData(ArrayList<HashMap<String, Object>> data) {
        if(!this.data.isEmpty()) {
            this.data.clear();
        }
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.menuItemSelected(item, MainActivity.this);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openDescriptionActivity(Intent intent) {
        startActivity(intent);
    }
}
