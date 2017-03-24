package net.buildbox.sample.retrofit_sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import net.buildbox.sample.retrofit_sample.databinding.ActivityMainBinding;
import net.buildbox.sample.retrofit_sample.network.RetrofitManager;
import net.buildbox.sample.retrofit_sample.sample.RxSample;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Log.d("hogehoge", RxSample.getEvenListBefore().toString());
        Log.d("hogehoge", RxSample.getEvenListAfter().blockingGet().toString());

        // 日本Androidの会 中国支部 第34回勉強会のイベント情報を取得する
        mDisposable = RetrofitManager.getEvent(51822)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(event -> {
                if (event.results > 0) {
                    mBinding.cjagEventId.setText(event.events.get(0).event_id);
                    mBinding.cjagTitle.setText(event.events.get(0).title);
                    mBinding.cjagCatch.setText(event.events.get(0).subTitle);
                    mBinding.cjagDescription.loadData(event.events.get(0).description,
                        "text/html; charset=UTF-8",
                        "UTF-8");
                } else {
                    Toast.makeText(this, "データが見つかりませんでした", Toast.LENGTH_SHORT).show();
                }
            }, Throwable::printStackTrace);
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
