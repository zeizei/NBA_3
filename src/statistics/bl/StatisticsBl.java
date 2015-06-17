package statistics.bl;

import java.util.ArrayList;

import presentation.statics.Method;
import beans.GamePlayer;
import beans.SeasonPlayer;
import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import statistics.blservice.StatisticsBlService;
import statistics.data.StatisticsData;
import statistics.dataservice.StatisticsDataService;

//import org.python.util.PythonInterpreter;   

public class StatisticsBl implements StatisticsBlService {
	StatisticsDataService statisticsDataBl = new StatisticsData();
	static double t_alpha[] = { 0.4, 0.3, 0.2, 0.1, 0.05, 0.025, 0.01, 0.005, 0.0005 };
	static double t[] = { 1, 0.325, 0.727, 1.376, 3.078, 6.314, 12.706, 31.821, 63.657, 636.619, 2, 0.289, 0.617, 1.061, 1.886, 2.920, 4.303, 6.965, 9.925, 31.599, 3, 0.277, 0.584, 0.978, 1.638,
			2.353, 3.182, 4.541, 5.841, 12.924, 4, 0.271, 0.569, 0.941, 1.533, 2.132, 2.776, 3.747, 4.604, 8.610, 5, 0.267, 0.559, 0.920, 1.476, 2.015, 2.571, 3.365, 4.032, 6.869, 6, 0.265, 0.553,
			0.906, 1.440, 1.943, 2.447, 3.143, 3.707, 5.959, 7, 0.263, 0.549, 0.896, 1.415, 1.895, 2.365, 2.998, 3.499, 5.408, 8, 0.262, 0.546, 0.889, 1.397, 1.860, 2.306, 2.896, 3.355, 5.041, 9,
			0.261, 0.543, 0.883, 1.383, 1.833, 2.262, 2.821, 3.250, 4.781, 10, 0.260, 0.542, 0.879, 1.372, 1.812, 2.228, 2.764, 3.169, 4.587, 11, 0.260, 0.540, 0.876, 1.363, 1.796, 2.201, 2.718,
			3.106, 4.437, 12, 0.259, 0.539, 0.873, 1.356, 1.782, 2.179, 2.681, 3.055, 4.318, 13, 0.259, 0.538, 0.870, 1.350, 1.771, 2.160, 2.650, 3.012, 4.221, 14, 0.258, 0.537, 0.868, 1.345, 1.761,
			2.145, 2.624, 2.977, 4.140, 15, 0.258, 0.536, 0.866, 1.341, 1.753, 2.131, 2.602, 2.947, 4.073, 16, 0.258, 0.535, 0.865, 1.337, 1.746, 2.120, 2.583, 2.921, 4.015, 17, 0.257, 0.534, 0.863,
			1.333, 1.740, 2.110, 2.567, 2.898, 3.965, 18, 0.257, 0.534, 0.862, 1.330, 1.734, 2.101, 2.552, 2.878, 3.922, 19, 0.257, 0.533, 0.861, 1.328, 1.729, 2.093, 2.539, 2.861, 3.883, 20, 0.257,
			0.533, 0.860, 1.325, 1.725, 2.086, 2.528, 2.845, 3.850, 21, 0.257, 0.532, 0.859, 1.323, 1.721, 2.080, 2.518, 2.831, 3.819, 22, 0.256, 0.532, 0.858, 1.321, 1.717, 2.074, 2.508, 2.819,
			3.792, 23, 0.256, 0.532, 0.858, 1.319, 1.714, 2.069, 2.500, 2.807, 3.768, 24, 0.256, 0.531, 0.857, 1.318, 1.711, 2.064, 2.492, 2.797, 3.745, 25, 0.256, 0.531, 0.856, 1.316, 1.708, 2.060,
			2.485, 2.787, 3.725, 26, 0.256, 0.531, 0.856, 1.315, 1.706, 2.056, 2.479, 2.779, 3.707, 27, 0.256, 0.531, 0.855, 1.314, 1.703, 2.052, 2.473, 2.771, 3.690, 28, 0.256, 0.530, 0.855, 1.313,
			1.701, 2.048, 2.467, 2.763, 3.674, 29, 0.256, 0.530, 0.854, 1.311, 1.699, 2.045, 2.462, 2.756, 3.659, 30, 0.256, 0.530, 0.854, 1.310, 1.697, 2.042, 2.457, 2.750, 3.646, 40, 0.255, 0.529,
			0.851, 1.303, 1.684, 2.021, 2.423, 2.704, 3.551, 50, 0.255, 0.528, 0.849, 1.299, 1.676, 2.009, 2.403, 2.678, 3.496, 60, 0.254, 0.527, 0.848, 1.296, 1.671, 2.000, 2.390, 2.660, 3.460, 70,
			0.254, 0.527, 0.847, 1.294, 1.667, 1.994, 2.381, 2.648, 3.435, 80, 0.254, 0.526, 0.846, 1.292, 1.664, 1.990, 2.374, 2.639, 3.416, 90, 0.254, 0.526, 0.846, 1.291, 1.662, 1.987, 2.368,
			2.632, 3.402, 100, 0.254, 0.526, 0.845, 1.290, 1.660, 1.984, 2.364, 2.626, 3.390, 110, 0.254, 0.526, 0.845, 1.289, 1.659, 1.982, 2.361, 2.621, 3.381, 120, 0.254, 0.526, 0.845, 1.289,
			1.658, 1.980, 2.358, 2.617, 3.373 };
	static double z[] = { 0.5000, 0.4960, 0.4920, 0.4880, 0.4840, 0.4801, 0.4761, 0.4721, 0.4681, 0.4641, 0.4602, 0.4562, 0.4522, 0.4483, 0.4443, 0.4404, 0.4364, 0.4325, 0.4286, 0.4247, 0.4207,
			0.4168, 0.4129, 0.4090, 0.4052, 0.4013, 0.3974, 0.3936, 0.3897, 0.3859, 0.3821, 0.3783, 0.3745, 0.3707, 0.3669, 0.3632, 0.3594, 0.3557, 0.3520, 0.3483, 0.3446, 0.3409, 0.3372, 0.3336,
			0.3300, 0.3264, 0.3228, 0.3192, 0.3156, 0.3121, 0.3085, 0.3050, 0.3015, 0.2981, 0.2946, 0.2912, 0.2877, 0.2843, 0.2810, 0.2776, 0.2743, 0.2709, 0.2676, 0.2643, 0.2611, 0.2578, 0.2546,
			0.2514, 0.2483, 0.2451, 0.2420, 0.2389, 0.2358, 0.2327, 0.2296, 0.2266, 0.2236, 0.2206, 0.2177, 0.2148, 0.2119, 0.2090, 0.2061, 0.2033, 0.2005, 0.1977, 0.1949, 0.1922, 0.1894, 0.1867,
			0.1841, 0.1814, 0.1788, 0.1762, 0.1736, 0.1711, 0.1685, 0.1660, 0.1635, 0.1611, 0.1587, 0.1562, 0.1539, 0.1515, 0.1492, 0.1469, 0.1446, 0.1423, 0.1401, 0.1379, 0.1357, 0.1335, 0.1314,
			0.1292, 0.1271, 0.1251, 0.1230, 0.1210, 0.1190, 0.1170, 0.1151, 0.1131, 0.1112, 0.1093, 0.1075, 0.1056, 0.1038, 0.1020, 0.1003, 0.0985, 0.0968, 0.0951, 0.0934, 0.0918, 0.0901, 0.0885,
			0.0869, 0.0853, 0.0838, 0.0823, 0.0808, 0.0793, 0.0778, 0.0764, 0.0749, 0.0735, 0.0721, 0.0708, 0.0694, 0.0681, 0.0668, 0.0655, 0.0643, 0.0630, 0.0618, 0.0606, 0.0594, 0.0582, 0.0571,
			0.0559, 0.0548, 0.0537, 0.0526, 0.0516, 0.0505, 0.0495, 0.0485, 0.0475, 0.0465, 0.0455, 0.0446, 0.0436, 0.0427, 0.0418, 0.0409, 0.0401, 0.0392, 0.0384, 0.0375, 0.0367, 0.0359, 0.0351,
			0.0344, 0.0336, 0.0329, 0.0322, 0.0314, 0.0307, 0.0301, 0.0294, 0.0287, 0.0281, 0.0274, 0.0268, 0.0262, 0.0256, 0.0250, 0.0244, 0.0239, 0.0233, 0.0228, 0.0222, 0.0217, 0.0212, 0.0207,
			0.0202, 0.0197, 0.0192, 0.0188, 0.0183, 0.0179, 0.0174, 0.0170, 0.0166, 0.0162, 0.0158, 0.0154, 0.0150, 0.0146, 0.0143, 0.0139, 0.0136, 0.0132, 0.0129, 0.0125, 0.0122, 0.0119, 0.0116,
			0.0113, 0.0110, 0.0107, 0.0104, 0.0102, 0.0099, 0.0096, 0.0094, 0.0091, 0.0089, 0.0087, 0.0084, 0.0082, 0.0080, 0.0078, 0.0075, 0.0073, 0.0071, 0.0069, 0.0068, 0.0066, 0.0064, 0.0062,
			0.0060, 0.0059, 0.0057, 0.0055, 0.0054, 0.0052, 0.0051, 0.0049, 0.0048, 0.0047, 0.0045, 0.0044, 0.0043, 0.0041, 0.0040, 0.0039, 0.0038, 0.0037, 0.0036, };
	double x2_alpha[] = { 0.995, 0.990, 0.975, 0.950, 0.900, 0.750, 0.250, 0.100, 0.050, 0.025, 0.010, 0.005 };
	double x2[] = { 1, 0.000, 0.0002, 0.0010, 0.0039, 0.016, 0.102, 1.323, 2.706, 3.841, 5.024, 6.635, 7.879, 2, 0.010, 0.020, 0.051, 0.103, 0.211, 0.575, 2.773, 4.605, 5.991, 7.378, 9.210, 10.597,
			3, 0.072, 0.115, 0.216, 0.352, 0.584, 1.213, 4.108, 6.251, 7.815, 9.348, 11.345, 12.838, 4, 0.207, 0.297, 0.484, 0.711, 1.064, 1.923, 5.385, 7.779, 9.488, 11.143, 13.277, 14.860, 5,
			0.412, 0.554, 0.831, 1.145, 1.610, 2.675, 6.626, 9.236, 11.070, 12.833, 15.086, 16.750, 6, 0.676, 0.872, 1.237, 1.635, 2.204, 3.455, 7.841, 10.645, 12.592, 14.449, 16.812, 18.548, 7,
			0.989, 1.239, 1.690, 2.167, 2.833, 4.255, 9.037, 12.017, 14.067, 16.013, 18.475, 20.278, 8, 1.344, 1.646, 2.180, 2.733, 3.490, 5.071, 10.219, 13.362, 15.507, 17.535, 20.090, 21.955, 9,
			1.735, 2.088, 2.700, 3.325, 4.168, 5.899, 11.389, 14.684, 16.919, 19.023, 21.666, 23.589, 10, 2.156, 2.558, 3.247, 3.940, 4.865, 6.737, 12.549, 15.987, 18.307, 20.483, 23.209, 25.188, 11,
			2.603, 3.053, 3.816, 4.575, 5.578, 7.584, 13.701, 17.275, 19.675, 21.920, 24.725, 26.757, 12, 3.074, 3.571, 4.404, 5.226, 6.304, 8.438, 14.845, 18.549, 21.026, 23.337, 26.217, 28.300, 13,
			3.565, 4.107, 5.009, 5.892, 7.042, 9.299, 15.984, 19.812, 22.362, 24.736, 27.688, 29.819, 14, 4.075, 4.660, 5.629, 6.571, 7.790, 10.165, 17.117, 21.064, 23.685, 26.119, 29.141, 31.319,
			15, 4.601, 5.229, 6.262, 7.261, 8.547, 11.037, 18.245, 22.307, 24.996, 27.488, 30.578, 32.801, 16, 5.142, 5.812, 6.908, 7.962, 9.312, 11.912, 19.369, 23.542, 26.296, 28.845, 32.000,
			34.267, 17, 5.697, 6.408, 7.564, 8.672, 10.085, 12.792, 20.489, 24.769, 27.587, 30.191, 33.409, 35.718, 18, 6.265, 7.015, 8.231, 9.390, 10.865, 13.675, 21.605, 25.989, 28.869, 31.526,
			34.805, 37.156, 19, 6.844, 7.633, 8.907, 10.117, 11.651, 14.562, 22.718, 27.204, 30.144, 32.852, 36.191, 38.582, 20, 7.434, 8.260, 9.591, 10.851, 12.443, 15.452, 23.828, 28.412, 31.410,
			34.170, 37.566, 39.997, 21, 8.034, 8.897, 10.283, 11.591, 13.240, 16.344, 24.935, 29.615, 32.671, 35.479, 38.932, 41.401, 22, 8.643, 9.542, 10.982, 12.338, 14.041, 17.240, 26.039, 30.813,
			33.924, 36.781, 40.289, 42.796, 23, 9.260, 10.196, 11.689, 13.091, 14.848, 18.137, 27.141, 32.007, 35.172, 38.076, 41.638, 44.181, 24, 9.886, 10.856, 12.401, 13.848, 15.659, 19.037,
			28.241, 33.196, 36.415, 39.364, 42.980, 45.559, 25, 10.520, 11.524, 13.120, 14.611, 16.473, 19.939, 29.339, 34.382, 37.652, 40.646, 44.314, 46.928, 26, 11.160, 12.198, 13.844, 15.379,
			17.292, 20.843, 30.435, 35.563, 38.885, 41.923, 45.642, 48.290, 27, 11.808, 12.879, 14.573, 16.151, 18.114, 21.749, 31.528, 36.741, 40.113, 43.195, 46.963, 49.645, 28, 12.461, 13.565,
			15.308, 16.928, 18.939, 22.657, 32.620, 37.916, 41.337, 44.461, 48.278, 50.993, 29, 13.121, 14.256, 16.047, 17.708, 19.768, 23.567, 33.711, 39.087, 42.557, 45.722, 49.588, 52.336, 30,
			13.787, 14.953, 16.791, 18.493, 20.599, 24.478, 34.800, 40.256, 43.773, 46.979, 50.892, 53.672, 40, 20.707, 22.164, 24.433, 26.509, 29.051, 33.660, 45.616, 51.805, 55.758, 59.342, 63.691,
			66.766, 50, 27.991, 29.707, 32.357, 34.764, 37.689, 42.942, 56.334, 63.167, 67.505, 71.420, 76.154, 79.490, 60, 35.534, 37.485, 40.482, 43.188, 46.459, 52.294, 66.981, 74.397, 79.082,
			83.298, 88.379, 91.952, 70, 43.275, 45.442, 48.758, 51.739, 55.329, 61.698, 77.577, 85.527, 90.531, 95.023, 100.425, 104.215, 80, 51.172, 53.540, 57.153, 60.391, 64.278, 71.145, 88.130,
			96.578, 101.879, 106.629, 112.329, 116.321, 90, 59.196, 61.754, 65.647, 69.126, 73.291, 80.625, 98.650, 107.565, 113.145, 118.136, 124.116, 128.299, 100, 67.328, 70.065, 74.222, 77.929,
			82.358, 90.133, 109.141, 118.498, 124.342, 129.561, 135.807, 140.169,

	};

