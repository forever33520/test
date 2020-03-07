package com.cy;

import java.text.NumberFormat;
import java.util.*;

public class Start {
	/*
	 * 游戏牌组，13张牌随机（按照生成的随机数数组）插入到数组里，循环40次
	 */
	static int[] cards=new int[520];
	
	static int index=0;//数组下标

	public static int[] card() {
		//生成0-519的数字下标数组
		int[] seed = new int[520];
		for (int i = 0; i < 520; i++) {
			seed[i]=i;
		}
		//生成0-519随机不重复的数字数组
		int[] ranArr = new int[520]; 		
		Random ran = new Random();
		for (int i = 0; i<seed.length; i++) {
			int j = ran.nextInt(seed.length - i); // 得到一个位置 
			ranArr[i] = seed[j]; // 得到那个位置的数值
			seed[j] = seed[seed.length - 1 - i];// 将最后一个未用的数字放到这里
		}
		//生成新数组
		int[] a= {1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] card=new int[520];
		int j = 0;
		for (int k = 0; k < 40; k++) {
			for (int i = 0; i < 13; i++) {
				card[ranArr[j]]=a[i];
				j++;
			}
		}
		//System.out.println(Arrays.toString(ranArr));
		//System.out.println("牌组"+Arrays.toString(card));
		return card;
	}
	
	public static int pat() {
		if (cards[index]==1) {
			System.out.println("发牌：A");
		}
		else if (cards[index]==11) {
			System.out.println("发牌：J");
		}		
		else if (cards[index]==12) {
			System.out.println("发牌：Q");
		}		
		else if (cards[index]==13) {
			System.out.println("发牌：K");
		}	
		else {
			System.out.println("发牌："+cards[index]);
		}	
		return cards[index++];
	}
	
	public static String name(int num) {
		if (num==1) {
			return "A";					
		}
		if (num==11) {
			return "J";					
		}
		if (num==12) {
			return "Q";					
		}
		if (num==13) {
			return "K";					
		}
		Integer a=num;
		return a.toString();
	}
	
	public static void main(String[] args) {
		cards = card();//生成牌组
		System.out.println("牌组"+Arrays.toString(cards));
		int zWin=0;
		int xWin=0;
		int hWin=0;
		int six=0;
		int zTwo=0;
		int xTwo=0;
		System.out.println("按数字1模拟一局百家乐游戏:");
		int i = new Scanner(System.in).nextInt();
		while (i==1&&index<=513) {
			int z1=pat();
			int zz1=0;
			if (z1<=9) {
				zz1=z1;
			}
			int x1=pat();
			int xx1=0;
			if (x1<=9) {
				xx1=x1;
			}
			int z2=pat();
			int zz2=0;
			if (z2<=9) {
				zz2=z2;
			}
			int x2=pat();
			int xx2=0;
			if (x2<=9) {
				xx2=x2;
			}
			
			int x = 0;//最终点数
			int z = 0;//最终点数
			int x3 = 14;
			int z3 = 14;
			
			if ((xx1+xx2)%10<=5) {
				System.out.print("闲("+name(x1)+"+"+name(x2)+"="+((xx1+xx2)%10)+")小于等于5-->");
				x3=pat();
				int xx3=0;
				if (x3<=9) {
					xx3=x3;
				}
				x=(xx1+xx2+xx3)%10;
				System.out.println("闲的牌为："+name(x1)+"+"+name(x2)+"+"+name(x3)+",闲最终"+x+"点");
			}else {
				x=(xx1+xx2)%10;
				System.out.println("闲的牌为："+name(x1)+"+"+name(x2)+",闲最终"+x+"点");
			}
			
			if ((zz1+zz2)%10<=5) {
				System.out.print("庄("+name(z1)+"+"+name(z2)+"="+((zz1+zz2)%10)+")小于等于5-->");
				z3=pat();
				int zz3=0;
				if (z3<=9) {
					zz3=z3;
				}
				z=(zz1+zz2+zz3)%10;
				System.out.println("庄的牌为："+name(z1)+"+"+name(z2)+"+"+name(z3)+",庄最终"+z+"点");
			}else {
				z=(zz1+zz2)%10;
				System.out.println("庄的牌为："+name(z1)+"+"+name(z2)+",庄最终"+z+"点");
			}
			
			System.out.println();
			
			if (x>z) {
				System.out.println(">本局闲赢！");
				xWin++;
			}else if(x<z) {
				System.out.println(">本局庄赢！");
				zWin++;
				if (z==6) {
					six++;
					System.out.println(">本局出现幸运六！");
				}
			}else {
				System.out.println(">本局和局！");
				hWin++;
			}
			
			if(z1==z2||z2==z3||z3==z1) {
				zTwo++;
				System.out.print(">本局出现庄对子:");
				if(z1==z2) {
					System.out.println("对"+name(z1)+"！");
				}else if (z2==z3) {
					System.out.println("对"+name(z2)+"！");
				}else if (z3==z1) {
					System.out.println("对"+name(z3)+"！");
				}
			}
			if(x1==x2||x2==x3||x3==x1) {
				xTwo++;
				System.out.print(">本局出现闲对子:");
				if(x1==x2) {
					System.out.println("对"+name(x1)+"！");
				}else if (x2==x3) {
					System.out.println("对"+name(x2)+"！");
				}else if (x3==x1) {
					System.out.println("对"+name(x3)+"！");
				}
			}
			System.out.println("---------------------");
			//i = new Scanner(System.in).nextInt();//注销		
		}		
		int sum=zWin+xWin+hWin;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(2);
		
		System.out.println();
		System.out.println("===========游戏退出=================");
		System.out.println("本局游戏总局数："+sum);
		System.out.println("庄赢"+zWin+"局,"+"占总局数百分比为"+nf.format((double)zWin/(double)sum));
		System.out.println("闲赢"+xWin+"局,"+"占总局数百分比为"+nf.format((double)xWin/(double)sum));
		System.out.println("和"+hWin+"局,"+"占总局数百分比为"+nf.format((double)hWin/(double)sum));
		System.out.println("出现幸运六次数："+six+"次,"+"占总局数百分比为"+nf.format((double)six/(double)sum));
		System.out.println("出现庄对子次数："+zTwo+"次,"+"占总局数百分比为"+nf.format((double)zTwo/(double)sum));
		System.out.println("出现闲对子次数："+xTwo+"次,"+"占总局数百分比为"+nf.format((double)xTwo/(double)sum));
	}
}


