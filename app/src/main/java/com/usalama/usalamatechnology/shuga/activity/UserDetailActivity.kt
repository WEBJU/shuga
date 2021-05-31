package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.activity.ui.dashboard.DashboardFragment
import com.usalama.usalamatechnology.shuga.databinding.ActivityUserDetailBinding
import com.usalama.usalamatechnology.shuga.models.User
import com.usalama.usalamatechnology.shuga.utils.*
import com.usalama.usalamatechnology.shuga.utils.cardstackview.*
import kotlinx.android.synthetic.main.da_item_user_detail.*
import kotlinx.android.synthetic.main.da_item_user_detail.view.*


class UserDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        binding.ivBack.onClick {
            onBackPressed()
        }

    }
    private  var fromExplore:Boolean=false
    private lateinit var adapter: RecyclerViewAdapter<User>
    private var manager: CardStackLayoutManager? = null
    private fun setUser() {
        val item = adapter.getItem(manager!!.topPosition)
        binding.userDetail.tvName.text = item.name
        binding.userDetail.tvDisatance.text = item.distance
        binding.userDetail.tvProfession.text = item.proffesion
        DashboardFragment.topPos=manager?.topPosition!!
    }
    private fun checkEmpty() {
        if (manager?.topPosition == adapter.itemCount - 1) {
            binding.userDetail.rlContent.visibility = View.GONE
            binding.llBottom.visibility = View.GONE
            finish()
        } else {
            binding.userDetail.rlContent.visibility = View.VISIBLE
            binding.llBottom.visibility = View.VISIBLE

        }

    }
    private var isProcessing=false
    private fun initialize() {
        fromExplore=intent.getBooleanExtra("from_explore",false)
        manager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction) {
                Log.e("cardSwiped",direction.name)
                if (direction==Direction.Right){
                    launchActivity<CongratulationsActivity> {  }
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
            R.layout.da_item_user_detail,
            onBind = { view: View, user: User, i: Int ->
                view.ivDateProfilez.setImageResource(user.img!!)

            })
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = adapter
        binding.cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        if (fromExplore){
            adapter.addItems(generateList())
            manager?.topPosition= intent.getIntExtra("pos",0)

        }else{

            adapter.addItems(generateUser())
            manager?.topPosition= DashboardFragment.topPos
        }

        binding.ivClose.setOnClickListener {
            if (manager?.topPosition!! < adapter.itemCount) {
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager?.setSwipeAnimationSetting(setting)
                if (!fromExplore){
                    DashboardFragment.swipe(setting)
                }
                binding.cardStackView.swipe()
            }
        }

        binding.ivUndo.setOnClickListener {

            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager?.setRewindAnimationSetting(setting)
            if (!fromExplore){
                DashboardFragment.rewind(setting)

            }
            binding.cardStackView.rewind()

        }

        binding.ivHeart.setOnClickListener {
            if (manager?.topPosition!! < adapter.itemCount && !isProcessing) {
                isProcessing=true
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager?.setSwipeAnimationSetting(setting)
                if (!fromExplore){
                    DashboardFragment.swipe(setting)
                }
                binding.cardStackView.swipe()

            }
        }
    }

    override fun onResume() {
        super.onResume()
        isProcessing=false
    }
    /*  private fun checkEmpty() {
          if (manager?.topPosition == adapter.itemCount - 1) {
              rlContent.visibility = View.GONE
              llBottom.visibility = View.GONE
              tvEmpty.visibility = View.VISIBLE
          } else {
              rlContent.visibility = View.VISIBLE
              llBottom.visibility = View.VISIBLE
              tvEmpty.visibility = View.GONE
          }

      }

      private fun setUser() {
          val item = adapter.getItem(manager!!.topPosition)
          tvName.text = item.name
          tvDisatance.text = item.distance
          tvProfession.text = item.proffesion
      }
  */
}
