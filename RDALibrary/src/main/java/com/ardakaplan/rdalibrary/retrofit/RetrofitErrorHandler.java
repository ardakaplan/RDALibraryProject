package com.ardakaplan.rdalibrary.retrofit;

import android.support.annotation.Nullable;

import com.ardakaplan.rdalibrary.requestException.NoNetworkRDARequestException;
import com.ardakaplan.rdalibrary.requestException.NotFoundRDARequestException;
import com.ardakaplan.rdalibrary.requestException.NullResponseRDARequestException;
import com.ardakaplan.rdalibrary.requestException.RDARequestException;
import com.ardakaplan.rdalibrary.requestException.ServerRDARequestException;
import com.ardakaplan.rdalibrary.requestException.UnknownRDARequestException;

import java.net.UnknownHostException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:01
 */
public class RetrofitErrorHandler {

    public static <W> RDARequestException checkError(@Nullable Response<W> response, @Nullable Throwable throwable) {

        if (response != null) {

            if (!response.isSuccessful()) {

                switch (response.code()) {

                    case 404:

                        return new NotFoundRDARequestException();

                    case 500:

                        return new ServerRDARequestException();

                    default:

                        return new UnknownRDARequestException();
                }

            } else {

                if (response.body() == null) {

                    return new NullResponseRDARequestException();

                } else {

                    return null;
                }
            }

        } else {

            if (throwable instanceof UnknownHostException) {

                return new NoNetworkRDARequestException();

            } else {

                return null;
            }
        }
    }
}
