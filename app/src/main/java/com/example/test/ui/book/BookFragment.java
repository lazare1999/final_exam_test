package com.example.test.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.books.BookDbHelper;
import com.example.test.books.BookModel;
import com.example.test.databinding.FragmentBookBinding;

import java.util.List;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonSearch = binding.buttonSearch;

        buttonSearch.setOnClickListener(view -> {

            BookDbHelper bookDbHelper = new BookDbHelper(getContext());
            String name = binding.editTextName.getText().toString();

            List<BookModel> select = bookDbHelper.select(name);

            String[] tutorials = new String[select.size()];

            for (int i = 0; i < select.size(); i++) {

                tutorials[i] = select.get(i).getNameAndYear();
                }

            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<>(
                    getActivity(),
                    R.layout.support_simple_spinner_dropdown_item,
                    tutorials);
            binding.listViewResults.setAdapter(arr);


        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}