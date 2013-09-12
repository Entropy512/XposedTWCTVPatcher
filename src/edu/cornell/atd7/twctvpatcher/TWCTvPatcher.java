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

			//Disable VPN checks - happened to be right above the method for adb_enabled checks
			//grep for tun0 in the source to find if this method changes name
			XposedHelpers.findAndHookMethod("com.twc.android.ui.base.TWCBaseActivity",
					classLoader, "D", XC_MethodReplacement.returnConstant(Boolean.valueOf("false")));
			
			//Disable ADB checks - look for a method that is checking adb_enabled
			XposedHelpers.findAndHookMethod("com.twc.android.ui.base.TWCBaseActivity",
					classLoader, "E", XC_MethodReplacement.returnConstant(Boolean.valueOf("false")));
		}

	}

}
