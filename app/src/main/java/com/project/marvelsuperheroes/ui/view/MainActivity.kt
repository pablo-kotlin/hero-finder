package com.project.marvelsuperheroes.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.api.ApiClient
import com.project.marvelsuperheroes.data.repository.SuperheroRepositoryImpl
import com.project.marvelsuperheroes.databinding.ActivityMainBinding
import com.project.marvelsuperheroes.ui.adapters.OnSuperheroClickListener
import com.project.marvelsuperheroes.ui.adapters.SuperheroAdapter
import com.project.marvelsuperheroes.ui.viewmodel.SuperheroViewModel
import com.project.marvelsuperheroes.ui.viewmodel.SuperheroViewModelFactory

class MainActivity : AppCompatActivity(), OnSuperheroClickListener {

    private lateinit var viewModel: SuperheroViewModel
    private lateinit var adapter: SuperheroAdapter
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mProgressBar = mBinding.loading

        mBinding.etSearchSuperheroes.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mBinding.btnSearchSuperheroes.performClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        adapter = SuperheroAdapter(listOf(), this)

        mBinding.rvSuperheroList.layoutManager = LinearLayoutManager(this)
        mBinding.rvSuperheroList.adapter = adapter

        val repository = SuperheroRepositoryImpl(ApiClient.instance)
        val factory = SuperheroViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[SuperheroViewModel::class.java]

        mBinding.btnSearchSuperheroes.setOnClickListener {
            val superhero = mBinding.etSearchSuperheroes.text.toString()
            viewModel.getSuperheroes(superhero).observe(this) { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d("MainActivity", "Superhero: ${resource.data}")
                        adapter.updateData(resource.data ?: listOf())
                        hideProgressBar()
                    }

                    Resource.Status.ERROR -> {
                        Log.d("MainActivity", "Error: ${resource.message}")
                        hideProgressBar()
                    }

                    Resource.Status.LOADING -> {
                        Log.d("MainActivity", "Loading...")
                        showProgressBar()
                    }
                }
            }
            hideKeyboard()
        }
    }

    override fun onSuperheroClick(id: Int) {
        val intent = Intent(this, SuperheroDetailsActivity::class.java).apply {
            putExtra(SuperheroDetailsActivity.EXTRA_SUPERHERO_ID, id)
        }
        startActivity(intent)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun showProgressBar() {
        mProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mProgressBar.visibility = View.GONE
    }
}