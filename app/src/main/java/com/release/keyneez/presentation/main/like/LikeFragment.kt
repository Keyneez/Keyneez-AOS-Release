import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
    lateinit var likeList: List<Activity>
    private val likeViewModel by viewModels<LikeViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = likeViewModel
        initLikeAdapter()
        setupLikeActivityList()
        initLikeEditBtnClickListener()
        initEditBtnClickListener()
        initCategoryBtnListener()
    }

    private fun initCategoryBtnListener() {
        binding.tvLikeAll.setOnClickListener {
            selectOnlyOneButton(binding.tvLikeAll)
        }
        binding.tvLikeCareer.setOnClickListener {
            selectOnlyOneButton(binding.tvLikeCareer)
        }
        binding.tvLikeHobby.setOnClickListener {
            selectOnlyOneButton(binding.tvLikeHobby)
        }
        binding.tvLikeOutside.setOnClickListener {
            selectOnlyOneButton(binding.tvLikeOutside)
        }
    }

    private fun selectOnlyOneButton(selectedButton: TextView) {
        binding.tvLikeAll.isSelected = false
        binding.tvLikeCareer.isSelected = false
        binding.tvLikeHobby.isSelected = false
        binding.tvLikeOutside.isSelected = false

        selectedButton.isSelected = true
    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter(
            setItemsSelected = likeViewModel::setItemsSelected,
            isEdit = likeViewModel.isEdit
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
            likeViewModel.updateEditView()
            mainViewModel.updateBnvView()
        }
    }

    private fun initEditBtnClickListener() {
        binding.btnEdit.setOnSingleClickListener {
            likeViewModel.deleteSelectedItems()
            BindingToast.initLikeDeleteToast(
                requireContext(),
                getString(com.release.keyneez.R.string.like_delete_complete)
            )?.show()
            binding.btnEdit.visibility = View.GONE
            binding.ivEditBackground.visibility = View.GONE
            likeViewModel.updateEditView()
            mainViewModel.updateBnvView()
        }
    }

    private fun setupLikeActivityList() {
        likeViewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeList = activityList
            likeAdapter?.submitList(activityList)
            if (likeViewModel.isEdit.value == false) {
                Log.d("1", "false일 때")
                binding.tvLikeNum.setText(likeList.size.toString())
            } else {
                Log.d("1", "true일 때")
                binding.tvLikeNum.setText(likeViewModel.getSelectedIdsCount().toString())
            }
            binding.btnLikeEdit.isEnabled = likeList.isNotEmpty()
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
