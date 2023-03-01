package com.francis.popularmovies.home.view.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.francis.popularmovies.databinding.MoviesListFragmentBinding
import com.francis.popularmovies.home.view.adapter.MovieListAdapter
import com.francis.popularmovies.home.viewmodel.MoviesViewModel
import com.francis.popularmovies.utility.isConnectionAvailable
import com.francis.popularmovies.utility.useSnackBar
import com.francis.popularmovies.utility.useToast
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : Fragment() {

    private lateinit var binding: MoviesListFragmentBinding

    private lateinit var moviesAdapter: MovieListAdapter

    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesListFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupObservers(){
        moviesViewModel.run {
            getSavedPopularMovies().observe(viewLifecycleOwner){movies->
                Log.e("okkkk", movies.size.toString())
                if (movies.isNotEmpty()){
                    moviesAdapter.submitList(movies)
                    binding.nodataLayout.visibility = View.GONE
                    binding.refreshButton.visibility = View.GONE
                }else{
                    binding.nodataLayout.visibility = View.VISIBLE
                    binding.refreshButton.visibility = View.VISIBLE
                    binding.refreshButton.setOnClickListener {
                        if (isConnectionAvailable(requireContext())) {
                            onRefresh()
                        }else{
                            useToast(requireContext(), "Please Connect to the Internet")
                        }
                    }
                }
            }

            status.observe(viewLifecycleOwner){
                it?.let {Status->
                    when(Status){
                        MoviesViewModel.MovieAPIStatus.LOADING -> binding.statusLoadingWheel.visibility = View.VISIBLE
                        MoviesViewModel.MovieAPIStatus.DONE-> binding.statusLoadingWheel.visibility = View.GONE
                        MoviesViewModel.MovieAPIStatus.ERROR->{
                            binding.statusLoadingWheel.visibility = View.GONE
                        }
                    }
                }
            }

            navigateToDetails.observe(viewLifecycleOwner){
                it?.let {
                    this@MovieListFragment.findNavController().navigate(MovieListFragmentDirections.actionShowDetail(
                        it
                    )

                    )
                    onMovieCompleteNavigation()
                    useSnackBar(requireView(), "Title: ${it.title}")
                }
            }


            checkInternet.observe(viewLifecycleOwner){
                it.let {connected->
                    if (!connected){
                        val snackbar = Snackbar.make(requireView(), "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Refresh"){
                                if (connected) {
                                    onRefresh()
                                }else{
                                    useToast(requireContext(), "Please Connect to the Internet")
                                }
                            }
                        snackbar.show()
                    }
                }
            }
        }
    }

    private fun setupView(){
        activity?.title = "POPULAR MOVIES"
        moviesAdapter = MovieListAdapter(MovieListAdapter.MovieListener{
            moviesViewModel.onMovieClicked(it)
        })
        binding.run {
            binding.movieRecycler.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = moviesAdapter
            }

        }
    }

    fun onRefresh(){
        binding.statusLoadingWheel.visibility = View.VISIBLE
        binding.nodataLayout.visibility = View.GONE
        moviesViewModel.refreshMovies()
        moviesViewModel.refreshmovieList.observe(viewLifecycleOwner){
            binding.statusLoadingWheel.visibility = View.GONE
            if (it.isNotEmpty()) {
                binding.statusLoadingWheel.visibility = View.GONE
                moviesAdapter.submitList(it)
            }else{
                binding.nodataLayout.visibility = View.VISIBLE
            }
        }
    }
}
