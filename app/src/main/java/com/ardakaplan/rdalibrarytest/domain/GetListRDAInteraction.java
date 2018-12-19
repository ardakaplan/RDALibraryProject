package com.ardakaplan.rdalibrarytest.domain;

import com.ardakaplan.rdalibrary.domain.interaction.RDAInteraction;
import com.ardakaplan.rdalibrary.domain.interaction.RDAInteractionResult;
import com.ardakaplan.rdalibrary.domain.interaction.RDAInteractionResultListener;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Arda Kaplan at 12/13/2018
 * <p>
 * arda.kaplan09@gmail.com
 */
public class GetListRDAInteraction extends RDAInteraction<String, ArrayList<String>> {


    @Inject
    GetListRDAInteraction() {

    }

    @Override
    public void execute(RDAInteractionResultListener<ArrayList<String>> RDAInteractionResultListener) {

        boolean testException = false;

        if (testException) {

            RDAInteractionResultListener.onResult(new RDAInteractionResult(new GetListExceptionRDA()));
        }

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            list.add(getIn() + " " + i);
        }

        RDAInteractionResultListener.onResult(new RDAInteractionResult<>(list));

    }
}
