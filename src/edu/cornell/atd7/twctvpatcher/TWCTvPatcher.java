package edu.cornell.atd7.twctvpatcher;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class TWCTvPatcher implements IXposedHookLoadPackage {
	public static final String MY_PACKAGE_NAME = TWCTvPatcher.class.getPackage()
			.getName();
	public static final String TAG = "EntropyWalletPatch";

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		if (lpparam.packageName.equals("com.TWCableTV")) {
			ClassLoader classLoader = lpparam.classLoader;

			XposedHelpers.findAndHookMethod("com.twc.android.ui.base.TWCApplication",
					classLoader, "b", XC_MethodReplacement.returnConstant(Boolean.valueOf("false")));

			XposedHelpers.findAndHookMethod("com.twc.android.ui.livetv.LiveTvActivity",
					classLoader, "w", XC_MethodReplacement.returnConstant(Boolean.valueOf("false")));
		}

	}

}
