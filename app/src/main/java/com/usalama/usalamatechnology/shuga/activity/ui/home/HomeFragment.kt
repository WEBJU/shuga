package com.usalama.usalamatechnology.shuga.activity.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.activity.ChatActivity
import com.usalama.usalamatechnology.shuga.databinding.FragmentHomeBinding
import com.usalama.usalamatechnology.shuga.models.User
import com.usalama.usalamatechnology.shuga.utils.RecyclerViewAdapter
import com.usalama.usalamatechnology.shuga.utils.generateUser
import com.usalama.usalamatechnology.shuga.utils.getDisplayWidth
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_match.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val width = (requireActivity().getDisplayWidth() / 4.2).toInt()
        var layoutParams = CoordinatorLayout.LayoutParams(width, width)
        layoutParams.bottomMargin = width / 8
        var layoutParams2 =
            CoordinatorLayout.LayoutParams((width / 2.5).toInt(), (width / 2.5).toInt())
        layoutParams2.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

        var matchAdapter = RecyclerViewAdapter<User>(
            R.layout.item_match,
            onBind = { view: View, user: User, i: Int ->
                view.ivProfile.layoutParams = layoutParams
                view.ivImg.layoutParams = layoutParams2
                view.ivImg.setPadding(width / 10, width / 10, width / 10, width / 10)
                if (i % 3 == 1) {
                    view.viewDummy.visibility = View.VISIBLE
                } else {
                    view.viewDummy.visibility = View.GONE
                }
                view.ivProfile.setImageResource(user.img!!)
                view.tvName.text = user.name
            })
        rvMatches.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = matchAdapter
        }
        matchAdapter.addItems(generateUser())
        matchAdapter.onItemClick = { i: Int, view: View, user: User ->
            requireActivity().launchActivity<ChatActivity>()
        }

    }

}