package com.ardakaplan.rdalibrarytest.ui.splash;

import com.ardakaplan.rdalibrary.domain.interaction.RDAInteractionException;
import com.ardakaplan.rdalibrary.base.objects.RDAPresenterContract;
import com.ardakaplan.rdalibrary.base.objects.RDAViewContract;

import java.util.ArrayList;

public class SplashContract {

    public interface SplashViewContract extends RDAViewContract {

        void testViewContract();

        void setList(ArrayList<String> list);

        void onError(RDAInteractionException e);

    }

    public interface SplashPresenterContract extends RDAPresenterContract<SplashViewContract> {

        void testPresenterContract();

        void getList();
    }
}
