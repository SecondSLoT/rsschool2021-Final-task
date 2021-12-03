package com.secondslot.finaltask.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.secondslot.finaltask.R
import com.secondslot.finaltask.databinding.FragmentMainBinding
import com.secondslot.finaltask.features.channels.ui.StreamsFragment
import com.secondslot.finaltask.features.people.ui.UsersFragment
import com.secondslot.finaltask.features.profile.ui.ProfileFragment

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var lastSelectedItem: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        changePage(binding.bottomNavigationView.selectedItemId)
    }

    private fun initViews() {
        binding.bottomNavigationView.run {
            selectedItemId = R.id.action_channels

            setOnItemSelectedListener { menuItem ->
                changePage(menuItem.itemId)
                true
            }
        }
    }

    private fun changePage(menuItemId: Int) {
        when (menuItemId) {

            R.id.action_channels -> {
                if (lastSelectedItem != R.id.action_channels) {
                    loadFragment(StreamsFragment.newInstance())
                    lastSelectedItem = R.id.action_channels
                }
            }

            R.id.action_people -> {
                if (lastSelectedItem != R.id.action_people) {
                    loadFragment(UsersFragment.newInstance())
                    lastSelectedItem = R.id.action_people
                }
            }

            else -> {
                if (lastSelectedItem != R.id.action_profile) {
                    loadFragment(ProfileFragment.newInstance())
                    lastSelectedItem = R.id.action_profile
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
