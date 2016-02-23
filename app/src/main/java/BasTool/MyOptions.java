package BasTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import a.myproject.R;

public class MyOptions {
    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "yaobaixing/Cache");// 获取到缓存的目录地址
        Log.d("cacheDir", cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                // .memoryCacheExtraOptions(480, 800) // max width, max
                // height，即保存的每个缓存文件的最大长宽
                // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
                // null) // Can slow ImageLoader, use it carefully (Better don't
                // use it)设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(5)
                        // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
                        // 1024)) // You can pass your own memory cache
                        // implementation你可以通过自己的内存缓存实现
                        // .memoryCacheSize(2 * 1024 * 1024)
                        // .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5
                        // 加密
                        // .discCacheFileNameGenerator(new
                        // HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .discCacheFileCount(100) //缓存的File数量
                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                        // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                        // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

    /**
     * 淡出100 没有在内存
     *
     * @return
     */
    public static DisplayImageOptions getListOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // // 设置图片在下载期间显示的图片
                .showImageOnLoading(R.mipmap.ic_launcher)
                        // // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                        // // 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(false)
                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)
                        // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
                        // .decodingOptions(android.graphics.BitmapFactory.Options
                        // decodingOptions)//设置图片的解码配置
                .considerExifParams(true)
                        // 设置图片下载前的延迟
                        // .delayBeforeLoading(int delayInMillis)//int
                        // delayInMillis为你设置的延迟时间
                        // 设置图片加入缓存前，对bitmap进行设置
                        // 。preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                        // .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 淡入
                .build();
        return options;
    }

    /**
     * 在内存 没有淡出
     *
     * @return
     */
    public static DisplayImageOptions getListOptions2() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // // 设置图片在下载期间显示的图片
                .showImageOnLoading(R.mipmap.ic_launcher)
                        // // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                        // // 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)
                        // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
                        // .decodingOptions(android.graphics.BitmapFactory.Options
                        // decodingOptions)//设置图片的解码配置
                .considerExifParams(true)
                        // 设置图片下载前的延迟
                        // .delayBeforeLoading(int delayInMillis)//int
                        // delayInMillis为你设置的延迟时间
                        // 设置图片加入缓存前，对bitmap进行设置
                        // 。preProcessor(BitmapProcessor preProcessor)
                        // .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                        // .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                        // .displayer(new FadeInBitmapDisplayer(100))// 淡入
                .build();
        return options;
    }

    public static DisplayImageOptions DEFAULT_PIC_OPTIONS = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
            .cacheOnDisc(true).considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565).build();
    /**
     * 有淡出，在内存
     */
    public static DisplayImageOptions DEFAULT_DEFO_OPTIONS = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
            .cacheOnDisc(true).considerExifParams(true)

            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
            .displayer(new FadeInBitmapDisplayer(80))// 淡入
            .bitmapConfig(Bitmap.Config.RGB_565).build();
}
