package com.example.bookaddict.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.bookaddict.Model.Item;
import com.example.bookaddict.Model.VolumesResponse;
import com.example.bookaddict.R;
import com.example.bookaddict.RecyclerAdapter.AdapterSearchResults;
import com.example.bookaddict.Repository.RepositoryVolumes;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycler_main_activity_search_results)
    RecyclerView recyclerView;
    @BindView(R.id.txtv_search)
    EditText etxtSearch;
    @BindView(R.id.imgv_icon_search)
    ImageView imgvSearch;
    List<Item> items;
    AdapterSearchResults adapterSearchResults;
    int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapterSearchResults = new AdapterSearchResults();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.getChildCount();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSearchResults);
        imgvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBooks(page);
            }
        });

    }

//    @OnClick(R.id.imgv_icon_search)
    public void loadBooks(int page){
        if(!TextUtils.isEmpty(etxtSearch.getText())){
            Single<VolumesResponse>  single = RepositoryVolumes.getRepositoryVolumesInstance().getResponse(etxtSearch.getText().toString());
            single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<VolumesResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(VolumesResponse responseBody) {
                            adapterSearchResults.setItems(responseBody.getItems());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: "+ e.getMessage());
                        }
                    });
        }
    }
}
