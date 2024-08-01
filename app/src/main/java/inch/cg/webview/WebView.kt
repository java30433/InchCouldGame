package inch.cg.webview

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import inch.cg.appContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListSet
import kotlin.random.Random

object WebView {
    @SuppressLint("StaticFieldLeak", "SetJavaScriptEnabled")
    private val view = WebView(appContext).apply {

        addJavascriptInterface(object {
            @JavascriptInterface
            fun callback(id: Int, result: String) {
                asyncJsCallback[id] = result
            }
        }, "android")

        with(settings) {
            javaScriptEnabled = true
            useWideViewPort = true
            userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0"
        }
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(p0: WebView, p1: WebResourceRequest): Boolean {
                p0.loadUrl(p1.url.toString())
                return false
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                CookieManager.getInstance().flush()
            }
        }
        CookieManager.getInstance().acceptThirdPartyCookies(this)
        loadUrl("https://cg.163.com/#/mobile")
    }

    private val asyncJsCallback = ConcurrentHashMap<Int, String>()

    @Suppress("ControlFlowWithEmptyBody")
    suspend fun runJs(code: String, id: Int = random()): String  = withContext(Dispatchers.IO) {
        view.evaluateJavascript(code) {
            asyncJsCallback[id] = it
        }
        while (!asyncJsCallback.containsKey(id)) {}
        return@withContext asyncJsCallback[id]!!
    }

    @Suppress("ControlFlowWithEmptyBody")
    suspend fun waitQuerySelector(path: String): Element = withContext(Dispatchers.IO) {
        val id = random()
        view.evaluateJavascript("""
            (async () => {
                while ( document.querySelector($path) === null) {
                    await new Promise( resolve =>  requestAnimationFrame(resolve) )
                }
                window.f$id = document.querySelector($path);
                android.callback($id, "")
            })();
        """.trimIndent()) {}
        while (!asyncJsCallback.containsKey(id)) {}
        return@withContext Element(id)
    }
    suspend fun querySelector(path: String): Element? {
        val id = random()
        val result = runJs("window.f$id = document.querySelector($path);", id)
        return if (result == "null") null else Element(id)
    }
    //概率很小，但是谁知道呢
    private fun random(): Int {
        var randomNumber = Random.nextInt()
        while (asyncJsCallback.contains(randomNumber)) {
            randomNumber = Random.nextInt()
        }
        return randomNumber
    }
}