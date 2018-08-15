package com.example.usuario.myapplication.intaractor;

import android.util.Log;

import com.example.usuario.myapplication.connection.RegistryAPI;
import com.example.usuario.myapplication.model.employeeModelRest;

import java.net.UnknownHostException;
import java.util.ArrayList;


import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class EmployeeInteractor {
    private Subscriber<ArrayList<employeeModelRest>> subscriber;
    public void load() {

        RegistryAPI.serviceRX.getEmployees()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getEmployees());
    }

    private Subscriber getEmployees() {
        //presenter.showLoader();
        subscriber = new Subscriber<ArrayList<employeeModelRest>>() {

            @Override
            public void onCompleted() {
                //presenter.hideLoader();
                //presenter.build(FinancialRelocationInteractorImpl.this.financialRelocationModelRests);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TESTE", "ERRO" + ((HttpException)e).code());
                if(e instanceof UnknownHostException) {
                    //presenter.loadNetworkError();
                    //@TODO notificar ao fabric
                } else {
                    //presenter.loadError(e);
                    //@TODO notificar ao fabric
                }
            }

            @Override
            public void onNext(ArrayList<employeeModelRest> financialRelocationModelRests) {
                //FinancialRelocationInteractorImpl.this.financialRelocationModelRests = financialRelocationModelRests;
                Log.i("TESTE", "PEGOU");
                Log.i("TESTE", financialRelocationModelRests.get(0).firstname);
            }
        };
        return subscriber;
    }

}
