package com.example.cameraapiopencv;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Arrays;
import java.util.concurrent.Semaphore;


public class MyCameraManager {
    private static final String TAG = "MyCameraManager";

    private Context context;
    private SurfaceImageView view;
    private OnPreviewListener onPreviewListener;

    public MyCameraManager(Context context, SurfaceImageView view) {
        this.context = context;
        this.view = view;

        onPreviewListener = new OnPreviewListener(context, this.view);
        this.view.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                openCamera();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}
        });
    }


    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int CAMERA_INDEX = 1;
    private static final int MAXIMUM_CAMERA_WIDTH = 640;
    private static final int MAXIMUM_CAMERA_HEIGHT = 480;
    private Size cameraResolution;

    private String cameraID;
    private CameraDevice cameraDevice;
    private Semaphore cameraLock = new Semaphore(1);


    private void openCamera() {
        try {
            // Get camera ID
            cameraLock.acquire();
            CameraManager manager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
            cameraID = manager.getCameraIdList()[CAMERA_INDEX];

            // Set preview size
            cameraResolution = getCameraResolution(manager);

            // Check permission
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                return;
            }

            // Open camera
            manager.openCamera(cameraID, cameraStateCallback, null);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Size getCameraResolution(CameraManager manager) throws CameraAccessException {
        CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraID);
        StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        Size[] sizes = map.getOutputSizes(SurfaceTexture.class);
        Size target_size = new Size(MAXIMUM_CAMERA_WIDTH, MAXIMUM_CAMERA_HEIGHT);

        for (final Size size: sizes) {
            int width = size.getWidth();
            int height = size.getHeight();
            if (MAXIMUM_CAMERA_WIDTH >= width && MAXIMUM_CAMERA_HEIGHT >= height) {
                target_size = size;
                break;
            }
        }
        return target_size;
    }

    CameraDevice.StateCallback cameraStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            createCameraPreveiw();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            camera.close();
            cameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            camera.close();
            cameraDevice = null;
        }
    };




    private CaptureRequest.Builder previewRequestBuilder;
    private Surface preview_surface;

    private ImageReader previewReader;


    private void createCameraPreveiw() {
        try{
            // Preview setting
            previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            view.setAspectRatio(cameraResolution.getWidth(), cameraResolution.getHeight());
            previewReader = ImageReader.newInstance(cameraResolution.getWidth(), cameraResolution.getHeight(), ImageFormat.YUV_420_888, 2);
            previewReader.setOnImageAvailableListener(onPreviewListener, null);
            preview_surface = previewReader.getSurface();

            previewRequestBuilder.addTarget(preview_surface);

            // Preview start
            startPreview();

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private CameraCaptureSession cameraCaptureSessions;

    private void startPreview() throws CameraAccessException {
        cameraDevice.createCaptureSession(Arrays.asList(preview_surface), new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {
                try {
                    cameraCaptureSessions = session;
                    previewRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO);
                    cameraCaptureSessions.setRepeatingRequest(previewRequestBuilder.build(), null, null);
                    cameraLock.release();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) { }
        }, null);
    }

    void closeCamera() {
        try {
            cameraLock.acquire();
            if (cameraCaptureSessions != null) {
                cameraCaptureSessions.close();
                cameraCaptureSessions = null;
            }
            if (cameraDevice != null) {
                cameraDevice.close();
                cameraDevice = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cameraLock.release();
        }
    }
}
