package com.laushkina.anastasia.uiexperiments.view.vision;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    public static final String searchFinishedIntent = "com.laushkina.anastasia.uiexperiments.view.vision.search_finished";

    private GraphicOverlay<OcrGraphic> graphicOverlay;
    private String wordForSearch;
    private Context context;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, String wordForSearch, Context context) {
        graphicOverlay = ocrGraphicOverlay;
        this.wordForSearch = wordForSearch;
        this.context= context;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null && item.getValue().contains(wordForSearch)) {
                Log.d(this.getClass().getCanonicalName(), "Text detected! " + item.getValue());
                sendSearchFinished();
                return;
            }
            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
            graphicOverlay.add(graphic);
        }
    }

    private void sendSearchFinished() {
        Intent intent = new Intent(searchFinishedIntent);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public void release() { graphicOverlay.clear(); }
}