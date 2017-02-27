package cpes01;

import java.util.HashMap;
import java.util.Map;

public class Test7 { 

	/* 次数      花费       总成本         奖金      盈利
	 * 1 ==>   2 ==>   2 ==>   5 ==> 3
	 * 2 ==>   4 ==>   6 ==>  10 ==> 4
	 * 3 ==>   8 ==>  14 ==>  20 ==> 6
	 * 4 ==>  16 ==>  30 ==>  40 ==> 10
	 * 5 ==>  32 ==>  62 ==>  80 ==> 18
	 * 6 ==>  64 ==> 126 ==> 160 ==> 34
	 */
	public static void main(String[] args) {
		// 购买第N次的数据统计
		int cnt = 18;
		Map<String, Long> valMap = cal(cnt);
    	System.out.println( "假设第"+cnt+"次购买彩票中奖，各项数据统计如下：");
    	System.out.println( "花费成本：" + valMap.get("cost") + "元");
    	System.out.println( "所有花费成本：" + valMap.get("allCost") + "元");
    	System.out.println( "最低中奖奖金：" + valMap.get("bonus") + "元");
    	System.out.println( "最低盈利：" + valMap.get("gain") + "元");
	    // 现有资金，如果中奖最少能赚多少钱
		long minemoney = 10000;
		//long money = howmoney(minemoney);
		//System.out.println( "现有资金:"+minemoney+"，如果中奖最少能赚"+money+"元钱" );
	    // 多长时间能赚够指定的钱
		long wantmoney = 120000;
		long count = howlong(wantmoney);
		//System.out.println( "想要赚得" + wantmoney + "元钱，需要购买"+count+"次彩票" );
		
		// 买彩有风险，赌博需谨慎。
	}
	private static long howmoney( long money ) {
		long i = 1;
		long gain = 0;
		while ( true ) {
			Map<String, Long> valMap = cal(i);
			long allCost = valMap.get("allCost");
			if ( allCost == money ) {
				gain = valMap.get("gain");
				break;
			} else if ( allCost > money ) {
				gain = cal(i-1).get("gain");
				break;
			} else {
				i++;
			}
		}
		return gain;
	}
	private static long howlong(long money) {
		long i = 1;
		while ( true ) {
			Map<String, Long> valMap = cal(i);
			long gain = valMap.get("gain");
			if ( gain >= money ) {
				break;
			} else {
				i++;
			}
		}
		return i;
	}
	
    private static Map<String, Long> cal( long count ) {
    	
    	Map<String, Long> valMap = new HashMap<String, Long>();
    	
    	// 当次花费成本
    	long cost = calCost(count);
    	// 所有花费成本
    	long allCost = calAllCost(count);
    	// 中得奖金
    	long bonus = cost / 2 * 5;
    	// 盈利
    	long gain = bonus - allCost;
    	
    	valMap.put("cost", cost);
    	valMap.put("allCost", allCost);
    	valMap.put("bonus", bonus);
    	valMap.put("gain", gain);
    	
    	return valMap;
    }
    
    private static long calCost( long count ) {
    	long val = 1;
    	if ( count > 0 ) {
    		val = 2 * calCost(count - 1);    		
    	}
    	return val;
    }
    private static long calAllCost(long count) {
    	long val = calCost(count);
    	if ( count > 1 ) {
    		val += calAllCost(count-1);
    	}
    	return val;
    }
}
