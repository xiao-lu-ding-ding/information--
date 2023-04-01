import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

class code{
	int a;                            //码字和对应编码放一起
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
	Vector list4=new Vector();//存放游程大于2^n的的数
}
public  class Second_three {
	
	allnode p=new allnode();

	  Vector list0=new Vector();
    String s ;
 public  void cin3() throws IOException {
		int b=0;
  char tom[]=new char[100];
	    File f=new File("C:\\Users\\xx\\Desktop\\游程编码.txt");
	    FileReader in=new FileReader(f);
	    	while((b=in.read(tom))!=-1) {
	    	s=new String(tom,0,b);
	        System.out.println("读取的字符串为：");
	        for(int i=0;i<s.length();i++) {
	        	System.out.print(s.charAt(i));
	        }
	        System.out.println();
	    	}
	    	in.close();
	    }
 
     int n,tempx;//为了在编码时调用，所以把他放在这
 public void youcheng() throws IOException {	 
	   char x=s.charAt(0);
	   int temp=0;
	   for(int i=0;i<s.length();i++) {
		   if(x==s.charAt(i)) {
			   temp++;
			  // System.out.println("temp的变化"+temp);
			   
		   }
		   if(x!=s.charAt(i)) {
			   x=s.charAt(i);
			   list0.add(temp);
			   temp=1;		//这里已经开始读另一个码的第一个了，这个if结束就读第二个，所以不能置零，一开始我置零错了   
		   }
		   if(i==s.length()-1)
			   list0.add(temp);
	   }
	   list0.add(s.charAt(0));
	   list0.add(s.charAt((int)list0.get(0)));//将两个信源添加在容器结尾；
	   int max=0,temp1;
	   System.out.println("得到的游程序列为");
	   for(int i=0;i<list0.size()-2;i++) {
		   temp1=(int)list0.get(i);
		   System.out.print(temp1+" ");
		   if(temp1>max)
			   max=temp1;
	   } 
	   for(int i=list0.size()-2;i<list0.size();i++) {
		   char cc=(char)list0.get(i);
		   System.out.print(cc+" ");
	   }
	   System.out.println("\n设置一个n值，");//然后2^n作为截断处理时的长度\n（这里我想了很久，这个n字母确定，一开始我以为要根据编码效率来择优选择，\n可是没得写好像，后来选择手动输入，编到后来我发现选出2^n后，\n如果统计时a或b没出现，或者只出现一个2^n，\n那没后面大于2^n次方的就没法编了，但是根据书上的，\n难怪没有给具体的选取n方式，根据实际，\n信息源肯定会很长，所以应该很容易找出ab都出现\n2^n次方的那个合适n值）");
	   Scanner reader=new Scanner(System.in);
	   n=reader.nextInt();
	   tempx=(int) Math.pow(2, n);
	
	   //System.out.println("tempx为："+tempx);
	   for(int i=0;i<list0.size()-2;i++) {
		   int temp3=0;
		  int j=(int)list0.get(i);
		//  System.out.println("取出："+j);
		  if(j<tempx) 
		      p.list1.add(j);
		  if(i%2==0&j==tempx)
			  p.list1.add(-j);
		  if(i%2==1&j==tempx)
			  p.list1.add(j);
		  if(j>tempx&i%2==0) {
			  p.list4.add(-j);
		                      //这里我碰到个问题，选出2^n后，如果统计时a或b没出现，或者只出现一个2^n，那没后面大于2^n次方的就没法编了，但是根据书上的，难怪没有给具体的选取n方式，根据实际，信息原肯定会很长，所以可能能找出ab都出现2^n次方的那个n值
		  }
		  if(j>tempx&i%2==1) {
			  p.list4.add(j);
		  }
	  }   
}
   public void statistics() {
  	boolean a;
  	int temp;
  	Object temp1=0;
  	p.list2.add(temp1);//不添加数下面数组循环时从0开始会越界
  	p.list3.add(temp1);
  	for(int i=0;i<p.list1.size();i++) {	
  		int j=0;
  		for(;j<p.list2.size();j++) {
  			a=false;
  			temp1=p.list2.get(j);
  			if(p.list1.get(i)==p.list2.get(j)) {
  					a=true;}
  			if(a==true) {
  				temp=(int)p.list3.get(j); 
  				temp++;
  				p.list3.set(j,temp); 
  				break;
  			}
  			if(a==false&j==p.list2.size()-1) {
  				p.list2.add(p.list1.get(i));
  				temp=1;
  				p.list3.add(temp);		
  				break;
  				}
  			}	
  	}
  } 
   public void sort() {
       int k=p.list3.size()-1;
       int t[]=new int[k+1];
       Vector arr1=new Vector();
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
   		t[i]=(int)p.list2.get(i);
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
	int a=2;
	 int num=p.list2.size()-1;
	int y=(num-a)%(a-1);
	int number;
	if(y!=0) {
		num+=a-1-y;
	number=(num-a)/(a-1); //缩减的次数
	}
	else 
		number=(num-a)/(a-1)+1;	
	for(int i=1;i<p.list2.size();i++) {
		  code o=new code();//这里曾经是放在code和node中的两个元素，但是因为下面的问题被我放到外面来了，就是改变节点数据的问题。还没太弄明白为什么
		  node n=new node();//这两个放在这个循环外面也不行，我考虑了下，放在外面或类里，都是静态的，没回添加的都是node i，添加了5个node i，所以node i一变酒全变了，改成循环内，每次训话你重新生成node 就不存在这个问题应该
	  o.a=(int)p.list2.get(i);
	  n.list.add(o);			
	  n.number=(int)p.list3.get(i);	//只能说到这步没什么问题	
	  
	  t.list.add(n);             //经验证，初始建立树有问题，在添加每一个节点是没问题，但却改变了之前的节点，比如添加第二个节点是把第一个节点你变得和第二个数据一样(已经解决)
	  
	  n=(node)t.list.get(i-1);
	  o=(code)n.list.get(0);
	  
}   	
	
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
	node kt=(node)t.list.get(0);
	
	code temp4=new code();//这里是a的2^n次即1
	code temp5=new code();
	for(int i=0;i<kt.list.size();i++) {
		code temp=(code)kt.list.get(i);
		if(temp.a==-tempx) {
			temp4=temp;
			  code oooo=(code)kt.list.get(i);
			for(int kk=0;kk<n;kk++)
			  oooo.l.add(0,0);
			kt.list.set(i, oooo);
		}
		if(temp.a==tempx) {
			temp5=temp;
			  code oooo=(code)kt.list.get(i);
				for(int kk=0;kk<n;kk++)
				  oooo.l.add(0,0);
				kt.list.set(i, oooo);
		}
	}
	for(int i=0;i<p.list4.size();i++) {
		code ooo=new code();
		if((int)p.list4.get(i)<0) {
			ooo.a=(int) p.list4.get(i);
		    int j=-(int)p.list4.get(i)/tempx;
		    int m=-(int)p.list4.get(i)%tempx;
		    for(int ii=0;ii<j;ii++) {
		    	for(int iii=temp4.l.size()-1;iii>=0;iii--) {
		    		ooo.l.add(temp4.l.get(iii));
		    	}

		    }
		    for(int ii=0;ii<n;ii++) {
		    	int iii=m%2;
		    	m/=2;
		    	ooo.l.set(j*(temp4.l.size())-1-ii, iii);
		    }
		    for(int ii=0;ii<ooo.l.size()/2;ii++) {
		    	int iii=(int)ooo.l.get(ii);
		    	ooo.l.set(ii, ooo.l.get(ooo.l.size()-1-ii));
		    	ooo.l.set(ooo.l.size()-1-ii,iii);
		    }
		    kt.list.add(ooo);
	}
		if((int)p.list4.get(i)>0) {
			ooo.a=(int) p.list4.get(i);
		    int j=(int)p.list4.get(i)/tempx;
		    int m=(int)p.list4.get(i)%tempx;
		    for(int ii=0;ii<j;ii++) {
		    	for(int iii=temp5.l.size()-1;iii>=0;iii--) {
		    		ooo.l.add(temp5.l.get(iii));
		    	}
		    }
		    for(int ii=0;ii<n;ii++) {
		    	int iii=m%2;
		    	m/=2;
		    	ooo.l.set(j*(temp5.l.size())-1-ii, iii);
		    }
		    for(int ii=0;ii<ooo.l.size()/2;ii++) {
		    	int iii=(int)ooo.l.get(ii);
		    	ooo.l.set(ii, ooo.l.get(ooo.l.size()-1-ii));
		    	ooo.l.set(ooo.l.size()-1-ii,iii);
		    }
		    kt.list.add(ooo);
	}
	}
	System.out.println("结果在这里：");
	
	int ad;
	int af;
	for(int i=0;i<kt.list.size();i++) {
		code l=(code)kt.list.get(i);
		ad=(int)l.a;
		System.out.print("码字："+ad+" 编码：");
		for(int p=l.l.size()-1;p>=0;p--) {
			af=(int)l.l.get(p);
			System.out.print(af+" ");
			
		}
		System.out.println();
	}
    for(int i=0;i<kt.list.size();i++) {
    	code oo=(code)kt.list.get(i);
    	if(oo.a==-1)
    		temp4=oo;
    	if(oo.a==-2)
    		temp5=oo;
    }
 //   System.out.println(temp4.l.size()+" ma1的长度 "+ma1.size());
	for(int i=0;i<list0.size()-2;i++) {
		int temp=(int)list0.get(i);
	//	System.out.println("取出 "+temp+" 次序为 "+i);
		for(int j=0;j<kt.list.size();j++) {
			code o=(code)kt.list.get(j);
			if(temp==o.a) {
				//System.out.println("添加：");
				for(int m=o.l.size()-1;m>=0;m--) {
					ma1.add(o.l.get(m));
					//System.out.print(o.l.get(m)+" ");
				}
				//System.out.println();
				break;
			}
			if(temp==-o.a&i%2==0) {
				//System.out.println("添加：");
				for(int m=o.l.size()-1;m>=0;m--) {
					ma1.add(o.l.get(m));
				//System.out.print(o.l.get(m)+" ");
				}
				//System.out.println();
				break;
			}
		}
	}
	System.out.println("编码完成后的码为：");
	for(int i=0;i<ma1.size();i++)
		System.out.print(ma1.get(i));
	
}   
  
  
  
