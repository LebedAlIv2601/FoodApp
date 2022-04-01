package com.example.foodapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.app.appComponent
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.ui.adapter.BannerRecyclerViewAdapter
import com.example.foodapp.ui.adapter.CategoryRecyclerViewAdapter
import com.example.foodapp.ui.adapter.ProductRecyclerViewAdapter
import com.example.foodapp.ui.vm.MainViewModel
import com.example.foodapp.ui.vm.MainViewModelFactory
import com.example.foodapp.utils.Constants
import com.example.foodapp.utils.Resource
import java.util.*
import javax.inject.Inject


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var categoryAdapter: CategoryRecyclerViewAdapter? = null
    private var bannerAdapter: BannerRecyclerViewAdapter? = null
    private var productAdapter: ProductRecyclerViewAdapter? = null

    private val vm: MainViewModel by viewModels{
        viewModelFactory
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        vm.productList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let { resource ->
                when(resource.status){
                    Resource.Status.SUCCESS -> {
                        if (resource.data != null) {
                            productAdapter = ProductRecyclerViewAdapter()
                            productAdapter?.data = resource.data
                            categoryAdapter?.clickEnable = true
                            setupProductRecyclerView()
                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(this.context, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING -> {
                        Toast.makeText(this.context, "Loading", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setupProductRecyclerView() {
        binding?.apply {
            productRecyclerView.hasFixedSize()
            productRecyclerView.adapter = productAdapter
            productRecyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context)
            productRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    productRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            productRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    val productLayoutManager = productRecyclerView.layoutManager

                    if (productLayoutManager is LinearLayoutManager &&
                        categoryRecyclerView.adapter is CategoryRecyclerViewAdapter
                    ){

                        val firstVisibleItemPosition = productLayoutManager.findFirstVisibleItemPosition()

                    if(!productLayoutManager.isSmoothScrolling
                        && (firstVisibleItemPosition % Constants.CATEGORY_SIZE == Constants.CATEGORY_SIZE - 1 ||
                                firstVisibleItemPosition % Constants.CATEGORY_SIZE == 0)) {

                        Log.e("ScrollListener", "Scrolling")

                        when (productLayoutManager.findFirstVisibleItemPosition()) {
                            in 0 until Constants.CATEGORY_SIZE -> {
                                (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter)
                                    .clickPosition = 0
                                (categoryRecyclerView.layoutManager as LinearLayoutManager)
                                    .scrollToPositionWithOffset(0, 70)
                                Log.e("ScrollListener", "Change")
                            }
                            in Constants.CATEGORY_SIZE until Constants.CATEGORY_SIZE*2 -> {
                                (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter)
                                    .clickPosition = 1

                                (categoryRecyclerView.layoutManager as LinearLayoutManager)
                                    .scrollToPositionWithOffset(1, 70)
                                Log.e("ScrollListener", "Change")
                            }
                            in Constants.CATEGORY_SIZE*2 until Constants.CATEGORY_SIZE*3 -> {
                                (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter)
                                    .clickPosition = 2

                                (categoryRecyclerView.layoutManager as LinearLayoutManager)
                                    .scrollToPositionWithOffset(2, 70)
                                Log.e("ScrollListener", "Change")
                            }
                            in Constants.CATEGORY_SIZE*3 until Constants.CATEGORY_SIZE*4 -> {
                                (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter)
                                    .clickPosition = 3

                                (categoryRecyclerView.layoutManager as LinearLayoutManager)
                                    .scrollToPositionWithOffset(3, 70)
                                Log.e("ScrollListener", "Change")
                            }
                            in Constants.CATEGORY_SIZE*4 until Constants.CATEGORY_SIZE*5 -> {
                                (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter)
                                    .clickPosition = 4
                                (categoryRecyclerView.layoutManager as LinearLayoutManager)
                                    .scrollToPositionWithOffset(4, -70)
                                Log.e("ScrollListener", "Change")
                            }
                        }
                        (categoryRecyclerView.adapter as CategoryRecyclerViewAdapter).notifyDataSetChanged()

                    }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun categoryClick(position: Int){
        val smoothScroller = object: LinearSmoothScroller(context){
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        when(position){
            0 -> {
                smoothScroller.targetPosition = 0
                binding?.apply { productRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)}
            }
            1 -> {
                smoothScroller.targetPosition = Constants.CATEGORY_SIZE
                binding?.apply { productRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)}
            }
            2 -> {
                smoothScroller.targetPosition = Constants.CATEGORY_SIZE*2
                binding?.apply { productRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)}
            }
            3-> {
                smoothScroller.targetPosition = Constants.CATEGORY_SIZE*3
                binding?.apply { productRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)}
            }
            4 -> {
                smoothScroller.targetPosition = Constants.CATEGORY_SIZE*4
                binding?.apply { productRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)}
            }
        }
    }


    private fun setupRecyclerView() {


        categoryAdapter = CategoryRecyclerViewAdapter{
            categoryClick(it)
        }
        categoryAdapter?.data = listOf("Пицца", "Напитки", "Десерты", "Закуски", "Разное")
        categoryAdapter?.clickEnable = false

        bannerAdapter = BannerRecyclerViewAdapter()
        bannerAdapter?.data = listOf(R.drawable.banner1_img,
            R.drawable.banner2_img, R.drawable.banner3_img)

        binding?.apply{
            categoryRecyclerView.hasFixedSize()
            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context,
                LinearLayoutManager.HORIZONTAL, false)

            bannerRecyclerView.hasFixedSize()
            bannerRecyclerView.adapter = bannerAdapter
            bannerRecyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context,
                LinearLayoutManager.HORIZONTAL, false)
        }

    }

    override fun onDestroy() {
        binding = null
        categoryAdapter = null
        bannerAdapter = null
        productAdapter = null
        super.onDestroy()
    }
}