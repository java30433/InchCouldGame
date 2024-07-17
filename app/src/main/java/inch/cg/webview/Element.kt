package inch.cg.webview

class Element(id: Int) {
    private val jsObj = "window.f$id"
    suspend fun textContent() = WebView.runJs("$jsObj.textContent")
}