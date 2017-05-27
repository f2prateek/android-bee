package com.f2prateek.bee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextSwitcher
import com.jakewharton.rxbinding.widget.RxTextSwitcher
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    internal var editor: EditText? = null
    internal var display: TextSwitcher? = null
    internal var editorTextChanges: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editor = findViewById(R.id.editor) as EditText
        display = findViewById(R.id.display) as TextSwitcher
    }

    override fun onResume() {
        super.onResume()
        editorTextChanges = RxTextView.textChanges(editor!!)
                .skip(1) // First event is a blank string "".
                .debounce(400, TimeUnit.MILLISECONDS, SchedulersHook.computation())
                .map(CharSequence::spell)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxTextSwitcher.text(display!!))
    }

    override fun onPause() {
        super.onPause()
        editorTextChanges!!.unsubscribe()
    }
}
