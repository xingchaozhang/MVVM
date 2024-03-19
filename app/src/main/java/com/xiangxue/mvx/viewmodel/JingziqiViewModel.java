package com.xiangxue.mvx.viewmodel;

import android.util.ArrayMap;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xiangxue.mvx.model.Board;
import com.xiangxue.mvx.model.Player;

public class JingziqiViewModel extends ViewModel {

    private Board model;

    public final MutableLiveData<ArrayMap<String, String>> cells = new MutableLiveData<>();
    public final MutableLiveData<String> winner = new MutableLiveData<>();

    public JingziqiViewModel() {
        model = new Board();
        cells.postValue(new ArrayMap<String, String>());
    }

    public void onResetSelected() {
        model.restart();
        winner.postValue(null);
        cells.getValue().clear();
        cells.postValue(cells.getValue());
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        if(playerThatMoved != null) {
            cells.getValue().put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
            cells.postValue(cells.getValue());
            winner.postValue(model.getWinner() == null ? null : model.getWinner().toString());
        }
    }
}
