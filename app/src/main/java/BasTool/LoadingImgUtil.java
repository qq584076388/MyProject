package BasTool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 加载图片工具类
 */
public class LoadingImgUtil {


    public static void loadimg(String url, ImageView imgview,
                               final ProgressBar progressBar) {

        ImageLoader imageLoader = ImageLoader.getInstance();
        SimpleImageLoadingListener loadListener = null;
        ImageLoadingProgressListener loadProgressListener = null;

        if (progressBar != null) {
            loadListener = new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setProgress(0);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view,
                                            FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view,
                                              Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }
            };
            loadProgressListener = new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view,
                                             int current, int total) {
                    progressBar.setProgress(Math
                            .round(100.0f * current / total));
                }
            };
        }
        imageLoader.displayImage(url, imgview, MyOptions.DEFAULT_PIC_OPTIONS,
                loadListener, loadProgressListener);

    }

    public static void loadimgAnimate(String url, ImageView imgview) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imgview, MyOptions.DEFAULT_PIC_OPTIONS,
                new AnimateFirstDisplayListener());
    }

    public static void loadimgAnimateSmail(String url, ImageView imgview) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imgview, MyOptions.getListOptions2());
    }

    public static void loadimgAnimateOption(String url, ImageView imgview) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imgview, MyOptions.getListOptions2());
    }

    /**
     * 在内存，淡出80
     *
     * @param url
     * @param imgview
     */
    public static void loadimgDefoOption(String url, ImageView imgview) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imgview, MyOptions.DEFAULT_DEFO_OPTIONS);
    }

    private static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
