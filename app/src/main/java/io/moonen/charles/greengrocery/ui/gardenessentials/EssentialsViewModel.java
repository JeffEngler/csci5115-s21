package io.moonen.charles.greengrocery.ui.gardenessentials;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EssentialsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EssentialsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Garden Essentials");
    }

    public LiveData<String> getText() {
        return mText;
    }
}