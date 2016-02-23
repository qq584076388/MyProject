package BasTool;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by Jam on 2015/11/19.
 * 本项目所有Activity的抽象父Activity
 * 本项目所有注解事件均使用Annotation框架
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
        initData();
        setListensr();
    }

    /**
     * 找到布局
     *
     * @return 返回布局的ID
     */
    abstract protected int getLayoutID();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定监听事件
     */
    protected abstract void setListensr();
}
