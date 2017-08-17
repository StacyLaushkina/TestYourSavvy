package com.laushkina.anastasia.testyoursavvy.view.vision;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> graphicOverlay;
    private String wordForSearch;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, String wordForSearch) {
        graphicOverlay = ocrGraphicOverlay;
        this.wordForSearch = wordForSearch;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d(this.getClass().getCanonicalName(), "Text detected! " + item.getValue());
                // TODO check if the word was found
                if (item.getValue().equals(wordForSearch)) {
                    Log.d(this.getClass().getCanonicalName(), "Searching text detected!");
                }
            }
            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
            graphicOverlay.add(graphic);
        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() { graphicOverlay.clear(); }
}