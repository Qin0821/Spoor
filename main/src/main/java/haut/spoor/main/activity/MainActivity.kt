package haut.spoor.main.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import haut.spoor.main.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun startToolbarActivity(view: View) {
        startActivity(Intent(this, ToolbarActivity::class.java))
    }

    fun startOtherActivity(view: View) {
        startActivity(Intent(this, OtherActivity::class.java))
    }
}