	public void decode(Vector ma){		
	int machang=0,temp;
	node kt=(node)t.list.get(0);
	node ktcopy=new node();
	for(int i=0;i<kt.list.size();i++) {
		code l=(code)kt.list.get(i);
		if(Math.abs((int)l.a)<=4) {
			if(Math.abs((int)l.a)==tempx)
				for(int j=0;j<n;j++) {
					l.l.remove(0);
				}
			ktcopy.list.add(l);
		}
	}
/*	System.out.println();
	for(int i=0;i<ktcopy.list.size();i++) {
		code l=(code)ktcopy.list.get(i);
		System.out.print("码字是： "+l.a+" 编码为： ");
		for(int j=l.l.size()-1;j>=0;j--)
			System.out.print(l.l.get(j));
		System.out.println();
	}*/
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
			   num2=0;
			p2=ma3.size();
			for(int i=0;i<ktcopy.list.size();i++) {
			code o=(code)ktcopy.list.get(i);
		       if(o.l.size()>m&i>=temp3) {   
		    	   
				int k=(int)ma.get(pp);	
				//System.out.println("编码后的码取出第 "+pp+" 个为 "+k);
				int temp2=(int)o.l.get(o.l.size()-1-m);
				//int mp=o.l.size()-m;
				//System.out.println("从code "+o.a+" 中抽取第 "+mp+" 个数为： "+temp2);
				if(k==temp2&m+1==o.l.size()) {
					    if((int)o.a==-tempx) {
					    	int kk=0;
					        for(int po=0;po<n;po++) {
					        	kk+=Math.pow(2, n-po-1)*(int)ma.get(pp+po+1);
					        }
					        //System.out.println("后两位读取："+kk);
					        kk=-kk-tempx;
					    	ma3.add(kk);
					    	pp=pp+3;
					    	break;
					    }
					    if((int)o.a==tempx) {
					    	int kk=0;
					        for(int po=0;po<n;po++) {
					        	kk+=Math.pow(2, n-po-1)*(int)ma.get(pp+po+1);
					        }
					     //   System.out.println("另一个后两位读取："+kk);
					        kk+=tempx;
					    	ma3.add(kk);
					    	pp=pp+3;
					    	break;
					    }
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
   System.out.println("\n直接译为游程码为：");
   for(int i=0;i<ma3.size();i++) {
	   int ttt=Math.abs((int)ma3.get(i));
  	 System.out.print(ma3.get(i));	
   }
   System.out.println("\n译为原二元字符码为");
   Vector ma4=new Vector();
   ma4.add(ma3.get(0));
   for(int i=1;i<ma3.size();i++) {
	   int j=(int)ma4.get(ma4.size()-1);
	   int j1=(int)ma3.get(i);
	   if((j<0&j1<0)|(j>=4&j1>=4)) 
		   ma4.set(ma4.size()-1,j+j1);
	   else
		   ma4.add(ma3.get(i));
   }
   System.out.println("\n译为调整后的游程码为：");
   for(int i=0;i<ma4.size();i++) {
	   int ttt=Math.abs((int)ma4.get(i));
  	 System.out.print(ma4.get(i));	
   }
    //       此处我要写编码效率，由于我写了截断，以及游程本身的特性，和之前的霍夫曼应该挺大不同的,根据上面写的统计来吧
   Vector ycxl1=new Vector();
   Vector ycxl2=new Vector();
	Vector ycxl3=new Vector();
	DecimalFormat df=new DecimalFormat ("0.000");
   for(int m=0;m<2;m++) {
	 	boolean axl;
	  	int tempxl;
	  	Object tempxl1=0;
	  	ycxl1.add(tempxl1);//不添加数下面数组循环时从0开始会越界
	  	ycxl2.add(tempxl1);
	  	for(int i=m;i<list0.size()-2;i+=2) {	
	  		int j=0;
	  		for(;j<ycxl1.size();j++) {
	  			a=false;
	  			tempxl1=ycxl1.get(j);
	  			if(list0.get(i)==ycxl1.get(j)) {
	  					a=true;}
	  			if(a==true) {
	  				tempxl=(int)ycxl2.get(j); 
	  				tempxl++;
	  				ycxl2.set(j,tempxl); 
	  				break;
	  			}
	  			if(a==false&j==ycxl1.size()-1) {
	  				ycxl1.add(list0.get(i));
	  				tempxl=1;
	  				ycxl2.add(tempxl);		
	  				break;
	  				}
	  			}	
	  	}
	  	ycxl1.remove(0);
	  	ycxl2.remove(0);
	  
        double s=0;
	    	
			double num1=ycxl1.size();
		for(int i=0;i<kt.list.size();i++) {
			code o=(code)kt.list.get(i);
			double temp1=o.l.size();
			int temp2;
			double temp3=0;
			int x=o.a;
			for(int j=1;j<ycxl1.size();j++) {
				if(x==(int)ycxl1.get(j)){
					temp2=(int)ycxl2.get(j);
					temp3=(double)temp2;
					break;
				}
			}
			s+=(temp3/num1)*temp1;
			
		}
		//System.out.println("平均码长："+df.format(s));
				double h=0;
				for(int i=1;i<ycxl2.size();i++) {
					int tempyc=(int) p.list3.get(i);
					double temp1=(double)tempyc;
					h+=-(Math.log(temp1/num1)/Math.log(2))*(temp1/num1);
				}
				h=h/s;
				System.out.println("\n其中一个字符的霍夫曼编码效率为："+df.format(h));
				ycxl3.add(h);
   }

   double pp1=0.0,pp2=0.0;
   for(int i=0;i<list0.size()-2;i++) {
	   if(i%2==0) {
		   int j=(int)list0.get(i);
		   pp1+=(double)j;
	   }
	   if(i%2==1) {
		   int j=(int)list0.get(i);
	       pp2+=(double)j;
	   }
   }
   double temph= -(Math.log(pp1/(pp1+pp2))/Math.log(2))*(pp1/(pp1+pp2))-(Math.log(pp2/(pp1+pp2))/Math.log(2))*(pp2/(pp1+pp2));
   double xiaolv=2.0*temph/(temph/(double)ycxl3.get(0)+temph/(double)ycxl3.get(1));
   System.out.println("最终编码效率为："+df.format(temph));
   
   for(int i=0;i<ycxl1.size();i++) {
	  // System.out.println("ma为："+ycxl1.get(i)+" 数为： "+ycxl2.get(i));
   }

  
   System.out.println("\n将游程码转为二元字符");
   for(int i=0;i<ma4.size();i++) {
	   int tt=Math.abs((int)ma4.get(i));
	   if(i%2==0) {
		   for(int j=0;j<tt;j++)
			   System.out.print(list0.get(list0.size()-2));
	   }
	   if(i%2==1) {
		   for(int j=0;j<tt;j++)
			   System.out.print(list0.get(list0.size()-1));
	   }
   }
	}
	public static void main(String[] args) throws IOException {
		Second_three a=new Second_three();
		a.cin3();
		a.youcheng();
	    a.statistics();
	    a.sort();
	    a.huffman(); 
	   a.decode(a.ma1);
	}
	       
}

