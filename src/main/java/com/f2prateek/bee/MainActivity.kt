package com.f2prateek.bee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextSwitcher
import com.jakewharton.rxbinding.widget.RxTextSwitcher
import com.jakewharton.rxbinding.widget.RxTextView
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit private var editor: EditText
    lateinit private var display: TextSwitcher
    private val subscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editor = findViewById(R.id.editor) as EditText
        display = findViewById(R.id.display) as TextSwitcher
    }

    override fun onResume() {
        super.onResume()
        subscription += RxTextView.textChanges(editor)
                .skip(1) // First event is a blank string "".
                .debounce(400, TimeUnit.MILLISECONDS)
                .map(CharSequence::spell)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxTextSwitcher.text(display))
    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }
}
