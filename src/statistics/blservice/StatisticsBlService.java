package statistics.blservice;

public interface StatisticsBlService {
	
	public double[] getRangeEstimation(double aver,double s,int n);//单总体均值区间估计      返回数组第一个值是下限，第二个值是上限
	
	public boolean hypothesisTest_z(double newAverage,double oldAverage,double s,int n,int type);//参数假设检验   z检验  条件：方差已知或方差未知但样本容量大
	                                                                    //type为左边检验、右边检验、双边检验。返回值为是否在拒绝域
	public boolean hypothesisTest_x2(double s2,double sigma2,int n,int type);//注意这里传的参数是sigma平方
	
	public boolean rank_sum(double x[],double y[]);
	
}