package com.f2prateek.bee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextSwitcher;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
  @Bind(R.id.editor) EditText editor;
  @Bind(R.id.display) TextSwitcher display;
  Subscription editorTextChanges;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override protected void onResume() {
    super.onResume();
    editorTextChanges = RxTextView.textChanges(editor) //
        .skip(1) // First event is a blank string "".
        .debounce(400, TimeUnit.MILLISECONDS) //
        .map(new Func1<CharSequence, String>() {
          @Override public String call(CharSequence text) {
            return Bee.spell(text);
          }
        }) //
        .observeOn(AndroidSchedulers.mainThread()) //
        .subscribe(RxTextSwitcher.text(display));
  }

  @Override protected void onPause() {
    super.onPause();
    editorTextChanges.unsubscribe();
  }
}
