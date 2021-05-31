package com.usalama.usalamatechnology.shuga.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.activity.ChatActivity
import com.usalama.usalamatechnology.shuga.databinding.FragmentNotificationsBinding
import com.usalama.usalamatechnology.shuga.models.DaChat
import com.usalama.usalamatechnology.shuga.utils.*
import kotlinx.android.synthetic.main.da_item_chat.view.*
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {
    private lateinit var mChatAdapter: RecyclerViewAdapter<DaChat>

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mChatAdapter = RecyclerViewAdapter(
            R.layout.da_item_chat, onBind = { view: View, chat: DaChat, i: Int ->
                view.ivUser.setImageResource(chat.people?.img!!)
                view.viewStatus.apply {
                    if (chat.people?.isOnline!!) {
                        visibility = View.VISIBLE
                    } else {
                        visibility = View.GONE
                    }
                }
                view.tvUserName.text = chat.people?.name!!
                view.tvChatMessage.text = chat.chat!!
                view.tvTime.text = chat.time
                view.buttonRemove.onClick {
                    removeItem(chat)
                }
                view.llMain.onClick {
                    requireActivity().launchActivity<ChatActivity>()

                }

            })


        rvAllMessages.apply {
            setVerticalLayout()
            adapter = mChatAdapter
        }

        mChatAdapter.addItems(getChats())
    }

    private fun removeItem(i: DaChat) {
        mChatAdapter.remove(i)
        if (mChatAdapter.itemCount == 0) {
            rlEmpty.visibility = View.VISIBLE
        } else {
            rlEmpty.visibility = View.GONE
        }
    }
}