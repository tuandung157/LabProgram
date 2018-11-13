package chatprogramserver;


import java.text.DateFormat;
import java.text.SimpleDateFormat;



/**
 * java.util.date to java.sql.date
 */
public class DatesConversion {

	public static void main(String[] args) {
		java.util.Date uDate = new java.util.Date();
		System.out.println("Time in java.util.Date is : " + uDate);
		java.sql.Date sDate = convertUtilToSql(uDate);
		System.out.println("Time in java.sql.Date is : " + sDate);
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
		System.out.println("Using a dateFormat date is : " + df.format(uDate));
	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
}