import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.presentation.main.explore.recent.RecentFragment

class ExploreAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(
        PopularFragment(),
        RecentFragment()
    )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}
