package kg.geektrch.newapp38.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektrch.newapp38.R;
import kg.geektrch.newapp38.databinding.FragmentHomeBinding;
import kg.geektrch.newapp38.models.NewsModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
        adapter.setOnClick(new NewsAdapter.OnClick() {
            @Override
            public void onLongClick(int newsModel) {
                adapter.removeItem(newsModel);
            }

            @Override
            public void onClick(int newsModel) {
                NewsModel newsModel1 = adapter.getItem(newsModel);
                openFragment(newsModel1);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(null);
            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NewsModel model = (NewsModel) result.getSerializable("text");
                adapter.addItem(model);
            }
        });
        init();
    }

    private void init() {
        binding.recyclerView.setAdapter(adapter);
    }

    private void openFragment(NewsModel newsModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("day", newsModel);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}