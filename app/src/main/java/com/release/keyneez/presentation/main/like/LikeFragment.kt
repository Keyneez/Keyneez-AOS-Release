import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
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
    }

    fun updateDeleteItems() {
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
            updateDeleteItems()
            BindingToast.initLikeDeleteToast(
                requireContext(),
                getString(com.release.keyneez.R.string.like_delete_complete)
            )?.show()
            mainViewModel.showBottomNavigation()
            binding.btnEdit.visibility = View.GONE
            binding.ivEditBackground.visibility = View.GONE
            likeViewModel.updateEditView()
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
