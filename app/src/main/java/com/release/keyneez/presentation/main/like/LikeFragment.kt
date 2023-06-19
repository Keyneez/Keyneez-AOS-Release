package com.release.keyneez.presentation.main.like

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.release.keyneez.R
import com.release.keyneez.databinding.FragmentLikeBinding
import com.release.keyneez.presentation.main.explore.popular.PopularFragment
import com.release.keyneez.util.binding.BindingFragment
import com.release.keyneez.util.extension.setOnSingleClickListener

class LikeFragment : BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {
    private val viewModel: LikeViewModel by viewModels()
    private var likeAdapter: LikeAdapter? = null
    var btnState = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLikeAdapter()
        initCategoryBtnClickListener()
        setupLikeActivityList()
        initEditBtnKeyListener()
    }
//  삭제하기 버튼 누르면 토스트 띄우게끔
//    Toast toast = Toast.makeText(context, getString(R.string.like_toast), Toast.LENGTH_SHORT)
//    toast.setGravity(Gravity.Top | Gravity.Bottom, 200 , 200)
//    toast.show();

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter()
        binding.rvLike.adapter = likeAdapter
    }

    private fun initEditBtnKeyListener() {
        binding.btnLikeEdit.setOnSingleClickListener {
            if (btnState) {
                btnState = false
                binding.btnLikeEdit.setText("편집")
                binding.tvLikeNum.setText("총 %s개")
                binding.tvLikeTitle.setText("좋아요")
            } else {
                btnState = true
                binding.btnLikeEdit.setText("취소")
                binding.tvLikeNum.setText("%s개 선택")
                binding.tvLikeTitle.setText("삭제할 활동 선택")
            }
        }
    }

    private fun initCategoryBtnClickListener() {
        // ocr result 코드 참고하기
        binding.tvLikeAll.setOnSingleClickListener {}
        binding.tvLikeHobby.setOnSingleClickListener {}
        binding.tvLikeCareer.setOnSingleClickListener {}
        binding.tvLikeOutside.setOnSingleClickListener {}
    }

    private fun setupLikeActivityList() {
        viewModel.activityList.observe(viewLifecycleOwner) { activityList ->
            likeAdapter?.submitList(activityList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        likeAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PopularFragment()
    }
}
