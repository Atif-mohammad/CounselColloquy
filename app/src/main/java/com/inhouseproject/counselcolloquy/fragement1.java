package com.inhouseproject.counselcolloquy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//public class fragement1 extends Fragment implements View.OnClickListener{
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragement1,container,false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        TextView textview = getView().findViewById(R.id.tv_prof);
//        textview.setOnClickListener(this);
//    }
//
//    public  void onClick(View view){
//        switch(view.getId()){
//           case R.id.tv_prof;
//            Intent intent = new Intent(getActivity(),CreateProfile.class);
//            startActivity(intent);
//               break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + view.getId());
//        }
//    }
//}
public class fragement1 extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textview = view.findViewById(R.id.tv_prof);
        textview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_prof) {
            Intent intent = new Intent(getActivity(), CreateProfile.class);
            startActivity(intent);
        } else {
            throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}