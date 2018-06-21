package cn.com.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2018/6/6.
 */

/**
 * fragment  首次加载的生命周期
 * 1.setUserVisibleHint
 * 2.onActivityCreated   子类可以在此方法初始完view之后调用请求数据的方法，
 * 当fragment切换时,可以通过 visibleToLoad()方法控制是否执行load()
 * <p>
 * 切换fragment会执行
 * 1.setUserVisibleHint
 */
public abstract class TestFragment extends Fragment {
    /**
     * 首次加载fragment的标志
     */
    protected boolean firstLoad;
    /**
     * 加载完成以后切换时是否需要加载
     * 切换fragment时 只会调用setUserVisibleHint()此方法
     */
    private boolean canLoad = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /**
         * firstLoad 首次进入 在onActivityCreated中初始化完成view以后 再去调用数据更新UI
         * isVisibleToUser 切换时改变
         * canLoad 是否在切换时执行 load()方法
         */
        if (firstLoad && isVisibleToUser && canLoad) {
            load();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firstLoad = true;
        canLoad = visibleToLoad();
    }

    protected abstract boolean visibleToLoad();

    /**
     * 一般是加载数据
     */
    protected void load() {

    }

}
