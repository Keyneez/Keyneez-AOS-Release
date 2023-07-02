import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.domain.model.Activity
import com.release.keyneez.presentation.main.MainViewModel
import com.release.keyneez.presentation.main.like.LikeAdapter
import com.release.keyneez.presentation.main.like.LikeViewModel
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.binding.BindingToast
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeFragment :
    BindingFragment<FragmentLikeBinding>(com.release.keyneez.R.layout.fragment_like) {
    private var likeAdapter: LikeAdapter? = null
    private lateinit var mainViewModel: MainViewModel
    lateinit var likeList: List<Activity>
    private val likeViewModel by viewModels<LikeViewModel>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = likeViewModel
        initLikeAdapter()
        setupLikeActivityList()
        initLikeEditBtnClickListener()
        initEditBtnClickListener()
        updateDeleteItems()
    }

    fun updateDeleteItems() {
        // 리스트에서 아이템을 삭제할 때 원본 리스트를 직접 수정하는 것은 권장하지 않기에 임의의 리스트를 만듦
        val selectedIdsList: LiveData<MutableList<Int>> = likeViewModel.selectedIds
        selectedIdsList.observe(viewLifecycleOwner) { selectedIds ->
            val updatedDataList = likeList.toMutableList()

            for (selectedId in selectedIds) {
                val itemToRemove = updatedDataList.find { it.id == selectedId }
                itemToRemove?.let {
                    val position = updatedDataList.indexOf(it)
                    updatedDataList.remove(it)
                    likeAdapter?.notifyItemRemoved(position)
                    likeAdapter?.notifyItemRangeChanged(position, updatedDataList.size)
                }
            }

            likeList = updatedDataList.toList()
            likeAdapter?.submitList(likeList)
        }
    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter(
            setItemsSelected = likeViewModel::setItemsSelected,
            getSelectedIdsCount = likeViewModel::getSelectedIdsCount
        )
        binding.rvLike.adapter = likeAdapter
        val animator = binding.rvLike.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        likeViewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeAdapter?.submitList(activityList)
        }
    }

    private fun initLikeEditBtnClickListener() {
        binding.btnLikeEdit.setOnSingleClickListener {
            mainViewModel.hideBottomNavigation()
        }
    }

    private fun initEditBtnClickListener() {
        binding.btnEdit.setOnSingleClickListener {
            likeViewModel.activityList.observe(
                viewLifecycleOwner,
                Observer {
                    it?.let {
                        likeAdapter?.submitList(it)
                        updateDeleteItems()
                    }
                }
            )
            BindingToast.initLikeDeleteToast(
                requireContext(),
                getString(com.release.keyneez.R.string.like_delete_complete)
            )?.show()
            mainViewModel.showBottomNavigation()
        }
    }

    private fun setupLikeActivityList() {
        likeViewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeList = activityList
            likeAdapter?.submitList(activityList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        likeAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = LikeFragment()
    }
}
