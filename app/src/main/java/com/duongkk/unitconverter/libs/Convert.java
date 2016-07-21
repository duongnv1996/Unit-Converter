package com.duongkk.unitconverter.libs;

import com.duongkk.unitconverter.utils.Constants;
import com.duongkk.unitconverter.utils.DataStore;

import java.text.DecimalFormat;
import java.util.Locale;

public class Convert {
	public static double convertUnit(double value, Unit unit) {
		return value / unit.getN();
	}
	public static double convertUnit(double value, Unit from, Unit to) {
		return value * from.getN() / to.getN();
	}

	public static String dinhDangData(double n, Unit u) {
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
		if (u.getN() <= 1) {
			//df.setMaximumFractionDigits(0);
		} else {
			df.setMaximumFractionDigits(DataStore.getInstance().getInt(Constants.MAX_DECIMA,6));
			df.setMinimumFractionDigits(DataStore.getInstance().getInt(Constants.MAX_DECIMA,6));
		}
		
		if(u.equals(Config.coordinates[0])){
			
		}
		return df.format(n);
	}
	// PHAM XUAN TRUONG
	/*
	 * Chu y: du lieu dau vao la toa do decimal -> goc.
	 * khong su dung cho viec doi tu goc -> decimal.
	 */

	public static String convertFromDegreeMinuteSecToDegreeMinute(double degree,double min,double sec){
		String mdegree = String.valueOf(degree);
		String mMin =  String.valueOf(min + (sec/60));
		return mdegree+"°"+mMin+"'";

	}
	public static String convertFromDegreeMinuteToDegreeDec(double degree,double min){
		return String.valueOf(degree+(min/60))+"°";
	}
	public static String convertFromDegreeMinuteToDegreeMinuteSec(double degree,double min){
		return degree+"°"+min+"'"+00+"''";
	}

	public static String convertCoordinateUnit(double decimal, Unit u) {

		String result = "";
		if (u.equals(Config.coordinates[0])) {
			if (decimal >= 0) {
				int doGoc = (int) decimal;
				double giayTotal = (double) (decimal - doGoc) * 60 * 60;
				int phut = (int) ((double) (decimal - doGoc) * 60);
				double giay = giayTotal - phut * 60.0;
				if (giay + 0.01 >= 60) {
					giay = 0;
					phut++;
				}
				if (phut == 60) {
					phut = 0;
					doGoc++;
				}
				//giay=(double) ((double) Math.round(giay*100)/100);
				// Round(giay, 2)
				
				result+=doGoc+ "\u00B0" + phut + "'" +Round(giay,DataStore.getInstance().getInt(Constants.MAX_DECIMA,6))+"\"";
				return result;

			} else {
				decimal = -1 * decimal;
				int doGoc = (int) decimal;
				double giayTotal = (double) (decimal - doGoc) * 60 * 60;
				int phut = (int) ((double) (decimal - doGoc) * 60);
				double giay = giayTotal - phut * 60.0;
				if (giay + 0.01 >= 60) {
					giay = 0;
					phut++;
				}
				if (phut == 60) {
					phut = 0;
					doGoc++;
				}
				result+=doGoc+ "\u00B0" + phut + "'" +Round(giay,DataStore.getInstance().getInt(Constants.MAX_DECIMA,6))+"\"";
				return result;
			}
		} else if (u.equals(Config.coordinates[1])) {
			if (decimal >= 0) {
				int doGoc = (int) decimal;
				double giayTotal = (double) (decimal - doGoc) * 60 * 60;
				double phut =  ((double) (decimal - doGoc) * 60);
				double giay = giayTotal - phut * 60.0;
				if (giay + 0.01 >= 60) {
					giay = 0;
					phut++;
				}
				if (phut == 60) {
					phut = 0;
					doGoc++;
				}
				result+= doGoc +"\u00B0"+Round(phut,DataStore.getInstance().getInt(Constants.MAX_DECIMA,6))+"'";
				return result;
			} else {
				decimal = -1 * decimal;
				int doGoc = (int) decimal;
				double giayTotal = (double) (decimal - doGoc) * 60 * 60;
				double phut =  ((double) (decimal - doGoc) * 60);
				double giay = giayTotal - phut * 60.0;
				//giay=(double)giay/60;
				if (giay + 0.01 >= 60) {
					giay = 0;
					phut++;
				}
				if (phut == 60) {
					phut = 0;
					doGoc++;
				}
				result+= doGoc +"\u00B0"+Round(phut,DataStore.getInstance().getInt(Constants.MAX_DECIMA,6))+"'";
				return result;
			}
		} else {
			return Round(decimal, DataStore.getInstance().getInt(Constants.MAX_DECIMA,6))+"";
		}

	}
	
	
	//
	// public static float convertUnit(GocDTO goc) {
	// return (float) goc.getDoGoc() + (float) goc.getPhut() / 60 + (float)
	// goc.getGiay() / 3600;
	// }

	public static double Round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		
		return (double) tmp / p;
	}


	public static String convertRadianToDDMMSS (double goc){
		double dogoc = goc*180/Math.PI;
		int d = (int)dogoc;
		int p = (int)((dogoc - d)*60);
		double giay = dogoc*3600 - (d*3600 + p*60);
		giay =  Round(giay,2);
		return d+"°"+p+"'"+giay+"''";

	}
//	public static GocDTO convertDecimalToGoc(double decimal){
//		if(decimal >=0){
//			int doGoc = (int)decimal;
//			double giayTotal = (double)(decimal - doGoc)* 60 * 60;
//			int phut = (int)((double)(decimal - doGoc)* 60);
//			double giay = giayTotal - phut * 60.0;
//			if(giay + 0.01 >= 60){
//				giay =0;
//				phut ++;
//			}
//			if(phut == 60){
//				phut = 0;
//				doGoc++;
//			}
//			return new GocDTO(doGoc, phut, giay);
//		}else{
//			decimal = -1 * decimal;
//			int doGoc = (int)decimal;
//			double giayTotal = (double)(decimal - doGoc)* 60 * 60;
//			int phut = (int)((double)(decimal - doGoc)* 60);
//			double giay = giayTotal - phut * 60.0;
//			if(giay + 0.01 >= 60){
//				giay =0;
//				phut ++;
//			}
//			if(phut == 60){
//				phut = 0;
//				doGoc++;
//			}
//			return new GocDTO(-1 * doGoc, phut, giay);
//		}
//
//	}
}
