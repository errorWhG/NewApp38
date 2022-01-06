package kg.geektrch.newapp38;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektrch.newapp38.databinding.FragmentHomeBinding;
import kg.geektrch.newapp38.databinding.FragmentNewsBinding;
import kg.geektrch.newapp38.models.NewsModel;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsModel newsModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsModel = (NewsModel) requireArguments().getSerializable("day");
        if (newsModel != null) {
            binding.editText.setText(newsModel.getTitle());
        }
        binding.btnSave.setOnClickListener(view1 -> save());
    }

    private void save() {
        String text = binding.editText.getText().toString().trim();
        Bundle bundle = new Bundle();
        newsModel = new NewsModel(text, System.currentTimeMillis());
        bundle.putSerializable("text", newsModel);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}