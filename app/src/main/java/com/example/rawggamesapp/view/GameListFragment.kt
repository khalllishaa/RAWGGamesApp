//package com.example.rawggamesapp.view
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.rawggamesapp.viewModel.GameViewModel
//import androidx.fragment.app.activityViewModels
//
//class GameListFragment : Fragment() {
//
//    private var _binding: FragmentGameListBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var adapter: GameAdapter
//    private val viewModel: GameViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentGameListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        adapter = GameAdapter(listOf())
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView.adapter = adapter
//
//        // observe API data disini aja
//        viewModel.games.observe(viewLifecycleOwner) { games ->
//            adapter.setGames(games)
//        }
//
//        // load API data
//        viewModel.loadGames("40c26eb90f2b42b49874b203ec03ddd8")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
