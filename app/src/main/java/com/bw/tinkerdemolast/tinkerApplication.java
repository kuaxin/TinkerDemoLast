package com.bw.tinkerdemolast;

import android.app.Application;

import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * Create by Rgx on 2019/5/8 9:13
 * Description:
 */
public class tinkerApplication extends Application {

    private ApplicationLike tinkerApplicationLike;
    @Override
   public void onCreate() {
            super.onCreate();

           if (BuildConfig.TINKER_ENABLE) {
                  // 我们可以从这里获得Tinker加载过程的信息
                   tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

                  // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
                  TinkerPatch.init(tinkerApplicationLike)
                           .reflectPatchLibrary()
                          .setPatchRollbackOnScreenOff(true)
                          .setPatchRestartOnSrceenOff(true);

                   // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
                  new FetchPatchHandler().fetchPatchWithInterval(3);
                 }
      }
}
