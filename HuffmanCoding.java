import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
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

public class Second_one {
		static String ss=null;
	 	allnode p=new allnode();
	public void cin() throws IOException {
		int b=0;
        char tom[]=new char[100];
	    File f=new File("C:\\Users\\xx\\Desktop\\cin.txt");
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
    
    
    tree t=new tree();
    Vector ma1=new Vector();
    Vector ma2=new Vector();
    
   public void huffman() {
		System.out.println("你想要几元码？：");
		Scanner reader=new Scanner(System.in);
		int a=reader.nextInt();
		 int num=p.list2.size()-1;
		int y=(num-a)%(a-1);
		int number;
		if(y!=0) {
			num+=a-1-y;
		number=(num-a)/(a-1); //缩减的次数
		}
		else 
			number=(num-a)/(a-1)+1;//这里是我最后加进去的，本没有这一步，number直接等于上面那个，但是如果恰好不需要扩展的化，就少以一次合并。
		//System.out.println("扩展后："+num);       //此处扩展huffman，测试正确
		/*String s=null;
		int temp=0;
		for(int i=0;i<a-1-y;i++) {
			p.list2.add(s);
			p.list3.add(temp);//     这一步的实用性我还没考虑好，这里是先把扩展的节点加进去
		}*/
		
		
		for(int i=1;i<p.list2.size();i++) {
			  code o=new code();//这里曾经是放在code和node中的两个元素，但是因为下面的问题被我放到外面来了，就是改变节点数据的问题。还没太弄明白为什么
			  node n=new node();//这两个放在这个循环外面也不行，我考虑了下，放在外面或类里，都是静态的，没回添加的都是node i，添加了5个node i，所以node i一变酒全变了，改成循环内，每次训话你重新生成node 就不存在这个问题应该
		  o.a=(char)p.list2.get(i);
		  n.list.add(o);			
		  n.number=(int)p.list3.get(i);	//只能说到这步没什么问题	
		  
		  t.list.add(n);             //经验证，初始建立树有问题，在添加每一个节点是没问题，但却改变了之前的节点，比如添加第二个节点是把第一个节点你变得和第二个数据一样(已经解决)
		  
		  n=(node)t.list.get(i-1);
		  o=(code)n.list.get(0);
		  
		 // node i11=(node)t.list.get(0);
		 // code o11=(code)i11.list.get(0);
			//System.out.println("字："+o.a+"数:"+n.number+"对比："+o11.a);
	}   
		
	/*	System.out.println("初步建立树节点");
	
		for(int i=0;i<t.list.size();i++) {
			code o=new code();
			node n=new node();
			n=(node) t.list.get(i);
			o=(code) n.list.get(0);
			System.out.println("节点"+i+":"+o.a+" 个数："+n.number);
		}
		*/
		
		
		
		int k=t.list.size();
		if(k!=num) {
			node n1=new node();
			n1=(node) t.list.get(k-1-y);
			code op=(code)n1.list.get(0);
			int c1=0;
			for(int j=0;j<n1.list.size();j++) {
				code oo=new code();
				oo=(code) n1.list.get(j);
				oo.l.add(c1);               //每步插入编码
				n1.list.set(j, oo);
			}
			
		for(int i=0;i<y;i++) {
			node n2=new node();
			n2=(node) t.list.get(k-1-y+i+1);
			c1++;
			for(int j=0;j<n2.list.size();j++) {
				code oo=new code();
				oo=(code) n2.list.get(j);
				oo.l.add(c1);               //每步插入编码
				n1.list.add(oo);
			}
			n1.number+=n2.number;
			t.list.remove(k-1-y+i+1);
		}

			t.list.set(t.list.size()-1 , n1);
			int l=t.list.size();
			int temp=n1.number;
			int temp2;
			for(int j=0;j<l-1;j++) {
				node n2=new node();
				n2=(node) t.list.get(j);
				temp2=n2.number;
				if(temp>=temp2) {
					t.list.add(j, n1);
					t.list.remove(t.list.size()-1);
					break;
				}					
			}
		}
	/*	for(int i=0;i<t.list.size();i++) {
			node n1=new node();
			n1=(node)t.list.get(i);
			for(int j=0;j<n1.list.size();j++) {
				code oo=new code();
				oo=(code)n1.list.get(j);
				System.out.println("第一轮字："+oo.a);
				System.out.println("码：");
				for(int m=0;m<oo.l.size();m++) {
					int temp=(int)oo.l.get(m);
					System.out.println(temp+" ");
				}
			}
		}
		*/
/////  上面时位数不足时先把最后几个解决掉，下面是正规的开始循环记录
		
		
		for(int i=0;i<number;i++) {
			k=t.list.size();
					node n1=new node();
					n1=(node) t.list.get(k-a);
					code op=(code)n1.list.get(0);
					int c1=0;
					for(int m=0;m<n1.list.size();m++) {
						code oo=new code();
						oo=(code) n1.list.get(m);
						oo.l.add(c1);               //每步插入编码
						n1.list.set(m, oo);
					

			}
			
					for(int j=0;j<a-1;j++) {
						node n2=new node();
						n2=(node) t.list.get(k-a+1);
						c1++;
						for(int m=0;m<n2.list.size();m++) {
							code oo=new code();
							oo=(code) n2.list.get(m);
							oo.l.add(c1);               //每步插入编码
							n1.list.add(oo);
						}
						n1.number+=n2.number;
						t.list.remove(k-a+1);
					}
			    t.list.set(t.list.size()-1 , n1);
				int l=t.list.size();
				int temp=n1.number;
				int temp2;
				for(int j=0;j<l-1;j++) {
					node n2=new node();
					n2=(node) t.list.get(j);
					temp2=n2.number;
					if(temp>=temp2) {
						t.list.add(j, n1);
						t.list.remove(t.list.size()-1);
						break;
					}					
				}		

		}
		System.out.println("结果在这里：");
		node kt=(node)t.list.get(0);
		char ad;
		int af;
		for(int i=0;i<kt.list.size();i++) {
			code l=(code)kt.list.get(i);
			ad=(char)l.a;
			System.out.print("码字："+ad+" 编码：");
			for(int p=l.l.size()-1;p>=0;p--) {
				af=(int)l.l.get(p);
				System.out.print(af+" ");
				
			}
			System.out.println();
		}
	
		for(int i=0;i<p.list1.size();i++) {
			char temp=(char)p.list1.get(i);
			for(int j=0;j<kt.list.size();j++) {
				code o=(code)kt.list.get(j);
				if(temp==o.a) {
					for(int m=o.l.size()-1;m>=0;m--) {
						ma1.add(o.l.get(m));
					}
				}
			}
		}
		System.out.println("编码完成后的码为：");
		for(int i=0;i<ma1.size();i++)
			System.out.print(ma1.get(i));
		
		System.out.println();  //用于换行
		        double s=0;
		    	DecimalFormat df=new DecimalFormat ("0.000");
				double num1=p.list1.size();
			for(int i=0;i<kt.list.size();i++) {
				code o=(code)kt.list.get(i);
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
				h+=-(Math.log(temp1/num1)/Math.log(a))*(temp1/num1);
			}
	//		System.out.println("熵："+df.format(h));
			h=h/s;
			System.out.println("编码效率为："+df.format(h));
	}   
    public void radom() {//这里我要根据编码随机生成一串码，待会用来解密。
    		
    	node kt=(node)t.list.get(0);
    	int length =kt.list.size();
    	System.out.println("你想要测试译码编码有几个码字？：");
    	Scanner reader=new Scanner(System.in);
    	int a=reader.nextInt();
    	for(int i=0;i<a;i++) {
    	int r=(int)(Math.random()*length);
    	code o=(code)kt.list.get(r);
    	for(int j=o.l.size()-1;j>=0;j--) {
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
	node kt=(node)t.list.get(0);
	for(int i=0;i<kt.list.size();i++) {
		code l=(code)kt.list.get(i);
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
			   num2=0;                //？这里因为是紧致码，不影响，为什么影响，与第二个费诺对比，应该不要这一步
			p2=ma3.size();
			for(int i=0;i<kt.list.size();i++) {
			code o=(code)kt.list.get(i);
		       if(o.l.size()>m&i>=temp3) {   
		    	   
				int k=(int)ma.get(pp);	
				//System.out.println("编码后的码取出第 "+pp+" 个为 "+k);
				int temp2=(int)o.l.get(o.l.size()-1-m);
				//int mp=o.l.size()-m;
				//System.out.println("从code "+o.a+" 中抽取第 "+mp+" 个数为： "+temp2);
				if(k==temp2&m+1==o.l.size()) {					
						ma3.add(o.a);
						//System.out.println("得到的码为："+o.a);
						pp++;
						break;
				
			}
				if(k==temp2&m+1!=o.l.size()) {
					pp++;
					break;
				}
				if(k!=temp2)
					num2++;
				//System.out.println("num变化："+num);
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
     System.out.println("译码为：");
     for(int i=0;i<ma3.size();i++)
    	 System.out.print(ma3.get(i));	
     System.out.println();
	}


    public static void main(String[] args) {
    Second_one a=new Second_one();
    try {
		a.cin();
	} catch (IOException e) {
		e.printStackTrace();
	}
    a.statistics();
    a.sort();
    a.huffman();   
    a.decode(a.ma1);
    a.radom();
    a.decode(a.ma2);
	}
}

