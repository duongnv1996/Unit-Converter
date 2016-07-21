package com.duongkk.unitconverter.libs;

import android.content.Context;
import android.content.SharedPreferences;

//import com.thsoft.geopro.R;

public class Config {
	private static String sp_app_config = "app_settings";
	private Context context;
	public final static String ACTION_CHANGE = "com.thsoft.geopro.action_config_change";
	public final static String DEFAULT_LINEAR_UNI = "default_linear_unit";
	public final static String DEFAULT_SQUARE_UNIT = "default_square_unit";
	public final static String DEFAULT_COORDINATE_UNIT = "default_coordinate_unit";

	public final static Unit[] linears = {  new Unit("mm", 0.001f),new Unit("cm", 0.01f),new Unit("in", 0.0254f),new Unit("m", 1.0f), new Unit("km", 1000.0f), new Unit("ft", 0.3048f),
			new Unit("yd", 0.9144f),new Unit("mi", 1609.344f) };
	public final static Unit[] squares = { new Unit("mm²", 0.000001f),new Unit("cm²", 0.0001f),new Unit("in²", 0.000645f),new Unit("m\u00b2", 1.0f), new Unit("ha", 10000.0f),
			new Unit("km\u00b2", 1000000.0f), new Unit("ft\u00b2", 0.3048f * 0.3048f),
			new Unit("yd\u00b2", 0.9144f * 0.9144f),new Unit("mi\u00b2", 1609.344f * 1609.344f), new Unit("ac", 1000000.0f) };
	public final static Unit[] volumes = { new Unit("cm³", 0.000001f),new Unit("in³", 0.000016f),new Unit("m³", 1.0f), new Unit("fd³", 0.028317f),
			new Unit("cup", 0.000237f), new Unit("l",0.001f),
			new Unit("yd³", 0.764555f)};
	public final static Unit[] temperatures = { new Unit("DD" + "\u00B0" + "MM'SS.S\"", 1.0f),
			new Unit("DD" + "\u00B0" + "MM.MMM'", 2.0f), new Unit("DD.DDDDD" + "\u00B0",4.0f) };
	public final static Unit[] coordinates = { new Unit("DD" + "\u00B0" + "MM'SS.S\"", 1.0f),
			new Unit("DD" + "\u00B0" + "MM.MMM'", 2.0f), new Unit("DD.DDDDD" + "\u00B0",4.0f) };

	public static Config getInstance(Context context) {
		Config c = new Config();
		c.context = context;
		return c;
	}

	public static SharedPreferences getConfigStorage(Context context) {
		return context.getSharedPreferences(sp_app_config, Context.MODE_PRIVATE);
	}

	public Unit getDefaultUnit(String key) {
		SharedPreferences sp = Config.getConfigStorage(context);
		if (key.equals(DEFAULT_LINEAR_UNI)) {
			int i = sp.getInt(key, 0);
			if (i < 0 || i >= linears.length)
				i = 0;
			return linears[i];
		} else if (key.equals(DEFAULT_SQUARE_UNIT)) {
			int i = sp.getInt(key, 0);
			if (i < 0 || i >= squares.length)
				i = 0;
			return squares[i];
		} else if (key.equals(DEFAULT_COORDINATE_UNIT)) {
			int i = sp.getInt(key, 0);
			if (i < 0 || i >= coordinates.length)
				i = 0;
			return coordinates[i];
		} else {
			return null;
		}

	}

}