package Model;

public class Dates {
	public static int monthNumeric(String month){
		if (month.equals("Enero")){
			return 0;
		}
		if (month.equals("Febrero")){
			return 1;
		}
		if (month.equals("Marzo")){
			return 2;
		}
		if (month.equals("Abril")){
			return 3;
		}
		if (month.equals("Mayo")){
			return 4;
		}
		if (month.equals("Junio")){
			return 5;
		}
		if (month.equals("Julio")){
			return 6;
		}
		if (month.equals("Agosto")){
			return 7;
		}
		if (month.equals("Septiembre")){
			return 8;
		}
		if (month.equals("Octubre")){
			return 9;
		}
		if (month.equals("Nomviembre")){
			return 10;
		}
		if (month.equals("Diciembre")){
			return 11;
		}
		return 0;
	}
	public static int DaysOfTheMonth(int month, String year){
		 switch(month){
		  case 0:  // Enero
		  case 2:  // Marzo
		  case 4:  // Mayo
		  case 6:  // Julio
		  case 7:  // Agosto
		  case 9:  // Octubre
		  case 11: // Diciembre
		   return 31;
		  case 3:  // Abril
		  case 5:  // Junio
		  case 8:  // Septiembre
		  case 10: // Noviembre
		   return 30;
		  case 1:  // Febrero
		   if ( ((Integer.parseInt(year)%100 == 0) && (Integer.parseInt(year)%400 == 0)) ||
		        ((Integer.parseInt(year)%100 != 0) && (Integer.parseInt(year)%  4 == 0))   ) {
			   return 29;  // A�o Bisiesto
		   }
		   else 
		    return 28;
		  default:
		 }
		 return -1;
	}
}
