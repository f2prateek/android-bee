package com.f2prateek.bee;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding.widget.RxTextView;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

import static android.view.animation.AnimationUtils.loadAnimation;

public class MainActivity extends AppCompatActivity {
  @Bind(R.id.editor) EditText editor;
  @Bind(R.id.display) TextSwitcher display;
  Subscription editorTextChangeEvents;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    display.setInAnimation(loadAnimation(this, android.R.anim.fade_in));
    display.setOutAnimation(loadAnimation(this, android.R.anim.fade_out));
    display.setFactory(new ViewSwitcher.ViewFactory() {
      @SuppressLint("InflateParams") @Override public View makeView() {
        return getLayoutInflater().inflate(R.layout.text_display, null);
      }
    });
  }

  @Override protected void onResume() {
    super.onResume();

    editorTextChangeEvents = RxTextView.textChanges(editor)
        .skip(1)
        .debounce(400, TimeUnit.MILLISECONDS)
        .map(new Func1<CharSequence, String>() {
          @Override public String call(CharSequence text) {
            return Bee.spell(text);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
          @Override public void call(String text) {
            display.setText(text);
          }
        });
  }

  @Override protected void onPause() {
    super.onPause();

    editorTextChangeEvents.unsubscribe();
  }
}
