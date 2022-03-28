//package com.sosa.dummyapp;
//
//import android.util.Log;
//
//import org.chromium.net.CronetException;
//import org.chromium.net.UrlRequest;
//import org.chromium.net.UrlResponseInfo;
//
//import java.nio.ByteBuffer;
//
//public class MyURLRequestCallback extends UrlRequest.Callback {
//    private static final String TAG = "MyUrlRequestCallback";
//
//    @Override
//    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
//        Log.i(TAG, "onRedirectReceived method called.");
//        // You should call the request.followRedirect() method to continue
//        // processing the request.
//        request.followRedirect();
//    }
//
//    @Override
//    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
//        Log.i(TAG, "onResponseStarted method called.");
//        int httpsStatusCode = info.getHttpStatusCode();
//        if (httpsStatusCode == 200){
//            request.read(ByteBuffer.allocateDirect(102400));
//        } else if (httpsStatusCode == 503){
//            Log.i(TAG, "Service unavailable");
//        }
//        // You should call the request.read() method before the request can be
//        // further processed. The following instruction provides a ByteBuffer object
//        // with a capacity of 102400 bytes to the read() method.
//        Log.i(TAG, info.getAllHeaders().toString());
//    }
//
//    @Override
//    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
//        Log.i(TAG, "onReadCompleted method called.");
//        byteBuffer.flip();
//        Log.i(TAG, byteBuffer.toString());
//        byteBuffer.clear();
//        // You should keep reading the request until there's no more data.
//        request.read(byteBuffer);
//    }
//
//    @Override
//    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
//        Log.i(TAG, "onSucceeded method called.");
//        Log.i(TAG, String.valueOf(info.wasCached()));
//    }
//
//    @Override
//    public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
//        Log.e(TAG, "The request has failed.");
//    }
//}