	// double t_0025[]={
	// 12.7062, 4.3027, 3.1824, 2.7764, 2.5706, 2.4469,
	// 2.3646, 2.3060, 2.2622, 2.2281, 2.2010, 2.1788, 2.1604, 2.1448,
	// 2.1314, 2.1199, 2.1098, 2.1009, 2.0930, 2.0860, 2.0796, 2.0739,
	// 2.0687, 2.0639, 2.0595, 2.0555, 2.0518, 2.0484, 2.0452, 2.0423,
	// 2.0395, 2.0369, 2.0345, 2.0322, 2.0301, 2.0281, 2.0262, 2.0244,
	// 2.0227, 2.0211, 2.0195, 2.0181, 2.0167, 2.0154, 2.0141, 2.0129,
	// 2.0117, 2.0106, 2.0096, 2.0086, 2.0076, 2.0066, 2.0057, 2.0049,
	// 2.0040, 2.0032, 2.0025, 2.0017, 2.0010, 2.0003, 1.9996, 1.9990,
	// 1.9983, 1.9977, 1.9971, 1.9966, 1.9960, 1.9955, 1.9949, 1.9944,
	// 1.9939, 1.9935, 1.9930, 1.9925, 1.9921, 1.9917, 1.9913, 1.9908,
	// 1.9905, 1.9901 };
	public boolean rank_sum(double[] x, double[] y, double alpha) {
		return false;
	}//

