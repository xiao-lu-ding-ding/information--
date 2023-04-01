import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

import com.sun.media.sound.SoftSynthesizer;

class code{
	char a;                            //码字和对应编码放一起
	Vector l=new Vector();
}
class node{//整个用来做哈夫曼数的节点
	Vector list=new Vector();//编码时用来添加字符
	int number;       //同一个码的次数

}
class tree{
	Vector list=new Vector();
}
class allnode{
	Vector list1=new Vector();//存放完整输入的码
	Vector list2=new Vector(); //存放按概率分类放好的码
	Vector list3=new Vector();//存放list2各个对应码的个数
}
public class Second_two{
 	allnode p=new allnode();
	public void cin2() throws IOException {
		int b=0;
        char tom[]=new char[100];
	    File f=new File("C:\\Users\\xx\\Desktop\\费诺.txt");
	    FileReader in=new FileReader(f);
	    	while((b=in.read(tom))!=-1) {
	    	String s1=new String(tom,0,b);
	    	int len=s1.length();
	    	System.out.println("list1中的字符为：");
	       	for(int i=0;i<len;i++){
                p.list1.add(s1.charAt(i));
                System.out.print(p.list1.get(i)+" ");
	        }
	    	//System.out.print("长度："+p.list1.size()+"?");
	    	}
	    	in.close();
	}
	
    public void statistics() {
    	boolean a;
    	int temp;
    	Object temp1=0;
    	p.list2.add(temp1);//不添加数下面数组循环时从0开始会越界
    	p.list3.add(temp1);
    	    //System.out.println("\n此时三个表长度为："+p.list1.size()+" "+p.list2.size()+" "+p.list3.size());
    	for(int i=0;i<p.list1.size();i++) {
    		//System.out.println("sign:"+p.list1.get(i));
    	
    		int j=0;
    		for(;j<p.list2.size();j++) {
    			a=false;
    			temp1=p.list2.get(j);
    			//System.out.println("放入前测试的链表2的字母和表1:"+temp1+" "+p.list1.get(i));
    			if(p.list1.get(i)==p.list2.get(j)) {
    					a=true;}
    			//System.out.println("bool:"+a);
    			if(a==true) {
    				temp=(int)p.list3.get(j); 
    				temp++;
    				p.list3.set(j,temp); 
    				//System.out.println("当前表3对应字母数量:"+p.list3.get(j));
    				//System.out.println("表3长："+p.list3.size());
    				break;
    			}
    			if(a==false&j==p.list2.size()-1) {
    				p.list2.add(p.list1.get(i));
    				temp=1;
    				//System.out.println("表3长："+p.list3.size());
    				p.list3.add(temp);
    				//System.out.println("表3长："+p.list3.size());
    				//System.out.println("置1："+j+" "+p.list3.get(j+1));				
    				break;
    				}
    			}
    		
    	}
 /*  	System.out.println("\n经历了分组后三表长："+p.list1.size()+" "+p.list2.size()+" "+p.list3.size());
   	for(int i=0;i<p.list1.size();i++)
    		System.out.println(p.list1.get(i));
    	for(int i=0;i<p.list2.size();i++) {
    		System.out.println("字母："+p.list2.get(i)+" "+"数量:"+p.list3.get(i));
    	}*/
    }
	
    public void sort() {
        int k=p.list3.size()-1;
        char t[]=new char[k+1];
        //System.out.println(k);
        Vector arr1=new Vector();
	   // int arr1[]=new int[k];这里之前用数组，但是在求转置矩阵时我发现了问题，如果两个连续的数一样，转置会出问题，比如数组1第一二个为1 1，数组2第一二个也为1 1则转置为1 1则最最后全输出1的字母 
	    int arr2[]=new int[k];
	    int arr3[]=new int[k];//这个是我搞得转置矩阵
    	for(int i=0;i<k;i++) {//这里是因为上面链表的扩展，给自己提个醒
    		arr2[i]=(int)p.list3.get(i+1);
    	    arr1.add(arr2[i]);
    	    //System.out.println("排序前："+arr2[i]);
    	}
    	int temp;
    	for(int i=0;i<k-1;i++) {
    		for(int j=0;j<k-i-1;j++) {//我想写的是小到大排序,（这里写错，应该写从大到小，不过没关系,后来我改了）
    			if(arr2[j]<arr2[j+1]) {
    				temp=arr2[j];
    				arr2[j]=arr2[j+1];
    				arr2[j+1]=temp;
    			}
    		}
    	}
    	//for(int i=0;i<k;i++)
    		//System.out.println("排序后："+arr2[i]);//我之间考虑过一个问题，排序后碰到两个一样的数字和原来的顺序如何匹配？其实不需要，数目一样随意配好了。
    	for(int i=0;i<k;i++) {
    		for(int j=0;j<arr1.size();j++) {
    			temp=(int) arr1.get(j);
    			if(temp==arr2[i]) {
    			arr3[i]=j+1;//这里额外加1，因为转置给链表用，链表从1开始；
    			temp=0;
    			arr1.set(j, temp);
    			break;
    		}
    		}
    	}
    	for(int i=1;i<k+1;i++) {
    		t[i]=(char)p.list2.get(i);
    		//System.out.println(t[i]);
    	}
    	for(int i=1;i<k+1;i++) {
    		p.list2.set(i, t[arr3[i-1]]);
    		p.list3.set(i, arr2[i-1]);
    	}
    	
    	System.out.println();
    	for(int i=1;i<k+1;i++) {
    		System.out.print("表2："+p.list2.get(i)+" "+"表3："+p.list3.get(i)+"\n");
    	}
    }
    
