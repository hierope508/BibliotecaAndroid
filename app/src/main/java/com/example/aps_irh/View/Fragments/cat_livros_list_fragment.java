package com.example.aps_irh.View.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aps_irh.R;

public class cat_livros_list_fragment extends Fragment {

    private CatLivrosListFragmentViewModel mViewModel;

    public static cat_livros_list_fragment newInstance() {
        return new cat_livros_list_fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cat_livros_list_fragment_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CatLivrosListFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
