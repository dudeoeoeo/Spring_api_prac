package com.cos.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.logging.log4j.util.Chars;

public class Sales {
	
	public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> numMap = new HashMap<>();
		for(int i=0; i<nums.length; i++) {
        	if(numMap.containsKey(nums[i])) return true;
        	numMap.put(nums[i], nums[i]);
        }
		return false;	
    }
	public int singleNumber(int[] nums) {
		if(nums.length == 1) return nums[0];
		HashMap<Integer, Integer> numMap = new HashMap<>();
		for(int i=0; i<nums.length; i++) {
			if(numMap.containsKey(nums[i])) numMap.remove(nums[i]);
		    else numMap.put(nums[i], i);	
		}
		int answer = 0;
		for(Integer key: numMap.keySet()) answer = nums[numMap.get(key)];
		return answer;
	}
	
	public int[] plusOne(int[] digits) {
		if(digits.length == 1) {
        	if(digits[0] == 9) {
        		return new int [] {1, 0};
        	} else {
        		return new int [] {digits[0]+1};
        	}
        }
        for(int i=digits.length-1; i>=0; i--) {
        	if(digits[i] == 9) {
        		digits[i] = 0;
        		if(i==0) {
        			int [] newDigits = new int[digits.length+1];
        			newDigits[0] = 1;
        			for(int j=1; j<newDigits.length; j++) {
        				newDigits[j] = digits[j-1];
        			}
        			return newDigits;
        		}
        	} else {
        		digits[i] += 1;
        		return digits;
        	}
        }
        return digits;
	}
	
	public void moveZeroes(int[] nums) {
        if(nums.length==1) System.out.println(Arrays.toString(nums));
        
        for(int i=0; i<nums.length; i++) {
        	if(nums[i] != 0) continue;
        	int cnt = 0;
        	for(int j=i+1; j<nums.length; j++) {
        		if(nums[j] == 0) continue;
        		else {
        			int temp = nums[i];
        			nums[i] = nums[j];
        			nums[j] = temp;
        			cnt = 1;
        			break;
        		}
        	}
        	if(cnt == 0) break;
        }
        System.out.println(Arrays.toString(nums));
    }
	
	public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        	int minus = target - nums[i];
            if (numMap.containsKey(minus)) {
                return new int[] { numMap.get(minus), i };
            }
            numMap.put(nums[i], i);
        }
        return null;
    }
	
	public boolean isValidSudoku(char[][] board) {
		
		ArrayList<HashMap<String, Integer>> lists = new ArrayList<>();
        for(int i=0; i<board.length; i++) {
        	lists.add(new HashMap<String, Integer>()); // hashMap 객체 생성
        	for(int j=0; j<board.length; j++) {
        		String key = String.valueOf(board[i][j]); // 현재 배열의 값
        		if(key.equals(".")) continue;  // '.' 은 건너뛰기
        		for(int k=0; k<lists.size(); k++) {   // ArrayList의 0번째 hashMap 객체부터 끝까지 현재 배열의 값을 키 값으로 가지고 있는지 확인
        			if(lists.get(k).containsKey(key)) if(lists.get(k).get(key) == j) return false;
        		}
        		if(lists.get(i).containsKey(key)) return false;  // 현재 ArrayList의 hashMap 객체에 이미 같은 값이 있는지 확인
    			lists.get(i).put(String.valueOf(board[i][j]), Integer.valueOf(j));  // 모두 통과했다면 값을 집어넣기
        	}
        }
        
        for(int i=0; i<lists.size(); i++) {
            for(String key: lists.get(i).keySet()) {
            	System.out.println("key: "+key+", value: "+lists.get(i).get(key));
            }
            System.out.println("==================================================");
        }
		return true;
    }
	public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(nums1.length > nums2.length) {
        	for(int i : nums2) {
        		if(map.containsKey(i)) map.put(i, map.get(i)+1);
        		else map.put(i, 1);
        	}
        	for(int key : nums1) {
        		if(map.containsKey(key) && map.get(key) > 0) {
        			result.add(key);
        			map.put(key, map.get(key)-1);
        			if(map.get(key) == 0) map.remove(key);
            		if(map.keySet().size() == 0) break;
        		}
        	}
        } else {
        	for(int i : nums1) {
        		if(map.containsKey(i)) map.put(i, map.get(i)+1);
        		else map.put(i, 1);
        	}
        	for(int key : nums2) {
        		if(map.containsKey(key) && map.get(key) > 0) {
        			result.add(key);
        			map.put(key, map.get(key)-1);
        			if(map.get(key) == 0) map.remove(key);
            		if(map.keySet().size() == 0) break;
        		}
        	}
        }
		int [] answer = new int[result.size()];
    	for(int i=0; i<result.size(); i++) answer[i] = result.get(i);
    	return answer;
    }

	
	ArrayList<Integer> price_list = new ArrayList<>();
	
	public int maxProfit(int[] prices) {
		int n = prices.length;
        if (n == 0) return 0;
		int lastBuy = -prices[0]; // 1일차 부터 산 가격 -
		int lastSold = 0; // 판매한 가격
		
                                
        for (int i = 1; i < n; i++) {
        	System.out.printf("(%d, %d) (%d, %d) \n", lastBuy, (lastSold - prices[i]), lastSold, (lastBuy + prices[i]));
            int curBuy = Math.max(lastBuy, lastSold - prices[i]);  // 구매한 가격과, 그 다음날 팔았을 때 가격 중 큰 것을 리턴
            int curSold = Math.max(lastSold, lastBuy + prices[i]); // 현재 판매한 가격과 전 날 구매하고 오늘 팔았을 때의 가격 중 큰 것 리턴 
            lastBuy = curBuy; 
            lastSold = curSold;
        }
        /*
         * -7 -1, 0, -6   1     1 
         * -1 -5, 0,  4   2		5
         * -1  1, 4,  2   3		3
         *  1 -2, 4,  7   4		6
         *  1, 3, 7,  5   5		4
         */
        
        return lastSold;
    }
	
	public static void main(String[] args) {
		Sales s = new Sales();
		int [] nums = {4,9,5};
		int [] nums2 = {9,4,9,4,5};
		char [][] board = 	{{'.','.','.','.','5','.','.','1','.'}
							,{'.','4','.','3','.','.','.','.','.'}
							,{'.','.','.','.','.','3','.','.','1'}
							,{'8','.','.','.','.','.','.','2','.'}
							,{'.','.','2','.','7','.','.','.','.'}
							,{'.','1','5','.','.','.','.','.','.'}
							,{'.','.','.','.','.','2','.','.','.'}
							,{'.','2','.','9','.','.','.','.','.'}
							,{'.','.','4','.','.','.','.','.','.'}};
		//int [] answer = s.intersect(nums, nums2);
		int [] prices = {7,1,5,3,6,4};
		System.out.println(s.maxProfit(prices)); 
	}

}
