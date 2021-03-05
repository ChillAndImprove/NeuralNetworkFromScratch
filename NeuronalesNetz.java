import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NeuronalesNetz
{
	double[][]				arr;
	final Aktivierungsfkt[]	a;
	final Error				err;
	double					lernrate;

	public NeuronalesNetz (Aktivierungsfkt[] akti, Error err, double lernrate, int... a) {
		initArray(a);
		this.a			= akti;
		this.err		= err;
		this.lernrate	= lernrate;
	}

	public int denke(int[] input)
	{
		// For über die Schichten
		for (int h = 0; h < arr.length - 2; h += 2) {
			// For über die Neuronen
			for (int m = 0; m < arr[h + 2].length; m++) {
				arr[h + 2][m] = 0;
			}
		}
		// Kopiere input rueber
		for (int i = 0; i < input.length; i++) {
			arr[0][i] = input[i];
		}
		// For über die Schichten
		for (int h = 0; h < arr.length - 2; h += 2) {
			// For über die Neuronen
			for (int m = 0; m < arr[h + 2].length; m++) {
				// For über die Gewichte
				for (int l = 0; l < arr[h].length; l++) {
					arr[h + 2][m] += arr[h][l] * arr[h + 1][l + m * arr[h].length];
				}
				arr[h + 2][m] = a[h >> 1].aktiv(arr[h + 2][m]);
			}
		}
		// MNIST
		int index = 0;
		double test = arr[arr.length - 2][0];
		for (int i = 0; i < arr[arr.length - 2].length; i++) {
			if (arr[arr.length - 2][i] > test) {
				index	= i;
				test	= arr[arr.length - 2][i];
			}
		}
		return index;
	}

	public double denkeXor(int[] input)
	{
		// For über die Schichten
		for (int h = 0; h < arr.length - 2; h += 2) {
			// For über die Neuronen
			for (int m = 0; m < arr[h + 2].length; m++) {
				arr[h + 2][m] = 0;
			}
		}
		// Kopiere input rueber
		for (int i = 0; i < input.length; i++) {
			arr[0][i] = input[i];
		}
		// For über die Schichten
		for (int h = 0; h < arr.length - 2; h += 2) {
			// For über die Neuronen
			for (int m = 0; m < arr[h + 2].length; m++) {
				// For über die Gewichte
				for (int l = 0; l < arr[h].length; l++) {
					arr[h + 2][m] += arr[h][l] * arr[h + 1][l + m * arr[h].length];
				}
				arr[h + 2][m] = a[h >> 1].aktiv(arr[h + 2][m]);
			}
		}
		return arr[arr.length - 2][0];
	}

	public void lerne(double[] klasse)
	{
		// For ueber die Schichten
		for (int i = 0; i < klasse.length; i++) {
			arr[arr.length - 1][i] = err.errorA(arr[arr.length - 2][i], klasse[i]);
		}
		// Einmal, wegen Error
		int htmp = arr.length - 2;
		for (int m = 0; m < arr[htmp - 2].length; m++) {
			double[] tmp = new double[arr[htmp].length];
			// double[] tmp2 = new double[arr[htmp].length];
			for (int l = 0; l < arr[htmp].length; l++) {
				// um die Position von dem richtigem Gewicht festzustellen: l + m * arr[h].length
				// Error *
				tmp[l] = a[0].aktivA(arr[htmp - 2][m]) * (a[0].aktivA(arr[htmp][l]) * arr[htmp + 1][l]) * arr[htmp - 1][m + l * arr[htmp - 2].length];
				// Gewichten
				// __________________________________________________________ableitung der aktivfkt_i-Schicht__________ableitung error
				arr[htmp - 1][m + l * arr[htmp - 2].length] -= (a[0].aktivA(arr[htmp][l]) * arr[htmp - 2][m] * arr[htmp + 1][l] * lernrate);
			}
			// FIXME: In Tmp speichern, dann kann man direkt in der oberen Forschleife machen
			arr[htmp - 2][m] = 0;
			for (int l = 0; l < arr[htmp].length; l++) {
				arr[htmp - 2][m] += tmp[l];
			}
		}
		// weitere Schichten
		for (int h = arr.length - 4; h > 0; h -= 2) {
			for (int m = 0; m < arr[h - 2].length; m++) {
				double[] tmp = new double[arr[h].length];
				for (int l = 0; l < arr[h].length; l++) {
					// um die Position von dem richtigem Gewicht festzustellen: l + m * arr[h].length
					tmp[l] = a[0].aktivA(arr[h - 2][m]) * (arr[h][l]) * arr[h - 1][m + l * arr[h - 2].length];
					// Gewichten
					arr[h - 1][m + l * arr[h - 2].length] -= (arr[h - 2][m] * arr[h][l] * lernrate);
				}
				// FIXME: In Tmp speichern, dann kann man direkt in der oberen Forschleife machen
				arr[h - 2][m] = 0;
				for (int l = 0; l < arr[h].length; l++) {
					arr[h - 2][m] = arr[h - 2][m] + tmp[l];
				}
			}
		}
	}

	public void initArray(int... a)
	{
		// Schichten
		arr = new double[a.length * 2][];
		// extra Zaehler fuer das Uebergebene Array
		int var = 0;
		// Maximale anzahl an den Knoten von dem uebergebenem Array
		int max = Integer.MIN_VALUE;
		// arr mit Knotenschichten befuehlen
		double tmp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			// Gewichtenschicht
			if (i % 2 == 1) {
				arr[i] = new double[a[var] * a[var - 1]];
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = Math.random() - 0.5;
				}
			}
			// Knotenschicht
			else {
				// ueberpruefe ob die groesste Schicht
				if (max < a[var]) {
					max = var;
				}
				arr[i] = new double[a[var++]];
			}
		}
		// Backpropagationsschicht
		arr[arr.length - 1] = new double[arr[arr.length - 2].length];
	}

	/*
	 * TODO: Forward Progation DONE! TODO: Backpropagaton TODO: RELU
	 */
	public static void main(String[] args)
	{
		Aktivierungsfkt aktiv1 = new Aktivierungsfkt() {
			@Override
			public double aktivA(double t)
			{
				if (t <= 0)
					return 0;
				else
					return 1;
			}

			@Override
			public double aktiv(double t)
			{
				if (t <= 0)
					return 0;
				else
					return t;
			}
		};
		Aktivierungsfkt aktiv1weird = new Aktivierungsfkt() {
			@Override
			public double aktivA(double t)
			{
				return 1;
			}

			@Override
			public double aktiv(double t)
			{
				if (t > 0)
					return t;
				else
					return 0;
			}
		};
		Aktivierungsfkt tan = new Aktivierungsfkt() {
			@Override
			public double aktivA(double t)
			{
				// die Ableitung wäre normalerweise: 1 - Math.tanh(t)^2
				// aber da man die t als mit tangens behandelte Variable speichert, so sieht wie folgt aus:
				// return 1 - (t * t);
				return t - (t * t);
			}

			@Override
			public double aktiv(double t)
			{
				// return Math.tanh(t);
				return 1.0 / (1.0 + Math.exp(-t));
			}
		};
		Aktivierungsfkt sigmoid = new Aktivierungsfkt() {
			@Override
			public double aktivA(double t)
			{
				return t * (1.0 - t);
			}

			@Override
			public double aktiv(double t)
			{
				return 1.0 / (1.0 + Math.exp(-t));
			}
		};
		Error errFnkt = new Error() {
			@Override
			public double errorA(double x, double soll)
			{
				return x - soll;
			}

			@Override
			public double error(double x, double soll)
			{
				return Math.pow(x - soll, 2) / 2;
			}
		};
		Aktivierungsfkt[] aktivierungsFnkt = new Aktivierungsfkt[2];
		aktivierungsFnkt[0]	= aktiv1weird;
		aktivierungsFnkt[1]	= sigmoid;
		NeuronalesNetz n = new NeuronalesNetz(aktivierungsFnkt, errFnkt, 0.1, new int[] { 2, 2, 1 });
		n.arr[1][0]	= 1;
		n.arr[1][1]	= 2;
		n.arr[1][2]	= 1;
		n.arr[1][3]	= 2;
		n.arr[3][0]	= 1;
		n.arr[3][1]	= 2;
		/*
		 * double[] klasse = new double[] { 1.0 }; for (int i = 0; i < 10000; i++) { System.out.println("0 ^ 0"); System.out.println(n.denkeXor(new int[] { 0, 0 })); n.lerne(new double[] { 0 }); System.out.println();
		 * System.out.println("1 ^ 0"); System.out.println(n.denkeXor(new int[] { 1, 0 })); n.lerne(klasse); System.out.println("0 ^ 1"); System.out.println(n.denkeXor(new int[] { 0, 1 })); n.lerne(klasse);
		 * System.out.println("1 ^ 1 "); System.out.println(n.denkeXor(new int[] { 1, 1 })); n.lerne(new double[] { 0 }); } System.out.println(); System.out.println("Neuro");
		 */
		NeuronalesNetz net = new NeuronalesNetz(aktivierungsFnkt, errFnkt, 0.000008, new int[] { 785, 200, 10 });
		for (int k = 0; k < 1; k++) {
			int cnt = 0;
			int correct = 0;
			try {
				FileReader filereader = new FileReader(new File("mnist_train.txt"));
				BufferedReader reader = new BufferedReader(filereader);
				String line;
				double[] t = new double[10];
				while ((line = reader.readLine()) != null) {
					String[] values = line.split(",");
					int[] iA_bild = new int[values.length - 1];
					// viaPix.add(intValues);
					int sol = Integer.parseInt(values[0]);
					for (int i = 1; i < values.length; i++) {
						try {
							iA_bild[i - 1] = ((Integer.parseInt(values[i]))) + 1;
						}
						catch (NumberFormatException nfe) {
							continue;
						}
					}
					for (int i = 0; i < 10; i++) {
						if (i == sol) {
							// t[i] = 1.00;
							t[i] = 0.99;
						}
						else {
							// t[i] = 0.00;
							t[i] = 0.01;
						}
					}
					int gedacht = net.denke(iA_bild);
					System.out.println(k + ":" + correct + "/" + cnt++ + " " + gedacht + " " + sol);
					if (gedacht != sol) {
						net.lerne(t);
					}
					else {
						correct++;
					}
				}
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		int iright = 0;
		int ic2 = 0;
		try {
			FileReader filereader = new FileReader(new File("mnist_test.txt"));
			BufferedReader reader = new BufferedReader(filereader);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				// System.out.println(ic2++);
				int[] intValues = new int[values.length];
				// viaPix.add(intValues);
				int sol = Integer.parseInt(values[0]);
				for (int i = 1; i < values.length; i++) {
					try {
						intValues[i - 1] = Integer.parseInt(values[i]);
					}
					catch (NumberFormatException nfe) {
						continue;
					}
				}
				int gedacht = net.denke(intValues);
				if (gedacht != sol) {
				}
				else
					iright++;
				System.out.println((ic2 - iright) + " " + iright + "/" + ic2++);
			}
			System.out.println("Genauigkeit: " + Integer.valueOf(iright / 100) + " %");
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
