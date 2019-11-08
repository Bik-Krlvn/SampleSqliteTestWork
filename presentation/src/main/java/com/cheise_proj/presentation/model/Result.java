package com.cheise_proj.presentation.model;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

public class  Result<T>  {
    @NonNull
    private Status status;
    @Nullable
    private T data;
    @Nullable
    private String message;

    public Result() {
        this.status = Status.LOADING;
        this.data = null;
        this.message = null;
    }

    public Result<T> loading(){
        this.status = Status.LOADING;
        this.data = null;
        this.message = null;
        return this;
    }

    public Result<T> success(@Nullable T data){
        this.status = Status.SUCCESS;
        this.data = data;
        this.message = null;
        return this;
    }

    public Result<T> error(@Nullable String message,@Nullable T data){
        this.status = Status.ERROR;
        this.message = message;
        this.data = data;
        return this;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
