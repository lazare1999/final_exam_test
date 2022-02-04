package com.example.test.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.books.BookDbHelper;
import com.example.test.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonAdd = binding.buttonAdd;

        buttonAdd.setOnClickListener(view -> {

            if (binding.editTextNameHome.getText() ==null || binding.editTextNameHome.getText().toString().isEmpty() ||
                    binding.editTextYearHome.getText() ==null || binding.editTextYearHome.getText().toString().isEmpty()
            ) {
                Toast.makeText(getActivity(), "შეავსეთ გრაფები", Toast.LENGTH_SHORT).show();
                return;
            }

            BookDbHelper bookDbHelper = new BookDbHelper(getContext());

            String name = binding.editTextNameHome.getText().toString();
            int year = Integer.parseInt(binding.editTextYearHome.getText().toString());

            bookDbHelper.insert(name, year);


            Toast.makeText(getActivity(), "წარმატებით დაემატა", Toast.LENGTH_SHORT).show();


        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}