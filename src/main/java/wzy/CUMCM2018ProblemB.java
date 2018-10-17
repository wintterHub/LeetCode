package wzy;

public class CUMCM2018ProblemB {

	public static void main(String[] args) {
		new CUMCM2018ProblemB().solution();
	}

	public void solution() {

	}
	 
	/*
		[jl,sx,qx,cnc]=rgv();
		c=[1     3     4     5     6     7     8     2] % 第一个循环的路径
		st=[0    53    83   141   171   229   259   353]% 对应的上料开始时间
		a=[c;ones(1,8);st] % cnc路径获取，状态获取，以及上料开始时间更新的矩阵
		kaishishijian=st;zuihou=0;
		lujing=c;k=0;l=0;+3
		
		while  zuihou<=28800 %八小时的限制
		
		    while any(a(2,:))==1 %要求一定要把cnc都更新完成才结束循环
		      %寻找从当前目标地到下一目标的cnc编号]
		        if all(a(2,:))==1
		            [jl,sx,qx,cnc]=rgv();
		            ing=a(3,find(a(1,:)==lujing(end)))+sx(lujing(end))+qx %rvg所处的状态 qx为清洗时间 sx（）为上下料时间，jl（）为移动距离
		            B=find(a(2,:)~=0);%寻找未被标记的cnc编号即与当前循环中cnc状态相反的cnc编号
		            n=length(B); %与当前循环cnc状态相反的cnc个数
		            min11=inf;min12=inf;
		            %寻找最小的youhuajiie   
		            for i=1:n
		                mubiaodi=a(3,B(i))+cnc+sx(B(i))-ing;% a（）为上一次的上料开始时间 cnc为加工时间 sx为对应的上下料时间
		                youhuajie=mubiaodi+jl(lujing(end),B(i));% jl（）为rvg从现在所处的位置到达目标的的移动时间
		                if youhuajie<=min11
		                    min11=youhuajie; %寻找最小的youhuajiie 其所对应的B（i）即为要求的目标地 
		                    e=i;
		                end
		            end
		
		        elseif all(a(2,:))==0 %跟上一循环的目标是一样的
		            [jl,sx,qx,cnc]=rgv();
		            ing=a(3,find(a(1,:)==lujing(end)))+sx(lujing(end))+qx
		            B=find(a(2,:)~=0);
		            n=length(B);
		            min11=inf;min12=inf;
		            for i=1:n
		                mubiaodi=a(3,B(i))+cnc+sx(B(i))-ing;
		                youhuajie=mubiaodi+jl(lujing(end),B(i));
		                if youhuajie<=min11
		                    min11=youhuajie;
		                    e=i;
		
		                end
		            end
		        end
		        a(2,B(e))=0; %把找出的目标cnc编号其的状态改变
		        lujing1=a(1,B(e))
		        wangcheng=a(3,B(e))+cnc+sx(B(e));%判断得出的目标cnc向rvg发出指令，rvg是否还在运行
		        if ing>=wangcheng %如果rvg没运行，那么cnc现在的上料开始时间为rvg所在的时间叫上移动到cnc的时间
		            a(3,B(e))=ing+jl(lujing(end),B(e));
		            kaishishijian1=a(3,B(e))
		        elseif ing<wangcheng %如果在运行，则需求出等待时间，cnc现在的上料开始时间为rvg所在时间加上等待时间再加上移动时间
		            dendai=abs(ing- wangcheng);
		            a(3,B(e))=ing+dendai+jl(lujing(end),B(e)) %用现在求的得上料开始时间替换以前的上料开始时间
		            kaishishijian1=a(3,B(e))
		        end
		        kaishishijian=[kaishishijian,kaishishijian1];%得出上料开始时间
		
		        zuihou=kaishishijian1+sx(lujing1)+jl(lujing(end),lujing1) %判断当把工件放上cnc上时，是否能在规定的时间内加工完成
		        lujing=[lujing,lujing1];%得出路径
		    end
		
		%当上一循环把cnc都更新，则进行这一循环，然后和上面的循环一样
		    while all(a(2,:))==0
		        if any(a(2,:))==0
		            [jl,sx,qx,cnc]=rgv();
		            ing=a(3,find(a(1,:)==lujing(end)))+sx(lujing(end))+qx
		            B=find(a(2,:)==0);
		            n=length(B);
		            min11=inf;min12=inf;
		            for i=1:n
		                mubiaodi=a(3,B(i))+cnc+sx(B(i))-ing;
		                youhuajie=mubiaodi+jl(lujing(end),B(i));
		
		                if youhuajie<=min11
		                    min11=youhuajie;
		                    sh=i;
		                end
		            end
		
		
		        elseif any(a(2,:))==1
		            [jl,sx,qx,cnc]=rgv()
		            ing=a(3,find(a(1,:)==lujing(end)))+sx(lujing(end))+qx
		            B=find(a(2,:)==0)
		            n=length(B)
		            min11=inf;min12=inf;
		            for i=1:n
		                mubiaodi=a(3,B(i))+cnc+sx(B(i))-ing;
		                youhuajie=mubiaodi+jl(lujing(end),B(i));
		                if youhuajie<=min11
		                    min11=youhuajie;
		                    sh=i;
		                end
		            end
		        end
		        a(2,B(sh))=1; %改变cnc的状态，不至于重复放
		        lujing2=a(1,B(sh))
		        wangcheng=a(3,B(sh))+cnc+sx(B(sh))
		        if ing>=wangcheng
		            a(3,B(sh))=ing+jl(lujing(end),B(sh));
		            kaishishijian2=a(3,B(sh))
		        elseif ing<wangcheng
		            dendai=abs(ing- wangcheng);
		            a(3,B(sh))=ing+dendai+jl(lujing(end),B(sh));
		            kaishishijian2=a(3,B(sh))
		        end
		        kaishishijian=[kaishishijian,a(3,B(sh))]
		
		        zuihou=kaishishijian2+sx(lujing2)+jl(lujing(end),lujing2)
		        lujing=[lujing,lujing2]
		    end
		end
		
		Z=[lujing;kaishishijian]'

	 */

}
