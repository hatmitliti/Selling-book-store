package com.example.book.ThuKho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.Dialog.NotificationDialog;
import com.example.book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassThuKho extends AppCompatActivity {

    // Toolbar toolbar;
    EditText edtCurrentPassword, edtNewPassword, edtRePassword;
    Button btnChangPassword;
    FirebaseUser user;
    FirebaseAuth auth;
    private NotificationDialog notificationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_change_pass);
        notificationDialog = new NotificationDialog(this);
        mapping();
        //init firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        setEvent();
        // toolbarr
        Toolbar toolbar = findViewById(R.id.tbChangePassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setEvent() {
        btnChangPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAuthenticationPassword();
            }
        });
    }

    private void reAuthenticationPassword() {
        String email = user.getEmail();
        user = auth.getCurrentUser();
        String password = edtNewPassword.getText().toString().trim();
        String repassword = edtRePassword.getText().toString().trim();
        String curpassword = edtCurrentPassword.getText().toString().trim();

        if (curpassword.isEmpty()) {
            edtCurrentPassword.setError(getResources().getString(R.string.empty_field));
        } else if (password.isEmpty()) {
            edtNewPassword.setError(getResources().getString(R.string.empty_field));
        } else if (repassword.isEmpty()) {
            edtRePassword.setError(getResources().getString(R.string.empty_field));
        } else if (!repassword.equalsIgnoreCase(password)) {
            edtRePassword.setError(getResources().getString(R.string.confirm_pass));
        } else {
            notificationDialog.startLoadingDialog();
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, password);
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    notificationDialog.endLoadingDialog();
                                    notificationDialog.startSuccessfulDialog(getResources().getString(R.string.change_pass_success));
                                } else {
                                    notificationDialog.endLoadingDialog();
                                    notificationDialog.startErrorDialog(getResources().getString(R.string.change_pass_failed));                                }
                            }
                        });
                    } else {
                        notificationDialog.endLoadingDialog();
                        notificationDialog.startErrorDialog(getResources().getString(R.string.pass_hientai));                    }
                }
            });
        }
    }

    private void mapping() {
        //  toolbar = findViewById(R.id.tbChangePassword);
        edtCurrentPassword = findViewById(R.id.edt_current_password);
        edtNewPassword = findViewById(R.id.edt_change_password);
        edtRePassword = findViewById(R.id.edt_re_enter_password);
        btnChangPassword = findViewById(R.id.btnChangePassword);
    }
}