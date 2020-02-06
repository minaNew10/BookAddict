package com.example.bookaddict.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.bookaddict.Model.Item;
import com.example.bookaddict.Model.VolumesResponse;
import com.example.bookaddict.R;
import com.example.bookaddict.RecyclerAdapter.PaginationAdapter;
import com.example.bookaddict.RecyclerAdapter.PaginationScrollListener;
import com.example.bookaddict.Repository.RepositoryVolumes;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycler_main_activity_search_results)
    RecyclerView recyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mainProgressBar;
    @BindView(R.id.etxt_search)
    EditText etxtSearch;
    @BindView(R.id.imgv_icon_search)
    ImageView imgvSearch;

    LinearLayoutManager linearLayoutManager;
    List<Item> items;
    PaginationAdapter paginationAdapter;
    private static final int NO_OF_ITEMS_IN_ONE_LOAD = 15;
    private static  int INDEX_OF_START_ITEM = 0;
    private static boolean ITEMS_ARE_NULL = false;
    private boolean isLoading = false;
    private boolean isLastPage = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        paginationAdapter = new PaginationAdapter();
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(paginationAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                loadBooks();
            }



            @Override
            public boolean itemsAreNull() {
                return ITEMS_ARE_NULL;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        imgvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ITEMS_ARE_NULL = false;
                if(INDEX_OF_START_ITEM == 0) {
                    mainProgressBar.setVisibility(View.VISIBLE);
                }
                loadBooks();
            }
        });

    }

//    @OnClick(R.id.imgv_icon_search)
    public void loadBooks(){
        if(!TextUtils.isEmpty(etxtSearch.getText())){
                Single<VolumesResponse> single = RepositoryVolumes.getRepositoryVolumesInstance().getTenItems(etxtSearch.getText().toString(),INDEX_OF_START_ITEM,3);
                single.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<VolumesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(VolumesResponse responseBody) {
                                if(responseBody.getItems() == null || responseBody.getItems().size() == 0 ){
                                    paginationAdapter.removeLoadingFooter();
                                    ITEMS_ARE_NULL = true;
                                    return;
                                }
                                Log.i(TAG, "onSuccess: items are null =  " + ITEMS_ARE_NULL);
                                mainProgressBar.setVisibility(View.GONE);
                                if(INDEX_OF_START_ITEM == 0) {
                                    paginationAdapter.setItems(responseBody.getItems());
                                    paginationAdapter.addLoadingFooter();
                                }
                                else {
                                    paginationAdapter.removeLoadingFooter();
                                    paginationAdapter.addBooks(responseBody.getItems());
                                    paginationAdapter.addLoadingFooter();
                                    isLoading= false;
                                }
                                INDEX_OF_START_ITEM+=NO_OF_ITEMS_IN_ONE_LOAD;
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " +e.getMessage());
                            }
                        });

        }
    }
}
