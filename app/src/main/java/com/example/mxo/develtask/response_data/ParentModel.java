package com.example.mxo.develtask.response_data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class ParentModel {

    @Expose
    private ArrayList<ChildModel> results;

    public ParentModel() {
    }

    public ParentModel(ArrayList<ChildModel> results) {
        this.results = results;
    }

    public ArrayList<ChildModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ChildModel> results) {
        this.results = results;
    }
}
