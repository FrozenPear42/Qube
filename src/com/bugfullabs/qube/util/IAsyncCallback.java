package com.bugfullabs.qube.util;
public interface IAsyncCallback {
    // ===========================================================
    // Methods
    // ===========================================================
 
    public abstract void workToDo();
 
    public abstract void onComplete();
 
}