package com.app.virtusatest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.virtusatest.adapters.BreedsListAdapter
import com.app.virtusatest.adapters.BreedsSubListImagesAdapter
import com.app.virtusatest.databinding.ActivityMainBinding
import com.app.virtusatest.interfaces.AdapterClickListeners
import com.app.virtusatest.models.BreadsList
import com.app.virtusatest.models.RandomImage
import com.app.virtusatest.networkService.UiState
import com.app.virtusatest.viewmodel.DashboardViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout.TabGravity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.http2.Http2Reader.Companion.logger

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterClickListeners {

    val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var activityMainBinding: ActivityMainBinding
    private val TAG : String = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loadDogsBreadsList()
        viewModelFlowObservers()
        clickListeners()
    }

    private fun clickListeners() {
        activityMainBinding.loadRandom.setOnClickListener {
            ApplicationStorage.masterSelectedName?.let { it1 ->
                dashboardViewModel.getRandomImages(
                    it1
                )
            }
        }
        activityMainBinding.loadFullList.setOnClickListener {
            loadDogsBreadsList()
            activityMainBinding.subBreadsListImages.isVisible = false
        }
    }

    private fun viewModelFlowObservers() {
        lifecycleScope.launch {
            dashboardViewModel.breadList.collect {
                when (it) {
                    is UiState.Success -> {
                        val breadsList: BreadsList = it.data as BreadsList
                        Log.i(TAG , "in is")
                        if (breadsList.message.size > 0) {
                            Log.i(TAG , "in if")
                            activityMainBinding.SubListEmpty.isVisible = false
                            activityMainBinding.heading.text = "Breeds List"
                            activityMainBinding.breadsList.layoutManager =
                                LinearLayoutManager(this@MainActivity)
                            val adapter = BreedsListAdapter(breadsList.message, this@MainActivity)
                            activityMainBinding.breadsList.adapter = adapter
                        }

                    }
                    is UiState.Loading -> {
                        Log.d("", it.toString())
                    }
                    is UiState.Failure -> {
                        showMessage(it.msg.toString())
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            dashboardViewModel.breadListSub.collect {
                when (it) {
                    is UiState.Success -> {
                        val breadsList: BreadsList = it.data as BreadsList
                        Log.i(TAG , "$breadsList")
                        activityMainBinding.heading.text = "${ApplicationStorage.masterSelectedName}Breeds Sub List"
                        activityMainBinding.breadsList.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                        val adapter = BreedsListAdapter(breadsList.message, this@MainActivity)
                        activityMainBinding.breadsList.adapter = adapter

                        activityMainBinding.SubListEmpty.isVisible = breadsList.message.size <= 0

                    }
                    is UiState.Loading -> {
                        Log.d("", it.toString())
                    }
                    is UiState.Failure -> {
                        showMessage(it.msg.toString())
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            dashboardViewModel.breadListSubImages.collect {
                when (it) {
                    is UiState.Success -> {
                        val breadsList: BreadsList = it.data as BreadsList
                        Log.i(TAG , "$breadsList")
                        if (breadsList.message.size > 0) {
                            activityMainBinding.subBreadsListImages.isVisible = true
                            activityMainBinding.subBreadsListImages.layoutManager =
                                LinearLayoutManager(
                                    this@MainActivity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            val adapter = BreedsSubListImagesAdapter(
                                this@MainActivity, breadsList.message
                            )
                            activityMainBinding.subBreadsListImages.adapter = adapter
                        }

                    }
                    is UiState.Loading -> {
                        Log.d("", it.toString())
                    }
                    is UiState.Failure -> {
                        showMessage(it.msg.toString())
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            dashboardViewModel.breadLisrandomImages.collect {
                when (it) {
                    is UiState.Success -> {
                        Log.i(TAG , "${it.data}")
                        val breadsList: RandomImage = it.data as RandomImage

                        Glide.with(this@MainActivity)
                            .load(breadsList.message)
                            .error(R.drawable.ic_launcher_background)
                            .into(activityMainBinding.RandomImages)

                    }
                    is UiState.Loading -> {
                        Log.d("", it.toString())
                    }
                    is UiState.Failure -> {
                        showMessage(it.msg.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    fun showMessage(string: String) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_LONG).show()
    }

    private fun loadDogsBreadsList() {
        dashboardViewModel.getBreadList()
    }

    override fun onClick(name: String) {
        ApplicationStorage.masterSelectedName = name
        dashboardViewModel.getBreadSubList(name)
        dashboardViewModel.getBreadSubListImages(name)
    }
}