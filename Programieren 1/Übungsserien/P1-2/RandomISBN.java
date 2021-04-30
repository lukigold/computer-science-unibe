//Lukas Ingold 20-123-998
import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN
{

	public static void main( String args[] )
	{
		
		System.out.println("1st ISBN:" + makeISBN());
		System.out.println("2nd ISBN:" + makeISBN());
		System.out.println("3rd ISBN:" + makeISBN());
	}

	/** generates and returns a random ISBN in the format XxX-XXX-XX-C */
	public static String makeISBN()
	{
		// Do NOT change the declaration of the following variables!
		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;


		
		Random rnd = new Random();
		
        String pattern = "00.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        
        
        laendercode = decimalFormat.format(1 + rnd.nextInt(99));

        bandnr = decimalFormat.format(100+ rnd.nextInt(900));
        
        verlagsnr = decimalFormat.format(1 + rnd.nextInt(99));
        
        int l1 = Integer.parseInt(laendercode.substring(0, 1));
        int l2 = Integer.parseInt(laendercode.substring(1, 2));
        int b1 = Integer.parseInt(bandnr.substring(0, 1));
        int b2 = Integer.parseInt(bandnr.substring(1, 2));
        int b3 = Integer.parseInt(bandnr.substring(2, 3));
        int v1 = Integer.parseInt(verlagsnr.substring(0, 1));
        int v2 = Integer.parseInt(verlagsnr.substring(1, 2));
        
        checksum = Integer.toString((hashOp(l1)+l2+hashOp(b1)+b2+hashOp(b3)+v1+hashOp(v2)) % 10);
        
        

		// Do not change the following line
		return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i)
	{
		// Do NOT change this method!
		int doubled = 2 * i;
		if (doubled >= 10) {
			doubled = doubled - 9;
		}
		return doubled;
	}
}