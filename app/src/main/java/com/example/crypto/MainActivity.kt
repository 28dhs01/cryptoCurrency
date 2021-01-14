package com.example.crypto

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CryptoItemClicked{

    private lateinit var mAdapter: CryptoListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = CryptoListAdapter(this)
        recyclerView.adapter = mAdapter


    }


//    private fun fetchData() {
//        //volly library
//        val url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=5f526ba3bd954661a76a4831e47c0859"
//        //making a request
//        val jsonObjectRequest = object: JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                Response.Listener {
//                    val cryptoJsonArray = it.getJSONArray("articles")
//                    val cryptoArray = ArrayList<Crypto>()
//                    for(i in 0 until cryptoJsonArray.length()) {
//                        val cryptoJsonObject = cryptoJsonArray.getJSONObject(i)
//                        val crypto = Crypto(
//                                cryptoJsonObject.getString("title"),
//                                cryptoJsonObject.getString("author"),
//                                cryptoJsonObject.getString("url"),
//                                cryptoJsonObject.getString("urlToImage")
//                        )
//                        cryptoArray.add(crypto)
//                    }
//
//                    mAdapter.updateCrypto(cryptoArray)
//                },
//                Response.ErrorListener {
//                }
//
//        ) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["User-Agent"] = "Mozilla/5.0"
//                return headers
//            }
//        }
//
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//    }


    private fun fetchData() {

        val url = "https://newsapi.org/v2/everything?q=fields&apiKey=5f526ba3bd954661a76a4831e47c0859"
        val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    val newsJsonArray = it.getJSONArray("articles")
                    val newsArray = ArrayList<Crypto>()
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = Crypto(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }

                    mAdapter.updateCrypto(newsArray)
                },
                Response.ErrorListener {

                }
        ) {

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }

        }

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

        override fun onItemClicked(item: Crypto) {

        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));

    }
}