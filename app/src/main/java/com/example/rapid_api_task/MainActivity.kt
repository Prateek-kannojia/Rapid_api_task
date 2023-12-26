package com.example.rapid_api_task

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapid_api_task.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = ArrayList<SportsCategory>()
    private lateinit var adapter: SportsCategoryAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var sportsCategoryDao: SportsCategoryDao


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase=AppDatabase.invoke(applicationContext)
        sportsCategoryDao=appDatabase.Dao()
        getData()
        adapter = SportsCategoryAdapter(list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun getData() {
        lifecycleScope.launch {
            // Fetch data from the database
            val dataList = sportsCategoryDao.getAllCategories()

            if (dataList.isEmpty()) {
                // If no data is available in the database, fetch from API
                val call = RetrofitObject.retrofieApi.getSportsCategories()
                call.enqueue(object : Callback<List<SportsCategory>> {
                    override fun onResponse(
                        call: Call<List<SportsCategory>>,
                        response: Response<List<SportsCategory>>
                    ) {
                        if (response.isSuccessful) {
                            val sportsCategories = response.body()
                            sportsCategories?.let {
                                val dataList = mutableListOf<SportsCategory>()

                                for (category in sportsCategories) {
                                    val data = SportsCategory(
                                        category.key,
                                        category.group,
                                        category.title,
                                        category.description,
                                        category.has_outrights,
                                        category.active
                                    )
                                    dataList.add(data)
                                }

                                // Save data to the database
                                lifecycleScope.launch {
                                    sportsCategoryDao.insertAll(dataList)
                                }

                                // Show data in the RecyclerView
                                showList(dataList)
                            }
                        }
                    }
                    override fun onFailure(call: Call<List<SportsCategory>>, t: Throwable) {
                    }
                })
            } else {
                // If data is available in the database, show it
                showList(dataList as MutableList<SportsCategory>)
            }
        }
    }
    private fun showList(mList: MutableList<SportsCategory>) {
        Log.d("ListTAG", mList.toString())
        adapter.sportsCategories = mList
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}