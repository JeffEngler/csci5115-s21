package io.moonen.charles.greengrocery.ui.pscan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PScanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PScanViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}