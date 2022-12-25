package com.example.loginapp.webservices;

import java.io.Serializable;

public interface WebServiceManagerListener {
    void onResponse(boolean hasError, Serializable serializable);
}
