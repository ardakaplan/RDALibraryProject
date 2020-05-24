package com.ardakaplan.rdalibrary.base.ui.screen.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.ardakaplan.rdalibrary.base.ui.screen.screencontracts.ScreenContract;
import com.ardakaplan.rdalibrary.helpers.RDAFragmentHelpers;
import com.ardakaplan.rdalogger.RDALogger;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

@SuppressWarnings("unused")
public abstract class RDAFragment extends DaggerFragment implements ScreenContract, RDAViewContract {

    //FragmentManager.OnBackStackChangedListener yönetimi için oluşturulan id, manager dışında kesinlikle kullanılmaması lazım
    public int ID = 0;

    protected String rdaTag = this.getClass().getSimpleName();

    public String className;

    private Unbinder unbinder;

    public String getRdaTag() {
        return rdaTag;
    }

    public RDAFragment() {
        className = getClass().getSimpleName();
    }

    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    public void onScreen() {

    }

    public int[] getFragmentAnimationList() {

        return new int[]{};
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        RDALogger.logLifeCycle(className);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RDALogger.logLifeCycle(className);

        View view = inflater.inflate(getLayout(), container, false);

        unbinder = ButterKnife.bind(this, view);

        initViews(view);

        return view;
    }

    protected void initViews(View parentView) {

    }

    public void onBackPressed() {

    }

    public RDAActivity getContainerActivity() {

        return ((RDAActivity) getActivity());
    }

    @Override
    public void changeStatusBarColor(@ColorRes int colorId) {

        if (getActivity() != null) {

            ((RDAActivity) getActivity()).changeStatusBarColor(colorId);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getPresenterContract() != null) {

            getPresenterContract().attach(this);
        }

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onStart() {
        super.onStart();

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onResume() {
        super.onResume();

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onPause() {
        super.onPause();

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onStop() {
        super.onStop();

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

        if (getPresenterContract() != null) {

            getPresenterContract().detach();
        }

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        RDALogger.logLifeCycle(className);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        RDALogger.logLifeCycle(className);
    }

    public boolean checkFragmentData(String key) {

        return this.getArguments() != null && this.getArguments().containsKey(key);
    }

    public void open(RDAActivity rdaActivity, String key, Serializable fragmentData, boolean clearBackStack) {

        Bundle bundle = new Bundle();

        bundle.putSerializable(key, fragmentData);

        open(rdaActivity, bundle, clearBackStack);
    }

    public void open(RDAActivity rdaActivity, String key, Serializable fragmentData) {

        Bundle bundle = new Bundle();

        bundle.putSerializable(key, fragmentData);

        open(rdaActivity, bundle, false);
    }

    public void open(RDAActivity rdaActivity, boolean clearBackStack) {

        open(rdaActivity, null, clearBackStack);
    }

    public void open(RDAActivity rdaActivity) {

        open(rdaActivity, false);
    }


    public void open(RDAActivity rdaActivity, Bundle fragmentDataBundle, boolean clearBackStack) {

        if (fragmentDataBundle != null) {

            this.setArguments(fragmentDataBundle);
        }

        getFragmentHelpers().addFragmentToBackStack(rdaActivity, this, fragmentPartContainerId(), clearBackStack);
    }

    public void open(RDAActivity rdaActivity, Bundle fragmentDataBundle) {

        open(rdaActivity, fragmentDataBundle, false);
    }

    public abstract RDAFragmentHelpers getFragmentHelpers();

    public abstract @IdRes
    int fragmentPartContainerId();
}