    int n;
    public void shuru() {
    	System.out.println("你想要几元码啊");
    	Scanner reader=new Scanner(System.in);
    	n=reader.nextInt();
    }
    
    Vector tree=new Vector();
    public void build() {
		for(int i=1;i<p.list2.size();i++) {
			  code o=new code();
			  node n=new node();
		  o.a=(char)p.list2.get(i);
		  n.list.add(o);			
		  n.number=(int)p.list3.get(i);
		//  System.out.println("建立树："+o.a+" "+n.number);
		  tree.add(n); 
		  
    }
    }
    DecimalFormat df=new DecimalFormat ("0.000");
     
    Vector jieguo=new Vector();
    
    Vector fenzu=new Vector();
    Vector ma1=new Vector();
    Vector ma2=new Vector();
    
    public void digui(Vector a,int p) {
    	
    	double average;
    	int sum=0;
   	    Vector temp=new Vector();
    	 for(int i=0;i<a.size();i++) {
    		 sum+=(int)a.get(i);
      }
    //	 System.out.println("总计："+sum);
    	 int num = 0,sum1=0;
    	 average=(double)sum/(double)p;
    	// System.out.println("均数："+average+" 总数： "+sum+" 个数："+p+"sum1初始化： "+sum1);
    	 if((int)a.get(0)>=average) {
    		 fenzu.add(1);
    		// System.out.println("fenzu添加了 "+1);
    		 for(int j=0;j<a.size()-1;j++) {
    			 temp.add(a.get(j+1));
    		 //System.out.println("jia:"+a.get(j+1));
    	 }
    	 }
    	 if((int)a.get(0)<average) {
    		 for(int j=0;j<a.size();j++) {
    			 sum1+=(int)a.get(j);
    			// System.out.println("he "+sum1);
    			 if(sum1>average) {
    				 num=j;
    				// System.out.println("num的值啊啊："+num);
    				 break;
    			 }
    			 if(j==(int)a.size()-1) {
    				 num=j+1;
    				// System.out.println("num值 "+num);
    				 break;
    			 }
    			 
    		 }
    		// System.out.println(" 另一个地方num "+num);
    		  fenzu.add(num);
    		 // System.out.println("fenzu添加了 "+num);
    	    	 for(int j=0;j<a.size()-num;j++) {
    	    		 temp.add(a.get(num+j));
         } 	
    	 }
    	 if(p>1) {
    		 p--;
    		// System.out.println();
    	 digui(temp,p);
    	 }
    	 
    }
    

