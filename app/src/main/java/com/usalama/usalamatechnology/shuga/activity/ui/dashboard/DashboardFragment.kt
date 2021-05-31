package com.usalama.usalamatechnology.shuga.activity.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.activity.CongratulationsActivity
import com.usalama.usalamatechnology.shuga.activity.UserDetailActivity
import com.usalama.usalamatechnology.shuga.databinding.FragmentDashboardBinding
import com.usalama.usalamatechnology.shuga.models.User
import com.usalama.usalamatechnology.shuga.utils.RecyclerViewAdapter
import com.usalama.usalamatechnology.shuga.utils.cardstackview.*
import com.usalama.usalamatechnology.shuga.utils.generateUser
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.makeGradient
import kotlinx.android.synthetic.main.da_item_user.view.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    private lateinit var adapter: RecyclerViewAdapter<User>

    companion object {

        private var cardView: CardStackView? = null
        private var manager: CardStackLayoutManager? = null
        var topPos = 0
        fun swipe(setting: SwipeAnimationSetting) {
            manager?.setSwipeAnimationSetting(setting)
            cardView?.swipe()
        }

        fun rewind(setting: RewindAnimationSetting) {
            manager?.setRewindAnimationSetting(setting)
            cardView?.rewind()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)

        adapter.addItems(generateUser())
        binding.tvAppName.makeGradient(requireActivity())
        binding.ivClose.setOnClickListener {
            if (manager?.topPosition!! < adapter.itemCount) {
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                swipe(setting)
            }

        }

        binding.ivUndo.setOnClickListener {

            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            rewind(setting)

        }

        binding.ivHeart.setOnClickListener {
            if (manager?.topPosition!! < adapter.itemCount) {
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                swipe(setting)
            }
        }
//        binding.ivNotification.onClick {
//            requireActivity().launchActivity<DANotificationActivity>()
//        }
    }

    private fun checkEmpty() {
        if (manager?.topPosition == adapter.itemCount - 1) {
            binding.rlContent.visibility = View.GONE
            binding.llBottom.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.rlContent.visibility = View.VISIBLE
            binding.llBottom.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
        }

    }

    private fun setUser() {
        val item = adapter.getItem(manager!!.topPosition)
        binding.tvName.text = item.name
        binding.tvDisatance.text = item.distance
        binding.tvProfession.text = item.proffesion
        topPos = manager?.topPosition!!
    }

    private var isPause = false
    override fun onPause() {
        super.onPause()
    }

    private fun initialize(view: View) {
        manager = CardStackLayoutManager(requireActivity(), object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction) {
                if (direction == Direction.Right && !isPause) {
                    isPause = true
                    activity!!.launchActivity<CongratulationsActivity> { }
                }

            }

            override fun onCardAppeared(view: View, position: Int) {
                setUser()

            }

            override fun onCardDisappeared(view: View, position: Int) {
                checkEmpty()

            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }


        })
        manager?.setStackFrom(StackFrom.None)
        manager?.setVisibleCount(3)
        manager?.setTranslationInterval(8.0f)
        manager?.setScaleInterval(0.95f)
        manager?.setSwipeThreshold(0.3f)
        manager?.setMaxDegree(60.0f)
        manager?.setDirections(Direction.HORIZONTAL)
        manager?.setCanScrollHorizontal(false)
        manager?.setCanScrollVertical(false)
        manager?.cardStackListener
        manager?.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager?.setOverlayInterpolator(LinearInterpolator())
        adapter = RecyclerViewAdapter(
            R.layout.da_item_user,
            onBind = { view: View, user: User, i: Int ->
                view.ivDateProfile.setImageResource(user.img!!)

            })
        adapter.onItemClick = { i: Int, view: View, user: User ->
            var intent = Intent(activity, UserDetailActivity::class.java);
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                view.ivDateProfile as View,
                "profile"
            )
            startActivityForResult(intent, 101, options.toBundle())
            isPause = true

        }
        cardView = view.findViewById(R.id.cardStackView)
        cardView?.layoutManager = manager
        cardView?.adapter = adapter
        cardView?.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isPause = true

        /*  manager?.topPosition = topPos
          setUser()*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}