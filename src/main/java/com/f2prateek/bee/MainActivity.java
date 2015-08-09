package com.f2prateek.bee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextSwitcher;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
  @Bind(R.id.editor) EditText editor;
  @Bind(R.id.display) TextSwitcher display;
  Subscription editorTextChangeEvents;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override protected void onResume() {
    super.onResume();

    editorTextChangeEvents = RxTextView.textChangeEvents(editor)
        .skip(1)
        .debounce(400, TimeUnit.MILLISECONDS)
        .map(new Func1<TextViewTextChangeEvent, String>() {
          @Override public String call(TextViewTextChangeEvent textViewTextChangeEvent) {
            return textViewTextChangeEvent.view().getText().toString();
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
          @Override public void call(String text) {
            display.setText(Bee.spell(text));
          }
        });
  }

  @Override protected void onPause() {
    super.onPause();

    editorTextChangeEvents.unsubscribe();
  }
}
