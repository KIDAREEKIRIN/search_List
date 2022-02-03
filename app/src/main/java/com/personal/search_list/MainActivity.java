package com.personal.search_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<DutyName> dutyNames;
    private Adapter adapter;
    private ApiInterface apiInterface;
    TextView tv_result;
    TextView search;
    // 선택된 항목리스트
    private RecyclerView rv_selectedDuty;
    private RecyclerView.LayoutManager selectedLayoutManager;
    private SelectAdapter selectAdapter;
    private List<String> selectedDutyNames;

    private static String TAG = "여기";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result); // 결과 TextView
        recyclerView = findViewById(R.id.recyclerView); // 리사이클러뷰.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact( "");

        // 선택된 업무항목
        rv_selectedDuty = findViewById(R.id.rv_selectedDuty);
        selectedLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        rv_selectedDuty.setLayoutManager(selectedLayoutManager);
        rv_selectedDuty.setHasFixedSize(true);





    }

    public void fetchContact(String key){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<DutyName>> call = apiInterface.getContact(key);
        call.enqueue(new Callback<List<DutyName>>() {
            @Override
            public void onResponse(Call<List<DutyName>> call, Response<List<DutyName>> response) {
                dutyNames = response.body();
                adapter = new Adapter(dutyNames, MainActivity.this);
                recyclerView.setAdapter(adapter);
                // 클릭 리스너.
                adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        Toast.makeText(getApplicationContext(), "클릭"+dutyNames.get(pos).getDuty_name(), Toast.LENGTH_SHORT).show();

                        Log.d(TAG, dutyNames.get(pos).getDuty_name());
                        tv_result.setText(dutyNames.get(pos).getDuty_name());

                        selectedDutyNames = new ArrayList<>();
                        selectedDutyNames.add(dutyNames.get(pos).getDuty_name());
                        Log.d(TAG, String.valueOf(selectedDutyNames));
//                        tv_result.setText(selectedDutyNames.get(pos));

                        // 선택항목에 대한 Adapter
                        selectAdapter = new SelectAdapter(selectedDutyNames,MainActivity.this);
                        rv_selectedDuty.setAdapter(selectAdapter);
                        selectAdapter.notifyDataSetChanged();


                    }
                });

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DutyName>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact(newText);
                return false;
            }
        });
        return true;


    }
//    private List<String> selectedDuty() {
//        List<String> selectList = new ArrayList<>();
//        for (int i = 0; i < selectedDutyNames.size(); i++){
//            selectList.add(i,selectedDutyNames.get(i));
//        }
//        return selectList;
//    }
}