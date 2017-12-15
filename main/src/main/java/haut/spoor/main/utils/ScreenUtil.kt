package haut.spoor.main.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import haut.spoor.main.R


/**
 * @author Qin
 * @date 2017/10/13.
 * @describe
 */
object ScreenUtil {

    /**
     * @describe 改变状态栏和导航栏只需要传对应的颜色进来就可以
     * 设置透明状态栏需要把fitSystem设置为false
     * 然后把颜色改为透明
     */
    fun setStatusNavigitionBarColor(activity: Activity, stateColor: Int, navigationColor: Int = R.color.black) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上，不设置透明状态栏，设置会有半透明阴影
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //设置statusBar的背景色
            activity.window.statusBarColor = activity.resources.getColor(stateColor, null)
            fitNavigationBar(activity, navigationColor)

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // 生成一个状态栏大小的矩形
            val statusView = createStatusBarView(activity, activity.resources.getColor(stateColor))
            // 添加 statusView 到布局中
            val decorView = activity.window.decorView as ViewGroup
            decorView.addView(statusView)
            //让我们的activity_main。xml中的布局适应屏幕
            setRootView(activity)
        }
    }

    private fun fitNavigationBar(activity: Activity, navigationColor: Int = R.color.black) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.navigationBarColor = activity.resources.getColor(navigationColor, null)
//            val navigationHeight = ScreenUtil.getBottomStatusHeight(activity)
//            val webView: com.github.lzyzsd.jsbridge.BridgeWebView = activity.findViewById<com.github.lzyzsd.jsbridge.BridgeWebView>(R.id.main_bridge_webview)
//            webView.setPadding(0, 0, 0, navigationHeight)
        }
    }

    /**
     * 设置根布局参数，让跟布局参数适应透明状态栏
     */
    private fun setRootView(activity: Activity) {
        //获取到activity_main.xml文件
        val rootView = (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup

        //如果不是设置参数，会使内容显示到状态栏上
        rootView.fitsSystemWindows = true
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private fun createStatusBarView(activity: Activity, color: Int): View {
        // 绘制一个和状态栏一样高的矩形
        val statusBarView = View(activity)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity))
        statusBarView.layoutParams = params
        statusBarView.setBackgroundColor(color)
        return statusBarView
    }

    /**
     * 获取状态栏的高度
     *
     * @param activity
     * @return
     */
    private fun getStatusBarHeight(activity: Activity): Int {

        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")

        return activity.resources.getDimensionPixelOffset(resourceId)
    }

    /**
     * 当顶部是图片时，是图片显示到状态栏上
     *
     * @param activity
     */
    fun setImage(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上，不设置透明状态栏，设置会有半透明阴影
            val window = activity.window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT


            /*
            Window window = getWindow();
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);*/
        } else {
            //。。。。
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    /**
     * 获得状态栏的高度
     */
    @SuppressLint("PrivateApi")
    fun getStatusHeight(context: Context): Int {

        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight
    }

    /**
     * 获取 虚拟按键的高度
     */
    fun getBottomStatusHeight(context: Context): Int {
        val totalHeight = getDpi(context)

        val contentHeight = getScreenHeight(context)

        return totalHeight - contentHeight
    }

    /**
     * 获取标题栏高度
     */
//    fun getTitleHeight(activity: Activity): Int =
//            (activity.window.findViewById(Window.ID_ANDROID_CONTENT) as View).top

    /**
     * 获得屏幕高度
     */
    private fun getScreenHeight(context: Context): Int {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    /**
     * 获得屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获取屏幕原始尺寸高度，包括虚拟功能键高度
     */
    private fun getDpi(context: Context): Int {
        var dpi = 0
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()
        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, displayMetrics)
            dpi = displayMetrics.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dpi
    }

}