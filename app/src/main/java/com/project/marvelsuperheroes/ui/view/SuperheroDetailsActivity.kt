package com.project.marvelsuperheroes.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.api.ApiClient
import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.data.repository.ComicEventRepositoryImpl
import com.project.marvelsuperheroes.data.repository.SuperheroRepositoryImpl
import com.project.marvelsuperheroes.databinding.ActivitySuperheroDetailsBinding
import com.project.marvelsuperheroes.ui.adapters.ComicEventAdapter
import com.project.marvelsuperheroes.ui.viewmodel.ComicEventViewModel
import com.project.marvelsuperheroes.ui.viewmodel.ComicEventViewModelFactory
import com.project.marvelsuperheroes.ui.viewmodel.SuperheroViewModel
import com.project.marvelsuperheroes.ui.viewmodel.SuperheroViewModelFactory
import com.project.marvelsuperheroes.utils.showErrorDialog

class SuperheroDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUPERHERO_ID = "extra_superhero_id"
    }

    private lateinit var viewModelSuperhero: SuperheroViewModel
    private lateinit var viewModelComicEvent: ComicEventViewModel
    private lateinit var mBinding: ActivitySuperheroDetailsBinding
    private lateinit var mProgressBar: ProgressBar
    private lateinit var adapter: ComicEventAdapter
    private var comicEvents: List<ComicEvent> = listOf()
    private var superheroId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySuperheroDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mProgressBar = mBinding.loading

        superheroId = intent?.extras?.getInt(EXTRA_SUPERHERO_ID)

        val superheroRepository = SuperheroRepositoryImpl(ApiClient.instance)
        val superheroFactory = SuperheroViewModelFactory(superheroRepository)

        viewModelSuperhero =
            ViewModelProvider(this, superheroFactory)[SuperheroViewModel::class.java]
        viewModelSuperhero.getSuperheroById(superheroId!!).observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    resource.data?.let { superhero ->
                        mBinding.tvSuperheroName.text = superhero.name
                        mBinding.tvSuperheroDescription.text = superhero.description
                        mBinding.imgSuperhero.let {
                            Glide.with(this)
                                .load("${superhero.thumbnail.path}.${superhero.thumbnail.extension}")
                                .into(it)
                        }
                    }
                }

                Resource.Status.ERROR -> {
                    hideProgressBar()
                }

                Resource.Status.LOADING -> {
                    showProgressBar()
                }
            }
        }

        val comicEventRepository = ComicEventRepositoryImpl(ApiClient.instance)
        val comicEventFactory = ComicEventViewModelFactory(comicEventRepository)

        viewModelComicEvent =
            ViewModelProvider(this, comicEventFactory)[ComicEventViewModel::class.java]

        viewModelComicEvent.getComicsByCharacterId(superheroId!!).observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("SuperheroDetailsActivity", "Comic: ${resource.data}")
                    hideProgressBar()
                    resource.data?.let {
                        comicEvents = it
                        adapter.updateData(comicEvents)
                    }
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "Error: ${resource.message}")
                    hideProgressBar()
                    showErrorDialog("An unknown error occurred.", applicationContext)
                }

                Resource.Status.LOADING -> {
                    Log.d("SuperheroDetailsActivity", "Loading...")
                    showProgressBar()
                }
            }
        }

        adapter = ComicEventAdapter(listOf())

        mBinding.rvComicEventList.layoutManager = LinearLayoutManager(this)
        mBinding.rvComicEventList.adapter = adapter

        mBinding.btnSeeComics.setOnClickListener {
            adapter.updateData(comicEvents)
        }

        mBinding.btnSeeEvents.setOnClickListener {
            viewModelComicEvent.getEventsByCharacterId(superheroId!!).observe(this) { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d("SuperheroDetailsActivity", "Event: ${resource.data}")
                        hideProgressBar()
                        resource.data?.let {
                            comicEvents = it
                            adapter.updateData(comicEvents)
                        }
                    }

                    Resource.Status.ERROR -> {
                        Log.d("MainActivity", "Error: ${resource.message}")
                        hideProgressBar()
                        showErrorDialog("An unknown error occurred.", applicationContext)
                    }

                    Resource.Status.LOADING -> {
                        Log.d("SuperheroDetailsActivity", "Loading...")
                        showProgressBar()
                    }
                }
            }
        }

        mBinding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showProgressBar() {
        mProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mProgressBar.visibility = View.GONE
    }
}