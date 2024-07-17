package inch.cg.webview

object API {
    object HomePage {
        suspend fun waitLoadDone() {
            WebView.waitQuerySelector("#app > header > div > div > div.nav-con > div.nav-userinfo")
        }
        suspend fun getUserName() =
            WebView
                .querySelector("#app > header > div > div > div.nav-con > div.nav-userinfo > div.nav-userinfo-item.nav-user-center > div.nav-dialog > div > div.c-mine-user-info > div.user-info > div.user-name > p")
                ?.textContent()
    }
}