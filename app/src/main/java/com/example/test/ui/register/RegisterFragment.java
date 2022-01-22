package com.example.test.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.MainActivity;
import com.example.test.ProfileActivity;
import com.example.test.databinding.FragmentLoginBinding;
import com.example.test.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button buttonRegister = binding.buttonRegister;

        buttonRegister.setOnClickListener(view -> {

            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                return;
            }

            if (!email.matches(emailPattern)) {
                Toast.makeText(getContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(),"ERROR!",Toast.LENGTH_SHORT).show();
                        }
                    });

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}