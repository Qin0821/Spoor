package haut.spoor.main.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import haut.spoor.main.R
import haut.spoor.main.utils.ScreenUtil
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(tlBar)

        ScreenUtil.setStatusNavigitionBarColor(this, R.color.transparent)

        //显示应用的Logo
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)

        //显示标题和子标题
        supportActionBar?.setDisplayShowTitleEnabled(true)
        tlBar.setTitle(R.string.app_name)
        tlBar.setSubtitle(R.string.sub_title)

        tlBar.setNavigationOnClickListener {
            Toast.makeText(this, "navigation", Toast.LENGTH_SHORT).show()
        }

    }

    //重写activity的onCreateOptionsMenu()方法，设置显示的menu资源
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.tb_action1 -> {
                Toast.makeText(this, "action1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.tb_action2 -> {
                Toast.makeText(this, "action2", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.home -> {
                finish()
                return true
            }
            R.id.icon -> {
                Toast.makeText(this, "icon", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.title -> {
                Toast.makeText(this, "title", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_bar_subtitle -> {
                Toast.makeText(this, "action_bar_subtitle", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
