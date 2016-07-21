package com.duongkk.unitconverter.libs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Spinner;

public class ConvertUnitDialog {
	private Spinner sel_linear, sel_linear1, sel_square, sel_coordinate;
	private Context ctx;
	private AlertDialog alert;

	public ConvertUnitDialog(Context ctx) {
		this.ctx = ctx;
	}

//	public AlertDialog create() {
//		if (alert != null)
//			return alert;
//		LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View v = inf.inflate(R.layout.dialog_convert_unit, null);
//		sel_linear = (Spinner) v.findViewById(R.id.sel_linear_unit);
//		sel_square = (Spinner) v.findViewById(R.id.sel_square_unit);
//		sel_coordinate = (Spinner) v.findViewById(R.id.sel_coordinate_unit);
//		ArrayAdapter<Unit> linearAdapter = new ArrayAdapter<Unit>(ctx, android.R.layout.simple_spinner_item,
//				Config.linears);
//		ArrayAdapter<Unit> squareAdapter = new ArrayAdapter<Unit>(ctx, android.R.layout.simple_spinner_item,
//				Config.squares);
//		ArrayAdapter<Unit> coordinateAdapter = new ArrayAdapter<Unit>(ctx, android.R.layout.simple_spinner_item,
//				Config.coordinates);
//		linearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		squareAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		coordinateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sel_linear.setAdapter(linearAdapter);
//		sel_square.setAdapter(squareAdapter);
//		sel_coordinate.setAdapter(coordinateAdapter);
//		SharedPreferences sp = Config.getConfigStorage(ctx);
//		sel_linear.setSelection(sp.getInt(Config.DEFAULT_LINEAR_UNI, 0));
//		sel_square.setSelection(sp.getInt(Config.DEFAULT_SQUARE_UNIT, 0));
//		sel_coordinate.setSelection(sp.getInt(Config.DEFAULT_COORDINATE_UNIT, 0));
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setView(v).setTitle(ctx.getString(R.string.menuitem6)).setPositiveButton(android.R.string.ok,
//				new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						saveSettings();
//						Intent i = new Intent(Config.ACTION_CHANGE);
//						ctx.sendBroadcast(i);
//						dialog.dismiss();
//					}
//				});
//		alert = builder.create();
//		return alert;
//	}

	private void saveSettings() {
		int linear = sel_linear.getSelectedItemPosition();
		int square = sel_square.getSelectedItemPosition();
		int coordinate = sel_coordinate.getSelectedItemPosition();
		SharedPreferences sp = Config.getConfigStorage(ctx);
		sp.edit().putInt(Config.DEFAULT_LINEAR_UNI, linear).putInt(Config.DEFAULT_SQUARE_UNIT, square)
				.putInt(Config.DEFAULT_COORDINATE_UNIT, coordinate).commit();

	}

}