// Generated by view binder compiler. Do not edit!
package com.example.mymovielist.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mymovielist.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFormLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final View ContainerComponents;

  @NonNull
  public final Button button;

  @NonNull
  public final TextView cadastrar;

  @NonNull
  public final EditText email;

  @NonNull
  public final ImageView logo;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final EditText senha;

  @NonNull
  public final ProgressBar spinner;

  @NonNull
  public final ImageView title;

  private ActivityFormLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull View ContainerComponents, @NonNull Button button, @NonNull TextView cadastrar,
      @NonNull EditText email, @NonNull ImageView logo, @NonNull ConstraintLayout main,
      @NonNull EditText senha, @NonNull ProgressBar spinner, @NonNull ImageView title) {
    this.rootView = rootView;
    this.ContainerComponents = ContainerComponents;
    this.button = button;
    this.cadastrar = cadastrar;
    this.email = email;
    this.logo = logo;
    this.main = main;
    this.senha = senha;
    this.spinner = spinner;
    this.title = title;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFormLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFormLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_form_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFormLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ContainerComponents;
      View ContainerComponents = ViewBindings.findChildViewById(rootView, id);
      if (ContainerComponents == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.cadastrar;
      TextView cadastrar = ViewBindings.findChildViewById(rootView, id);
      if (cadastrar == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.logo;
      ImageView logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.senha;
      EditText senha = ViewBindings.findChildViewById(rootView, id);
      if (senha == null) {
        break missingId;
      }

      id = R.id.spinner;
      ProgressBar spinner = ViewBindings.findChildViewById(rootView, id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.title;
      ImageView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivityFormLoginBinding((ConstraintLayout) rootView, ContainerComponents, button,
          cadastrar, email, logo, main, senha, spinner, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}