  public void fano(Vector a,int b) {//b元
	//  System.out.println("调用的Vector长度为："+a.size());
	  if(a.size()>b) {
		  //System.out.println();
		  Vector count=new Vector();
		  for(int i=0;i<a.size();i++) {
      node temp=(node)a.get(i);
      count.add(temp.number);
   //   System.out.println("count添加的数字："+temp.number);
		  }
		  fenzu.clear();//先清个零，不然我这个设置的是静态变量，会继续往后面假数据
	  digui(count,b);//递归在这里
/*	  System.out.println("fenzu里的数据");
	  for(int i=0;i<fenzu.size();i++) {
		  System.out.print(fenzu.get(i)+" ");
	  }System.out.println();*/
	  int add=0;
	  for(int i=0;i<b;i++) {
		  
		  int dg=(int)fenzu.get(i);
		  add+=dg;
		 // System.out.println("取出的个数："+dg);
	
		  Vector tempx=new Vector();
		  for(int j=0;j<dg;j++) {
			  
			  node temp2=(node)a.get(add-dg+j);		  
			  code temp3=(code)temp2.list.get(0);
			  temp3.l.add(i);
			  temp2.list.set(0, temp3);
			  tempx.add(temp2);
			  //System.out.println("字符："+temp3.a+" 添加码 "+i);
		  }
		  
		 fano(tempx,b);
	  }
	  }
	 // Vector count2=new Vector();	  
	  if(a.size()<=b&a.size()>1) {
		  for(int i=0;i<a.size();i++) {
			node   oo=(node)a.get(i);
			code   ooo=(code)oo.list.get(0);
			ooo.l.add(i);
		//	System.out.println("最终根部节点："+ooo.a+" 添加： "+i);
			jieguo.add(ooo);
		  }
	  }
	  if(a.size()==1) {
		  for(int i=0;i<a.size();i++) {
			node   oo=(node)a.get(i);
			code   ooo=(code)oo.list.get(0);
			
			//System.out.println("不添加");
			jieguo.add(ooo);
		  }
	  } 
  }
  public void out() {
	  for(int i=0;i<jieguo.size();i++) {
		  code temp=(code)jieguo.get(i);
		 // code temp2=(code)temp.list.get(0);
		  System.out.print("\n字符为："+temp.a+" 编码为：");
		  for(int j=0;j<(int)temp.l.size();j++) {
			  System.out.print(temp.l.get(j));
		  }
	  }
	  System.out.println("\n编码结果为：");
	  for(int i=0;i<p.list1.size();i++) {
		    char x=(char) p.list1.get(i);
		  for(int j=0;j<jieguo.size();j++) {
			  
			  code y=(code)jieguo.get(j);
			  if(x==y.a) {
				  for(int m=0;m<y.l.size();m++) {
					  System.out.print(y.l.get(m));
					  ma1.add(y.l.get(m));
				  }
			  }
		  }
	  }
	  			double s=0;
				double num1=p.list1.size();
			for(int i=0;i<jieguo.size();i++) {
				code o=(code)jieguo.get(i);
				double temp1=o.l.size();
				int temp2;
				double temp3=0;
				char x=o.a;
				for(int j=1;j<p.list2.size();j++) {
					if(x==(char)p.list2.get(j)){
						temp2=(int)p.list3.get(j);
						temp3=(double)temp2;
						break;
					}
				}
				s+=(temp3/num1)*temp1;
				
			}
			//System.out.println("平均码长："+df.format(s));
			double h=0;
			for(int i=1;i<p.list3.size();i++) {
				int temp=(int) p.list3.get(i);
				double temp1=(double)temp;
				h+=-(Math.log(temp1/num1)/Math.log(n))*(temp1/num1);
			}
	//		System.out.println("熵："+df.format(h));
			h=h/s;
			System.out.println("\n编码效率为："+df.format(h));
	  
  }
	
 public void radom() {//这里我要根据编码随机生成一串码，待会用来解密。
  			
  	int length =jieguo.size();
  	System.out.println("你想要测试译码编码有几个码字？：");
  	Scanner reader=new Scanner(System.in);
  	int a=reader.nextInt();
  	for(int i=0;i<a;i++) {
  	int r=(int)(Math.random()*length);
  	code o=(code)jieguo.get(r);
  	for(int j=0;j<o.l.size()-1;j++) {
  		ma2.add(o.l.get(j));
  	}  	
  	}
  	System.out.println("你要的码（如果想自己输码也行，就是没这个方便）");
  	for(int i=0;i<ma2.size();i++) {
  		System.out.print(ma2.get(i));
  	}
  	System.out.println();
  }
	public void decode(Vector ma){		
	int machang=0,temp;
	
	for(int i=0;i<jieguo.size();i++) {
		code l=(code)jieguo.get(i);//这里原本是kt.list,给自己下面改留记号
		temp=l.l.size();
		if(machang<temp)
			machang=temp;
	}
	Vector ma3=new Vector();
	int pp=0,p2;
	boolean a=true;
	//System.out.println(ma.size()+" "+machang);
	while(a) {		
			int num2=0;
		for(int m=0;m<machang;m++) {//对code中的数字逐个检验 
			//System.out.println("m= "+m+" num= "+num);
			int temp3=num2;
			 //  num2=0;
			p2=ma3.size();
			for(int i=0;i<jieguo.size();i++) {
			code o=(code)jieguo.get(i);
		       if(o.l.size()>m&i>=temp3) {   
		    	   
				int k=(int)ma.get(pp);	
			//	System.out.println("编码后的码取出第 "+pp+" 个为 "+k);
				int temp2=(int)o.l.get(m);
				int mp=o.l.size()-m;
			//	System.out.println("从code "+o.a+" 中抽取第 "+mp+" 个数为： "+temp2);
				if(k==temp2&m+1==o.l.size()) {					
						ma3.add(o.a);
					//	System.out.println("得到的码为："+o.a);
						pp++;
						break;
				
			}
				if(k==temp2&m+1!=o.l.size()) {//这里和霍夫曼不一样，不是最佳码，译码要修改了。
					pp++;
					break;
				}
				if(k!=temp2)
					num2++;
				//System.out.println("num变化："+num2);
		}
		}   
			
			//System.out.println("最后的pp为  "+pp);
			if(p2!=ma3.size())
				break;
			if(pp==ma.size())
				break;
	}   
		if(pp==ma.size())	
			a=false;
	}
   System.out.println("\n译码为：");
   for(int i=0;i<ma3.size();i++)
  	 System.out.print(ma3.get(i));	
   System.out.println();
	}

  
	public static void main(String[] args) throws IOException {
		Second_two a=new Second_two();
		a.cin2();
		a.statistics();
		a.sort();
		a.shuru();
		a.build();
		a.fano(a.tree,a.n);
		a.out();
		a.decode(a.ma1);
		//a.radom();
		//a.decode(a.ma2);

	}
}