	public double[] getRangeEstimation(double average, double s, int n, double alpha) {
		double result[] = new double[2];
		double std_sigma = s;

		result[0] = average - std_sigma * t_value(n - 1, alpha) / Math.sqrt(n);
		result[1] = average + std_sigma * t_value(n - 1, alpha) / Math.sqrt(n);
		return result;
	}

	public boolean hypothesisTest_z(double newAverage, double oldAverage, double s, int n, int type, double alpha) {
		double z = (newAverage - oldAverage) / (s / Math.sqrt(n));
		System.out.print(z);
		if (type == 0) {// 左边检验
			if (z <= -z_value(alpha)) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (type == 1) {// 右边检验
			if (z >= z_value(alpha)) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (type == 2) {// 双边检验
			if (Math.abs(z) >= z_value(alpha / 2)) {
				return false;
			}
			else {
				return true;
			}
		}
		System.out.print("NO SUCH TYPE!");
		return false;
	}

	public boolean hypothesisTest_x2(double s2, double sigma2, int n, int type, double alpha) {
		double x2 = ((n - 1) * s2) / sigma2;
		if (type == 0) {// 左边检验
			double x2_point = x2_value(n - 1, 1 - alpha);
			if (x2 <= x2_point) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (type == 1) {// 右边检验
			double x2_point = x2_value(n - 1, alpha);
			if (x2 >= x2_point) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (type == 2) {
			double x2_point_up = x2_value(n - 1, alpha / 2);
			double x2_point_down = x2_value(n - 1, 1 - alpha / 2);
			// System.out.println(x2_point_down);
			// System.out.println(x2_point_up);
			if (x2 >= x2_point_up || x2 <= x2_point_down) {
				return false;
			}
			else {
				return true;
			}
		}
		System.out.print("NO SUCH TYPE!");
		return false;
	}

	private double x2_value(int num, double a) {
		boolean isExistNum = false;
		boolean isExistAlpha = false;
		int row = 0;
		int col = 0;
		for (int i = 0; i < x2.length; i += 13) {
			if (num == x2[i]) {
				isExistNum = true;
				break;
			}
			row++;
		}
		if (!isExistNum) {
			System.out.println("No Such Num");
			System.exit(0);
		}
		for (int i = 0; i < x2_alpha.length; i++) {
			if (a == x2_alpha[i]) {
				isExistAlpha = true;
				break;
			}
			col++;
		}
		if (!isExistAlpha) {
			System.out.println("No Such alpha");
			System.exit(0);
		}
		col++;
		return x2[13 * row + col];
	}

	private double z_value(double a) {

		for (int i = 0; i < z.length; i++) {
			if (z[i] >= a && z[i + 1] <= a) {
				return (i / 10) * 0.1 + (i % 10) * 0.01 + 0.005;
			}
		}
		System.out.println("No Such alpha");
		return 0.0;
	}

	private double t_value(int number, double a) {
		int col = -1;
		for (int i = 0; i < t_alpha.length; i++) {
			if (a == t_alpha[i]) {
				col = i;
				break;
			}
		}
		if (col == -1) {
			System.out.println("No Such alpha");
			return 0;
		}
		else {
			for (int i = 0; i < t.length; i += 10) {
				if (t[i] == number) {
					return t[i + col + 1];
				}
			}
			return 0;
		}

	}

	// public static void main(String args[]){
	// StatisticsBl s=new StatisticsBl();
	// System.out.print(s.t_value(20,0.005));
	// }
	//
	public ArrayList<GamePlayer> getRegularSeasonPlayersGames(String playerID) {
		return statisticsDataBl.getPlayersGames(playerID, 0);
	}

	public ArrayList<GamePlayer> getPlayOffSeasonPlayersGames(String playerID) {
		return statisticsDataBl.getPlayersGames(playerID, 1);
	}

	public SeasonPlayer getRegularSeasonPlayer(String playerID) {
		return statisticsDataBl.getSeasonPlayer(playerID, 0);
	}

	public SeasonPlayer getPlayOffSeasonPlayer(String playerID) {
		return statisticsDataBl.getSeasonPlayer(playerID, 1);
	}

	public ArrayList<PlayerOnOrOff> getRegularSeasonCourtPerform(String playerID) {
		return statisticsDataBl.getCourtPerform(playerID, 0);
	}

	public ArrayList<PlayerOnOrOff> getPlayOffSeasonCourtPerform(String playerID) {
		return statisticsDataBl.getCourtPerform(playerID, 1);
	}

	public ArrayList<PlayerShootDistance> getRegularSeasonDistanceShoot(String playerID) {
		return statisticsDataBl.getDistanceShoot(playerID, 0);
	}

	public ArrayList<PlayerShootDistance> getPlayOffSeasonDistanceShoot(String playerID) {
		return statisticsDataBl.getDistanceShoot(playerID, 1);
	}

	public ArrayList<PlayerShootOpponent> getRegularSeasonOpponentShoot(String playerID) {
		return statisticsDataBl.getOpponentShoot(playerID, 0);
	}

	public ArrayList<PlayerShootOpponent> getPlayOffSeasonOpponentShoot(String playerID) {
		return statisticsDataBl.getOpponentShoot(playerID, 1);
	}

	public ArrayList<PlayerShootType> getRegularSeasonShootType(String playerID) {
		return statisticsDataBl.getShootType(playerID, 0);
	}

	public ArrayList<PlayerShootType> getPlayOffSeasonShootType(String playerID) {
		return statisticsDataBl.getShootType(playerID, 1);
	}

	public boolean isBetterThanRegular(String playerID, String field) {
		ArrayList<GamePlayer> playOffMatchList =getPlayOffSeasonPlayersGames(playerID);
		 ArrayList<GamePlayer> regularMatchList =getRegularSeasonPlayersGames(playerID);
		String[] field1 = { field };
		double sum = 0;
		double sum_regular=0;
		for (int i = 0; i < regularMatchList.size(); i++) {
			Object[] o = regularMatchList.get(i).getSpeContent(field1);
			sum_regular += (double) o[0];
		}
		for (int i = 0; i < playOffMatchList.size(); i++) {
			Object[] o = playOffMatchList.get(i).getSpeContent(field1);
			sum += (double) o[0];
		}
		double aver = sum / playOffMatchList.size();
		double aver_regular =sum_regular /regularMatchList.size();
		double s = 0;
		for (int i = 0; i < playOffMatchList.size(); i++) {
			s = s + (playOffMatchList.get(i).getShotEFF() - aver) * (playOffMatchList.get(i).getShotEFF() - aver);
		}
		s = Math.sqrt(s)/(playOffMatchList.size()-1);
		boolean isOut = hypothesisTest_z(aver,aver_regular, s, playOffMatchList.size(), 1, 0.025);
		return isOut;
	}

	@Override
	public double[] forcast(String playerID, String field) {
		ArrayList<GamePlayer> list = getRegularSeasonPlayersGames(playerID);
		String[] field1 = { field };
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Object[] o = list.get(i).getSpeContent(field1);
			sum += (double) o[0];
		}
		double aver = sum / list.size();
		double s = 0;
		for (int i = 0; i < list.size(); i++) {
			s = s + (list.get(i).getShotEFF() - aver) * (list.get(i).getShotEFF() - aver);
		}
		s = Math.sqrt(s)/(list.size()-1);
		double result[] = getRangeEstimation(aver, s,81, 0.025);
		result[0] = Method.cutTail(result[0]);
		result[1] = Method.cutTail(result[1]);
		System.out.println();
		System.out.println(result[0]+" "+result[1]);
		return result;
	}

	public ArrayList<SeasonPlayer> vagueSearchPlayer(String str) {
		return statisticsDataBl.vagueSearchPlayer(str);
	}

	public ArrayList<SeasonPlayer> getPlayOffPlayer() {
		return statisticsDataBl.getPlayOffPlayer();
	}
}
